package com.tc.ufocodingchallenge.data.model



import com.tc.ufocodingchallenge.R
import java.util.*
import kotlin.random.Random
import java.time.LocalDateTime


data class UfoSighting(
    val id: UUID = UUID.randomUUID(),
    val date: String,
    val type: String,
    val iconResId: Int,
    val speed: Int
) {
    companion object {
        private val types = listOf(
            "Blob" to R.drawable.blob_medium,
            "Lamp Shade" to R.drawable.lampshade_medium
        )
        fun createRandom(): UfoSighting {
            val (type, icon) = types.random()
            val speed = Random.nextInt(50, 500)
            val date = LocalDateTime.now().toString()
            return UfoSighting(date = date, type = type, iconResId = icon, speed = speed)
        }
    }
}