import kotlinx.browser.document
import react.dom.render
import styled.injectGlobal

fun main() {
    injectGlobal(globalStyles)
    render(document.getElementById("root")) {
        app {}
    }
}