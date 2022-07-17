package com.example.coffeeapp.presentation.main.screens.menu

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
import com.example.coffeeapp.R
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.databinding.FragmentMenuBinding
import com.example.coffeeapp.presentation.main.CoffeeActivity
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuAdapter: MenuAdapter
    private val args: MenuFragmentArgs by navArgs()

    private val menuViewModel by viewModels<MenuViewModel> {
        (activity as CoffeeActivity).mainComponent.menuViewModelFactory().create(args.shopId)
    }


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
        setAdapter()
        setViewModel()
        binding.showCartBtn.setOnClickListener {
            MenuFragmentDirections.actionMenuFragmentToTotalFragment().also {
                findNavController().navigate(it)
            }
        }
    }

    private fun setViewModel() {
        lifecycleScope.launchWhenStarted {
            menuViewModel.status.observe(viewLifecycleOwner) { menu ->
                when (menu) {
                    is Resource.Loading -> {
                        //show loading
                    }
                    is Resource.Success -> {
                        menuAdapter.submitList(menuViewModel.getList())
                       // menuAdapter.submitList(menu.data)
                    }
                    is Resource.Error -> {
                        //show error
                    }
                }
            }
        }
    }

    private fun setAdapter() {
        menuAdapter = MenuAdapter { id, isAdd ->
            if (isAdd)
                menuViewModel.addAmount(id)
            else
                menuViewModel.subAmount(id)
        }
        binding.menuRecycler.apply {
            val flexBox = FlexboxLayoutManager(requireContext()).apply {
                justifyContent = JustifyContent.SPACE_AROUND
                alignItems = AlignItems.CENTER

            }
            layoutManager = flexBox
            adapter = menuAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}