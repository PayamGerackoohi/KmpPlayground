package screenshots.graphics.icons

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.graphics.icons.Icons_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.graphics.icons.Icons_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class IconsPreviewScreenshot : BaseScreenshot(Order.Graphics.Icons.Preview) {
    @Composable
    override fun Preview() = Icons_Preview_Preview()
}

class IconsCodeScreenshot : BaseScreenshot(Order.Graphics.Icons.Code) {
    @Composable
    override fun Preview() = Icons_Code_Preview()
}
