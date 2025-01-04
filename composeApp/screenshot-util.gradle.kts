enum class UiMode(val qualifier: String) { Light("notnight"), Dark("night") }
enum class Orientation(val qualifier: String) { Portrait("port"), Landscape("land") }
enum class ScreenSize(val size: String, val density: String, val meta: String) {
    XSmall("w320dp-h427dp", "120dpi", "240 x 320 px, 2.7"),
    Small("w360dp-h640dp", "320dpi", "720 x 1280 px, 4.65"),
    Foldable("w360dp-h879dp", "480dpi", "1080 x 2636 px, 6.7"),
    Tablet("w1280dp-h900dp", "320dpi", "2560 x 1800 px, 9.94"), // PixelC
    Desktop("w1920dp-h1080dp", "160dpi", "1920 x 1080 px, 17"),
}

enum class Lang(val qualifier: String) {
    English("en-rUS"),
    German("de"),
    Hebrew("iw"),
}

abstract class CreateScreenshotDimensionsTask : DefaultTask() {
    private val dimensions: List<List<Any>> = Dimension.cross(
        Lang.values().toList(),
        ScreenSize.values().toList(),
        Orientation.values().toList(),
        UiMode.values().toList(),
    )

    private fun fileTemplate(testCases: String): String =
        """// AUTO-GENERATED from 'createScreenshotDimensionsTask'
package screenshots.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode
import screenshots.util.order.Node

@RunWith(RobolectricTestRunner::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
abstract class BaseScreenshot(private val leaf: Node.Leaf) {
    @get:Rule
    val composeRule = createComposeRule()

    private fun screenshot(qualifier: String) {
        composeRule.setRobolectricContent { Preview() }
        composeRule.onRoot().captureRoboImage(image("${'$'}{leaf.name}-${'$'}{qualifier}"))
    }

    @Composable
    abstract fun Preview()

${testCases}}
"""

    private fun testCaseTemplate(qualifier: DeviceQualifier): String = """    @Test
    @Config(qualifiers = "${qualifier.label}")
    open fun `screenshot - $qualifier`() {
        screenshot("$qualifier")
    }
"""

    @TaskAction
    fun action() {
        val testCases = buildList {
            dimensions.forEach { (lang, screenSize, orientation, uiMode) ->
                if (screenSize == ScreenSize.Desktop && orientation == Orientation.Portrait) return@forEach
                val qualifier = DeviceQualifier(
                    lang as Lang,
                    screenSize as ScreenSize,
                    orientation as Orientation,
                    uiMode as UiMode,
                )
                add(testCaseTemplate(qualifier))
            }
        }.joinToString("\n")

        val projectDir = project.gradle.rootProject.projectDir
        File("${projectDir}/composeApp/src/androidUnitTest/kotlin/screenshots/util/BaseScreenshot.kt").apply {
            createNewFile()
            writeText(fileTemplate(testCases))
        }
    }
}

tasks.register<CreateScreenshotDimensionsTask>("createScreenshotDimensionsTask") {
    group = "screenshot"
    description = "Create Screenshot Test Dimensions"
}

data class DeviceQualifier(
    val lang: Lang? = null,
    val screenSize: ScreenSize? = null,
    val orientation: Orientation? = null,
    val uiMode: UiMode? = null,
) {
    val label: String by lazy {
        buildList {
            lang?.let { add(it.qualifier) }
            screenSize?.let { add(it.size) }
            orientation?.let { add(it.qualifier) }
            uiMode?.let { add(it.qualifier) }
            screenSize?.let { add(it.density) }
        }.joinToString("-")
    }
    private val descriptiveLabel: String by lazy {
        buildList {
            lang?.let { add(it) }
            uiMode?.let { add(it) }
            orientation?.let { add(it) }
            screenSize?.let { add(it) }
        }.joinToString("-")
    }

    override fun toString(): String = descriptiveLabel
}

object Dimension {
    fun cross(vararg lists: List<Any>): List<List<Any>> = buildList {
        cross(listOf(), *lists)
    }

    private fun MutableList<List<Any>>.cross(args: List<Any>, vararg lists: List<Any>) {
        if (lists.isEmpty()) return
        val first = lists[0]
        val others = lists.drop(1)
        val isLast = others.isEmpty()
        first.forEach {
            val newArgs = args + listOf(it)
            if (isLast)
                add(buildList { addAll(newArgs) })
            else
                cross(newArgs, *others.toTypedArray())
        }
    }
}
