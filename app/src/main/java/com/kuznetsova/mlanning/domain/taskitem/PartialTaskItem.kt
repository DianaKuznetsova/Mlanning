package com.kuznetsova.mlanning.domain.taskitem

import com.kuznetsova.mlanning.domain.Day

data class PartialTaskItem(
    val description: String,
    val day: Day
)