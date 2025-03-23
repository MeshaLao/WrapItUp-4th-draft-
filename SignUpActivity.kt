package com.android.wrapitup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUpActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        var savedUsername: String? = null
        var savedPassword: String? = null

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val signupButton = findViewById<Button>(R.id.signupButton)

        intent?.let {
            it.getStringExtra("username")?.let { username ->
                savedUsername = username
                usernameEditText.setText(username)
            }
            it.getStringExtra("password")?.let { password ->
                savedPassword = password
                passwordEditText.setText(password)
                confirmPasswordEditText.setText(password)
            }
        }

        signupButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Toast.makeText(this, "Sign Up Successful!", Toast.LENGTH_SHORT).show()
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra("username", username)
                putExtra("password", password)
            })
            finish()
        }
    }
}
