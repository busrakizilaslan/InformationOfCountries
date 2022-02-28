package com.busrayalcin.informationofcountries.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.busrayalcin.informationofcountries.model.Country

@Database(entities = arrayOf(Country::class), version = 1)
abstract class CountryDB : RoomDatabase() {

    abstract fun countryDao() : CountryDAO
    companion object{
        @Volatile private var instance : CountryDB? = null

        private val lock = Any()
        operator fun invoke(context : Context) = instance ?: synchronized(lock){
            instance ?: makeDatabase(context).also {
                instance = it
            }
        }
        private fun makeDatabase(context : Context) = Room.databaseBuilder(
            context.applicationContext,CountryDB::class.java,"countrydatabase").build()

    }
}