package com.kirvigen.templateapplication.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Game(
    val _id: Int,
    val box: Box,
    val giantbomb_id: Int,
    val logo: Logo,
    val name: String
)