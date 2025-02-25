package com.payam1991gr.kmp.playground.data.icon

import com.payam1991gr.kmp.playground.data.model.IconData
import kotlinx.collections.immutable.PersistentList

interface Icons {
    fun core(): PersistentList<IconData>
    suspend fun extended(): PersistentList<IconData>
}
