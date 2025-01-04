package screenshots.module

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.module.RandomImage_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class RandomImageScreenshot : BaseScreenshot(Order.Modules.RandomImage) {
    @Composable
    override fun Preview() = RandomImage_Preview()
}
