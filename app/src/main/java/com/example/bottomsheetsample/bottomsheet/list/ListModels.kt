package com.example.bottomsheetsample.bottomsheet.list

data class BlueModel(
    val id: Int,
    val content: String
) {
    fun toColorModel(): ColorModel =
        ColorModel(
            type = ColorType.BLUE,
            blueModel = this
        )
}

data class PinkModel(
    val id: Int,
    val content: String
) {
    fun toColorModel(): ColorModel =
        ColorModel(
            type = ColorType.PINK,
            pinkModel = this
        )
}

data class ColorModel(
    val type: Int,
    val blueModel: BlueModel? = null,
    val pinkModel: PinkModel? = null
)


val blueList = List(20) {
    BlueModel(id = it, content = "${it+1}번째").toColorModel()
}

val pinkList = List(20) {
    PinkModel(id = it, content = "${it+1}번째").toColorModel()
}

// -----------------------------------------------------------------------------

interface Color

data class YellowModel(
    val id: Int,
    val content: String
) : Color

data class GreenModel(
    val id: Int,
    val content: String
) : Color


val yellowList = List(20) {
    YellowModel(id = it, content = "${it+1}번째")
}

val greenList = List(20) {
    GreenModel(id = it, content = "${it+1}번째")
}