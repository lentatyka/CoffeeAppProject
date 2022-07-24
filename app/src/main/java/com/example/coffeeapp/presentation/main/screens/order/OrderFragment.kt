package com.example.coffeeapp.presentation.main.screens.order

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.coffeeapp.databinding.FragmentOrderBinding
import com.example.coffeeapp.presentation.main.CoffeeActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var orderAdapter: OrderAdapter

    private val orderViewModel by viewModels<OrderViewModel> {
        (activity as CoffeeActivity).mainComponent.shopsViewModelFactory()
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
        setAdapter()
        setViewModel()
        binding.confirmOrderBtn.setOnClickListener {
            Utils.showToast(requireContext(), getString(R.string.success))
            OrderFragmentDirections.actionTotalFragmentToShopsFragment().also {
                findNavController().navigate(it)
            }
        }
    }

    private fun setAdapter() {
        binding.orderRecycler.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            orderAdapter = OrderAdapter{ id, isAdd ->
                if (isAdd){
                    orderViewModel.addAmount(id)
                }
                else
                    orderViewModel.subAmount(id)
            }
            adapter = orderAdapter
        }
    }

    private fun setViewModel() {
        orderViewModel.getOrder().onEach(orderAdapter::submitList).launchWhenStarted(lifecycleScope)
        orderViewModel.getTotal().onEach(binding::setTotal).launchWhenStarted(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}