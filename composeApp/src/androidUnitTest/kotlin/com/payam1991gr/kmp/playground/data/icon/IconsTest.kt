package com.payam1991gr.kmp.playground.data.icon

import com.google.common.truth.Truth.*
import kotlinx.coroutines.test.runTest
import org.junit.Test

class IconsTest {
    @Test
    fun `core icons test`() {
        val icons = IconsImpl()
        val coreIcons = icons.core()
        assertThat(coreIcons.size).isEqualTo(49)
        assertThat(coreIcons[0].title).isEqualTo("AccountBox")
        assertThat(coreIcons[1].title).isEqualTo("AccountCircle")
        assertThat(coreIcons[2].title).isEqualTo("AddCircle")
    }

    @Suppress("SpellCheckingInspection")
    @Test
    fun `extended icons test`() = runTest {
        val icons = IconsImpl()
        val extendedIcons = icons.extended()
        assertThat(extendedIcons.size).isEqualTo(2082)
        assertThat(extendedIcons[0].title).isEqualTo("_1k")
        assertThat(extendedIcons[1].title).isEqualTo("_1kPlus")
        assertThat(extendedIcons[2].title).isEqualTo("_1xMobiledata")
    }
}
