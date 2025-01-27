package com.payam1991gr.kmp.playground.view.module

import com.payam1991gr.kmp.playground.view.module.robot.randomImageRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import org.junit.Test

class RandomImageTest : BaseTest() {
    @Test
    fun test() {
        rule.setContent {
            RandomImage(contentDescription = "Image 1")
        }
        randomImageRobot(rule) {
            image("Image 1")
        }
    }
}
