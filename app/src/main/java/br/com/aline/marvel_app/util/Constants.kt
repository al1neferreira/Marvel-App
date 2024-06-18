package br.com.aline.marvel_app.util

import br.com.aline.marvel_app.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object{
        const val BASE_URL = "https://gateway.marvel.com"
        val timStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = BuildConfig.apiKey
        const val PRIVATE_KEY = BuildConfig.privateKey
        const val limit = "20"
        fun hash():String{
            val input = "$timStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }

    }

}