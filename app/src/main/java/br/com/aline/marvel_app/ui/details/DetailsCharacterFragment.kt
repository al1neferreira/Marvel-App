package br.com.aline.marvel_app.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.aline.marvel_app.R
import br.com.aline.marvel_app.databinding.FragmentDetailsCharacterBinding
import br.com.aline.marvel_app.ui.base.BaseFragment

class DetailsCharacterFragment : BaseFragment<FragmentDetailsCharacterBinding, DetailsCharacterViewModel>() {
    override val viewModel: DetailsCharacterViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsCharacterBinding  = FragmentDetailsCharacterBinding.inflate(inflater, container, false)

}