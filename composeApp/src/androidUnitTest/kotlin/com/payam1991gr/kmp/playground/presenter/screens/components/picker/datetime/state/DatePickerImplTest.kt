package com.payam1991gr.kmp.playground.presenter.screens.components.picker.datetime.state

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.preview.Fake
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.state.DatePickerImpl
import org.junit.Test

class DatePickerImplTest {
    @Test
    fun `no initial value - test`() {
        DatePickerImpl().apply {
            assertThat(initialDisplayDate).isNull()
            date.apply {
                assertThat(ms).isNull()
                assertThat(text).isNull()
                ms = 0
                assertThat(text).isEqualTo("1970-01-01")
            }
        }
    }

    @Test
    fun `with initial value - test`() {
        DatePickerImpl(Fake.timestamp, Fake.timestamp2).apply {
            assertThat(initialDisplayDate).isEqualTo(687398400000)
            date.apply {
                assertThat(ms).isEqualTo(688867200000)
                assertThat(text).isEqualTo("1991-10-31")
            }
        }
    }
}
