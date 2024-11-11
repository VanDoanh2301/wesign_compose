package com.example.wesign.domain.usecase.study.getallquestion_use_case

import androidx.test.ext.junit.runners.AndroidJUnit4
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class GetAllQuestionUseCaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)
    @Inject
    lateinit var getAllQuestionUseCase: GetAllQuestionUseCase

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    operator fun invoke() =  runBlocking {
        getAllQuestionUseCase.invoke(14).collect {
            println(it)
        }

    }
}