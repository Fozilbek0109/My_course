package uz.coder.d2lesson114retrofit.ui

import android.app.Application
import com.mocklets.pluto.Pluto

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Pluto.initialize(this)
    }
}