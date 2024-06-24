package br.com.aline.marvel_app.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import br.com.aline.marvel_app.R
import br.com.aline.marvel_app.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setupSplash()
        supportActionBar?.hide()
    }

    private fun setupSplash() = with(binding) {
        tvSplash.alpha = 0f
        tvSplash.animate().setDuration(1000).alpha(1f).withEndAction{
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim. fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}