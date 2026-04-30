package com.example.formlogregiss

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.formlogregiss.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRealTimeValidation()

        binding.btnRegister.setOnClickListener {
            if (validateAll()) {
                Toast.makeText(this, "Registrasi Berhasil!", Toast.LENGTH_SHORT).show()
                finish() // Kembali ke Login
            }
        }

        binding.tvLogin.setOnClickListener {
            finish() // Kembali ke Login
        }
    }

    private fun setupRealTimeValidation() {
        binding.etRegUsername.addTextChangedListener(SimpleTextWatcher { validateUsername() })
        binding.etRegNama.addTextChangedListener(SimpleTextWatcher { validateNama() })
        binding.etRegEmail.addTextChangedListener(SimpleTextWatcher { validateEmail() })
        binding.etRegPassword.addTextChangedListener(SimpleTextWatcher { validatePassword() })
        binding.etRegConfirmPassword.addTextChangedListener(SimpleTextWatcher { validateConfirmPassword() })
    }

    private fun validateUsername(): Boolean {
        val username = binding.etRegUsername.text.toString().trim()
        return if (username.isEmpty()) {
            binding.tilRegUsername.error = "Username harus diisi"
            false
        } else {
            binding.tilRegUsername.error = null
            true
        }
    }

    private fun validateNama(): Boolean {
        val nama = binding.etRegNama.text.toString().trim()
        return if (nama.isEmpty()) {
            binding.tilRegNama.error = "Nama lengkap harus diisi"
            false
        } else {
            binding.tilRegNama.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val email = binding.etRegEmail.text.toString().trim()
        return when {
            email.isEmpty() -> {
                binding.tilRegEmail.error = "Email harus diisi"
                false
            }
            "@" !in email -> {
                binding.tilRegEmail.error = "Email tidak valid (harus mengandung @)"
                false
            }
            else -> {
                binding.tilRegEmail.error = null
                true
            }
        }
    }

    private fun validatePassword(): Boolean {
        val password = binding.etRegPassword.text.toString()
        return if (password.isEmpty()) {
            binding.tilRegPassword.error = "Password harus diisi"
            false
        } else if (password.length < 6) {
            binding.tilRegPassword.error = "Password minimal 6 karakter"
            false
        } else {
            binding.tilRegPassword.error = null
            true
        }
    }

    private fun validateConfirmPassword(): Boolean {
        val password = binding.etRegPassword.text.toString()
        val confirmPassword = binding.etRegConfirmPassword.text.toString()
        return if (confirmPassword != password) {
            binding.tilRegConfirmPassword.error = "Password tidak cocok"
            false
        } else {
            binding.tilRegConfirmPassword.error = null
            true
        }
    }

    private fun validateAll(): Boolean {
        val isUsernameValid = validateUsername()
        val isNamaValid = validateNama()
        val isEmailValid = validateEmail()
        val isPasswordValid = validatePassword()
        val isConfirmValid = validateConfirmPassword()

        return isUsernameValid && isNamaValid && isEmailValid && isPasswordValid && isConfirmValid
    }

    class SimpleTextWatcher(val afterChanged: () -> Unit) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { afterChanged() }
    }
}