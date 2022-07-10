package com.example.coffeeapp.presentation.main.screens.menu

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.coffeeapp.R
import com.example.coffeeapp.databinding.FragmentMenuBinding
import com.example.coffeeapp.presentation.main.CoffeeActivity

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!


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
            R.layout.fragment_menu,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.viewmodel = loginViewModel
//        binding.lifecycleOwner = viewLifecycleOwner

//        loginViewModel.status.observe(viewLifecycleOwner, EventObserver { info ->
//            when (info) {
//                is Loading -> {
//                    //show loading
//                }
//                is Error -> {
//                    showMessage(info.message)
//                }
//                is Success -> {
//                    showMessage(getString(R.string.success))
//                }
//            }
//        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}