package ru.sberschool.hystrix

import feign.Param
import feign.RequestLine

interface SlowlyApi {
    @RequestLine("GET stat/{id}")
    fun getStatById(@Param("id") id: Long): Stat
}