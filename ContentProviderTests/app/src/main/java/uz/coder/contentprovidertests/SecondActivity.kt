package uz.coder.contentprovidertests

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.coder.contentprovidertests.databinding.ActivitySecondBinding
import kotlin.concurrent.thread

class SecondActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivitySecondBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        thread {
            binding.btnSave.setOnClickListener {
                contentResolver.insert(
                    Uri.parse("content://uz.coder.shoppinglist/shop_item"),
                    ContentValues().apply {
                        put("id", 0)
                        put("name", binding.editeName.text.toString())
                        put("count", binding.editeCount.text.toString().toInt())
                        put("enabled", true)
                    }
                )
                lifecycleScope.launch{
                    delay(3000)
                }
                finish()
            }
        }

    }
}