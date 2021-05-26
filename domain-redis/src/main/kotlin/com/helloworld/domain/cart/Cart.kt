package com.helloworld.domain.cart

import org.apache.commons.lang3.RandomStringUtils
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.annotation.Generated

@RedisHash(timeToLive = 60 * 60 * 24) // 24 hour
open class Cart(
    accountId: Long
) : Serializable {
    @Id
    var id: String = getId(accountId)
        @Generated
        protected set

    @Indexed
    var accountId: Long = accountId
        @Generated
        protected set

    companion object {
        fun getId(accountId: Long): String {
            val sb: StringBuilder = StringBuilder()
                .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSSSS")))
                .append(RandomStringUtils.randomAlphabetic(4).uppercase(Locale.getDefault()))
                .append(accountId)
            return sb.toString()
        }
    }
}