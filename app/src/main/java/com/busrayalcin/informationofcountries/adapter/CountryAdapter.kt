package com.busrayalcin.informationofcountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.busrayalcin.informationofcountries.R
import com.busrayalcin.informationofcountries.databinding.CountryRowBinding
import com.busrayalcin.informationofcountries.model.Country
import com.busrayalcin.informationofcountries.util.downloadFromUrl
import com.busrayalcin.informationofcountries.util.progressBarForPlaceHolder
import com.busrayalcin.informationofcountries.view.FragmentFeedDirections
import kotlinx.android.synthetic.main.country_row.view.*
import java.util.zip.Inflater

class CountryAdapter(val countryList : ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>(),CountryClickListener {

    class CountryHolder(var view : CountryRowBinding) : RecyclerView.ViewHolder(view.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.country_row,parent,false)
        val view = DataBindingUtil.inflate<CountryRowBinding>(inflater,R.layout.country_row,parent,false)
        return CountryHolder(view)
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this
        /*
        holder.view.name.text = countryList[position].countryName
        holder.view.region.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = FragmentFeedDirections.actionFragmentFeedToFragmentCountries(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.imageView.downloadFromUrl(countryList[position].imageUrl,
            progressBarForPlaceHolder(holder.view.context))

         */
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    fun updateCountryList(newCountryList : List<Country>){
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val uuid = v.countryUuidText.text.toString().toInt()
        val action = FragmentFeedDirections.actionFragmentFeedToFragmentCountries(uuid)
        Navigation.findNavController(v).navigate(action)
    }
}