package com.obvious.nasapicturesapp.ui

import androidx.lifecycle.ViewModel
import com.obvious.nasapicturesapp.model.Nasa

class MainActivitySharedViewModel:ViewModel() {

    var nasaImagesList:List<Nasa> = mutableListOf()
}