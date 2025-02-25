package com.payam1991gr.kmp.playground.view.screens.graphics.icons

import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.model.IconData
import com.payam1991gr.kmp.playground.data.model.LazyData
import com.payam1991gr.kmp.playground.view.effectOf
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State.Effect
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.robot.iconsRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class IconsTest : BaseTest() {
    private val coreIcons = persistentListOf(
        IconData("AccountBox", Default.AccountBox),
        IconData("AccountCircle", Default.AccountCircle),
        IconData("AddCircle", Default.AddCircle),
        IconData("Add", Default.Add),
    )

    @Suppress("SpellCheckingInspection")
    private val extendedIcons = persistentListOf(
        IconData("_1k", Default._1k),
        IconData("_1kPlus", Default._1kPlus),
        IconData("_1xMobiledata", Default._1xMobiledata),
        IconData("_2k", Default._2k),
    )

    @Test
    fun test() {
        var showCode by mutableStateOf(false)
        val effect = effectOf<Effect>()
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            Icons().Content(
                State(
                    showCode = showCode,
                    toolbarActions = persistentListOf(
                        Action.Back,
                        if (showCode) Action.Preview else Action.Code,
                    ),
                    coreIcons = coreIcons,
                    extendedIcons = LazyData.Data(extendedIcons),
                    effect = effect,
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            iconsRobot(rule) {
                toolbar {
                    title("Icons")
                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        core {
                            coreIcons.forEach { icon ->
                                icon(icon.title) { performClick() }
                                verify { event(Event.OnIconClicked(icon)) }
                                effect.value = Effect.ShowMessage(icon.title)
                                snackbar(icon.title)
                            }
                        }
                        extended {
                            extendedIcons.forEach { icon ->
                                icon(icon.title) { performClick() }
                                verify { event(Event.OnIconClicked(icon)) }
                                effect.value = Effect.ShowMessage(icon.title)
                                snackbar(icon.title)
                            }
                        }

                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }

                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        previewSample()
                        state()
                        lazyData()
                        iconData()
                        lazyGridScope_header()
                        lazyGridScope_icons()
                        iconSample()
                        loading()
                        state_ObserveEffects()


                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
