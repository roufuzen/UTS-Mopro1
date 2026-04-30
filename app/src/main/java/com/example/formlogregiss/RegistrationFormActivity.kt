package com.example.formlogregiss

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.formlogregiss.databinding.ActivityRegistrationFormBinding

class RegistrationFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinner()
        setupRealTimeValidation()

        binding.btnSubmit.setOnClickListener {
            if (validateAll()) {
                showConfirmationDialog()
            }
        }
    }

    private fun setupSpinner() {
        val seminars = arrayOf(
            "Android Development 2026",
            "UI/UX Design Masterclass",
            "AI & Machine Learning",
            "Cyber Security Essentials",
            "Cloud Computing Fundamentals"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, seminars)
        binding.spinnerSeminar.adapter = adapter
    }

    private fun setupRealTimeValidation() {
        binding.etNama.addTextChangedListener(SimpleTextWatcher { validateNama() })
        binding.etEmail.addTextChangedListener(SimpleTextWatcher { validateEmail() })
        binding.etPhone.addTextChangedListener(SimpleTextWatcher { validatePhone() })
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Konfirmasi")
        builder.setMessage("Apakah data yang Anda isi sudah benar?")
        
        builder.setPositiveButton("Ya") { _, _ ->
            navigateToResult()
        }
        
        builder.setNegativeButton("Tidak") { dialog, _ ->
            dialog.dismiss()
        }
        
        val dialog = builder.create()
        dialog.show()
    }

    private fun navigateToResult() {
        val selectedGenderId = binding.rgGender.checkedRadioButtonId
        if (selectedGenderId == -1) return
        
        val gender = findViewById<RadioButton>(selectedGenderId).text.toString()
        val seminar = binding.spinnerSeminar.selectedItem.toString()

        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra("EXTRA_NAMA", binding.etNama.text.toString())
            putExtra("EXTRA_EMAIL", binding.etEmail.text.toString())
            putExtra("EXTRA_PHONE", binding.etPhone.text.toString())
            putExtra("EXTRA_GENDER", gender)
            putExtra("EXTRA_SEMINAR", seminar)
        }
        startActivity(intent)
        finish()
    }

    private fun validateNama(): Boolean {
        val nama = binding.etNama.text.toString().trim()
        return if (nama.isEmpty()) {
            binding.tilNama.error = "Nama wajib diisi"
            false
        } else {
            binding.tilNama.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        val email = binding.etEmail.text.toString().trim()
        return when {
            email.isEmpty() -> {
                binding.tilEmail.error = "Email wajib diisi"
                false
            }
            "@" !in email -> {
                binding.tilEmail.error = "Email tidak valid (harus mengandung @)"
                false
            }
            else -> {
                binding.tilEmail.error = null
                true
            }
        }
    }

    private fun validatePhone(): Boolean {
        val phone = binding.etPhone.text.toString().trim()
        return when {
            phone.isEmpty() -> {
                binding.tilPhone.error = "Nomor HP wajib diisi"
                false
            }
            !phone.all { it.isDigit() } -> {
                binding.tilPhone.error = "Hanya boleh angka"
                false
            }
            !phone.startsWith("08") -> {
                binding.tilPhone.error = "Harus diawali dengan 08"
                false
            }
            phone.length !in 10..13 -> {
                binding.tilPhone.error = "Panjang 10-13 digit"
                false
            }
            else -> {
                binding.tilPhone.error = null
                true
            }
        }
    }

    private fun validateGender(): Boolean {
        return if (binding.rgGender.checkedRadioButtonId == -1) {
            Toast.makeText(this, "Pilih Jenis Kelamin", Toast.LENGTH_SHORT).show()
            false
        } else true
    }

    private fun validateCheckbox(): Boolean {
        return if (!binding.cbAgreement.isChecked) {
            Toast.makeText(this, "Centang persetujuan untuk melanjutkan", Toast.LENGTH_SHORT).show()
            false
        } else true
    }

    private fun validateAll(): Boolean {
        val isNamaValid = validateNama()
        val isEmailValid = validateEmail()
        val isPhoneValid = validatePhone()
        val isGenderValid = validateGender()
        val isCheckboxValid = validateCheckbox()

        return isNamaValid && isEmailValid && isPhoneValid && isGenderValid && isCheckboxValid
    }

    class SimpleTextWatcher(val afterChanged: () -> Unit) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) { afterChanged() }
    }
}