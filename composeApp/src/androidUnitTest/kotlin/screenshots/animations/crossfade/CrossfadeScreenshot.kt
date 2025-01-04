package screenshots.animations.crossfade

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.animations.crossfade.Crossfade_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.animations.crossfade.Crossfade_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class CrossfadePreviewScreenshot : BaseScreenshot(Order.Animations.Crossfade.Preview) {
    @Composable
    override fun Preview() = Crossfade_Preview_Preview()
}

class CrossfadeCodeScreenshot : BaseScreenshot(Order.Animations.Crossfade.Code) {
    @Composable
    override fun Preview() = Crossfade_Code_Preview()
}
