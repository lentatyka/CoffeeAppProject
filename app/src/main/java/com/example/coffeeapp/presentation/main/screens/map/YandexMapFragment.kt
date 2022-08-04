package com.example.coffeeapp.presentation.main.screens.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coffeeapp.BuildConfig
import com.example.coffeeapp.R
import com.example.coffeeapp.common.Utils.launchWhenStarted
import com.example.coffeeapp.databinding.FragmentMapBinding
import com.example.coffeeapp.presentation.main.CoffeeActivity
import com.example.coffeeapp.presentation.main.screens.shop.ShopViewModel
import com.yandex.mapkit.MapKitFactory
import kotlinx.coroutines.flow.onEach

class YandexMapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    lateinit var shopViewModel: ShopViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_map,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        shopViewModel.shopList.onEach {
            Log.d("TAG", "COLLECT: $it")
        }.launchWhenStarted(lifecycleScope)
    }

    private fun setMap() {
        val mapView = binding.mapview

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}