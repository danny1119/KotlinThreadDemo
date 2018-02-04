package io.github.ziginsider.kotlinthreaddemo.model

/**
 * Created by zigin on 26.01.2018.
 */

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Destination(
        var name: String? = "",
        @SerialName("rodina")
        var country: String? = "",
        var code: Int = 0,
        @Optional
        var isMetro: Boolean = false,
        @Transient
        var favorite: Boolean = false
)