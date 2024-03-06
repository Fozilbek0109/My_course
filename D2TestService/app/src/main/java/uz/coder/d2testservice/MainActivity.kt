package uz.coder.d2testservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import uz.coder.d2testservice.databinding.ActivityMainBinding
import uz.coder.d2testservice.service.MyForgroundService
import uz.coder.d2testservice.service.MyService
import uz.coder.d2testservice.service.MyService2

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            servise.setOnClickListener {
                startService(MyService.newIntent(this@MainActivity))
            }
            servise2.setOnClickListener {
                startService(MyService2.newIntent(this@MainActivity, 15))
            }
            forgroudServise.setOnClickListener {
                ContextCompat.startForegroundService(
                    this@MainActivity,
                    MyForgroundService.newIntent(this@MainActivity, 10)

                )
//                shoNotification()
            }


        }


    }

    private fun shoNotification() {
        val notifiManeger: NotificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notifiManeger.createNotificationChannel(notificationChannel)
        }
        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Titile")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()
        notifiManeger.notify(1, notification)

    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
    }
}