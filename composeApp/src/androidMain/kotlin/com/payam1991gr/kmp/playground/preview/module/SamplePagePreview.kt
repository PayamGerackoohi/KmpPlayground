package com.payam1991gr.kmp.playground.preview.module

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.payam1991gr.kmp.playground.data.model.sample.rememberSetting
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview
import com.payam1991gr.kmp.playground.view.module.editor.CodePanel
import com.payam1991gr.kmp.playground.view.module.editor.rememberCodeEditor
import kmpplayground.composeapp.generated.resources.*
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
@Composable
fun SamplePage_Preview_Preview() = preview {
    SamplePage(
        showCode = false,
        titleRes = Res.string.home_components,
        actions = persistentListOf(Action.Back, Action.Code),
        preview = {
            SamplePage.preview(
                { Preview.Header("Title") },
                { Preview.Description("Description") },
                {
                    Preview.Settings(
                        rememberSetting { Res.string.show },
                        rememberSetting { Res.string.settings },
                        rememberSetting { Res.string.dismissible },
                        rememberSetting { Res.string.mandatory },
                        rememberSetting { Res.string.animate },
                        rememberSetting { Res.string.instant },
                    )
                },
                {
                    Text(
                        text = "Content",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
            )
        },
        code = {},
    )
}

@SinglePreview
//@androidx.compose.ui.tooling.preview.Preview(fontScale = .5f)
//@androidx.compose.ui.tooling.preview.Preview
//@androidx.compose.ui.tooling.preview.Preview(fontScale = 2.0f)
@Composable
fun SamplePage_Code_Preview() = preview {
    SamplePage(
        showCode = true,
        titleRes = Res.string.home_components,
        actions = persistentListOf(Action.Back, Action.Preview),
        preview = {},
        code = {
            CodePanel(editor = rememberCodeEditor {
                line { yellow { "#include " }; green { "<iostream>" } }
                line()
                line { orange { "using namespace " }; lavender { "std" }; normal { ";" } }
                line()
                line { orange { "int " }; blue { "main" }; normal { "() {" } }
                line(1) { normal { "cout " }; teal { "<< " };green { "\"Hi\" " }; teal { "<< " }; normal { "endl;" } }
                line(1) { orange { "return " }; cyan { "0" }; normal { ";" } }
                line { normal { "}" } }
                line()
            })
        },
    )
}
