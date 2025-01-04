package com.payam1991gr.kmp.playground

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform