package screenshots.miscellaneous.datetime

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.miscellaneous.datetime.DateTime_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.miscellaneous.datetime.DateTime_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class DateTimePreviewScreenshot : BaseScreenshot(Order.Miscellaneous.DateTime.Preview) {
    @Composable
    override fun Preview() = DateTime_Preview_Preview()
}

class DateTimeCodeScreenshot : BaseScreenshot(Order.Miscellaneous.DateTime.Code) {
    @Composable
    override fun Preview() = DateTime_Code_Preview()
}
