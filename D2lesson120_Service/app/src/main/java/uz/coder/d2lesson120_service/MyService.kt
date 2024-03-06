package uz.coder.d2lesson120_service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.*


class MyService : Service() {
    var scope = CoroutineScope(Dispatchers.Main)
    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("onStartCommand")
        scope.launch {
            for (i in 0..100) {
                delay(1000)
                log("Thread $i")
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun log(s: String) {
        Log.d("SERVICE", "log: $s ")
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
        log("onDestroy")
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    companion object {
        /**
         * my fobrical method newIntent
         */
        fun newIntent(context: Context): Intent {
            return Intent(context, MyService::class.java)
        }
    }
}