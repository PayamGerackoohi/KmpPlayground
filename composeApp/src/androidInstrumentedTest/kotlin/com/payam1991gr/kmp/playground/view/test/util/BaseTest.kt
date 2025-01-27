package com.payam1991gr.kmp.playground.view.test.util

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

abstract class BaseTest {
    @get:Rule
    val rule = createComposeRule()
}
