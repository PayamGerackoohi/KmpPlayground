package com.payam1991gr.kmp.playground.view.screens.io.api.robot

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasStateDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.io.api.robot.ApiRobot.FakeModeScope
import com.payam1991gr.kmp.playground.view.screens.io.api.robot.ApiRobot.RequestsScope
import com.payam1991gr.kmp.playground.view.screens.io.api.robot.ApiRobot.RequestsScope.CardScope
import com.payam1991gr.kmp.playground.view.screens.io.api.robot.ApiRobot.RequestsScope.GetHomeScope
import com.payam1991gr.kmp.playground.view.screens.io.api.robot.ApiRobot.RequestsScope.GetWrittenNumbersScope
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.SniBlock

class ApiRobotImpl(private val rule: ComposeContentTestRule) : ApiRobot {
    override fun PreviewScope.description() = rule
        .onNodeWithText("Simple API connection using Ktor")
        .assertIsDisplayed()

    override fun PreviewScope.configuration() = rule
        .onNodeWithText("Configurations")
        .assertIsDisplayed()

    override fun PreviewScope.configurationDescription() = rule
        .onNodeWithText("`real server` mode uses `Base URL` to make", substring = true)
        .assertIsDisplayed()

    override fun PreviewScope.fakeModeSettings(block: FakeModeScope.() -> Unit) =
        FakeModeScopeImpl().block()

    override fun PreviewScope.hostInput(url: String) = rule
        .onNode(hasTextExactly("Base URL", url))
        .assertIsDisplayed()

    override fun PreviewScope.requests(block: RequestsScope.() -> Unit) =
        RequestsScopeImpl().block()

    override fun CodeScope.sharedSample() = title("Shared") {
        performClick()
        snippet("typealias Numbers = List<WrittenNumber>")
        snippet("sealed interface RemoteData<out T> {")
        snippet("data class WrittenNumber(val value: Int = 0, val string: String = \"\") {")
        snippet("interface Api {")
        snippet("class ApiImpl(")
    }

    override fun CodeScope.stateSample() = title("State") {
        performClick()
        snippet("data class State(")
    }

    override fun CodeScope.presenterSample() = title("Presenter") {
        performClick()
        snippet("class ApiPresenter(")
    }

    override fun CodeScope.uiSample() = title("UI") {
        performClick()
        snippet("typealias FRange = ClosedFloatingPointRange<Float>")
        snippet("private fun State.FakeModeSetting() = rememberSetting(shouldUseRealApi) {")
        snippet("private fun HostInput(host: String, event: (Event) -> Unit) = TextField(")
        snippet("private fun RequestCard(")
        snippet("private fun RemoteData<String>.GetHome(host: String, event: (Event) -> Unit) = RequestCard(")
        snippet("private fun RemoteData<Numbers>.GetWrittenNumbers(host: String, event: (Event) -> Unit) {")
        snippet("private fun RangeSlider(state: String, range: FRange, onRangeChanged: (FRange) -> Unit) = Row(")
        snippet("private fun Request(url: String, status: Int?, method: HttpMethod, call: () -> Unit) = Row(")
        snippet("private fun Status(status: Int) = colorScheme.run {")
        snippet("private inline val Int.isOk get() = this in 200..<300")
        snippet("private fun ConnectingToServer() = Text(\"Connecting to the server...\")")
        snippet("private val Url.string get() = toString().replaceFirst(\"http://\", \"\")")
    }

    inner class FakeModeScopeImpl : FakeModeScope {
        override fun realServerButton(block: SniBlock) = rule
            .onNodeWithText("Real Server")
            .assertIsDisplayed()
            .block()

        override fun fakeServerButton(block: SniBlock) = rule
            .onNodeWithText("Fake Server")
            .assertIsDisplayed()
            .block()
    }

    inner class RequestsScopeImpl : RequestsScope {
        override fun header() = rule
            .onNodeWithText("Requests")
            .assertIsDisplayed()

        override fun getHome(block: GetHomeScope.() -> Unit) = rule
            .onNodeWithTag("RequestCard.GetHome")
            .assertIsDisplayed()
            .apply { GetHomeScopeImpl(this).block() }

        override fun getWrittenNumbers(block: GetWrittenNumbersScope.() -> Unit) =
            rule.onNodeWithTag("RequestCard.GetWrittenNumbers")
                .assertIsDisplayed()
                .apply { GetWrittenNumbersScopeImpl(this).block() }

        inner class CardScopeImpl(private val sni: Sni) : CardScope {
            init {
                sni.performScrollTo()
            }

            override fun button(label: String, block: SniBlock) = sni
                .onChildren()
                .filterToOne(hasTextExactly(label))
                .assertIsDisplayed()
                .block()

            override fun url(value: String) = sni
                .onChildren()
                .filterToOne(hasTextExactly(value))
                .assertIsDisplayed()

            override fun noStatus() = sni
                .onChildren()
                .filter(hasContentDescription("Status"))
                .assertCountEquals(0)

            override fun content(block: SniBlock) = sni
                .onChildren()
                .filterToOne(hasTestTag("Content"))
                .assertIsDisplayed()
                .block()

            override fun noContent() = sni
                .onChildren()
                .filter(hasTestTag("Content"))
                .assertCountEquals(0)

            override fun status(value: String) = sni
                .onChildren()
                .filterToOne(hasContentDescription("Status"))
                .assertIsDisplayed()
                .assertTextEquals(value)
        }

        inner class GetHomeScopeImpl(
            private val sni: Sni,
        ) : GetHomeScope, CardScope by CardScopeImpl(sni) {
            override fun content(value: String) = content {
                onChildren()
                    .filterToOne(hasTextExactly(value))
                    .assertIsDisplayed()
            }
        }

        inner class GetWrittenNumbersScopeImpl(private val sni: Sni) : GetWrittenNumbersScope,
            CardScope by CardScopeImpl(sni) {
            override fun range(value: String) = sni
                .onChildren()
                .filterToOne(hasStateDescription("RangeSlider: [$value]"))
                .assertIsDisplayed()

            override fun content(vararg values: String) = content {
                values.forEachIndexed { index, value ->
                    onChildAt(index)
                        .assertTextEquals(value)
                        .assertIsDisplayed()
                }
            }
        }
    }
}
