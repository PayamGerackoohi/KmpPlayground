package com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.robot

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.isEnabled
import androidx.compose.ui.test.isNotEnabled
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.module.robot.TextFieldInputRobot
import com.payam1991gr.kmp.playground.view.module.robot.textFieldInputRobot
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.robot.CppRobot.CalculationScope
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.robot.CppRobot.CalculationScope.ItemsScope
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.SniBlock
import com.payam1991gr.kmp.playground.view.test.util.hasState

interface CppRobot {
    fun PreviewScope.description(): Any
    fun PreviewScope.number(block: TextFieldInputRobot.() -> Unit): Any
    fun PreviewScope.randomCalculations(block: CalculationScope.() -> Unit): Any
    fun CodeScope.ui(): Any
    fun CodeScope.state(): Any
    fun CodeScope.presenter(): Any
    fun CodeScope.cpp(): Any

    interface CalculationScope {
        fun button(isEnabled: Boolean, block: SniBlock = {}): Any
        fun items(block: ItemsScope.() -> Unit = {}): Any

        interface ItemsScope {
            fun calculated(text: String): Any
            fun calculating(text: String): Any
        }
    }
}

class CppRobotImpl(private val rule: ComposeContentTestRule) : CppRobot {
    override fun PreviewScope.description() = rule
        .onNodeWithText("A simple C++ code to calculate prime factors of ", substring = true)
        .assertIsDisplayed()

    override fun PreviewScope.number(block: TextFieldInputRobot.() -> Unit) =
        textFieldInputRobot(rule, "Number", block)

    override fun PreviewScope.randomCalculations(block: CalculationScope.() -> Unit) {
        CalculationScopeImpl().block()
    }

    override fun CodeScope.ui() = title("UI") {
        performClick()
        snippet("private val converter = object : Converter<String> {")
        snippet("private val decimalFormatTransformation = DecimalFormatTransformation()")
        snippet("class DecimalFormatTransformation(")
        snippet("private fun State.NumberInput(placeholder: String = \"18446744073709551615\".decimalFormat()) {")
        snippet("private fun State.RandomCalculations() = ContentList(Modifier.testTag(\"Random Calculations\")) {")
        snippet("fun State.Number.CalculationCard() = Card(")
        snippet("private fun Modifier.stateOf(number: State.Number) = semantics(mergeDescendants = true) {")
        snippet("fun State.Number.cardColor() = when (this) {")
    }

    override fun CodeScope.state() = title("State") {
        performClick()
        snippet("data class State(")
        snippet("sealed interface Number {")
    }

    override fun CodeScope.presenter() = title("Presenter") {
        performClick()
        snippet("class CppPresenter(")
        snippet("is Event.OnNumberChanged -> scope.launch(bg) {")
        snippet("Event.PerformRandomCalculations -> scope.launch(bg) {")
    }

    override fun CodeScope.cpp() = title("C++") {
        performClick()
        snippet("// math-util.hpp")
        snippet("namespace MathUtil {")
        snippet("std::string primeFactors(std::string);")
        snippet("// math-util.cpp")
        snippet("template <typename T> struct Factor {")
        snippet("template <typename T> struct Factors {")
        snippet("template <typename T> string factors(T n) {")
        snippet("string MathUtil::primeFactors(string text) {")
    }

    inner class CalculationScopeImpl : CalculationScope {
        override fun button(isEnabled: Boolean, block: SniBlock) = rule
            .onNodeWithText("Random Calculations")
            .assertIsDisplayed()
            .assert(if (isEnabled) isEnabled() else isNotEnabled())
            .block()

        override fun items(block: ItemsScope.() -> Unit) = rule
            .onNodeWithTag("Random Calculations")
            .assertIsDisplayed()
            .apply { ItemsScopeImpl(this).block() }

        inner class ItemsScopeImpl(private val sni: Sni) : ItemsScope {
            override fun calculated(text: String) = sni
                .onChildren()
                .filterToOne(hasTextExactly(text))
                .hasState("Calculated")

            override fun calculating(text: String) = sni
                .onChildren()
                .filterToOne(hasTextExactly("$text = …"))
                .hasState("Calculating")
        }
    }
}

fun cppRobot(
    rule: ComposeContentTestRule,
    block: CppRobot.() -> Unit,
): CppRobot = CppRobotImpl(rule).apply(block)
