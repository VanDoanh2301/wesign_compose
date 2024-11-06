package com.example.wesign

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wesign.domain.model.Vocabulary
import com.example.wesign.domain.model.VocabularyImage
import com.example.wesign.domain.model.VocabularyVideo
import com.example.wesign.domain.model.WordType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val gson = Gson()
    @Test
    fun useAppContext() {
        // Sample vocabulary list
        val vocabularyList = listOf(
            Vocabulary(
                id = 1,
                content = "example",
                images = listOf(VocabularyImage(null, "Image 1", primary = true), VocabularyImage("img1.png", "Image 1", primary = false)),
                videos = listOf(VocabularyVideo("vid1.mp4", "Video 1", primary = true), VocabularyVideo("vid1.mp4", "Video 1", primary = false), VocabularyVideo("vid1.mp4", "Video 1", primary = false)),
                topicId = 101,
                topicContent = "Animals",
                note = "Example note",
                type = WordType.WORD
            ),
            Vocabulary(
                id = 1,
                content = "example",
                images = listOf(VocabularyImage(null, "Image 1", primary = true), VocabularyImage("img1.png", "Image 1", primary = false)),
                videos = listOf(VocabularyVideo("vid1.mp4", "Video 1", primary = true), VocabularyVideo("vid1.mp4", "Video 1", primary = false), VocabularyVideo("vid1.mp4", "Video 1", primary = false)),
                topicId = 101,
                topicContent = "Animals",
                note = "Example note",
                type = WordType.WORD
            )
        )

        // Convert to JSON
        val json = vocabularyListToJson(vocabularyList)
        println("JSON: $json")

        // Convert back to List<Vocabulary>
        val parsedList = jsonToVocabularyList(json)
        println("Parsed List: $parsedList")

        // Assert that the original list and parsed list are the same
        assertEquals(vocabularyList, parsedList)
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