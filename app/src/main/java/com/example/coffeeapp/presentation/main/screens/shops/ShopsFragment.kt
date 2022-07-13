package com.example.coffeeapp.presentation.main.screens.shops

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeapp.R
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.databinding.FragmentShopsBinding
import com.example.coffeeapp.presentation.main.CoffeeActivity

class ShopsFragment : Fragment() {

    private var _binding: FragmentShopsBinding? = null
    private val binding get() = _binding!!

    private val shopsViewModel by viewModels<ShopsViewModel> {
        (activity as CoffeeActivity).mainComponent.shopsViewModelFactory()
    }

    private lateinit var shopLocationAdapted: ShopsLocationAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //(activity as CoffeeActivity).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_shops,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setAdapter()
        setViewModel()

        val test = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(
                    android.Manifest.permission.ACCESS_COARSE_LOCATION, false
                ) -> {
                    shopsViewModel.getShopList()
                }
                else -> {
                    Log.d("TAG", "DENY")
                    shopsViewModel.getShopList()
                }
            }
        }
        test.launch(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION))
    }

    private fun setViewModel() {
        lifecycleScope.launchWhenStarted {
            shopsViewModel.status.observe(viewLifecycleOwner) { info ->
                when (info) {
                    is Resource.Loading -> {
                        //show loading
                    }
                    is Resource.Error -> {
                        showMessage(info.message)
                    }
                    is Resource.Success -> {
                        shopLocationAdapted.submitList(info.data)
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        shopLocationAdapted = ShopsLocationAdapter { id ->
            val action = ShopsFragmentDirections.actionShopsFragmentToMenuFragment(id)
            findNavController().navigate(action)
        }
        binding.shopsRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = shopLocationAdapted
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}