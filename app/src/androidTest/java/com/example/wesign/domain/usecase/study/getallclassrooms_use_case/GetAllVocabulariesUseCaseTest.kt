package com.example.wesign.domain.usecase.study.getallclassrooms_use_case

import android.util.Log
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.repository.StudyRepository
import com.example.wesign.domain.usecase.study.getallvocabularies_use_case.GetAllVocabulariesUseCase
import com.example.wesign.utils.DataPreferences
import com.example.wesign.utils.Resource
import com.example.wesign.utils.SharedPreferencesUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class GetAllVocabulariesUseCaseTest {
    private val gson = Gson()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var studyRepository: StudyRepository

    @Inject
    lateinit var dataPreferences: DataPreferences

    @Before
    fun setUp() {
        hiltRule.inject()

        SharedPreferencesUtils.init(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun getAllVocabulariesUseCase()  = runBlocking {
       val vocabularies = studyRepository.getAllVocabularies().data


        if (vocabularies != null) {
            val vocabulariesJson = vocabularyListToJson(vocabularies)
            SharedPreferencesUtils.setString("DATA_VOCAL", vocabulariesJson)
        }

    }

    @Test
    fun testGetAllVocabulariesUseCase() = runBlocking {
        val vocabulariesJson = SharedPreferencesUtils.getString("DATA_VOCAL")
        val vocabularies = jsonToVocabularyList(vocabulariesJson!!)
        println(vocabularies)

    }

    private fun vocabularyListToJson(vocabularyList: List<Vocabulary>): String {
        return gson.toJson(vocabularyList)
    }

    // Convert JSON back to List<Vocabulary>
    private fun jsonToVocabularyList(json: String): List<Vocabulary> {
        val listType = object : TypeToken<List<Vocabulary>>() {}.type
        return gson.fromJson(json, listType)
    }
}