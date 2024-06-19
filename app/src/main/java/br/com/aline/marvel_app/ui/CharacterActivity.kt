package br.com.aline.marvel_app.ui


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.aline.marvel_app.R
import br.com.aline.marvel_app.adapters.CharacterListAdapter
import br.com.aline.marvel_app.databinding.ActivityCharacterBinding
import br.com.aline.marvel_app.domain.model.Character
import br.com.aline.marvel_app.uiCharacterList.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharacterBinding
    var flag = 3
    var paginatedValue = 0

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterListAdapter
    private lateinit var layoutManager: GridLayoutManager
    private val viewModel: CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = ContextCompat.getColor(this, R.color.red1)

        recyclerView = binding.charactersRecyclerView
        layoutManager = GridLayoutManager(this, 2)
        recyclerViewCharacters()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (layoutManager.findLastVisibleItemPosition() == layoutManager.itemCount - 1) {
                    paginatedValue += 20
                    viewModel.getAllCharactersData(paginatedValue)
                    callApi()
                }
            }
        })
    }

    private fun callApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flag) {
                viewModel._marvelValue.collect {
                    when {
                        it.isLoading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }

                        it.error.isNotBlank() -> {
                            binding.progressBar.visibility = View.GONE
                            flag = 0
                            Toast.makeText(this@CharacterActivity, it.error, Toast.LENGTH_LONG).show()
                        }

                        it.characterList.isNotEmpty() -> {
                            binding.progressBar.visibility = View.GONE
                            flag = 0
                            adapter.setData(it.characterList as ArrayList<Character>)
                        }
                    }
                    delay(1000)
                }
            }

        }
    }

    private fun recyclerViewCharacters() {
        adapter = CharacterListAdapter(this, ArrayList())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
