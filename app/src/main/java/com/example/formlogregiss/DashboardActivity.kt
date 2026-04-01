package com.example.formlogregiss

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

        // Menampilkan pesan selamat datang
        binding.tvUserDisplayName.text = namaUser?.uppercase() ?: "UNKNOWN USER"
    }
}