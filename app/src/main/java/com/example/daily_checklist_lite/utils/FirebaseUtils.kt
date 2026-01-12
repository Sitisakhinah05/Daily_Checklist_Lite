package com.example.daily_checklist_lite.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseUtils {
    val auth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    val database = FirebaseDatabase.getInstance().reference
}
