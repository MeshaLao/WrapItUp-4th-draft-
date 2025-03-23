package com.android.wrapitup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var savedUsername: String? = null
        var savedPassword: String? = null

        val editTextUsername = findViewById<EditText>(R.id.usernameEditText)
        val editTextPassword = findViewById<EditText>(R.id.passwordEditText)
        val buttonLogin = findViewById<Button>(R.id.loginButton)
        val buttonSignup = findViewById<Button>(R.id.signupButton)

        intent?.let {
            it.getStringExtra("username")?.let { username ->
                savedUsername = username
                editTextUsername.setText(username)
            }
            it.getStringExtra("password")?.let { password ->
                savedPassword = password
                editTextPassword.setText(password)
            }
        }

        buttonSignup.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        buttonLogin.setOnClickListener {
            if (editTextUsername.text.toString().isEmpty() || editTextPassword.text.toString().isEmpty()) {
                Toast.makeText(this, "Username or Password cannot be empty.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (editTextUsername.text.toString() != savedUsername || editTextPassword.text.toString() != savedPassword) {
                Toast.makeText(this, "Username and password don't match", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            startActivity(
                Intent(this, LandingActivity::class.java).apply {
                    putExtra("username", savedUsername)
                    putExtra("password", savedPassword)
                }
            )
        }
    }
}
