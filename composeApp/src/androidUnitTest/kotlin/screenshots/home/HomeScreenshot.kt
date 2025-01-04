package screenshots.home

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.home.Home_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class HomeScreenshot : BaseScreenshot(Order.Home) {
    @Composable
    override fun Preview() = Home_Preview()
}
