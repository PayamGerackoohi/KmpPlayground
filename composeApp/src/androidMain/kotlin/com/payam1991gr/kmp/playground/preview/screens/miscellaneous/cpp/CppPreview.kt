package com.payam1991gr.kmp.playground.preview.screens.miscellaneous.cpp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.math.Factor
import com.payam1991gr.kmp.playground.data.string.decimalFormat
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.Cpp
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
@Composable
fun Cpp_Preview_Preview() = preview {
    val n = remember { "12345654321" }
    Cpp().Content(
        State(
            showCode = false,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Preview,
            ),
            canEdit = true,
            canCalculate = false,
            inputNumber = n,
            inputCalculation = State.Number.Data(
                n, persistentListOf(
                    Factor("3", "2"),
                    Factor("7", "2"),
                    Factor("11", "2"),
                    Factor("13", "2"),
                    Factor("37", "2"),
                )
            ),
            calculations = persistentListOf(
                State.Number.Data(
                    "1000".decimalFormat(),
                    persistentListOf(Factor("2", "3"), Factor("5", "3")),
                ),
                State.Number.Data(
                    "277945762500".decimalFormat(),
                    persistentListOf(
                        Factor("2", "2"),
                        Factor("3", "3"),
                        Factor("5", "5"),
                        Factor("7", "7"),
                    ),
                ),
                State.Number.Data(
                    "580909190400000".decimalFormat(),
                    persistentListOf(
                        Factor("2", "11"),
                        Factor("3", "7"),
                        Factor("5", "5"),
                        Factor("7", "3"),
                        Factor("11", "2"),
                    ),
                ),
                State.Number.Data(
                    "614889782588491410".decimalFormat(),
                    persistentListOf(
                        Factor("2"),
                        Factor("3"),
                        Factor("5"),
                        Factor("7"),
                        Factor("11"),
                        Factor("13"),
                        Factor("17"),
                        Factor("19"),
                        Factor("23"),
                        Factor("29"),
                        Factor("31"),
                        Factor("37"),
                        Factor("41"),
                        Factor("43"),
                        Factor("47"),
                    ),
                ),
                State.Number.Data(
                    "668940403720827858".decimalFormat(),
                    persistentListOf(
                        Factor("2"),
                        Factor("3"),
                        Factor("17"),
                        Factor("359851"),
                        Factor("18224874329"),
                    ),
                ),
                State.Number.Data(
                    "16704385347977987526".decimalFormat(),
                    persistentListOf(
                        Factor("2"),
                        Factor("3", "3"),
                        Factor("6553"),
                        Factor("47205931543873"),
                    ),
                ),
                State.Number.Calculating("6210722251338751269".decimalFormat()),
            ),
        ) {},
        Modifier,
    )
}

@Composable
fun Cpp_Code_Preview() = preview {
    Cpp().Content(
        State(
            showCode = true,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Code,
            ),
            canEdit = true,
            canCalculate = true,
            inputNumber = "",
            inputCalculation = State.Number.Calculating(""),
            calculations = persistentListOf(),
        ) {},
        Modifier,
    )
}
