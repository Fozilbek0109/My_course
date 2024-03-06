package uz.coder.d2lesson139broadcastresiver

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val myResiver: MyResiver = MyResiver()
    private var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intentFilter = IntentFilter().apply {
            addAction(Intent.ACTION_BATTERY_LOW)
            addAction(MyResiver.COSTM_ACTION_ON_CLICK_BUTTON)
        }

        findViewById<Button>(R.id.btn).setOnClickListener {
            val intent = Intent(MyResiver.COSTM_ACTION_ON_CLICK_BUTTON)
            intent.putExtra(MyResiver.PAGE,number++)
            sendBroadcast(intent)
        }

        registerReceiver(myResiver,intentFilter)
    }


    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myResiver)
    }
}