import kotlinx.css.*
import kotlinx.css.properties.TextDecoration

val globalStyles = CssBuilder(allowClasses = false).apply {
    body {
        margin(0.px)
        padding(0.px)
        backgroundImage = Image("url(/img/background.svg)")
        backgroundRepeat = BackgroundRepeat.repeat
        fontFamily = "Roboto, sans-serif"
    }

    a {
        color = Color.black
        textDecoration = TextDecoration.none
    }
}