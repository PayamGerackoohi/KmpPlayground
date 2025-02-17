package screenshots.graphics.color.scheme

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.graphics.color.scheme.ColorScheme_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.graphics.color.scheme.ColorScheme_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class ColorSchemePreviewScreenshot : BaseScreenshot(Order.Graphics.ColorScheme.Preview) {
    @Composable
    override fun Preview() = ColorScheme_Preview_Preview()
}

class ColorSchemeCodeScreenshot : BaseScreenshot(Order.Graphics.ColorScheme.Code) {
    @Composable
    override fun Preview() = ColorScheme_Code_Preview()
}
