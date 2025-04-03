package com.payam1991gr.kmp.playground.data

import java.io.File

@Suppress("UnsafeDynamicallyLoadedCode", "EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object Native {
    private const val LIBRARY_NAME = "KmpPlayground"

    init {
        loadLibrary()
    }

    private fun loadLibrary() = System.getProperty("compose.application.resources.dir")
        ?.loadForDeployment()
        ?: loadForDevelopment()

    private fun loadForDevelopment() = System.loadLibrary(LIBRARY_NAME)

    private fun String.loadForDeployment() = File(this)
        .resolve("lib$LIBRARY_NAME.dylib")
        .let { System.load(it.absolutePath) }

    actual external suspend fun primeFactors(text: String): String
}
