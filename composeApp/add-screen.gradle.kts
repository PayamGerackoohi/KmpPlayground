import java.util.Locale

/**
 * Adds circuit bases screen (State-UI-Presenter)
 * @param path   the destination files local path
 * @param screen the screen name
 * @sample ./gradlew addScreen -Pscreen=samplePage
 * It produces:
 * [PACKAGE_DIR]
 *   ∟ view
 *     ∟ screens
 *       ∟ sample
 *         ∟ page
 *           ∟ SamplePage.kt
 *           ∟ SamplePageScreen.kt
 * [PACKAGE_DIR]
 *   ∟ presenter
 *     ∟ screens
 *       ∟ sample
 *         ∟ page
 *           ∟ SamplePagePresenter.kt
 * @sample ./gradlew addScreen -Ppath=path.to.screen -Pscreen=samplePage
 * It produces:
 * [PACKAGE_DIR]
 *   ∟ view
 *     ∟ screens
 *       ∟ path
 *         ∟ to
 *           ∟ screen
 *             ∟ SamplePage.kt
 *             ∟ SamplePageScreen.kt
 * [PACKAGE_DIR]
 *   ∟ presenter
 *     ∟ screens
 *       ∟ path
 *         ∟ to
 *           ∟ screen
 *             ∟ SamplePagePresenter.kt
 */
abstract class AddScreenTask : DefaultTask() {
    private val properties by lazy { project.gradle.startParameter.projectProperties }
    private val className by lazy {
        properties["screen"].toString().let {
            if (it == "null") throw IllegalArgumentException("Illegal Argument Exception: The screen parameter cannot be empty")
            else it.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
    }

    private val subDirectory by lazy {
        properties["path"].toString().let { path ->
            if (path == "null") className.mapIndexed { index, it ->
                if (it.isLowerCase()) it
                else if (index == 0) it.lowercase()
                else ".${it.lowercase()}"
            }.joinToString("")
            else path
        }.let { Dir.dot(it) }
    }

    @TaskAction
    fun action() {
        val screen = "${className}Screen"
        val presenter = "${className}Presenter"
        val viewPackageName = Util.run {
            "com.payam1991gr.kmp.playground.view.screens".joinWith(subDirectory.dot, ".")
        }
        val presenterPackageName = Util.run {
            "com.payam1991gr.kmp.playground.presenter.screens".joinWith(subDirectory.dot, ".")
        }
        val projectDir = project.gradle.rootProject.projectDir
        val rootDir =
            "${projectDir}/composeApp/src/commonMain/kotlin/com/payam1991gr/kmp/playground"
        val viewDirectory = Util.makeDirectory(rootDir, subDirectory, "view")
        val presenterDirectory = Util.makeDirectory(rootDir, subDirectory, "presenter")

        val screenFile = File(viewDirectory, "${screen}.kt")
        screenFile.createNewFile()
        screenFile.writeText(
            """
            package $viewPackageName

            import com.payam1991gr.kmp.playground.platform.KmpParcelize
            import com.slack.circuit.runtime.CircuitUiState
            import com.slack.circuit.runtime.screen.Screen

            @KmpParcelize
            data object $screen : Screen {
                data object State : CircuitUiState
            }

        """.trimIndent()
        )

        val viewFile = File(viewDirectory, "${className}.kt")
        viewFile.createNewFile()
        viewFile.writeText(
            """
            package $viewPackageName

            import androidx.compose.foundation.layout.Arrangement
            import androidx.compose.foundation.layout.Column
            import androidx.compose.material3.Text
            import androidx.compose.runtime.Composable
            import androidx.compose.ui.Alignment
            import androidx.compose.ui.Modifier
            import $viewPackageName.$screen.State
            import com.slack.circuit.runtime.ui.Ui

            class $className : Ui<State> {
                @Composable
                override fun Content(state: State, modifier: Modifier) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = modifier,
                    ) {
                        Text(text = "$className")
                    }
                }
            }

        """.trimIndent()
        )

        val presenterFile = File(presenterDirectory, "$presenter.kt")
        presenterFile.createNewFile()
        presenterFile.writeText(
            """
            package $presenterPackageName

            import androidx.compose.runtime.Composable
            import $viewPackageName.$screen.State
            import com.slack.circuit.runtime.presenter.Presenter

            class $presenter : Presenter<State> {
                @Composable
                override fun present(): State {
                    return State
                }
            }

        """.trimIndent()
        )
    }
}

tasks.register<AddScreenTask>("addScreen") {
    group = "dev-utility"
    description = "Adds a new screen to the source code"
}

data class Dir(val dot: String, val slash: String) {
    companion object {
        fun dot(value: String) = Dir(value, value.split('.').joinToString("/"))
    }

    infix fun join(other: Dir) = Util.run {
        Dir(
            dot.joinWith(other.dot, "."),
            slash.joinWith(other.slash, "/"),
        )
    }
}

object Util {
    fun String.joinWith(other: String, spacer: String) =
        if (other.isEmpty()) this
        else "$this$spacer$other"

    fun makeDirectory(rootDir: String, subDir: Dir, dirType: String) =
        "${rootDir}/${dirType}/screens".let {
            it.joinWith(subDir.slash, "/")
        }.let {
            File(it)
        }.apply {
            if (!exists()) mkdirs()
        }
}
