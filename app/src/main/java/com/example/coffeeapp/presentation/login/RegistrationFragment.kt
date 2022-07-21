package com.example.coffeeapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.coffeeapp.R
import com.example.coffeeapp.common.EventObserver
import com.example.coffeeapp.common.State
import com.example.coffeeapp.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    val loginViewModel by lazy {
        (activity as LoginActivity).loginViewModel
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_registration,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewmodel = loginViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        loginViewModel.status.observe(viewLifecycleOwner, EventObserver { info ->
            when (info) {
                is State.Loading -> {
                    //show loading
                }
                is State.Error -> {
                    showMessage(info.message)
                }
                is State.Success -> {
                    showMessage(getString(R.string.success))
                    findNavController().popBackStack()
                }
            }
        })
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}