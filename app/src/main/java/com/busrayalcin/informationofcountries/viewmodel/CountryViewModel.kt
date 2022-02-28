package com.busrayalcin.informationofcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.busrayalcin.informationofcountries.model.Country

class CountryViewModel : ViewModel() {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataRoom(){

        val country = Country("Turkey","Asia","Ankara","TRY","Turkish","www.ssss.com")
        countryLiveData.value = country
    }
}