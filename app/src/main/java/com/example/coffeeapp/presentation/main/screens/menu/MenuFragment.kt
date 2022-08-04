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
import com.example.coffeeapp.common.Utils.launchWhenStarted
import com.example.coffeeapp.data.main.menu.model.MenuItem
import com.example.coffeeapp.databinding.FragmentMenuBinding
import com.example.coffeeapp.presentation.main.CoffeeActivity
import com.example.coffeeapp.presentation.main.screens.shop.ViewModelFactory
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private lateinit var menuAdapter: MenuAdapter
    private val args: MenuFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val menuViewModel by viewModels<MenuViewModel> {
        viewModelFactory
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as CoffeeActivity).appComponent.menuComponent.create(args.shopId).inject(this)
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
//        menuViewModel.state.onEach(binding::setState).launchWhenStarted(lifecycleScope)
        menuViewModel.getMenu().onEach(menuAdapter::submitList).launchWhenStarted(lifecycleScope)
    }

    private fun setAdapter() {

        binding.menuRecycler.apply {
            val addAmount: (MenuItem) -> Unit = { item -> menuViewModel.addAmount(item) }
            val subAmount: (MenuItem) -> Unit = { item -> menuViewModel.subAmount(item) }
            menuAdapter = MenuAdapter(addAmount, subAmount)
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