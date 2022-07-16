package com.example.coffeeapp.presentation.main.screens.order

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.coffeeapp.R
import com.example.coffeeapp.databinding.FragmentOrderBinding
import com.example.coffeeapp.presentation.main.screens.menu.MenuViewModel


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private val  orderViewModel by viewModels<MenuViewModel>()

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
//        lifecycleScope.launchWhenStarted {
//            orderViewModel.status.observe(viewLifecycleOwner) { info ->
//                when (info) {
//                    is Resource.Loading -> {
//                        //show loading
//                    }
//                    is Resource.Error -> {
//
//                    }
//                    is Resource.Success -> {
//                        Log.d("TAG", "ORDER: ${info.data}")
//                    }
//                }
//            }
//        }
        orderViewModel.addAmount(2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}