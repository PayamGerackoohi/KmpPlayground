package com.payam1991gr.kmp.playground.data.icon

import androidx.compose.material.icons.Icons.AutoMirrored.Filled as AutoMirroredIcon
import androidx.compose.material.icons.Icons.Default as Icon
import androidx.compose.material.icons.automirrored.filled.*
import androidx.compose.material.icons.filled.*
import com.payam1991gr.kmp.playground.data.model.IconData
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

class IconsImpl : Icons {
    override fun core() = persistentListOf(
        IconData("AccountBox", Icon.AccountBox),
        IconData("AccountCircle", Icon.AccountCircle),
        IconData("AddCircle", Icon.AddCircle),
        IconData("Add", Icon.Add),
        IconData("ArrowBack", AutoMirroredIcon.ArrowBack),
        IconData("ArrowDropDown", Icon.ArrowDropDown),
        IconData("ArrowForward", AutoMirroredIcon.ArrowForward),
        IconData("Build", Icon.Build),
        IconData("Call", Icon.Call),
        IconData("CheckCircle", Icon.CheckCircle),
        IconData("Check", Icon.Check),
        IconData("Clear", Icon.Clear),
        IconData("Close", Icon.Close),
        IconData("Create", Icon.Create),
        IconData("DateRange", Icon.DateRange),
        IconData("Delete", Icon.Delete),
        IconData("Done", Icon.Done),
        IconData("Edit", Icon.Edit),
        IconData("Email", Icon.Email),
        IconData("ExitToApp", AutoMirroredIcon.ExitToApp),
        IconData("Face", Icon.Face),
        IconData("FavoriteBorder", Icon.FavoriteBorder),
        IconData("Favorite", Icon.Favorite),
        IconData("Home", Icon.Home),
        IconData("Info", Icon.Info),
        IconData("KeyboardArrowDown", Icon.KeyboardArrowDown),
        IconData("KeyboardArrowLeft", AutoMirroredIcon.KeyboardArrowLeft),
        IconData("KeyboardArrowRight", AutoMirroredIcon.KeyboardArrowRight),
        IconData("KeyboardArrowUp", Icon.KeyboardArrowUp),
        IconData("List", AutoMirroredIcon.List),
        IconData("LocationOn", Icon.LocationOn),
        IconData("Lock", Icon.Lock),
        IconData("MailOutline", Icon.MailOutline),
        IconData("Menu", Icon.Menu),
        IconData("MoreVert", Icon.MoreVert),
        IconData("Notifications", Icon.Notifications),
        IconData("Person", Icon.Person),
        IconData("Phone", Icon.Phone),
        IconData("Place", Icon.Place),
        IconData("PlayArrow", Icon.PlayArrow),
        IconData("Refresh", Icon.Refresh),
        IconData("Search", Icon.Search),
        IconData("Send", AutoMirroredIcon.Send),
        IconData("Settings", Icon.Settings),
        IconData("Share", Icon.Share),
        IconData("ShoppingCart", Icon.ShoppingCart),
        IconData("Star", Icon.Star),
        IconData("ThumbUp", Icon.ThumbUp),
        IconData("Warning", Icon.Warning),
    )

    override suspend fun extended() = listOf(
        extended1(),
        extended2(),
        extended3(),
        extended4(),
        extended5(),
    ).flatten().toPersistentList()
}
