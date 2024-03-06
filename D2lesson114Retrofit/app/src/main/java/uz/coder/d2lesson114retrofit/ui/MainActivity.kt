package uz.coder.d2lesson114retrofit.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.coder.d2lesson114retrofit.adapter.CurrencyAdapterRec
import uz.coder.d2lesson114retrofit.databinding.ActivityMainBinding
import uz.coder.d2lesson114retrofit.model.CurrencyModel
import uz.coder.d2lesson114retrofit.retrofit.ApiClient
import uz.coder.d2lesson114retrofit.retrofit.ApiService

class MainActivity : AppCompatActivity() {


    private lateinit var adapterRec: CurrencyAdapterRec
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apiService = ApiClient.getRetrofit().create(ApiService::class.java)
        apiService.getCurrency().enqueue(object : Callback<List<CurrencyModel>> {
            override fun onResponse(
                call: Call<List<CurrencyModel>>,
                response: Response<List<CurrencyModel>>
            ) {
                if (response.isSuccessful) {
                    val list: List<CurrencyModel>? = response.body()
                    adapterRec = CurrencyAdapterRec(list ?: throw RuntimeException("list is null"),this@MainActivity)
                    binding.rec.adapter = adapterRec
                }
            }
            override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
            }
        })
    }
}