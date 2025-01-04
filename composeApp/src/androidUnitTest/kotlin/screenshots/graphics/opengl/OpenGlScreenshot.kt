package screenshots.graphics.opengl

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.graphics.opengl.OpenGl_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.graphics.opengl.OpenGl_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class OpenGlPreviewScreenshot : BaseScreenshot(Order.Graphics.OpenGl.Preview) {
    @Composable
    override fun Preview() = OpenGl_Preview_Preview()
}

class OpenGlCodeScreenshot : BaseScreenshot(Order.Graphics.OpenGl.Code) {
    @Composable
    override fun Preview() = OpenGl_Code_Preview()
}
