package com.example.wesign

import android.app.Application
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.wesign.utils.DataUpdateWorker
import com.example.wesign.utils.SharedPreferencesUtils
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class WeSignApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPreferencesUtils.init(this)
        scheduleDataUpdateWorker()
    }
    private fun scheduleDataUpdateWorker() {
        val updateDataRequest = PeriodicWorkRequest.Builder(
            DataUpdateWorker::class.java, 24, TimeUnit.HOURS)
            .build()

        WorkManager.getInstance(applicationContext).enqueue(updateDataRequest)
    }
}