package br.com.aline.marvel_app.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import br.com.aline.marvel_app.R
import br.com.aline.marvel_app.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        window.statusBarColor = ContextCompat.getColor(this, R.color.red1)

        initViews(binding)

    }

    private fun initViews(binding: ActivityMainBinding) {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.apply {
            setupWithNavController(navController)
            setOnNavigationItemReselectedListener { }
        }
    }
}