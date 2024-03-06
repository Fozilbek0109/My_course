package uz.coder.d2lesson114retrofit.retrofit

import retrofit2.Call
import retrofit2.http.GET
import uz.coder.d2lesson114retrofit.model.CurrencyModel

interface ApiService {
    @GET("arkhiv-kursov-valyut/json/")
    fun getCurrency():Call<List<CurrencyModel>>
}