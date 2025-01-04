package com.payam1991gr.kmp.playground.ui.screens.components.carousel

import com.payam1991gr.kmp.playground.ui.module.editor.CodeEditor

fun CodeEditor.appendHorizontalMultiBrowseCarouselSample() {
    line { yellow { "@OptIn" }; normal { "(" }; yellow { "ExperimentalMaterial3Api" }; normal { "::" }; orange { "class" }; normal { ")" } }
    line { yellow { "@Composable" } }
    line { orange { "fun " }; blue { "HorizontalMultiBrowseCarouselSample" }; normal { "() {" } }
    line(1) { normal { "HorizontalMultiBrowseCarousel(" } }
    line(2) { teal { "state = " }; normal { "rememberCarouselState { " }; teal { "10" }; normal { " }," } }
    line(2) { teal { "preferredItemWidth = 200" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(2) { teal { "itemSpacing = 8" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(2) { teal { "contentPadding = " }; normal { "PaddingValues(" }; teal { "horizontal = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(1) { normal { ")  {  index ->" } }
    line(2) { normal { "RandomImage(" } }
    line(3) { teal { "contentDescription = " }; green { "\"Image " }; orange { "\$" }; normal { "index" }; green { "\"" }; normal { "," } }
    line(3) { teal { "modifier = " }; normal { "Modifier." }; blue { "size" }; normal { "(" }; teal { "200" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "." }; blue { "maskClip" }; normal { "(MaterialTheme." }; purple { "shapes" }; normal { "." }; purple { "medium" }; normal { ")" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
}

fun CodeEditor.appendHorizontalUncontainedCarouselSample() {
    line { yellow { "@OptIn" }; normal { "(" }; yellow { "ExperimentalMaterial3Api" }; normal { "::" }; orange { "class" }; normal { ")" } }
    line { yellow { "@Composable" } }
    line { orange { "fun " }; blue { "HorizontalUncontainedCarouselSample" }; normal { "() {" } }
    line(1) { normal { "HorizontalUncontainedCarousel(" } }
    line(2) { teal { "state = " }; normal { "rememberCarouselState { " }; teal { "10" }; normal { " }," } }
    line(2) { teal { "itemWidth = 200" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(2) { teal { "itemSpacing = 8" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(2) { teal { "contentPadding = " }; normal { "PaddingValues(" }; teal { "horizontal = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(1) { normal { ")  {  index ->" } }
    line(2) { normal { "RandomImage(" } }
    line(3) { teal { "contentDescription = " }; green { "\"Image " }; orange { "\$" }; normal { "index" }; green { "\"" }; normal { "," } }
    line(3) { teal { "modifier = " }; normal { "Modifier." }; blue { "size" }; normal { "(" }; teal { "200" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "." }; blue { "maskClip" }; normal { "(MaterialTheme." }; purple { "shapes" }; normal { "." }; purple { "medium" }; normal { ")" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
}
