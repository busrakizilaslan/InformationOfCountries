package com.busrayalcin.informationofcountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.busrayalcin.informationofcountries.R
import com.busrayalcin.informationofcountries.databinding.FragmentCountriesBinding
import com.busrayalcin.informationofcountries.util.downloadFromUrl
import com.busrayalcin.informationofcountries.util.progressBarForPlaceHolder
import com.busrayalcin.informationofcountries.viewmodel.CountryViewModel
import kotlinx.android.synthetic.main.fragment_countries.*


class FragmentCountries : Fragment() {

    private lateinit var viewModel : CountryViewModel
    private var countryUUID = 0
    private lateinit var dataBinding : FragmentCountriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_countries,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUUID = FragmentCountriesArgs.fromBundle(it).countryUUID
        }

        viewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        viewModel.getDataRoom(countryUUID)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer { country ->
            country?.let {
                dataBinding.selectedCountry = it
                /*
                countryName.text = country.countryName
                countryRegion.text = country.countryRegion
                countryCapital.text = country.countryCapital
                countryCurrency.text = country.countryCurrency
                countryLanguage.text = country.countryLanguage
                context?.let {
                    countryImage.downloadFromUrl(country.imageUrl, progressBarForPlaceHolder(it))
                }
                 */
            }
        })
    }
}