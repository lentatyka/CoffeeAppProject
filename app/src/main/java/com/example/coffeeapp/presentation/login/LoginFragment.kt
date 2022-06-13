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
import com.example.coffeeapp.common.Resource.*
import com.example.coffeeapp.databinding.FragmentLoginBinding
import javax.inject.Inject

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
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
                    //show error
                }
                is Success -> {
                    Toast.makeText(requireContext(), "test", Toast.LENGTH_LONG).show()
                }
            }
        })
        binding.btnRegistration.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                replace<RegistrationFragment>(R.id.nav_host_fragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}