package ru.sberschool.hystrix

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Stat(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("name")
    val name: String
)