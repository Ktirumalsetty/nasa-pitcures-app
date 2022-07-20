package com.obvious.nasapicturesapp.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.obvious.nasapicturesapp.data.NasaRepository
import com.obvious.nasapicturesapp.model.Nasa
import com.obvious.nasapicturesapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.lang.Exception
import java.nio.charset.Charset
import javax.inject.Inject

@HiltViewModel
class NasaListViewModel @Inject constructor(private val repo:NasaRepository): ViewModel() {

    private val _nasaItems =MutableLiveData<Result<List<Nasa>>>()
    val nasaItemsLiveData:LiveData<Result<List<Nasa>>> =_nasaItems

    init {
        getData()
    }

    private fun getData() = viewModelScope.launch {
        try {
            val data = repo.getData()
            _nasaItems.value = Result.Success(data)
        }catch (e:Exception){
            _nasaItems.value = Result.Error(e)
        }

    }
}