package com.example.daily_checklist_lite.ui.home

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.daily_checklist_lite.R
// PASTIKAN IMPORT DI BAWAH INI ADA
import com.example.daily_checklist_lite.model.ChecklistViewModel
import com.example.daily_checklist_lite.model.Checklist

class HomeFragment : Fragment(R.layout.fragment_home) {

    // 1. Menggunakan activityViewModels agar sinkron dengan ChecklistFragment
    private val viewModel: ChecklistViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Pastikan ID di fragment_home.xml adalah tvTotalChecklist
        val tvTotalChecklist = view.findViewById<TextView>(R.id.tvTotalChecklist)

        // 2. Mengamati perubahan data di ViewModel
        viewModel.checklistItems.observe(viewLifecycleOwner) { items ->
            // items adalah List<Checklist>
            val totalCount = items.size

            // Menghitung jumlah item yang statusnya true (dicentang)
            val completedCount = items.count { it.status }

            // 3. Update tampilan teks secara otomatis
            tvTotalChecklist.text = "Total Checklist: $completedCount dari $totalCount"
        }
    }
}