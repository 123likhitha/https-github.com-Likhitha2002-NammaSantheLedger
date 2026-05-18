package com.example.nammasantheledger

import android.content.Context
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_profile)

        supportActionBar?.title = "🏪 My Shop Profile"

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val prefs =
            getSharedPreferences(
                "ProfilePrefs",
                Context.MODE_PRIVATE
            )

        val shopName =
            findViewById<EditText>(R.id.shopName)

        val ownerName =
            findViewById<EditText>(R.id.ownerName)

        val ownerPhone =
            findViewById<EditText>(R.id.ownerPhone)

        val ownerAddress =
            findViewById<EditText>(R.id.ownerAddress)

        val saveBtn =
            findViewById<Button>(R.id.saveProfileBtn)

        val profileImage =
            findViewById<TextView>(R.id.profileImage)

        val titleText =
            findViewById<TextView>(R.id.profileTitle)

        // Animation
        val fadeIn =
            AnimationUtils.loadAnimation(
                this,
                android.R.anim.fade_in
            )

        profileImage.startAnimation(fadeIn)

        titleText.startAnimation(fadeIn)

        shopName.startAnimation(fadeIn)

        ownerName.startAnimation(fadeIn)

        ownerPhone.startAnimation(fadeIn)

        ownerAddress.startAnimation(fadeIn)

        saveBtn.startAnimation(fadeIn)

        // Load saved data
        shopName.setText(
            prefs.getString("shopName", "")
        )

        ownerName.setText(
            prefs.getString("ownerName", "")
        )

        ownerPhone.setText(
            prefs.getString("ownerPhone", "")
        )

        ownerAddress.setText(
            prefs.getString("ownerAddress", "")
        )

        saveBtn.setOnClickListener {

            if (
                shopName.text.isBlank() ||
                ownerName.text.isBlank()
            ) {

                Toast.makeText(
                    this,
                    "Shop name and owner name are required",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            prefs.edit()

                .putString(
                    "shopName",
                    shopName.text.toString().trim()
                )

                .putString(
                    "ownerName",
                    ownerName.text.toString().trim()
                )

                .putString(
                    "ownerPhone",
                    ownerPhone.text.toString().trim()
                )

                .putString(
                    "ownerAddress",
                    ownerAddress.text.toString().trim()
                )

                .apply()

            Toast.makeText(
                this,
                "✅ Shop Profile Saved!",
                Toast.LENGTH_SHORT
            ).show()

            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {

        finish()

        return true
    }
}
