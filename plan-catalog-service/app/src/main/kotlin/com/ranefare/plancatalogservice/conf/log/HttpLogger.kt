package com.ranefare.plancatalogservice.conf.log

import com.fasterxml.jackson.databind.ObjectMapper
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import net.logstash.logback.argument.StructuredArguments.value
import java.util.*

open class HttpLogger {

    private val log by LoggerDelegate()
    private val objectWriter = ObjectMapper().writerWithDefaultPrettyPrinter()

    protected fun logRequest(message: String, request: HttpRequest<*>?) {
        log.debug(
            message,
            value("METHOD", request?.method),
            value("PATH", request?.path),
            value("DESTINATION", request?.serverName),
            value("HEADERS", logHeaders(request?.headers)),
            value("BODY", logBody(request?.body))
        )
    }

    protected fun logResponse(message: String, response: HttpResponse<*>?) {
        log.debug(
            message,
            value("STATUS", response?.status),
            value("HEADERS", logHeaders(response?.headers)),
            value("BODY", logBody(response?.body))
        )
    }

    private fun logBody(body: Optional<out Any>?): String? {
        return objectWriter.writeValueAsString(body?.orElse(null))
    }

    private fun logHeaders(headers: HttpHeaders?): String? {
        return headers?.map { entry -> "${entry.key} -> ${entry.value}" }?.joinToString(separator = "\n")
    }
}