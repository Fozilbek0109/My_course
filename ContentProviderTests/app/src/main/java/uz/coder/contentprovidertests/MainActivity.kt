package uz.coder.contentprovidertests

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import uz.coder.contentprovidertests.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var list: ArrayList<ShopItem>
    private lateinit var adapterRec: AdapterRec
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        list = ArrayList<ShopItem>()
        adapterRec = AdapterRec(list)
        binding.rec.adapter = adapterRec
//        loadDataContentProvider()
//        Log.d(TAG, "onCreate: $list")
        loadDataContentProvider()
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }

    }

    private fun loadDataContentProvider() {
        thread {
            val cursor = contentResolver.query(
                Uri.parse("$CONTENT$AUTHORITY$PATH_SHOP_ITEM"),
                null,
                null,
                null,
                null
            )
            while (cursor?.moveToNext() == true) {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val count = cursor.getInt(cursor.getColumnIndexOrThrow("count"))
                val enabled = cursor.getInt(cursor.getColumnIndexOrThrow("enabled")) > 0
                val shopItem = ShopItem(id = id, name = name, count = count, enabled = enabled)
                list.add(shopItem)
                adapterRec.notifyItemInserted(list.size)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }


    companion object {
        const val INSERT_SHOP_QUERY = "/get_shop_item"
        const val AUTHORITY = "uz.coder.shoppinglist"
        const val PATH_SHOP_ITEM = "/shop_item"
        const val CONTENT = "content://"
        private const val TAG = "MainActivity"
    }
}