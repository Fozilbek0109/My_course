package uz.coder.d2lesson126workmanger

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.*

class MyWorker(context: Context, private val  workerParameters: WorkerParameters):Worker(context,workerParameters) {
    override fun doWork(): Result {
        log("doWork")
        val page = workerParameters.inputData.getInt(PAGE, 0)
        for (i in 0 .. 100) {
            Thread.sleep(1000)
            log("Timer $i $page ")
        }
        return Result.success()
    }

    private fun log(s: String) {
        Log.d("MyWorker", "log: $s")
    }


    companion object{
       private const val PAGE = "page"
        const val WORK_NAME = "work_name"
        fun  makeRequest(page:Int):OneTimeWorkRequest{
            return OneTimeWorkRequestBuilder<MyWorker>()
                .setInputData(workDataOf(PAGE to page))
                .setConstraints(makeContraints())
                .build()
        }
        private fun  makeContraints():Constraints{
            return Constraints.Builder()
                .setRequiresCharging(true)
                .build()
        }
    }
}