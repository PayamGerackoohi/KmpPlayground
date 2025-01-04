package screenshots.miscellaneous.pdf

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.miscellaneous.pdf.Pdf_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.miscellaneous.pdf.Pdf_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class PdfPreviewScreenshot : BaseScreenshot(Order.Miscellaneous.Pdf.Preview) {
    @Composable
    override fun Preview() = Pdf_Preview_Preview()
}

class PdfCodeScreenshot : BaseScreenshot(Order.Miscellaneous.Pdf.Code) {
    @Composable
    override fun Preview() = Pdf_Code_Preview()
}
