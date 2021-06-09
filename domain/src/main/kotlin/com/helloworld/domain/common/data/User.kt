package com.helloworld.domain.common.data

data class User(
        var channelType: String,
        var authorization: String,
        var acceptLanguage: String,
        var accountId: Long,
        var isMember: Boolean,
        var deviceId: String,
        var osCode: String,
        var osVersion: String,
        var appVersion: String
)