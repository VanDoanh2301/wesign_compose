package com.example.wesign.utils

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.repository.StudyRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DataUpdateWorker @AssistedInject constructor(

    @Assisted private val appContext: Context,
    @Assisted private val workerParams: WorkerParameters,
    private val studyRepository: StudyRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        SharedPreferencesUtils.init(applicationContext)

        val lastUpdated = SharedPreferencesUtils.getLong("last_updated_time", 0)
        val currentTime = System.currentTimeMillis()

        if (currentTime - lastUpdated >= 24 * 60 * 60 * 1000) {
            try {
                val responseVocal = studyRepository.getAllVocabularies()
                val responseTopic = studyRepository.getAllTopic()
                val responseClass = studyRepository.getAllClassrooms()

                if (responseVocal.code == 200 && responseClass.code == 200 && responseTopic.code == 200) {
                    val cachedVocabularies = responseVocal.data
                    val vocabulariesJson = vocabularyListToJson(cachedVocabularies!!)
                    SharedPreferencesUtils.setString(DATA_VOCAL, vocabulariesJson)

                    val cachedClassrooms = responseClass.data
                    val classroomsJson = listToJson(cachedClassrooms!!)
                    SharedPreferencesUtils.setString(DATA_CLASSROOM, classroomsJson)

                    val cachedTopics = responseTopic.data
                    val topicsJson = listToJson(cachedTopics!!)
                    SharedPreferencesUtils.setString(DATA_TOPIC, topicsJson)

                    SharedPreferencesUtils.setLong("last_updated_time", currentTime)

                    return Result.success()
                } else {
                    return Result.failure()
                }
            } catch (e: Exception) {
                return Result.failure()
            }
        } else {
            return Result.success()
        }
    }

    private fun vocabularyListToJson(vocabularyList: List<Vocabulary>): String {
        return Gson().toJson(vocabularyList)
    }

    // Convert JSON back to List<Vocabulary>
    private fun jsonToVocabularyList(json: String): List<Vocabulary> {
        val listType = object : TypeToken<List<Vocabulary>>() {}.type
        return Gson().fromJson(json, listType)
    }
}
