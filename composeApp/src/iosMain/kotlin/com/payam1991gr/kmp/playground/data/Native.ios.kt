package com.payam1991gr.kmp.playground.data

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.toKString

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING", "UnnecessaryOptInAnnotation")
@OptIn(ExperimentalForeignApi::class)
actual object Native {
    actual suspend fun primeFactors(text: String): String =
        nativePrimeFactors(text)?.toKString() ?: "?"
}
