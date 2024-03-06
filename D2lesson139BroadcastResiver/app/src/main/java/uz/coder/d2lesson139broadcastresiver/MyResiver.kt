package uz.coder.d2lesson139broadcastresiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyResiver:BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when(intent?.action){
            Intent.ACTION_BATTERY_LOW->{
                Toast.makeText(context, "Batareka quvati oz qoldi", Toast.LENGTH_SHORT).show()
            }
            COSTM_ACTION_ON_CLICK_BUTTON ->{
                val number = intent.getIntExtra(PAGE, 0)
                Toast.makeText(context, "click button $number", Toast.LENGTH_SHORT).show()
            }
        }
    }


    companion object{
        const val COSTM_ACTION_ON_CLICK_BUTTON = "onClick_button"
        const val PAGE = "page"
    }
}