package com.example.formlogregiss

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formlogregiss.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari Intent
        val nama = intent.getStringExtra("EXTRA_NAMA")
        val email = intent.getStringExtra("EXTRA_EMAIL")
        val phone = intent.getStringExtra("EXTRA_PHONE")
        val gender = intent.getStringExtra("EXTRA_GENDER")
        val seminar = intent.getStringExtra("EXTRA_SEMINAR")

        // Tampilkan data
        binding.tvResNama.text = "Nama: $nama"
        binding.tvResEmail.text = "Email: $email"
        binding.tvResPhone.text = "Nomor HP: $phone"
        binding.tvResGender.text = "Jenis Kelamin: $gender"
        binding.tvResSeminar.text = "Seminar: $seminar"

        binding.btnBackToHome.setOnClickListener {
            finish()
        }
    }
}