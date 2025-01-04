package screenshots.components

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.components.ComponentsPreview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class ComponentsScreenshot : BaseScreenshot(Order.Components.List) {
    @Composable
    override fun Preview() = ComponentsPreview()
}
