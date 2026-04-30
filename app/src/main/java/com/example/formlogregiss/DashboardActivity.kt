package com.example.formlogregiss

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formlogregiss.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangkap data dari Intent
        val namaUser = intent.getStringExtra("NAMA_KIRIMAN")

        // Menampilkan sapaan user
        binding.tvUserGreeting.text = "Halo, ${namaUser ?: "User"}!"

        // Navigasi ke Form Pendaftaran
        val intentToForm = Intent(this, RegistrationFormActivity::class.java)
        
        binding.btnDaftar1.setOnClickListener { startActivity(intentToForm) }
        binding.btnDaftar2.setOnClickListener { startActivity(intentToForm) }
        binding.btnDaftar3.setOnClickListener { startActivity(intentToForm) }
    }
}