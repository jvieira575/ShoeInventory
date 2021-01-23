package com.udacity.shoestore.screens.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoesList = MutableLiveData<MutableList<Shoe>>(mutableListOf())
    val shoesList : LiveData<MutableList<Shoe>>
        get() = _shoesList

    fun addShoe(shoe: Shoe) {
        _shoesList.value?.add(shoe)
        _shoesList.value = _shoesList.value
    }
}