package screenshots.animations.animated.visibility

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.animations.animated.visibility.AnimatedVisibility_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.animations.animated.visibility.AnimatedVisibility_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class AnimatedVisibilityPreviewScreenshot :
    BaseScreenshot(Order.Animations.AnimatedVisibility.Preview) {
    @Composable
    override fun Preview() = AnimatedVisibility_Preview_Preview()
}

class AnimatedVisibilityCodeScreenshot : BaseScreenshot(Order.Animations.AnimatedVisibility.Code) {
    @Composable
    override fun Preview() = AnimatedVisibility_Code_Preview()
}
