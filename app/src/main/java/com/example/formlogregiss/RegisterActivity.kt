package com.example.formlogregiss

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.formlogregiss.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            val username = binding.etRegUsername.text.toString()
            val nama = binding.etRegNama.text.toString()
            val email = binding.etRegEmail.text.toString()
            val password = binding.etRegPassword.text.toString()
            val confirmPassword = binding.etRegConfirmPassword.text.toString()

            if (username.isNotEmpty() && nama.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                if (password == confirmPassword) {
                    Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()
                    finish() // Kembali ke Login
                } else {
                    Toast.makeText(this, "Password tidak cocok!", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Tolong lengkapi semua data!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvLogin.setOnClickListener {
            finish() // Kembali ke Login
        }
    }
}