package screenshots.miscellaneous.ble

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.miscellaneous.ble.Ble_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.miscellaneous.ble.Ble_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class BlePreviewScreenshot : BaseScreenshot(Order.Miscellaneous.Ble.Preview) {
    @Composable
    override fun Preview() = Ble_Preview_Preview()
}

class BleCodeScreenshot : BaseScreenshot(Order.Miscellaneous.Ble.Code) {
    @Composable
    override fun Preview() = Ble_Code_Preview()
}
