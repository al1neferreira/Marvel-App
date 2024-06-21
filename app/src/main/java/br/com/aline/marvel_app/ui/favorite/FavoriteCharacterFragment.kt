package br.com.aline.marvel_app.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.aline.marvel_app.databinding.FragmentFavoriteCharacterBinding
import br.com.aline.marvel_app.ui.base.BaseFragment


class FavoriteCharacterFragment:BaseFragment<FragmentFavoriteCharacterBinding, FavoriteCharacterViewModel>() {

    override val viewModel: FavoriteCharacterViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteCharacterBinding = FragmentFavoriteCharacterBinding.inflate(inflater, container, false)
}