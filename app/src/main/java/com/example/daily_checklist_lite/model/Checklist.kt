package com.example.daily_checklist_lite.model

data class Checklist(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var status: Boolean = false
)
