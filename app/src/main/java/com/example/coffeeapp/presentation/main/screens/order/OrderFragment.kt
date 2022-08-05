package com.example.coffeeapp.presentation.main.screens.order

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeapp.R
import com.example.coffeeapp.common.Utils
import com.example.coffeeapp.common.Utils.launchWhenStarted
import com.example.coffeeapp.databinding.FragmentOrderBinding
import com.example.coffeeapp.presentation.main.CoffeeActivity
import com.example.coffeeapp.presentation.main.screens.shop.ViewModelFactory
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    private lateinit var orderAdapter: OrderAdapter

    private val args: OrderFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val orderViewModel by viewModels<OrderViewModel> {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as CoffeeActivity).appComponent.orderComponent.create(args.shopId).inject(this)
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
            orderViewModel.deleteOrder()
            OrderFragmentDirections.actionTotalFragmentToShopsFragment().also {
                findNavController().navigate(it)
            }
        }
    }

    private fun setAdapter() {
        binding.orderRecycler.apply {
            val addAmount: (Int, Int) -> Unit =
                { id, amount -> orderViewModel.addAmount(id, amount) }
            val subAmount: (Int, Int) -> Unit =
                { id, amount -> orderViewModel.subtractAmount(id, amount) }
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            orderAdapter = OrderAdapter(addAmount, subAmount)
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