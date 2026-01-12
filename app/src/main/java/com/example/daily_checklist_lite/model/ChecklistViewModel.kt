package com.example.daily_checklist_lite.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChecklistViewModel : ViewModel() {
    // 1. Variabel internal untuk menyimpan list tugas (Private)
    private val _checklistItems = MutableLiveData<List<Checklist>>()

    // 2. Variabel publik yang bisa dipantau (Observed) oleh Fragment
    val checklistItems: LiveData<List<Checklist>> = _checklistItems

    // 3. Fungsi untuk memperbarui data dari ChecklistFragment
    fun updateChecklist(newList: List<Checklist>) {
        _checklistItems.value = newList
    }
}