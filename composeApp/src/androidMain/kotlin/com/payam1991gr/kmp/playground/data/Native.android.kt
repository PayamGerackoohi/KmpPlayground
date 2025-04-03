package com.payam1991gr.kmp.playground.data

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object Native {
    init {
        System.loadLibrary("KmpPlayground")
    }

    actual external suspend fun primeFactors(text: String): String
}
