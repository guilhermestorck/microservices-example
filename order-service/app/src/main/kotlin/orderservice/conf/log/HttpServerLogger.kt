package orderservice.conf.log

import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.http.*
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.*
import io.reactivex.Flowable
import net.logstash.logback.argument.StructuredArguments.value
import org.reactivestreams.Publisher

@Filter("/**")
class HttpServerLogger : HttpServerFilter, HttpLogger() {

    override fun doFilter(request: HttpRequest<*>?, chain: ServerFilterChain?): Publisher<MutableHttpResponse<*>> {
        return Flowable.fromCallable { logRequest(request) }
            .switchMap { chain?.proceed(request) }
            .doOnNext { response -> logResponse(response) }
    }

    private fun logRequest(request: HttpRequest<*>?) {
        logRequest("Received HTTP {} {}", request)
    }

    private fun logResponse(response: HttpResponse<*>?) {
        logResponse("Responded HTTP {} back to client", response)
    }
}