import androidx.annotation.DrawableRes

data class Hero(
    val id : Int,
    val name : String,
    @DrawableRes val drawable: Int
)
