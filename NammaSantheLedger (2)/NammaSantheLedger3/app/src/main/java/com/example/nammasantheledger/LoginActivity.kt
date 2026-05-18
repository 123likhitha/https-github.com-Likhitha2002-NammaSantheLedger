package com.example.nammasantheledger

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = prefs.getBoolean("isLoggedIn", false)

        // Skip login if already logged in
        if (isLoggedIn) {
            goToMain()
            return
        }

        setContentView(R.layout.activity_login)

        // Views
        val pinInput   = findViewById<EditText>(R.id.pinInput)
        val loginBtn   = findViewById<Button>(R.id.loginButton)
        val titleText  = findViewById<TextView>(R.id.loginTitle)
        val subTitle   = findViewById<TextView>(R.id.loginSubtitle)
        val logoImage  = findViewById<TextView>(R.id.logoImage)

        // Animation
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)

        logoImage.startAnimation(fadeIn)
        titleText.startAnimation(fadeIn)
        subTitle.startAnimation(fadeIn)
        pinInput.startAnimation(fadeIn)
        loginBtn.startAnimation(fadeIn)

        val hasPin = prefs.getString("pin", null) != null

        if (!hasPin) {

            titleText.text = "Create Secure PIN"

            subTitle.text =
                "Protect your Santhe Ledger using a 4-digit PIN"

            loginBtn.text = "Create PIN"

        } else {

            titleText.text = "Welcome Back 👋"

            subTitle.text =
                "Enter your secure PIN to continue"

            loginBtn.text = "Login"
        }

        loginBtn.setOnClickListener {

            val pin = pinInput.text.toString().trim()

            if (pin.length != 4) {

                Toast.makeText(
                    this,
                    "Enter exactly 4 digits",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (!hasPin) {

                prefs.edit().putString("pin", pin).apply()

                Toast.makeText(
                    this,
                    "✅ PIN created successfully!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val savedPin = prefs.getString("pin", "")

                if (pin != savedPin) {

                    Toast.makeText(
                        this,
                        "❌ Wrong PIN!",
                        Toast.LENGTH_SHORT
                    ).show()

                    pinInput.setText("")

                    return@setOnClickListener
                }
            }

            prefs.edit().putBoolean("isLoggedIn", true).apply()

            Toast.makeText(
                this,
                "✨ Login Successful",
                Toast.LENGTH_SHORT
            ).show()

            goToMain()
        }
    }

    private fun goToMain() {

        startActivity(
            Intent(this, MainActivity::class.java)
        )

        finish()
    }
}