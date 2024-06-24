package br.com.aline.marvel_app.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.aline.marvel_app.R
import br.com.aline.marvel_app.databinding.FragmentSearchCharacterBinding
import br.com.aline.marvel_app.ui.adapters.CharacterAdapter
import br.com.aline.marvel_app.ui.base.BaseFragment
import br.com.aline.marvel_app.ui.state.ResourceState
import br.com.aline.marvel_app.util.Constants.DEFAULT_QUERY
import br.com.aline.marvel_app.util.Constants.LAST_SEARCH_QUERY
import br.com.aline.marvel_app.util.hide
import br.com.aline.marvel_app.util.show
import br.com.aline.marvel_app.util.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SearchCharacterFragment :
    BaseFragment<FragmentSearchCharacterBinding, SearchCharacterViewModel>() {

    override val viewModel: SearchCharacterViewModel by viewModels()
    private val characterAdapter by lazy { CharacterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        clickAdapter()

        binding.imageSearch.visibility = View.VISIBLE

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        searchInit(query)
        collectObserver()


    }

    private fun collectObserver() = lifecycleScope.launch {
        viewModel.searchCharacter.collect { result ->
            when (result) {
                is ResourceState.Success -> {
                    binding.progressbarSearch.hide()
                    binding.imageSearch.visibility = View.GONE
                    result.data?.let {
                        characterAdapter.characters = it.data.results.toList()
                        binding.imageSearch.visibility = View.GONE
                    }
                }

                is ResourceState.Error -> {
                    binding.progressbarSearch.hide()
                    binding.imageSearch.visibility = View.GONE
                    result.message?.let { message ->
                        Timber.tag("SearchCharacterFragment").e("Error -> ${message}")
                        toast(getString(R.string.an_error_occurred))
                    }
                }

                is ResourceState.Loading -> {
                    binding.progressbarSearch.show()
                    binding.imageSearch.visibility = View.GONE
                }

                else -> {}
            }
        }
    }

    private fun searchInit(query: String) = with(binding) {
        edSearchCharacter.setText(query)
        edSearchCharacter.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateCharacterList()
                binding.imageSearch.visibility = View.GONE
                true
            } else {
                false
            }
        }
        edSearchCharacter.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateCharacterList()
                binding.imageSearch.visibility = View.GONE
                true
            } else {
                false
            }
        }
    }

    private fun updateCharacterList() = with(binding) {
        edSearchCharacter.editableText.trim().let {
            if (it.isNotEmpty()) {
                searchQuery(it.toString())
                binding.imageSearch.visibility = View.GONE
            }
        }
    }

    private fun searchQuery(query: String) {
        viewModel.fetch(query)
        binding.imageSearch.visibility = View.GONE
    }


    private fun clickAdapter() {
        characterAdapter.setOnClickListener { characterModel ->
            val action = SearchCharacterFragmentDirections
                .actionSearchCharacterFragmentToDetailsCharacterFragment(characterModel)
            binding.imageSearch.visibility = View.GONE
            findNavController().navigate(action)
        }
    }

    private fun setupRecyclerView() = with(binding) {
        rvSearchCharacter.apply {
            adapter = characterAdapter
            layoutManager = LinearLayoutManager(context)
            binding.imageSearch.visibility = View.GONE
        }
    }

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
    ): FragmentSearchCharacterBinding =
        FragmentSearchCharacterBinding.inflate(inflater, container, false)
}