package catalogservice.conf.log

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.FilterChain
import io.micronaut.http.filter.HttpClientFilter
import io.reactivex.Flowable
import org.reactivestreams.Publisher

@Filter("/**")
class HttpClientLogger : HttpClientFilter, HttpLogger() {

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
        logRequest("Sent HTTP {} {}", request)
    }

    private fun logResponse(response: HttpResponse<*>?) {
        logResponse("Received HTTP {} response", response)
    }

}