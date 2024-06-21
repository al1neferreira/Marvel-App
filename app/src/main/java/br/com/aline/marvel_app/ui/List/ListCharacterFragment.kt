package br.com.aline.marvel_app.ui.List

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.aline.marvel_app.R
import br.com.aline.marvel_app.databinding.FragmentListCharacterBinding
import br.com.aline.marvel_app.ui.base.BaseFragment

class ListCharacterFragment : BaseFragment<FragmentListCharacterBinding, ListCharacterViewModel>() {


    override val viewModel: ListCharacterViewModel by viewModels()


    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListCharacterBinding = FragmentListCharacterBinding.inflate(inflater, container, false)
}