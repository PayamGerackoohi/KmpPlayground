package com.payam1991gr.kmp.playground.view.module

import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import com.payam1991gr.kmp.playground.data.math.Factor
import com.payam1991gr.kmp.playground.data.string.decimalFormat
import kotlinx.collections.immutable.PersistentList

@Composable
fun MathFactors(
    factors: PersistentList<Factor>,
    modifier: Modifier = Modifier,
    fontSize: MathFactors.FontSize = MathFactors.Defaults.fontSize(),
    prefix: String? = null,
) = Text(MathFactors.annotatedString(factors, fontSize, prefix), modifier)

object MathFactors {
    @Immutable
    data class FontSize(
        val base: TextUnit,
        val power: TextUnit,
    )

    @Composable
    fun annotatedString(
        factors: PersistentList<Factor>,
        fontSize: FontSize = Defaults.fontSize(),
        prefix: String? = null,
    ) = buildAnnotatedString {
        prefix?.let { withStyle(SpanStyle(fontSize = fontSize.base)) { append(prefix) } }
        factors.forEachIndexed { index, factor ->
            if (index != 0) withStyle(SpanStyle(fontSize = fontSize.power)) { append(" ⨉ ") }
            withStyle(SpanStyle(fontSize = fontSize.base)) { append(factor.base.decimalFormat()) }
            factor.power?.let { power ->
                withStyle(
                    SpanStyle(
                        baselineShift = BaselineShift.Superscript,
                        fontSize = fontSize.power,
                    )
                ) { append(power.decimalFormat()) }
            }
        }
    }

    object Defaults {
        @Composable
        fun fontSize(
            base: TextUnit = MaterialTheme.typography.titleMedium.fontSize,
            power: TextUnit = MaterialTheme.typography.labelSmall.fontSize,
        ) = FontSize(base, power)
    }
}
