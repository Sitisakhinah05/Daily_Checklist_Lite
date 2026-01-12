package com.example.daily_checklist_lite.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.daily_checklist_lite.R
import com.example.daily_checklist_lite.checklist.EditChecklistActivity
import com.example.daily_checklist_lite.model.Checklist
import com.example.daily_checklist_lite.utils.FirebaseUtils

class ChecklistAdapter(
    private val context: Context,
    private val list: List<Checklist>
) : BaseAdapter() {

    override fun getCount(): Int = list.size
    override fun getItem(position: Int): Any = list[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_checklist, parent, false)

        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)

        // Sesuaikan id 'checkboxTask' dengan yang ada di item_checklist.xml
        val checkBox: CheckBox = view.findViewById(R.id.checkboxTask)
        val btnEdit: ImageView = view.findViewById(R.id.btnEdit)
        val btnDelete: ImageView = view.findViewById(R.id.btnDelete)

        val item = list[position]

        tvTitle.text = item.title
        tvStatus.text = if (item.status) "Selesai" else "Belum Selesai"

        // Logika Checklist (Centang)
        checkBox.setOnCheckedChangeListener(null)
        checkBox.isChecked = item.status
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            item.status = isChecked
            // Mengirim perubahan status ke Firebase berdasarkan ID unik
            FirebaseUtils.database.child("checklist").child(item.id).child("status")
                .setValue(isChecked)
        }

        // Logika Edit Data
        btnEdit.setOnClickListener {
            val intent = Intent(context, EditChecklistActivity::class.java)
            intent.putExtra("ID", item.id)
            intent.putExtra("TITLE", item.title)
            intent.putExtra("DESC", item.description)
            context.startActivity(intent)
        }

        // Logika Hapus Data
        btnDelete.setOnClickListener {
            val builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("Hapus Tugas")
            builder.setMessage("Yakin ingin menghapus ${item.title}?")
            builder.setPositiveButton("Hapus") { _, _ ->
                // Menghapus data spesifik di Firebase menggunakan item.id
                FirebaseUtils.database.child("checklist").child(item.id).removeValue()
                    .addOnSuccessListener {
                        Toast.makeText(context, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                    }
            }
            builder.setNegativeButton("Batal", null)
            builder.show()
        }

        return view
    }
}