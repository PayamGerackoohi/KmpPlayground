package screenshots.animations.animate.x.`as`

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.animations.animate.x.`as`.AnimateXAs_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.animations.animate.x.`as`.AnimateXAs_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class AnimateXAsPreviewScreenshot : BaseScreenshot(Order.Animations.AnimateXAs.Preview) {
    @Composable
    override fun Preview() = AnimateXAs_Preview_Preview()
}

class AnimateXAsCodeScreenshot : BaseScreenshot(Order.Animations.AnimateXAs.Code) {
    @Composable
    override fun Preview() = AnimateXAs_Code_Preview()
}
