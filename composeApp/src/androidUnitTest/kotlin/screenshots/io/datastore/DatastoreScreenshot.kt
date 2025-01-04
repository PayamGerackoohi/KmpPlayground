package screenshots.io.datastore

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.screens.io.datastore.Datastore_Code_Preview
import com.payam1991gr.kmp.playground.preview.screens.io.datastore.Datastore_Preview_Preview
import screenshots.util.BaseScreenshot
import screenshots.util.order.Order

class DatastorePreviewScreenshot : BaseScreenshot(Order.Io.Datastore.Preview) {
    @Composable
    override fun Preview() = Datastore_Preview_Preview()
}

class DatastoreCodeScreenshot : BaseScreenshot(Order.Io.Datastore.Code) {
    @Composable
    override fun Preview() = Datastore_Code_Preview()
}
