package catalogservice.conf.log

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.HttpServerFilter
import io.micronaut.http.filter.ServerFilterChain
import io.reactivex.Flowable
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