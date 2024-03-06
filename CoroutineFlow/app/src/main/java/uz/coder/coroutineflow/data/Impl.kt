package uz.coder.coroutineflow.data

import android.app.Application
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import uz.coder.coroutineflow.domain.Repo

class Impl(private val application: Application):Repo {
    override fun load() {
        val worker = WorkManager.getInstance(application)
        worker.enqueueUniqueWork(Worker.WORKER_NAME,ExistingWorkPolicy.REPLACE,Worker.makeRequeste())
    }


}