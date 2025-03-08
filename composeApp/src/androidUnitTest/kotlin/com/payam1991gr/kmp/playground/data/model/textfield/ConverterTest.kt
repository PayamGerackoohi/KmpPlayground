package com.payam1991gr.kmp.playground.data.model.textfield

import com.google.common.truth.Truth.*
import org.junit.Test

class ConverterTest {
    @Test
    fun `int converter - valid conversions - test`() {
        IntConverter().apply {
            listOf(
                -1 to "-1",
                0 to "0",
                1 to "1",
                Int.MAX_VALUE to "2147483647",
                Int.MIN_VALUE to "-2147483648",
            ).forEach { (input, output) ->
                assertThat(from(input)).isEqualTo(output)
                output.toData().let { data ->
                    assertThat(data.value).isEqualTo(input)
                    assertThat(data.hasError).isFalse()
                }
            }
        }
    }

    @Test
    fun `int converter - valid strings - test`() {
        IntConverter().apply {
            listOf(
                " 0 " to 0,
            ).forEach { (input, output) ->
                input.toData().let { data ->
                    assertThat(data.value).isEqualTo(output)
                    assertThat(data.hasError).isFalse()
                }
            }
        }
    }

    @Test
    fun `int converter - invalid strings - test`() {
        IntConverter().apply {
            listOf(
                "",
                " ",
                "a",
                "0xff",
                "0.0",
                "2147483648",
                "-2147483649",
            ).forEach { input ->
                input.toData().let { data ->
                    assertThat(data.value).isEqualTo(0)
                    assertThat(data.hasError).isTrue()
                }
            }
        }
    }

    @Test
    fun `float converter - valid conversions - test`() {
        FloatConverter().apply {
            listOf(
                -1f to "-1.0",
                0f to "0.0",
                1f to "1.0",
                3.14f to "3.14",
                Float.POSITIVE_INFINITY to "Infinity",
                Float.NEGATIVE_INFINITY to "-Infinity",
                Float.MAX_VALUE to "3.4028235E38",
                Float.MIN_VALUE to "1.4E-45",
            ).forEach { (input, output) ->
                assertThat(from(input)).isEqualTo(output)
                output.toData().let { data ->
                    assertThat(data.value).isEqualTo(input)
                    assertThat(data.hasError).isFalse()
                }
            }
        }
    }

    @Test
    fun `float converter - valid strings - test`() {
        FloatConverter().apply {
            listOf(
                "0" to 0f,
                " 0.0 " to 0f,
                "3.4028236E38" to Float.POSITIVE_INFINITY,
                "1.4E-46" to 0f,
            ).forEach { (input, output) ->
                input.toData().let { data ->
                    assertThat(data.value).isEqualTo(output)
                    assertThat(data.hasError).isFalse()
                }
            }
        }
    }

    @Test
    fun `float converter - invalid strings - test`() {
        FloatConverter().apply {
            listOf(
                "",
                " ",
                "a",
                "0xff",
            ).forEach { input ->
                input.toData().let { data ->
                    assertThat(data.value).isEqualTo(0f)
                    assertThat(data.hasError).isTrue()
                }
            }
        }
    }

    @Test
    fun `byte array converter - valid conversions - test`() {
        ByteArrayConverter().apply {
            listOf(
                byteArrayOf() to "",
                byteArrayOf(1) to "1",
                byteArrayOf(1, 2) to "1,2",
                byteArrayOf(-1, 0, 1) to "-1,0,1",
            ).forEach { (input, output) ->
                assertThat(from(input)).isEqualTo(output)
                output.toData().let { data ->
                    assertThat(data.value).isEqualTo(input)
                    assertThat(data.hasError).isFalse()
                }
            }
        }
    }

    @Test
    fun `byte array converter - valid strings - test`() {
        ByteArrayConverter().apply {
            listOf(
                " " to byteArrayOf(),
                " 1 " to byteArrayOf(1),
                " 1 , 2 " to byteArrayOf(1, 2),
                " -1  , 0 ,  1 " to byteArrayOf(-1, -0, 1),
                "-128, -127, 127" to byteArrayOf(-128, -127, 127),
            ).forEach { (input, output) ->
                input.toData().let { data ->
                    assertThat(data.value).isEqualTo(output)
                    assertThat(data.hasError).isFalse()
                }
            }
        }
    }

    @Test
    fun `byte array converter - invalid strings - test`() {
        ByteArrayConverter().apply {
            listOf(
                "128",
                "129",
                "-129",
                "a",
                "0xff",
            ).forEach { input ->
                input.toData().let { data ->
                    assertThat(data.value).isEqualTo(byteArrayOf())
                    assertThat(data.hasError).isTrue()
                }
            }
        }
    }
}
