package com.payam1991gr.kmp.playground.data

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect object Native {
    suspend fun primeFactors(text: String): String
}
