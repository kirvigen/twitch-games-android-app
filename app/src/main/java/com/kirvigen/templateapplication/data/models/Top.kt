package com.kirvigen.templateapplication.data.models

import androidx.room.Entity

@Entity
data class Top(
    val channels: Int,
    val game: Game,
    val viewers: Int
)