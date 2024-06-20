package br.com.aline.marvel_app.domain.use_cases

import br.com.aline.marvel_app.domain.model.Character
import br.com.aline.marvel_app.domain.repository.MarvelRepository
import br.com.aline.marvel_app.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SearchCharacterUseCase @Inject constructor(
    private val repository: MarvelRepository

) {
    operator fun invoke(nameStartsWith: String): Flow<Response<List<Character>>> = flow {
        try {
            emit(Response.Loading<List<Character>>())
            val list = repository.getAllSearchedCharacters(nameStartsWith).data.results.map {
                it.toCharacter()

            }
            emit(Response.Success<List<Character>>(list))

        }
        catch (e: HttpException){
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))

        }
        catch (e:IOException){
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }

    }
}