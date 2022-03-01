package com.busrayalcin.informationofcountries.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.busrayalcin.informationofcountries.model.Country
import com.busrayalcin.informationofcountries.service.CountryDB
import kotlinx.coroutines.launch

class CountryViewModel(application: Application) : BaseViewModel(application) {

    val countryLiveData = MutableLiveData<Country>()

    fun getDataRoom(uuid : Int){
        launch {
            val dao = CountryDB(getApplication()).countryDao()
            val country = dao.getCountry(uuid)
            countryLiveData.value = country
        }
    }
}