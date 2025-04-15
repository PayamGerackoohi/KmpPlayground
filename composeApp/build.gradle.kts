import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.time.Clock
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("kotlin-parcelize")
    alias(libs.plugins.roborazzi)
    alias(libs.plugins.kover)
    kotlin("plugin.serialization")
}

object AppInfo {
    const val PACKAGE_NAME = "com.payam1991gr.kmp.playground"
    const val PROJECT_NAME = "kmpplayground"
    const val APP_NAME = "KMP-Playground"
    const val VERSION = "1.0.0"
    private val formatter = DateTimeFormatter.ofPattern("yyMMdd-HHmm")
    val buildDate: String get() = LocalDateTime.now(Clock.systemUTC()).format(formatter)
}

val keystore = FileInputStream(rootProject.file("keystore/keystore.properties")).let { file ->
    Properties().apply { load(file) }
}

kotlin {
    androidTarget {
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
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
        iosTarget.compilations {
            val main by getting {
                cinterops {
                    val libBridge by creating {
                        definitionFile.set(project.file("src/cpp/libBridge.def"))
                        packageName("com.payam1991gr.kmp.playground.data")
                    }
                }
            }
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
            implementation(compose.materialIconsExtended)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtime.compose)
            implementation(projects.shared)
            implementation(libs.circuit)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.core)
            implementation(libs.kotlinx.date.time)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.datastore)
            implementation(libs.datastore.preferences)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.json)
            implementation(libs.slf4j.simple)
        }
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.lifecycle.runtime.android)
            implementation(libs.koin.android)
            implementation("androidx.annotation:annotation-jvm") { version { strictly("1.8.2") } }
            implementation(libs.ktor.client.okhttp)
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
            implementation(libs.ktor.client.mock)
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
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.androidx.lifecycle.common.jvm)
            implementation("androidx.annotation:annotation-jvm") { version { strictly("1.8.2") } }
            implementation(libs.ktor.client.okhttp)
        }
    }
}

android {
    namespace = "com.payam1991gr.kmp.playground"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    ndkVersion = "27.1.12297006"

    defaultConfig {
        applicationId = "com.payam1991gr.kmp.playground"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = AppInfo.VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        create("release") {
            keystore.apply {
                storeFile = file(getProperty("storeFile"))
                storePassword = getProperty("storePassword")
                keyAlias = getProperty("keyAlias")
                keyPassword = getProperty("keyPassword")
            }
        }
    }
    buildTypes {
        applicationVariants.all {
//            val meta = if (buildType.name == "release") "" else "-${AppInfo.buildDate}"
            val meta = "-${AppInfo.buildDate}"
            outputs.all {
                (this as BaseVariantOutputImpl).outputFileName =
                    "${AppInfo.APP_NAME}-${AppInfo.VERSION}$meta.apk"
            }
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
    }
    externalNativeBuild {
        cmake {
            path = file("src/cpp/CMakeLists.txt")
            version = "3.30.3"
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
                        setIncludePatterns("screenshots.*")
                    else
                        setExcludePatterns("screenshots.*")
                }
            }
        }
    }
}

AppInfo.apply {
    kover.reports.filters.excludes.classes(
        "${PACKAGE_NAME}.*.sample.*",
        "${PACKAGE_NAME}.*.*_ComposeKt*",
        "${PACKAGE_NAME}.*.*_PlatformKt*",
        "${PACKAGE_NAME}.preview.*",
        "${PACKAGE_NAME}.view.*",
        "${PACKAGE_NAME}.AndroidApplication",
        "${PACKAGE_NAME}.data.koin.*",
        "${PACKAGE_NAME}.data.store.*",
        "${PACKAGE_NAME}.data.Native",
        "${PACKAGE_NAME}.data.remote.FakeApi*",
        "${PROJECT_NAME}.composeapp.generated.resources.*",
    )
}

dependencies {
    debugImplementation(compose.uiTooling)
    debugImplementation(libs.test.androidx.ui.manifest)
}

compose.desktop.application {
    mainClass = "com.payam1991gr.kmp.playground.view.MainKt"
    nativeDistributions {
        packageName = "KMP Playground"
        packageVersion = AppInfo.VERSION
        targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
        appResourcesRootDir.set(project.layout.projectDirectory.dir("src/desktopMain/resources"))
        outputBaseDir.set(project.layout.buildDirectory.dir("desktop"))
        licenseFile.set(project.file("../LICENSE"))
        modules("jdk.unsupported") // datastore
        modules("jdk.unsupported.desktop") // datastore
    }
    buildTypes.release.proguard {
        obfuscate.set(true)
        optimize.set(true)
        configurationFiles.from(project.file("compose-desktop.pro"))
    }
}

apply(from = "add-screen.gradle.kts")
apply(from = "screenshot-util.gradle.kts")
