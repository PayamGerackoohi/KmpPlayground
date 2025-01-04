package screenshots.components.dialog

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.components.dialog.Dialog_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.components.dialog.Dialog_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class DialogPreviewScreenshot : BaseScreenshot(Order.Components.Dialog.Preview) {
    @Composable
    override fun Preview() = Dialog_Preview_Preview()
}

class DialogCodeScreenshot : BaseScreenshot(Order.Components.Dialog.Code) {
    @Composable
    override fun Preview() = Dialog_Code_Preview()
}
