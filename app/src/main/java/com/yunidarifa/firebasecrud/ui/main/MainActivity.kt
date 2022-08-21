package com.yunidarifa.firebasecrud.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import com.yunidarifa.firebasecrud.R
import com.yunidarifa.firebasecrud.data.User

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etName: TextInputEditText
    private lateinit var etAddress: TextInputEditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.et_name)
        etAddress = findViewById(R.id.et_address)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        saveData()
    }

    private fun saveData() {
        val name = etName.text.toString().trim()
        val address = etAddress.text.toString().trim()

        if (name.isEmpty()) {
            etName.error = "Please fill empty name field!"
            return
        }
        if (address.isEmpty()) {
            etAddress.error = "Please fill empty address field!"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("user")
        val userId = ref.push().key

        val user = User(userId, name, address)

        if (userId != null) {
            ref.child(userId).setValue(user).addOnCompleteListener {
                Toast.makeText(applicationContext, "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }


}