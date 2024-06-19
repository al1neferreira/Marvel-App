package br.com.aline.marvel_app.uiCharacterList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.aline.marvel_app.domain.use_cases.CharacterUseCase
import br.com.aline.marvel_app.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val charactersUseCase: CharacterUseCase
) : ViewModel() {

    private val marvelValue = MutableStateFlow(MarvelListState())
    var _marvelValue: StateFlow<MarvelListState> = marvelValue

    fun getAllCharactersData(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        charactersUseCase(offset = offset).collect {
            when (it) {
                is Response.Success -> {
                    marvelValue.value = MarvelListState(characterList = it.data ?: emptyList())
                }

                is Response.Loading -> {
                    marvelValue.value = MarvelListState(isLoading = true)
                }

                is Response.Error -> {
                    marvelValue.value =
                        MarvelListState(error = it.message ?: "Erro ao carregar os dados")
                }
            }
        }
    }
}