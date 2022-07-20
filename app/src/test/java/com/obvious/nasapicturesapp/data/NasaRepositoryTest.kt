package com.obvious.nasapicturesapp.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(AndroidJUnit4::class)
class NasaRepositoryTest {

    private lateinit var context:Context
    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context>()

    }

    @Test
    fun testGetData()= runBlocking {
        val data = NasaRepository(context).getData()
        assertNotNull(data)
    }


}