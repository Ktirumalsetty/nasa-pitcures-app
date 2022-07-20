package com.obvious.nasapicturesapp.data

import android.content.Context
import com.google.gson.Gson
import com.obvious.nasapicturesapp.model.Nasa
import kotlinx.coroutines.delay
import org.json.JSONArray
import java.nio.charset.Charset
import javax.inject.Inject

class NasaRepository @Inject constructor(val context:Context){


    suspend fun getData(): MutableList<Nasa> {
        delay(2000)
        val inputStream = context.assets.open("data.json")
        val nasaInfo = mutableListOf<Nasa>()
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charset.forName("UTF-8"))
        val items = JSONArray(json)
        val gson = Gson()
        for (i in 0 until items.length()) {
            val obj = items.getJSONObject(i)
            val info = gson.fromJson(obj.toString(), Nasa::class.java)
            nasaInfo.add(info)
        }
        return nasaInfo
    }
}