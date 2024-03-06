package uz.coder.d2testservice.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.coder.d2testservice.MainActivity
import uz.coder.d2testservice.R

class MyForgroundService:Service() {
    val scope = CoroutineScope(Dispatchers.Main)
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        createNotificationChanel()
        startForeground(NOTIFICATION_ID,createNotification())
        log("onCreate")
        super.onCreate()

    }
    private fun createNotificationChanel(){
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

    }
    private fun createNotification():Notification{
        return  NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Titile")
            .setContentText("Text")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .build()
    }
    private fun log(src: String) {
        Log.d("MyForgroundService", "Timer: $src")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        scope.launch {
            for (i in 0 .. 25) {
                /**
                 * delay ushbu qatorda codni 1 sekund kutadi va yana ishni davom ettradi bunda
                 * 1 sekun kutish vaqtida u scope ichidan chiqib ketadi
                 */
                delay(1000)
                log(i.toString())
            }
        }
        /**
         * returinda qaytarishimiz mumkin bulgan Statik keyalr:
         * START_STIKY dastur onDestroy bolganda service  onDestroy bolmaydi va  ishlashda davom etadi
         * START_NOT_STIKY dastur onDestroy bolganda service ham onDestroy boladi va  ishlashdan to'xtaydi
         * START_REDELIVER_INTENT bu holatda bining dastur intent orqali ishlata olamiz...
         * default holatdagi super.onStartCommand(intent, flags, startId) metodi START_STYKY vazifasini bajaradi
         * START_REDELIVER_INTENT bu key orqali biz serviceni intentda kelgan qiymatdan boshlashimiz mumkun bo'ladi
         */
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    companion object {
        private const val CHANNEL_ID = "channel_id"
        private const val CHANNEL_NAME = "channel_name"
        private const val NOTIFICATION_ID = 1
        private const val EXTRA = "num"
        fun newIntent(context: Context, num:Int): Intent {
            return Intent(context, MyForgroundService::class.java)

        }
    }
}