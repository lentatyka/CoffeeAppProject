package com.example.coffeeapp.presentation.main.screens.shop


import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeapp.R
import com.example.coffeeapp.common.State
import com.example.coffeeapp.common.Utils
import com.example.coffeeapp.common.Utils.launchWhenStarted
import com.example.coffeeapp.databinding.FragmentShopBinding
import com.example.coffeeapp.presentation.main.CoffeeActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private val shopViewModel by viewModels<ShopViewModel> {
        (activity as CoffeeActivity).mainComponent.shopsViewModelFactory()
    }

    private lateinit var shopLocationAdapted: ShopLocationAdapter

    private lateinit var locationPermission: ActivityResultLauncher<Array<String>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_shop,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationPermission = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(
                    android.Manifest.permission.ACCESS_FINE_LOCATION, false
                ) -> {
                    when {
                        Utils.isGPSEnabled(requireContext()) -> {
                            shopViewModel.setLocationEnable(true)
                            shopViewModel.startUpdateLocation()
                        }
                        else -> Utils.showSnackBar(
                            view,
                            getString(R.string.gps_enable)
                        ) {
                            val intent = Intent()
                            intent.action = Settings.ACTION_LOCATION_SOURCE_SETTINGS
                            startActivity(intent)
                        }
                    }
                }
                else -> {
                    Utils.showToast(requireContext(), getString(R.string.location_disable))
                    shopViewModel.setLocationEnable(false)
                }
            }
        }
        setAdapter()
        setViewModel()
        binding.showMapBtn.setOnClickListener {
            ShopFragmentDirections.actionShopsFragmentToYandexMapFragment().also(
                findNavController()::navigate
            )
        }
    }

    private fun setViewModel() {
        shopViewModel.shopList
            .onEach(shopLocationAdapted::submitList)
            .launchWhenStarted(lifecycleScope)

        shopViewModel.state.onEach(binding::setState).launchWhenStarted(lifecycleScope)
    }

    private fun setAdapter() {
        shopLocationAdapted = ShopLocationAdapter { id ->
            val action = ShopFragmentDirections.actionShopsFragmentToMenuFragment(id)
            findNavController().navigate(action)
        }
        binding.shopsRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = shopLocationAdapted
        }
    }

    override fun onStop() {
        super.onStop()
        shopViewModel.stopUpdateLocation()
    }

    override fun onStart() {
        super.onStart()
        locationPermission.launch(arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}