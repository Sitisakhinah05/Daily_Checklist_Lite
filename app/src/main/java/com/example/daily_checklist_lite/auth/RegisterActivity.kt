package com.example.daily_checklist_lite.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.daily_checklist_lite.databinding.ActivityRegisterBinding
import com.example.daily_checklist_lite.utils.FirebaseUtils

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        FirebaseUtils.auth.createUserWithEmailAndPassword(
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString()
        ).addOnSuccessListener {
            finish()
        }
    }
}
