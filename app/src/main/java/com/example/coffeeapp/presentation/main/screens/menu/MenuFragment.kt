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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeapp.R
import com.example.coffeeapp.common.Resource
import com.example.coffeeapp.data.main.menu.ShopMenu
import com.example.coffeeapp.data.main.shops.remote.Point
import com.example.coffeeapp.databinding.FragmentMenuBinding
import com.example.coffeeapp.domain.main.shops.model.ShopLocation
import com.example.coffeeapp.presentation.main.CoffeeActivity
import com.example.coffeeapp.presentation.main.screens.shops.ShopsLocationAdapter

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuAdapter: MenuAdapter
    private val args: MenuFragmentArgs by navArgs()

    private val menuViewModel by viewModels<MenuViewModel>{
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
    }

    private fun setViewModel(){
        lifecycleScope.launchWhenStarted {
            menuViewModel.status.observe(viewLifecycleOwner) { menu ->
                when(menu){
                    is Resource.Loading ->{
                        //show loading
                    }
                    is Resource.Success ->{
                        menuAdapter.submitList(menu.data)
                    }
                    is Resource.Error ->{
                        //show error
                    }
                }
            }
        }
    }

    private fun setAdapter(){
        menuAdapter = MenuAdapter {}
        binding.menuRecycler.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2)
            adapter = menuAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}