package screenshots.components.picker.datatime

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.components.picker.datetime.DateTimePicker_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.components.picker.datetime.DateTimePicker_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class DateTimePickerPreviewScreenshot : BaseScreenshot(Order.Components.Picker.DateTime.Preview) {
    @Composable
    override fun Preview() = DateTimePicker_Preview_Preview()
}

class DateTimePickerCodeScreenshot : BaseScreenshot(Order.Components.Picker.DateTime.Code) {
    @Composable
    override fun Preview() = DateTimePicker_Code_Preview()
}
