package com.example.coffeeapp.presentation.main.screens.shops

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeapp.R
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.shops.ShopLocationDto
import com.example.coffeeapp.databinding.FragmentShopsBinding
import com.example.coffeeapp.presentation.main.CoffeeActivity
import javax.inject.Inject

class ShopsFragment : Fragment() {

    private var _binding: FragmentShopsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var shopsViewModel: ShopsViewModel

    private lateinit var shopLocationAdapted: ShopsLocationAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as CoffeeActivity).mainComponent.inject(this)
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
        shopLocationAdapted = ShopsLocationAdapter {
            showMessage(it.toString())
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