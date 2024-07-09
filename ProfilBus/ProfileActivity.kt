package com.example.busapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.busapp.data.AppDatabase
import com.example.busapp.data.User

class ProfileActivity : AppCompatActivity() {

    private lateinit var databaseHelper: AppDatabase
    private val REQUEST_PICK_PHOTO = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val username = intent.getStringExtra("username")
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val ivProfileImage = findViewById<ImageView>(R.id.ivProfileImage)
        val tvName = findViewById<TextView>(R.id.tvName)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvAge = findViewById<TextView>(R.id.tvAge)
        val tvAddress = findViewById<TextView>(R.id.tvAddress)

        val db = AppDatabase.getDatabase(this)
        val userDao = db.userDao()
        val user = userDao.getUserByName(username!!)

        user?.let {
            tvGreeting.text = "Halo, ${it.nama}"
            tvName.text = "Nama: ${it.nama}"
            tvEmail.text = "Email: ${it.email}"
            tvAge.text = "Umur: ${it.umur}"
            tvAddress.text = "Alamat: ${it.address}"
            // Load profile image from database or set default image
        }

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        val btnChangeImage = findViewById<ImageButton>(R.id.btnChangeImage)
        val btnEdit = findViewById<Button>(R.id.btnEdit)

        btnBack.setOnClickListener {
            finish()
        }

        btnEdit.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            intent.putExtra("username", username)
            startActivity(intent)
        }

        btnChangeImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, REQUEST_PICK_PHOTO)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_PICK_PHOTO -> {
                    val selectedImage: Uri? = data?.data
                    val ivProfileImage = findViewById<ImageView>(R.id.ivProfileImage)
                    ivProfileImage.setImageURI(selectedImage)
                    // Save selectedImage URI to database
                }
            }
        }
    }
}
