package com.payam1991gr.kmp.playground.view.test.util

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.junit4.ComposeTestRule

typealias Sni = SemanticsNodeInteraction

typealias SniBlock = Sni.() -> Unit

fun Sni.onDescendant(
    matcher: SemanticsMatcher,
    rule: ComposeTestRule,
): Sni = fetchSemanticsNode().let { node ->
    rule.onNode(matcher and hasAnyAncestor(
        SemanticsMatcher("Same node?") { it.id == node.id }
    ))
}
