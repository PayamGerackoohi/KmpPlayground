package com.payam1991gr.kmp.playground.preview

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import com.payam1991gr.kmp.playground.ui.theme.KmpPlaygroundTheme

@Preview(showBackground = true, apiLevel = 33)
annotation class SinglePreview

@Preview(
    name = "01-MaterialTheme-Day",
    showBackground = true,
)
@Preview(
    name = "03-RedWallpaper-Day",
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
)
@Preview(
    name = "05-GreenWallpaper-Day",
    showBackground = true,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
)
@Preview(
    name = "07-BlueWallpaper-Day",
    showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
)
@Preview(
    name = "09-YellowWallpaper-Day",
    showBackground = true,
    wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE,
)
annotation class DayThemePreview

@Preview(
    name = "02-MaterialTheme-Night",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "04-RedWallpaper-Night",
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "06-GreenWallpaper-Night",
    showBackground = true,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "08-BlueWallpaper-Night",
    showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "10-YellowWallpaper-Night",
    showBackground = true,
    wallpaper = Wallpapers.YELLOW_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class NightThemePreview

@DayThemePreview
@NightThemePreview
annotation class ThemesPreview

@Preview(
    name = "01-MaterialTheme-Day",
    showBackground = true,
)
@Preview(
    name = "02-MaterialTheme-Night",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class SimpleDayNightPreview

@Preview(name = "English", locale = "en-rUS", showBackground = true, apiLevel = 33)
@Preview(name = "German", locale = "de", showBackground = true, apiLevel = 33)
@Preview(name = "Hebrew", locale = "iw", showBackground = true, apiLevel = 33)
annotation class LocalesPreview

@Preview(
    name = "1-Phone-ExtraSmall-Portrait",
    device = "id:2.7in QVGA",
    showBackground = true,
    apiLevel = 33,
)
@Preview(
    name = "3-Phone-Small-Portrait",
    device = "id:small_phone",
    showBackground = true,
    apiLevel = 33,
)
@Preview(
    name = "5-Phone-Foldable-Portrait",
    device = "id:6.7in Foldable",
    showBackground = true,
    apiLevel = 33,
)
@Preview(
    name = "7-Tablet-Portrait",
    device = "spec:parent=pixel_c,orientation=portrait",
    showBackground = true,
    apiLevel = 33,
)
annotation class PortraitScreensPreview

@Preview(
    name = "2-Phone-ExtraSmall-Landscape",
    device = "spec:parent=2.7in QVGA,orientation=landscape",
    showBackground = true,
    apiLevel = 33,
)
@Preview(
    name = "4-Phone-Small-Landscape",
    device = "spec:parent=small_phone,orientation=landscape",
    showBackground = true,
    apiLevel = 33,
)
@Preview(
    name = "6-Phone-Foldable-Landscape",
    device = "spec:parent=6.7in Foldable,orientation=landscape",
    showBackground = true,
    apiLevel = 33,
)
@Preview(
    name = "8-Tablet-Landscape",
    device = "id:pixel_c",
    showBackground = true,
    apiLevel = 33,
)
@Preview(
    name = "9-Desktop",
    device = "spec:id=reference_desktop,shape=Normal,width=1920,height=1080,unit=dp,dpi=160",
    showBackground = true,
    apiLevel = 33,
)
annotation class LandscapeScreensPreview

@PortraitScreensPreview
@LandscapeScreensPreview
annotation class ScreensPreview

@LocalesPreview
@ThemesPreview
@ScreensPreview
annotation class FullPreview

@SuppressLint("ComposableNaming")
@Composable
fun preview(content: @Composable () -> Unit) = KmpPlaygroundTheme {
    Surface(Modifier.fillMaxSize(), content = content)
}
