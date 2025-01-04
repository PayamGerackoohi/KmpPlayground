package screenshots.graphics

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.graphics.GraphicsPreview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class GraphicsScreenshot : BaseScreenshot(Order.Graphics.List) {
    @Composable
    override fun Preview() = GraphicsPreview()
}
