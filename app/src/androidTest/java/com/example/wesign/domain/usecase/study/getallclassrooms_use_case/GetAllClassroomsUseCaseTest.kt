package com.example.wesign.domain.usecase.study.getallclassrooms_use_case

import android.util.Log
import androidx.paging.filter
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.wesign.utils.Resource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class GetAllClassroomsUseCaseTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var getAllClassroomsUseCase: GetAllClassroomsUseCase

    @Before
    fun setUp() {
        hiltRule.inject()
    }
    @Test
    fun getAllClassroomsUseCase() {
        runBlocking {
           getAllClassroomsUseCase.invoke().collect {resource->
                Log.d("PagingData", resource.toString())
            }


        }
    }
}