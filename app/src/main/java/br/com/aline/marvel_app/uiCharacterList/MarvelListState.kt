package br.com.aline.marvel_app.uiCharacterList

import br.com.aline.marvel_app.domain.model.Character

data class MarvelListState(
    val isLoading: Boolean = false,
    val characterList: List<Character> = emptyList(),
    val error: String = ""

)
