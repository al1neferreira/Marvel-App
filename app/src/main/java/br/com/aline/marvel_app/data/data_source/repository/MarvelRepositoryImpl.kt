package br.com.aline.marvel_app.data.data_source.repository

import br.com.aline.marvel_app.data.data_source.MarvelApi
import br.com.aline.marvel_app.data.data_source.dto.CharactersDto
import br.com.aline.marvel_app.domain.repository.MarvelRepository
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelApi
):MarvelRepository{
    override suspend fun getAllCharacthers(offset: Int): CharactersDto {
        return api.getAllCharacters(offset = offset.toString())
    }

}
