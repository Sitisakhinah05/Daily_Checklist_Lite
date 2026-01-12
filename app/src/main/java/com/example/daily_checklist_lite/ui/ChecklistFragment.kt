package com.example.daily_checklist_lite.ui.checklist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.daily_checklist_lite.R
import com.example.daily_checklist_lite.adapter.ChecklistAdapter
import com.example.daily_checklist_lite.databinding.FragmentChecklistBinding
import com.example.daily_checklist_lite.model.Checklist
import com.example.daily_checklist_lite.model.ChecklistViewModel
import com.example.daily_checklist_lite.utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class ChecklistFragment : Fragment(R.layout.fragment_checklist) {

    private lateinit var binding: FragmentChecklistBinding

    // 1. Deklarasi ViewModel agar berbagi data dengan HomeFragment
    private val viewModel: ChecklistViewModel by activityViewModels()
    private val list = mutableListOf<Checklist>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChecklistBinding.bind(view)

        FirebaseUtils.database.child("checklist")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    list.clear()
                    for (data in snapshot.children) {
                        val item = data.getValue(Checklist::class.java)
                        item?.let {
                            it.id = data.key ?: "" // Mengambil ID dari Key Firebase
                            list.add(it)
                        }
                    }

                    // 2. KIRIM DATA KE VIEWMODEL (Penting agar HomeFragment tahu)
                    viewModel.updateChecklist(list)

                    binding.listChecklist.adapter = ChecklistAdapter(requireContext(), list)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
    }
}