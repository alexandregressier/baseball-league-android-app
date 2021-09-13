import kotlinx.browser.document
import kotlinx.css.*
import kotlinx.css.BackgroundRepeat.repeat
import kotlinx.css.Color.Companion.black
import kotlinx.css.Color.Companion.white
import kotlinx.css.Display.grid
import kotlinx.css.properties.BoxShadow
import kotlinx.css.properties.TextDecoration
import react.dom.h1
import react.dom.p
import react.dom.render
import styled.*
import teams.Team

val teamList = listOf(
    Team("APL", "Appleton", "Foxes", Team.Division.EAST),
    Team("BOO", "Baraboo", "Big Tops", Team.Division.WEST),
    Team("EC", "Eau Claire", "Blues", Team.Division.WEST),
    Team("GB", "Green Bay", "Skyline", Team.Division.EAST),
    Team("LAX", "La Crosse", "Lovers", Team.Division.WEST),
    Team("LD", "Lake Delton", "Breakers", Team.Division.WEST),
    Team("MSN", "Madison", "Snowflakes", Team.Division.WEST),
    Team("MKE", "Milwaukee", "Sunrise", Team.Division.EAST),
    Team("PKE", "Pewaukee", "Princesses", Team.Division.EAST),
    Team("SHW", "Shawano", "Shorebirds", Team.Division.EAST),
    Team("SG", "Spring Green", "Thespians", Team.Division.WEST),
    Team("SB", "Sturgeon Bay", "Elders", Team.Division.EAST),
    Team("WAU", "Waukesha", "Riffs", Team.Division.EAST),
    Team("WR", "Wisconsin Rapids", "Cranberries", Team.Division.WEST),
)

val styles = CssBuilder(allowClasses = false).apply {
    body {
        margin(0.px)
        padding(0.px)
        backgroundImage = Image("url(/img/background.svg)")
        backgroundRepeat = repeat
        fontFamily = "Roboto, sans-serif"
    }
    a {
        color = black
        textDecoration = TextDecoration.none
    }
}

fun main() {
    injectGlobal(styles)
    render(document.getElementById("root")) {
        h1 { +"Android Baseball League Teams" }
        styledDiv {
            css {
                display = grid
                gridTemplateColumns = GridTemplateColumns("repeat(auto-fill, minmax(6rem, 1fr))")
            }
            teamList.forEach { team ->
                with(team) {
                    styledDiv {
                        styledA(href = "/teams/$id?teamName=$city $name") {
                            css {
                                fontSize = (0.8).rem
                            }
                            styledImg(src = "/img/team-logos/${id.lowercase()}.svg", alt = "$city $name Logo") {
                                css {
                                    width = 3.rem
                                    height = 3.rem
                                    padding(1.rem)
                                    borderRadius = 16.px
                                    backgroundColor = white
                                    boxShadow += BoxShadow(false, 0.px, 4.px, 8.px, 0.px, rgba(0, 0, 0, 0.2))
                                }
                            }
                            p { +"$city $name" }
                        }
                    }
                }
            }
        }
    }
}