package com.payam1991gr.kmp.playground.view.screens.components.carousel.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendHorizontalMultiBrowseCarouselSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { `fun`; blue { " HorizontalMultiBrowseCarouselSample" }; normal { "() {" } }
    line(1) { normal { "HorizontalMultiBrowseCarousel(" } }
    line(2) { cyan { "state = " }; normal { "rememberCarouselState { " }; cyan { "10" }; normal { " }," } }
    line(2) { cyan { "preferredItemWidth = 200" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(2) { cyan { "itemSpacing = 8" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(1) { normal { ")  {  index ->" } }
    line(2) { normal { "RandomImage(" } }
    line(3) { cyan { "contentDescription = " }; green { "\"Image " }; orange { "\$" }; normal { "index" }; green { "\"" }; normal { "," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "size" }; normal { "(" }; cyan { "200" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "." }; blue { "maskClip" }; normal { "(MaterialTheme." }; purple { "shapes" }; normal { "." }; purple { "medium" }; normal { ")" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendHorizontalUncontainedCarouselSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { `fun`; blue { " HorizontalUncontainedCarouselSample" }; normal { "() {" } }
    line(1) { normal { "HorizontalUncontainedCarousel(" } }
    line(2) { cyan { "state = " }; normal { "rememberCarouselState { " }; cyan { "10" }; normal { " }," } }
    line(2) { cyan { "itemWidth = 200" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(2) { cyan { "itemSpacing = 8" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(1) { normal { ")  {  index ->" } }
    line(2) { normal { "RandomImage(" } }
    line(3) { cyan { "contentDescription = " }; green { "\"Image " }; orange { "\$" }; normal { "index" }; green { "\"" }; normal { "," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "size" }; normal { "(" }; cyan { "200" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "." }; blue { "maskClip" }; normal { "(MaterialTheme." }; purple { "shapes" }; normal { "." }; purple { "medium" }; normal { ")" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
