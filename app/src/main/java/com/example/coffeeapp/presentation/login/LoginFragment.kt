package com.example.coffeeapp.presentation.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.coffeeapp.R
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by lazy {
        (activity as LoginActivity).loginViewModel
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

        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.status.observe(viewLifecycleOwner){info ->
            if(info is Resource.Error){
                Log.d("TAG", "${info.message}")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}