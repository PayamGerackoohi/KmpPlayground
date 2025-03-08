package com.payam1991gr.kmp.playground.view.screens.io.datastore.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.module.robot.TextFieldInputRobot
import com.payam1991gr.kmp.playground.view.module.robot.textFieldInputRobot

interface DatastoreRobot {
    fun PreviewScope.intItem(block: TextFieldInputRobot.() -> Unit): Any
    fun PreviewScope.floatItem(block: TextFieldInputRobot.() -> Unit): Any
    fun PreviewScope.byteArrayItem(block: TextFieldInputRobot.() -> Unit): Any
    fun CodeScope.intItem(): Any
    fun CodeScope.floatItem(): Any
    fun CodeScope.byteArrayItem(): Any
    fun CodeScope.textFieldInput(): Any
    fun CodeScope.converters(): Any
    fun CodeScope.state(): Any
}

class DatastoreRobotImpl(private val rule: ComposeContentTestRule) : DatastoreRobot {
    override fun PreviewScope.intItem(block: TextFieldInputRobot.() -> Unit) =
        textFieldInputRobot(rule, "Int", block)

    override fun PreviewScope.floatItem(block: TextFieldInputRobot.() -> Unit) =
        textFieldInputRobot(rule, "Float", block)

    override fun PreviewScope.byteArrayItem(block: TextFieldInputRobot.() -> Unit) =
        textFieldInputRobot(rule, "ByteArray", block)

    override fun CodeScope.intItem() = snippet("fun State.IntItem() = TextFieldInput(", false)
    override fun CodeScope.floatItem() = snippet("fun State.FloatItem() = TextFieldInput(", false)
    override fun CodeScope.byteArrayItem() = snippet(
        "fun State.ByteArrayItem() = TextFieldInput(",
        false,
    )

    override fun CodeScope.textFieldInput() = snippet("fun <T> TextFieldInput(", false)
    override fun CodeScope.converters() = snippet("interface Converter<T> {", false)
    override fun CodeScope.state() = snippet("data class State(", false)
}

fun datastoreRobot(
    rule: ComposeContentTestRule,
    block: DatastoreRobot.() -> Unit,
): DatastoreRobot = DatastoreRobotImpl(rule).apply(block)
