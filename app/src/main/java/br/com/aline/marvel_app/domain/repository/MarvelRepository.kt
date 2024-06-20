package br.com.aline.marvel_app.domain.repository

import br.com.aline.marvel_app.data.data_source.dto.CharactersDto

interface MarvelRepository {

    suspend fun getAllCharacters(offset:Int):CharactersDto
    suspend fun getAllSearchedCharacters(nameStartsWith:String):CharactersDto
}