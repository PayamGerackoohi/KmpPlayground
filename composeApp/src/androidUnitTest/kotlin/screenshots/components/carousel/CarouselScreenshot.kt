package screenshots.components.carousel

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.components.carousel.Carousel_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.components.carousel.Carousel_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class CarouselPreviewScreenshot : BaseScreenshot(Order.Components.Carousel.Preview) {
    @Composable
    override fun Preview() = Carousel_Preview_Preview()
}

class CarouselCodeScreenshot : BaseScreenshot(Order.Components.Carousel.Code) {
    @Composable
    override fun Preview() = Carousel_Code_Preview()
}
