package screenshots.animations

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.animations.AnimationsPreview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class AnimationsScreenshot : BaseScreenshot(Order.Animations.List) {
    @Composable
    override fun Preview() = AnimationsPreview()
}
