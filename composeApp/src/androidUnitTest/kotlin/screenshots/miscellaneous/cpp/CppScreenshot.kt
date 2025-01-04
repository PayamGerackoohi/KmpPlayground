package screenshots.miscellaneous.cpp

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.miscellaneous.cpp.Cpp_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.miscellaneous.cpp.Cpp_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class CppPreviewScreenshot : BaseScreenshot(Order.Miscellaneous.Cpp.Preview) {
    @Composable
    override fun Preview() = Cpp_Preview_Preview()
}

class CppCodeScreenshot : BaseScreenshot(Order.Miscellaneous.Cpp.Code) {
    @Composable
    override fun Preview() = Cpp_Code_Preview()
}
