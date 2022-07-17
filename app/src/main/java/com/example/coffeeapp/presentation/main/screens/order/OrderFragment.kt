package com.example.coffeeapp.presentation.main.screens.order

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeapp.R
import com.example.coffeeapp.databinding.FragmentOrderBinding
import com.example.coffeeapp.presentation.main.CoffeeActivity
import com.example.coffeeapp.presentation.main.screens.menu.MenuViewModel
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private val orderViewModel by viewModels<OrderViewModel> {
        (activity as CoffeeActivity).mainComponent.shopsViewModelFactory()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_order,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterz = OrderAdapter(
            orderViewModel.getList()
        ) { a, b -> true }
        binding.orderRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = adapterz
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}