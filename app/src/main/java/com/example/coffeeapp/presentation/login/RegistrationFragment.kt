package com.example.coffeeapp.presentation.login

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.coffeeapp.R
import com.example.coffeeapp.common.EventObserver
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.databinding.FragmentRegistrationBinding
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegistrationBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as LoginActivity).loginComponent.inject(this)
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
                is Resource.Loading -> {
                    //show loading
                }
                is Resource.Error -> {
                    //show error
                }
                is Resource.Success -> {
                    Toast.makeText(requireContext(), "test", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}