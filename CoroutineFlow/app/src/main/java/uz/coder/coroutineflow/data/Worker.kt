package uz.coder.coroutineflow.data

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class Worker(private val context: Context,wokerParameters: WorkerParameters):CoroutineWorker(context,wokerParameters) {
    override suspend fun doWork(): Result {
        while (true){
            Log.d(TAG, "doWork: ")
            delay(1000)
        }
    }

    companion object{
        private const val TAG = "Worker"
         const val WORKER_NAME = "Worker"

        fun makeRequeste():OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<Worker>().build()
        }
    }
}