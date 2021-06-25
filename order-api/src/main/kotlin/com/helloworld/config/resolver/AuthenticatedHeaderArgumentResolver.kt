package com.helloworld.config.resolver

import com.helloworld.config.HelloworldUser
import com.helloworld.domain.common.data.User
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.MethodParameter
import org.springframework.http.HttpHeaders
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

class AuthenticatedHeaderArgumentResolver: HandlerMethodArgumentResolver {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private val HEADER_CHANNEL_TYPE = "Channel-Type"
    private val HEADER_AUTHENTICATED = "Authenticated"
    private val HEADER_DEVICE_ID = "Device-Id"
    private val HEADER_OS_CODE = "Os-Code"
    private val HEADER_OS_VERSION = "Os-Version"
    private val HEADER_APP_VERSION = "App-Version"
    private val NOT_MEMBER_ACCOUNT_ID = 0L

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.getParameterAnnotation(HelloworldUser::class.java) != null
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val request = webRequest.nativeRequest as HttpServletRequest
        val accountIdString = request.getHeader(HEADER_AUTHENTICATED)

        val accountId = try {
                            accountIdString?.toLong() ?: throw NumberFormatException()
                        } catch (e: NumberFormatException) {
                            logger.warn("invalid accountId format {}", accountIdString)
                            NOT_MEMBER_ACCOUNT_ID
                        }

        return User(accountId = accountId,
                isMember = accountId != NOT_MEMBER_ACCOUNT_ID,
                authorization=request.getHeader(HttpHeaders.AUTHORIZATION),
                channelType=request.getHeader(HEADER_CHANNEL_TYPE),
                acceptLanguage=request.getHeader(HttpHeaders.ACCEPT_LANGUAGE),
                deviceId=request.getHeader(HEADER_DEVICE_ID),
                osCode=request.getHeader(HEADER_OS_CODE),
                osVersion=request.getHeader(HEADER_OS_VERSION),
                appVersion=request.getHeader(HEADER_APP_VERSION)
        )
    }
}