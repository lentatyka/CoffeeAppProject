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
import com.example.coffeeapp.common.State.*
import com.example.coffeeapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
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
            R.layout.fragment_login,
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
                is Loading -> {
                    //show loading
                }
                is Error -> {
                    showMessage(info.message)
                }
                is Success -> {
                    showMessage(getString(R.string.success))
                    //GOTO COFFEE ACTIVITY
                }
            }
        })
        binding.btnRegistration.setOnClickListener {
            LoginFragmentDirections.actionLoginFragmentToRegistrationFragment().also {
                findNavController().navigate(it)
            }
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