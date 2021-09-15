import kotlinx.css.*
import kotlinx.css.BackgroundRepeat.repeat
import kotlinx.css.Color.Companion.black
import kotlinx.css.Color.Companion.grey
import kotlinx.css.Color.Companion.white
import kotlinx.css.Display.flex
import kotlinx.css.Display.inlineBlock
import kotlinx.css.FlexWrap.wrap
import kotlinx.css.FontWeight.Companion.bold
import kotlinx.css.Position.sticky
import kotlinx.css.TextAlign.center
import kotlinx.css.properties.TextDecoration
import styled.StyleSheet

val globalStyles = CssBuilder(allowClasses = false).apply {
    body {
        fontFamily = "IBM Plex Sans, sans-serif"
        backgroundImage = Image("url(/images/background.svg)")
        backgroundRepeat = repeat
    }
    a {
        color = black
        textDecoration = TextDecoration.none
    }
}

object Styles : StyleSheet("ComponentStyles", isStatic = true) {
    val header by css {
        position = sticky
        top = 0.px
        textAlign = center
        padding = "1rem"
        backgroundColor = white
    }
    val title by css {
        margin = "0"
        fontSize = 1.8.rem
    }

    val card by css {
        width = 5.rem
        margin = "0.8rem"
        textAlign = center
    }
    val cardImage by css {
        display = inlineBlock
        width = 4.rem
        height = 4.rem
        padding = "0.4rem"
        margin = "auto"
        backgroundColor = white
        borderRadius = 1.rem
    }
    val cardLabel by css {
        fontSize = 0.7.rem
        margin = "0 10px"
    }
    val cardsContainer by css {
        display = flex
        justifyContent = JustifyContent.center
        flexWrap = wrap
        maxWidth = 1000.px
        margin = "auto"
        paddingTop = 3.vh
    }

    val bigCard by css {
        width = 17.rem
        margin = "auto"
        textAlign = center
    }
    val bigCardImage by css {
        display = inlineBlock
        width = 14.rem
        height = 14.rem
        padding = "1.2rem"
        margin = "auto"
        backgroundColor = white
        borderRadius = 3.rem
    }
    val bigCardContainer by css {
        marginTop = 7.vh
    }

    val backButton by css {
        display = inlineBlock
        width = 8.rem
        padding = "1rem"
        color = black
        textDecoration = TextDecoration.none
        textAlign = center
        fontWeight = bold
        backgroundColor = white
        borderRadius = 1.rem
    }
    val actionButtonsContainer by css {
        margin = "6vh 0"
        textAlign = center
    }

    val footer by css {
        padding = "1rem 0"
        color = grey
        fontSize = 0.7.rem
        textAlign = center
    }
}