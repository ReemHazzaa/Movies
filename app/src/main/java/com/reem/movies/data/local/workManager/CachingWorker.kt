package com.reem.movies.data.local.workManager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.reem.movies.data.local.movieCache.MovieCacheDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CachingWorker @Inject constructor(
    private val ctx: Context,
    private val params: WorkerParameters,
    private val cacheDao: MovieCacheDao,
) : Worker(ctx, params) {
    override fun doWork(): Result {
        CoroutineScope(Dispatchers.IO).launch {
            cacheDao.deleteCache()
        }
        Log.e("CachingWorker", "SUCCESS")
        return Result.success()
    }
}