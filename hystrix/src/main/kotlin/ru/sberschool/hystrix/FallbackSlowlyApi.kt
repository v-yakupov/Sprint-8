package ru.sberschool.hystrix

import feign.Param

class FallbackSlowlyApi : SlowlyApi {
    override fun getStatById(@Param("id") id: Long): Stat = Stat(id = Int.MIN_VALUE, name = "REQUEST TIMED OUT")
}


