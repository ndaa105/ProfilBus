package com.example.busapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.busapp.database.DatabaseHelper

class EditProfileActivity : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etAge = findViewById<EditText>(R.id.etAge)
        val etAddress = findViewById<EditText>(R.id.etAddress)
        val btnSave = findViewById<Button>(R.id.btnSave)

        databaseHelper = DatabaseHelper(this)

        val user = databaseHelper.getUser() // Fungsi ini harus diimplementasikan di DatabaseHelper untuk mengambil data user dari database

        user?.let {
            etName.setText(it.name)
            etEmail.setText(it.email)
            etAge.setText(it.age.toString())
            etAddress.setText(it.address)
        }

        btnSave.setOnClickListener {
            // Save the updated user information to the database
            val updatedUser = User(
                name = etName.text.toString(),
                email = etEmail.text.toString(),
                age = etAge.text.toString().toInt(),
                address = etAddress.text.toString()
            )
            databaseHelper.updateUser(updatedUser) 

            finish() // Close the edit profile activity
        }
    }
}
