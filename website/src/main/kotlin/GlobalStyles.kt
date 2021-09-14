import kotlinx.css.*
import kotlinx.css.properties.TextDecoration

val globalStyles = CssBuilder(allowClasses = false).apply {
    body {
        fontFamily = "IBM Plex Sans, sans-serif"
        backgroundImage = Image("url(/img/background.svg)")
        backgroundRepeat = BackgroundRepeat.repeat
    }

    a {
        color = Color.black
        textDecoration = TextDecoration.none
    }
}