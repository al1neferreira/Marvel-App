package br.com.aline.marvel_app.util

import br.com.aline.marvel_app.BuildConfig

object Constants {

    const val BASE_URL = "https://gateway.marvel.com/V1/public/ "
    const val PUBLIC_KEY = BuildConfig.publicKey
    const val PRIVATE_KEY = BuildConfig.privateKey

    const val APIKEY = "apiKey"
    const val HASH = "hash"
    const val TS = "ts"
}