package br.com.aline.marvel_app.ui


import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import br.com.aline.marvel_app.R
import br.com.aline.marvel_app.databinding.ActivityCharacterBinding
import br.com.aline.marvel_app.uiCharacterList.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterBinding

    private val viewModel: CharacterViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.red1)
    }
}
