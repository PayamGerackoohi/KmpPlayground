package screenshots.components.pull.to.refresh

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.components.pull.to.refresh.PullToRefresh_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.components.pull.to.refresh.PullToRefresh_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class PullToRefreshPreviewScreenshot : BaseScreenshot(Order.Components.PullToRefresh.Preview) {
    @Composable
    override fun Preview() = PullToRefresh_Preview_Preview()
}

class PullToRefreshCodeScreenshot : BaseScreenshot(Order.Components.PullToRefresh.Code) {
    @Composable
    override fun Preview() = PullToRefresh_Code_Preview()
}
