package screenshots.animations.animated.content

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.animations.animated.content.AnimatedContent_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.animations.animated.content.AnimatedContent_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class AnimatedContentPreviewScreenshot : BaseScreenshot(Order.Animations.AnimatedContent.Preview) {
    @Composable
    override fun Preview() = AnimatedContent_Preview_Preview()
}

class AnimatedContentCodeScreenshot : BaseScreenshot(Order.Animations.AnimatedContent.Code) {
    @Composable
    override fun Preview() = AnimatedContent_Code_Preview()
}
