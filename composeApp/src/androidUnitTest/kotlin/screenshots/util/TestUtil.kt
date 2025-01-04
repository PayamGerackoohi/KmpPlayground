package screenshots.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.junit4.ComposeContentTestRule

fun image(name: String) = "../docs/screenshots/images/${name}.png"

fun ComposeContentTestRule.setRobolectricContent(content: @Composable () -> Unit) {
    setContent {
        CompositionLocalProvider(LocalInspectionMode provides true) {
            content()
        }
    }
}
