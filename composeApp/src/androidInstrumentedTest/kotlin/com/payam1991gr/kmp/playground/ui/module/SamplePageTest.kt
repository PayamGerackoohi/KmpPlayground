package com.payam1991gr.kmp.playground.ui.module

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.model.sample.rememberSetting
import com.payam1991gr.kmp.playground.ui.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.dismissible
import kmpplayground.composeapp.generated.resources.home_components
import kmpplayground.composeapp.generated.resources.mandatory
import kotlinx.collections.immutable.toPersistentList
import org.jetbrains.compose.resources.stringResource
import org.junit.Test

class SamplePageTest : BaseTest() {
    @Test
    fun test() {
        val onClick = mockk<(Action) -> Unit> { every { this@mockk(any()) } just runs }
        var label: Map<Action, String>? = null
        var showCode by mutableStateOf(false)
        rule.setContent {
            if (label == null)
                label = Action.entries.associateWith { stringResource(it.contentDescriptionRes) }
            SamplePage(
                showCode = showCode,
                titleRes = Res.string.home_components,
                actions = Action.entries.toPersistentList(),
                onClick = onClick,
                preview = { Text("Preview Page") },
                code = { Text("Code Page") },
            )
        }
        samplePageRobot(rule) {
            toolbar {
                title("Components")

                on(label!![Action.Back]!!) { performClick() }
                verify { onClick(Action.Back) }

                on(label!![Action.Code]!!) { performClick() }
                verify { onClick(Action.Code) }

                on(label!![Action.Preview]!!) { performClick() }
                verify { onClick(Action.Preview) }
            }

            assertThatCodePageIsNotDisplayed()
            preview {
                sni.assertTextEquals("Preview Page")
            }

            showCode = true
            assertThatPreviewPageIsNotDisplayed()
            code {
                sni.assertTextEquals("Code Page")
            }
        }
        confirmVerified()
    }

    @Test
    fun headerTest() {
        rule.setContent {
            SamplePage.Preview.Header(title = "Title")
        }
        samplePageRobot(rule) {
            header("Title")
        }
    }

    @Test
    fun descriptionTest() {
        rule.setContent {
            SamplePage.Preview.Description(description = "Description")
        }
        samplePageRobot(rule) {
            description("Description")
        }
    }

    @Test
    fun settingsTest() {
        rule.setContent {
            val isDismissible = rememberSetting(true) {
                if (it) Res.string.dismissible else Res.string.mandatory
            }
            Column {
                SamplePage.Preview.Settings(isDismissible)
                Text(if (isDismissible.value) "It's dismissible" else "It's not dismissible")
            }
        }
        samplePageRobot(rule) {
            settings {
                rule.onNodeWithText("It's dismissible").assertIsDisplayed()
                on("Dismissible") { performClick() }
                rule.onNodeWithText("It's not dismissible").assertIsDisplayed()
                on("Mandatory") { performClick() }
                rule.onNodeWithText("It's dismissible").assertIsDisplayed()
            }
        }
    }
}
