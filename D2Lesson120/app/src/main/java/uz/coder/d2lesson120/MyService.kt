package uz.coder.d2lesson120

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder

class MyService:Service() {
    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    companion object{
        fun newIntent(context: Context):Intent{
            return Intent(context,MyService::class.java)
        }
    }
}