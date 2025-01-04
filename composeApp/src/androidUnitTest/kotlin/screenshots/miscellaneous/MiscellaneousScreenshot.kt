package screenshots.miscellaneous

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.miscellaneous.MiscellaneousPreview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class MiscellaneousScreenshot : BaseScreenshot(Order.Miscellaneous.List) {
    @Composable
    override fun Preview() = MiscellaneousPreview()
}
