import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("kotlin-parcelize")
    alias(libs.plugins.roborazzi)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
            freeCompilerArgs.addAll(
                "-P",
                "plugin:org.jetbrains.kotlin.parcelize:additionalAnnotation=com.payam1991gr.kmp.playground.platform.KmpParcelize"
            )
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    jvm("desktop")

    sourceSets {
        val desktopMain by getting

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(projects.shared)
            implementation(libs.circuit)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.lifecycle.runtime.android)
            implementation("androidx.annotation:annotation-jvm") { version { strictly("1.8.2") } }
        }
        androidUnitTest.dependencies {
            implementation(libs.test.androidx.ui.junit4)
            implementation(libs.test.junit)
            implementation(libs.test.google.truth)
            implementation(libs.test.mockk)
            implementation(libs.test.robolectric)
            implementation(libs.test.roborazzi)
            implementation(libs.test.roborazzi.compose)
            implementation(libs.test.circuit)
        }
        androidInstrumentedTest.dependencies {
            implementation(libs.test.androidx.ui.junit4)
            implementation(libs.test.androidx)
            implementation(libs.test.rules.androidx)
            implementation(libs.test.runner.androidx)
            implementation(libs.test.google.truth)
            implementation(libs.test.junit)
            implementation(libs.test.mockk)
            implementation(libs.test.ui.automator)
//            implementation(libs.test.androidx.espresso.core)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.androidx.lifecycle.common.jvm)
            implementation("androidx.annotation:annotation-jvm") { version { strictly("1.8.2") } }
        }
    }
}

android {
    namespace = "com.payam1991gr.kmp.playground"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.payam1991gr.kmp.playground"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }
    @Suppress("UnstableApiUsage")
    testOptions {
        unitTests {
            isReturnDefaultValues = true
            isIncludeAndroidResources = true
            all {
                it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
                it.filter {
                    val properties = project.gradle.startParameter.projectProperties
                    if (properties["screenshotMode"].toString() == "true")
//                        setIncludePatterns("screenshots.*")
//                        setIncludePatterns("screenshots.home.*")
                        setIncludePatterns("screenshots.components.carousel.*")
//                        setIncludePatterns("screenshots.components.dialog.*")
                    else
                        setExcludePatterns("screenshots.*")
                }
            }
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
    debugImplementation(libs.test.androidx.ui.manifest)
}

compose.desktop {
    application {
        mainClass = "com.payam1991gr.kmp.playground.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.payam1991gr.kmp.playground"
            packageVersion = "1.0.0"
        }
    }
}

apply(from = "add-screen.gradle.kts")
apply(from = "screenshot-util.gradle.kts")
