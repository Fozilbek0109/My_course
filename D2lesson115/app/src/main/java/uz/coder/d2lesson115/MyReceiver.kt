package uz.coder.d2lesson115

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.Capability
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import java.net.ConnectException

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (isNetwork(context)){
            Toast.makeText(context,"Connactet",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Disconnactet",Toast.LENGTH_SHORT).show()
        }
    }
    fun isNetwork(context: Context):Boolean{
        val connectivityManager:ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }



}