package com.helloworld.common.exception

import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes
import org.springframework.stereotype.Component
import org.springframework.web.context.request.WebRequest

@Component
class CustomErrorAttributes : DefaultErrorAttributes() {
    override fun getErrorAttributes(webRequest: WebRequest?, options: ErrorAttributeOptions?): MutableMap<String, Any> {
        var errorAttributes = super.getErrorAttributes(webRequest, options)
        val error = getError(webRequest) ?: return errorAttributes
        if (error.message == null) {
            errorAttributes.remove("message")
        }
        return errorAttributes
    }
}
