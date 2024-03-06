package uz.coder.d2lessonn116

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import uz.coder.d2lessonn116.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val handler: Handler = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonLoad.setOnClickListener {
            loadData()
        }
    }

    private fun loadData() {
        binding.progress.visibility = View.VISIBLE
        binding.buttonLoad.isEnabled = false
        loadCity {
            binding.tvLocation.text = it
            loadTemp(it) { i ->
                binding.tvTemperature.text = i.toString()
                binding.progress.visibility = View.GONE
                binding.buttonLoad.isEnabled = true
            }
        }
    }

    fun loadTemp(city: String, colback: (Int) -> Unit) {
        thread {
            handler.post {
                Toast.makeText(this, "Load teperature for sity $city", Toast.LENGTH_SHORT).show()
            }
            Thread.sleep(5000)
            handler.post {
                colback.invoke(17)
            }
        }
    }

    fun loadCity(colback: (String) -> Unit) {
        Thread.sleep(5000)
        handler.post {
            colback.invoke("Urgench")
        }
    }
}