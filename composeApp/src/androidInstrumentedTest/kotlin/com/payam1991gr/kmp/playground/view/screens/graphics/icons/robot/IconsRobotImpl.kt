package com.payam1991gr.kmp.playground.view.screens.graphics.icons.robot

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performSemanticsAction
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.robot.IconsRobot.CoreScope
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.robot.IconsRobot.IconScope
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.robot.IconsRobot.ExtendedScope
import com.payam1991gr.kmp.playground.view.test.util.SniBlock

class IconsRobotImpl(private val rule: ComposeContentTestRule) : IconsRobot {
    override fun core(block: CoreScope.() -> Unit) = rule
        .onNodeWithText("Core Icons")
        .assertIsDisplayed()
        .apply { CoreScopeImpl().block() }

    override fun extended(block: ExtendedScope.() -> Unit) = rule
        .onNodeWithText("Extended Icons")
        .assertIsDisplayed()
        .apply { ExtendedScopeImpl().block() }

    override fun snackbar(text: String) {
        rule.onNodeWithText(text)
            .assertIsDisplayed()
        rule.onNodeWithTag("Snackbar")
            .onChild()
            .performSemanticsAction(SemanticsActions.Dismiss)
    }

    override fun CodeScope.previewSample() = snippet(
        "fun Preview(state: State) = state.apply {",
        false,
    )

    override fun CodeScope.state() = snippet(
        "data class State(",
        false,
    )

    override fun CodeScope.lazyData() = snippet(
        "sealed interface LazyData<out T> {",
        false,
    )

    override fun CodeScope.iconData() = snippet(
        "data class IconData(val title: String, val icon: ImageVector)",
        false,
    )

    override fun CodeScope.lazyGridScope_header() = snippet(
        "private fun LazyGridScope.header(",
        false,
    )

    override fun CodeScope.lazyGridScope_icons() = snippet(
        "private fun LazyGridScope.icons(",
        false,
    )

    override fun CodeScope.iconSample() = snippet(
        "fun Icon(data: IconData, onClick: () -> Unit) = Icon(",
        false,
    )

    override fun CodeScope.loading() = snippet(
        "private fun LazyGridScope.loading() = item(span = { GridItemSpan(maxLineSpan) }) {",
        false,
    )

    override fun CodeScope.state_ObserveEffects() = snippet(
        "private fun State.ObserveEffects(showMessage: (String) -> Unit) {",
        false,
    )

    open inner class IconScopeImpl : IconScope {
        override fun icon(title: String, block: SniBlock) = rule
            .onNodeWithContentDescription(title)
            .assertIsDisplayed()
            .block()
    }

    inner class CoreScopeImpl : CoreScope, IconScope by IconScopeImpl()

    inner class ExtendedScopeImpl : ExtendedScope, IconScope by IconScopeImpl()
}
