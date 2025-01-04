package screenshots.io

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.io.IoPreview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class IoScreenshot : BaseScreenshot(Order.Io.List) {
    @Composable
    override fun Preview() = IoPreview()
}
