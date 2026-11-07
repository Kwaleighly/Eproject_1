package com.example.eproject_1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        auth = FirebaseAuth.getInstance()


        if (auth.currentUser == null) {
            goToLogin()
            return
        }

        setContentView(R.layout.activity_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(bars.left, bars.top, bars.right, bars.bottom)
            insets
        }

        Toast.makeText(this, "Logged in as ${auth.currentUser?.email}", Toast.LENGTH_SHORT).show()

        findViewById<View>(R.id.servicesCard).setOnClickListener {
            Toast.makeText(this, "Services selected!", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.informationCard).setOnClickListener {
            Toast.makeText(this, "Information selected!", Toast.LENGTH_SHORT).show()
        }
        findViewById<View>(R.id.learnMoreCard).setOnClickListener {
            Toast.makeText(this, "Learn more about E-Platform!", Toast.LENGTH_SHORT).show()
        }

        findViewById<View>(R.id.btnLogout).setOnClickListener {
            auth.signOut()
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            goToLogin()
        }
    }

    private fun goToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}