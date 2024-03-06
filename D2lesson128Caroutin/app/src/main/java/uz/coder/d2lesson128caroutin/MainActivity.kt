package uz.coder.d2lesson128caroutin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.newCoroutineContext
import uz.coder.d2lesson114retrofit.mod.News
import uz.coder.d2lesson128caroutin.adapter.AdapterRc
import uz.coder.d2lesson128caroutin.databinding.ActivityMainBinding
import uz.coder.d2lesson128caroutin.model.User
import uz.coder.d2lesson128caroutin.network.ApiClient
import uz.coder.d2lesson128caroutin.network.ApiService
import kotlin.coroutines.CoroutineContext

class MainActivity() : AppCompatActivity(),CoroutineScope {
    private lateinit var adapterRec:AdapterRc
    private val job = Job()
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var apiService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        apiService = ApiClient.getRetrofit().create(ApiService::class.java)
        /* birinchi usul
         apiService.getUser().enqueue(object : Callback<List<User>>{
             override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

             }

             override fun onFailure(call: Call<List<User>>, t: Throwable) {

             }
         })*/
        launch(Dispatchers.Main) {
            getUser()
        }
    }
    /* ikkinchi ushul metod orqali
        fun getUser(): List<User>? {
            var  list:List<User>? = null
            apiService.getUser().enqueue(object : Callback<List<User>> {
                override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                    list = response.body()
                }

                override fun onFailure(call: Call<List<User>>, t: Throwable) {

                }
            })
            return list
        }
    */
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job + handler

    val handler = CoroutineExceptionHandler {  _,exseption,->
        Log.d("hand", "handler: $exseption   ")
    }
    private suspend fun getUser(): List<User> {
        val userList = apiService.getUser()
        getAndShowLest(userList)
        return userList
    }
    private fun getAndShowLest(news: News){
        news.data[0].body.get(0).
        adapterRec = AdapterRc(list)
        val  lyManager = LinearLayoutManager(this)
        binding.rec.adapter = adapterRec
        binding.rec.layoutManager = lyManager
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}