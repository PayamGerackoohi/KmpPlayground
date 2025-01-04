package screenshots.io.api

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.io.api.Api_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.io.api.Api_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class ApiPreviewScreenshot : BaseScreenshot(Order.Io.Api.Preview) {
    @Composable
    override fun Preview() = Api_Preview_Preview()
}

class ApiCodeScreenshot : BaseScreenshot(Order.Io.Api.Code) {
    @Composable
    override fun Preview() = Api_Code_Preview()
}
