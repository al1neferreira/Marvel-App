package br.com.aline.marvel_app.api

import br.com.aline.marvel_app.data.data_source.dto.CharactersDto
import br.com.aline.marvel_app.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    suspend fun getAllCharacters(
        @Query("apikey")apiKey: String = Constants.API_KEY,
        @Query("ts")ts:String = Constants.timStamp,
        @Query("hash")hash:String = Constants.hash(),
        @Query("offset")offset:String
    ):CharactersDto

    @GET("/v1/public/characters")
    suspend fun searchCharacters(
       @Query("name")name: String,
       @Query("nameStartsWith") nameStartsWith: String
    ): CharactersDto

}