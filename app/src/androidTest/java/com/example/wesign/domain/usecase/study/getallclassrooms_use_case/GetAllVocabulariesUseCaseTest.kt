package com.example.wesign.domain.usecase.study.getallclassrooms_use_case

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wesign.utils.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class GetAllVocabulariesUseCaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getAllVocabulariesUseCase: GetAllVocabulariesUseCase

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun getAllVocabulariesUseCase()  = runBlocking {
        getAllVocabulariesUseCase.invoke().collect {
            when (it) {
                is Resource.Success -> {
                    Log.e("Vocabularies", "List: ${it.dataResource}")
                    println(it.dataResource)
                }

                is Resource.Error -> {
                    println("Login failed: ${it.message}")
                }

                is Resource.Loading -> {
                    println("Status Loading")
                }
            }
        }
    }
}