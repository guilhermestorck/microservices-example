package orderservice.conf.log

import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.http.*
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.*
import io.reactivex.Flowable
import net.logstash.logback.argument.StructuredArguments.value
import org.reactivestreams.Publisher

@Filter("/**")
class HttpClientLogger : HttpClientFilter {


    private val log by LoggerDelegate()
    private val objectWriter = ObjectMapper().writerWithDefaultPrettyPrinter()

    override fun doFilter(request: HttpRequest<*>?, chain: FilterChain?): Publisher<out HttpResponse<*>> {
        return Flowable.fromCallable { logRequest(request) }
            .switchMap { chain?.proceed(request) }
            .doOnNext { response -> logResponse(response) }
    }

    override fun doFilter(request: MutableHttpRequest<*>?, chain: ClientFilterChain?): Publisher<out HttpResponse<*>> {
        return Flowable.fromCallable { logRequest(request) }
            .switchMap { chain?.proceed(request) }
            .doOnNext { response -> logResponse(response) }
    }

    private fun logRequest(request: HttpRequest<*>?) {
        log.debug(
            "Sent HTTP {} {}",
            value("METHOD", request?.method),
            value("PATH", request?.path),
            value("DESTINATION", request?.serverName),
            value("HEADERS", logHeaders(request?.headers)),
            value("BODY", objectWriter.writeValueAsString(request?.body?.orElse(null)))
        )
    }

    private fun logResponse(response: HttpResponse<*>?) {
        log.debug(
            "Received HTTP {} response",
            value("STATUS", response?.status),
            value("HEADERS", logHeaders(response?.headers)),
            value("BODY", objectWriter.writeValueAsString(response?.body?.orElse(null)))
        )
    }

    private fun logHeaders(headers: HttpHeaders?): String? {
        return headers?.map { entry -> "${entry.key} -> ${entry.value}" }?.joinToString(separator = "\n")
    }

}

@Filter("/**")
class HttpServerLogger : HttpServerFilter {


    override fun doFilter(request: HttpRequest<*>?, chain: ServerFilterChain?): Publisher<MutableHttpResponse<*>> {
        return Flowable.fromCallable { logRequest(request) }
            .switchMap { chain?.proceed(request) }
            .doOnNext { response -> logResponse(response) }
    }

    private val log by LoggerDelegate()
    private val objectWriter = ObjectMapper().writerWithDefaultPrettyPrinter()

    private fun logRequest(request: HttpRequest<*>?) {
        log.debug(
            "Received HTTP {} {}",
            value("METHOD", request?.method),
            value("PATH", request?.path),
            value("DESTINATION", request?.serverName),
            value("HEADERS", logHeaders(request?.headers)),
            value("BODY", objectWriter.writeValueAsString(request?.body?.orElse(null)))
        )
    }

    private fun logResponse(response: HttpResponse<*>?) {
        log.debug(
            "Responded HTTP {} back to client",
            value("STATUS", response?.status),
            value("HEADERS", logHeaders(response?.headers)),
            value("BODY", objectWriter.writeValueAsString(response?.body?.orElse(null)))
        )
    }

    private fun logHeaders(headers: HttpHeaders?): String? {
        return headers?.map { entry -> "${entry.key} -> ${entry.value}" }?.joinToString(separator = "\n")
    }
}