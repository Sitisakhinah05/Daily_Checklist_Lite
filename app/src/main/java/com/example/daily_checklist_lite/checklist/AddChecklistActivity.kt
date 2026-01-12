package com.example.daily_checklist_lite.checklist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daily_checklist_lite.databinding.ActivityAddChecklistBinding
import com.example.daily_checklist_lite.model.Checklist
import com.example.daily_checklist_lite.notification.NotificationHelper
import com.example.daily_checklist_lite.utils.FirebaseUtils

class AddChecklistActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddChecklistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddChecklistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            saveData()
        }
    }

    private fun saveData() {
        val id = FirebaseUtils.database.child("checklist").push().key!!
        val checklist = Checklist(
            id,
            binding.etTitle.text.toString(),
            binding.etDesc.text.toString(),
            false
        )

        FirebaseUtils.database.child("checklist").child(id).setValue(checklist)
        NotificationHelper(this).showNotification("Checklist berhasil ditambahkan")
        finish()
    }
}
