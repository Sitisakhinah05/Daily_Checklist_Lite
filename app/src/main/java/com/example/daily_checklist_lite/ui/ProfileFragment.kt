package com.example.daily_checklist_lite.ui

import android.content.Intent // Tambahkan ini
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button // Tambahkan ini
import com.example.daily_checklist_lite.R
import com.example.daily_checklist_lite.auth.LoginActivity // Sesuaikan dengan folder login Anda

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    // --- TAMBAHKAN BAGIAN INI ---
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inisialisasi tombol Logout berdasarkan ID di layout fragment_profile.xml
        val btnLogout = view.findViewById<Button>(R.id.btnLogout)

        btnLogout.setOnClickListener {
            // Pindah ke LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)

            // Menghapus history agar tidak bisa kembali (Back) ke Profile lagi
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)

            // Menutup activity saat ini (MainActivity)
            activity?.finish()
        }
    }
    // ----------------------------
}