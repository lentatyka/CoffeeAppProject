package com.example.coffeeapp.presentation.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.coffeeapp.R
import com.example.coffeeapp.common.EventObserver
import com.example.coffeeapp.common.State.*
import com.example.coffeeapp.common.Utils
import com.example.coffeeapp.databinding.FragmentLoginBinding
import com.example.coffeeapp.presentation.ViewModelFactory
import javax.inject.Inject

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val loginViewModel by activityViewModels<LoginViewModel> {
        viewModelFactory
    }

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
                    Utils.showToast(requireContext(), info.message)
                }
                is Success -> {
                    Utils.showToast(requireContext(), getString(R.string.success))
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}