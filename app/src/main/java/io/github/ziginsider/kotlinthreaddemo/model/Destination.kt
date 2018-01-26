package io.github.ziginsider.kotlinthreaddemo.model

/**
 * Created by zigin on 26.01.2018.
 */

@Serializable
data class Destination(
        var name : String? = "",
        var country : String? = "",
        var code : Int = 0,
        )