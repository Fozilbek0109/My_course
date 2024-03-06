package uz.coder.d2lesson128caroutin.network

import retrofit2.Call
import retrofit2.http.GET
import uz.coder.d2lesson114retrofit.mod.News
import uz.coder.d2lesson128caroutin.model.User

interface ApiService {
    @GET("v2/news/?lang=EN")
    suspend fun  getUser():News
}