// AUTO-GENERATED from 'createScreenshotDimensionsTask'
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
        composeRule.onRoot().captureRoboImage(image("${leaf.name}-${qualifier}"))
    }

    @Composable
    abstract fun Preview()

    @Test
    @Config(qualifiers = "en-rUS-w320dp-h427dp-port-notnight-120dpi")
    open fun `screenshot - English-Light-Portrait-XSmall`() {
        screenshot("English-Light-Portrait-XSmall")
    }

    @Test
    @Config(qualifiers = "en-rUS-w320dp-h427dp-port-night-120dpi")
    open fun `screenshot - English-Dark-Portrait-XSmall`() {
        screenshot("English-Dark-Portrait-XSmall")
    }

    @Test
    @Config(qualifiers = "en-rUS-w320dp-h427dp-land-notnight-120dpi")
    open fun `screenshot - English-Light-Landscape-XSmall`() {
        screenshot("English-Light-Landscape-XSmall")
    }

    @Test
    @Config(qualifiers = "en-rUS-w320dp-h427dp-land-night-120dpi")
    open fun `screenshot - English-Dark-Landscape-XSmall`() {
        screenshot("English-Dark-Landscape-XSmall")
    }

    @Test
    @Config(qualifiers = "en-rUS-w360dp-h640dp-port-notnight-320dpi")
    open fun `screenshot - English-Light-Portrait-Small`() {
        screenshot("English-Light-Portrait-Small")
    }

    @Test
    @Config(qualifiers = "en-rUS-w360dp-h640dp-port-night-320dpi")
    open fun `screenshot - English-Dark-Portrait-Small`() {
        screenshot("English-Dark-Portrait-Small")
    }

    @Test
    @Config(qualifiers = "en-rUS-w360dp-h640dp-land-notnight-320dpi")
    open fun `screenshot - English-Light-Landscape-Small`() {
        screenshot("English-Light-Landscape-Small")
    }

    @Test
    @Config(qualifiers = "en-rUS-w360dp-h640dp-land-night-320dpi")
    open fun `screenshot - English-Dark-Landscape-Small`() {
        screenshot("English-Dark-Landscape-Small")
    }

    @Test
    @Config(qualifiers = "en-rUS-w360dp-h879dp-port-notnight-480dpi")
    open fun `screenshot - English-Light-Portrait-Foldable`() {
        screenshot("English-Light-Portrait-Foldable")
    }

    @Test
    @Config(qualifiers = "en-rUS-w360dp-h879dp-port-night-480dpi")
    open fun `screenshot - English-Dark-Portrait-Foldable`() {
        screenshot("English-Dark-Portrait-Foldable")
    }

    @Test
    @Config(qualifiers = "en-rUS-w360dp-h879dp-land-notnight-480dpi")
    open fun `screenshot - English-Light-Landscape-Foldable`() {
        screenshot("English-Light-Landscape-Foldable")
    }

    @Test
    @Config(qualifiers = "en-rUS-w360dp-h879dp-land-night-480dpi")
    open fun `screenshot - English-Dark-Landscape-Foldable`() {
        screenshot("English-Dark-Landscape-Foldable")
    }

    @Test
    @Config(qualifiers = "en-rUS-w1280dp-h900dp-port-notnight-320dpi")
    open fun `screenshot - English-Light-Portrait-Tablet`() {
        screenshot("English-Light-Portrait-Tablet")
    }

    @Test
    @Config(qualifiers = "en-rUS-w1280dp-h900dp-port-night-320dpi")
    open fun `screenshot - English-Dark-Portrait-Tablet`() {
        screenshot("English-Dark-Portrait-Tablet")
    }

    @Test
    @Config(qualifiers = "en-rUS-w1280dp-h900dp-land-notnight-320dpi")
    open fun `screenshot - English-Light-Landscape-Tablet`() {
        screenshot("English-Light-Landscape-Tablet")
    }

    @Test
    @Config(qualifiers = "en-rUS-w1280dp-h900dp-land-night-320dpi")
    open fun `screenshot - English-Dark-Landscape-Tablet`() {
        screenshot("English-Dark-Landscape-Tablet")
    }

    @Test
    @Config(qualifiers = "en-rUS-w1920dp-h1080dp-land-notnight-160dpi")
    open fun `screenshot - English-Light-Landscape-Desktop`() {
        screenshot("English-Light-Landscape-Desktop")
    }

    @Test
    @Config(qualifiers = "en-rUS-w1920dp-h1080dp-land-night-160dpi")
    open fun `screenshot - English-Dark-Landscape-Desktop`() {
        screenshot("English-Dark-Landscape-Desktop")
    }

    @Test
    @Config(qualifiers = "de-w320dp-h427dp-port-notnight-120dpi")
    open fun `screenshot - German-Light-Portrait-XSmall`() {
        screenshot("German-Light-Portrait-XSmall")
    }

    @Test
    @Config(qualifiers = "de-w320dp-h427dp-port-night-120dpi")
    open fun `screenshot - German-Dark-Portrait-XSmall`() {
        screenshot("German-Dark-Portrait-XSmall")
    }

    @Test
    @Config(qualifiers = "de-w320dp-h427dp-land-notnight-120dpi")
    open fun `screenshot - German-Light-Landscape-XSmall`() {
        screenshot("German-Light-Landscape-XSmall")
    }

    @Test
    @Config(qualifiers = "de-w320dp-h427dp-land-night-120dpi")
    open fun `screenshot - German-Dark-Landscape-XSmall`() {
        screenshot("German-Dark-Landscape-XSmall")
    }

    @Test
    @Config(qualifiers = "de-w360dp-h640dp-port-notnight-320dpi")
    open fun `screenshot - German-Light-Portrait-Small`() {
        screenshot("German-Light-Portrait-Small")
    }

    @Test
    @Config(qualifiers = "de-w360dp-h640dp-port-night-320dpi")
    open fun `screenshot - German-Dark-Portrait-Small`() {
        screenshot("German-Dark-Portrait-Small")
    }

    @Test
    @Config(qualifiers = "de-w360dp-h640dp-land-notnight-320dpi")
    open fun `screenshot - German-Light-Landscape-Small`() {
        screenshot("German-Light-Landscape-Small")
    }

    @Test
    @Config(qualifiers = "de-w360dp-h640dp-land-night-320dpi")
    open fun `screenshot - German-Dark-Landscape-Small`() {
        screenshot("German-Dark-Landscape-Small")
    }

    @Test
    @Config(qualifiers = "de-w360dp-h879dp-port-notnight-480dpi")
    open fun `screenshot - German-Light-Portrait-Foldable`() {
        screenshot("German-Light-Portrait-Foldable")
    }

    @Test
    @Config(qualifiers = "de-w360dp-h879dp-port-night-480dpi")
    open fun `screenshot - German-Dark-Portrait-Foldable`() {
        screenshot("German-Dark-Portrait-Foldable")
    }

    @Test
    @Config(qualifiers = "de-w360dp-h879dp-land-notnight-480dpi")
    open fun `screenshot - German-Light-Landscape-Foldable`() {
        screenshot("German-Light-Landscape-Foldable")
    }

    @Test
    @Config(qualifiers = "de-w360dp-h879dp-land-night-480dpi")
    open fun `screenshot - German-Dark-Landscape-Foldable`() {
        screenshot("German-Dark-Landscape-Foldable")
    }

    @Test
    @Config(qualifiers = "de-w1280dp-h900dp-port-notnight-320dpi")
    open fun `screenshot - German-Light-Portrait-Tablet`() {
        screenshot("German-Light-Portrait-Tablet")
    }

    @Test
    @Config(qualifiers = "de-w1280dp-h900dp-port-night-320dpi")
    open fun `screenshot - German-Dark-Portrait-Tablet`() {
        screenshot("German-Dark-Portrait-Tablet")
    }

    @Test
    @Config(qualifiers = "de-w1280dp-h900dp-land-notnight-320dpi")
    open fun `screenshot - German-Light-Landscape-Tablet`() {
        screenshot("German-Light-Landscape-Tablet")
    }

    @Test
    @Config(qualifiers = "de-w1280dp-h900dp-land-night-320dpi")
    open fun `screenshot - German-Dark-Landscape-Tablet`() {
        screenshot("German-Dark-Landscape-Tablet")
    }

    @Test
    @Config(qualifiers = "de-w1920dp-h1080dp-land-notnight-160dpi")
    open fun `screenshot - German-Light-Landscape-Desktop`() {
        screenshot("German-Light-Landscape-Desktop")
    }

    @Test
    @Config(qualifiers = "de-w1920dp-h1080dp-land-night-160dpi")
    open fun `screenshot - German-Dark-Landscape-Desktop`() {
        screenshot("German-Dark-Landscape-Desktop")
    }

    @Test
    @Config(qualifiers = "iw-w320dp-h427dp-port-notnight-120dpi")
    open fun `screenshot - Hebrew-Light-Portrait-XSmall`() {
        screenshot("Hebrew-Light-Portrait-XSmall")
    }

    @Test
    @Config(qualifiers = "iw-w320dp-h427dp-port-night-120dpi")
    open fun `screenshot - Hebrew-Dark-Portrait-XSmall`() {
        screenshot("Hebrew-Dark-Portrait-XSmall")
    }

    @Test
    @Config(qualifiers = "iw-w320dp-h427dp-land-notnight-120dpi")
    open fun `screenshot - Hebrew-Light-Landscape-XSmall`() {
        screenshot("Hebrew-Light-Landscape-XSmall")
    }

    @Test
    @Config(qualifiers = "iw-w320dp-h427dp-land-night-120dpi")
    open fun `screenshot - Hebrew-Dark-Landscape-XSmall`() {
        screenshot("Hebrew-Dark-Landscape-XSmall")
    }

    @Test
    @Config(qualifiers = "iw-w360dp-h640dp-port-notnight-320dpi")
    open fun `screenshot - Hebrew-Light-Portrait-Small`() {
        screenshot("Hebrew-Light-Portrait-Small")
    }

    @Test
    @Config(qualifiers = "iw-w360dp-h640dp-port-night-320dpi")
    open fun `screenshot - Hebrew-Dark-Portrait-Small`() {
        screenshot("Hebrew-Dark-Portrait-Small")
    }

    @Test
    @Config(qualifiers = "iw-w360dp-h640dp-land-notnight-320dpi")
    open fun `screenshot - Hebrew-Light-Landscape-Small`() {
        screenshot("Hebrew-Light-Landscape-Small")
    }

    @Test
    @Config(qualifiers = "iw-w360dp-h640dp-land-night-320dpi")
    open fun `screenshot - Hebrew-Dark-Landscape-Small`() {
        screenshot("Hebrew-Dark-Landscape-Small")
    }

    @Test
    @Config(qualifiers = "iw-w360dp-h879dp-port-notnight-480dpi")
    open fun `screenshot - Hebrew-Light-Portrait-Foldable`() {
        screenshot("Hebrew-Light-Portrait-Foldable")
    }

    @Test
    @Config(qualifiers = "iw-w360dp-h879dp-port-night-480dpi")
    open fun `screenshot - Hebrew-Dark-Portrait-Foldable`() {
        screenshot("Hebrew-Dark-Portrait-Foldable")
    }

    @Test
    @Config(qualifiers = "iw-w360dp-h879dp-land-notnight-480dpi")
    open fun `screenshot - Hebrew-Light-Landscape-Foldable`() {
        screenshot("Hebrew-Light-Landscape-Foldable")
    }

    @Test
    @Config(qualifiers = "iw-w360dp-h879dp-land-night-480dpi")
    open fun `screenshot - Hebrew-Dark-Landscape-Foldable`() {
        screenshot("Hebrew-Dark-Landscape-Foldable")
    }

    @Test
    @Config(qualifiers = "iw-w1280dp-h900dp-port-notnight-320dpi")
    open fun `screenshot - Hebrew-Light-Portrait-Tablet`() {
        screenshot("Hebrew-Light-Portrait-Tablet")
    }

    @Test
    @Config(qualifiers = "iw-w1280dp-h900dp-port-night-320dpi")
    open fun `screenshot - Hebrew-Dark-Portrait-Tablet`() {
        screenshot("Hebrew-Dark-Portrait-Tablet")
    }

    @Test
    @Config(qualifiers = "iw-w1280dp-h900dp-land-notnight-320dpi")
    open fun `screenshot - Hebrew-Light-Landscape-Tablet`() {
        screenshot("Hebrew-Light-Landscape-Tablet")
    }

    @Test
    @Config(qualifiers = "iw-w1280dp-h900dp-land-night-320dpi")
    open fun `screenshot - Hebrew-Dark-Landscape-Tablet`() {
        screenshot("Hebrew-Dark-Landscape-Tablet")
    }

    @Test
    @Config(qualifiers = "iw-w1920dp-h1080dp-land-notnight-160dpi")
    open fun `screenshot - Hebrew-Light-Landscape-Desktop`() {
        screenshot("Hebrew-Light-Landscape-Desktop")
    }

    @Test
    @Config(qualifiers = "iw-w1920dp-h1080dp-land-night-160dpi")
    open fun `screenshot - Hebrew-Dark-Landscape-Desktop`() {
        screenshot("Hebrew-Dark-Landscape-Desktop")
    }
}
