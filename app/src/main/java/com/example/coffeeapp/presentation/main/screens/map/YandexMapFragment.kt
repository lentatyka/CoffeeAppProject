package com.example.coffeeapp.presentation.main.screens.map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.coffeeapp.R
import com.example.coffeeapp.common.Utils.launchWhenStarted
import com.example.coffeeapp.databinding.FragmentMapBinding
import com.example.coffeeapp.domain.main.shop.model.Shop
import com.example.coffeeapp.presentation.ViewModelFactory
import com.example.coffeeapp.presentation.main.CoffeeActivity
import com.example.coffeeapp.presentation.main.screens.shop.ShopViewModel
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.MapObjectCollection
import com.yandex.mapkit.mapview.MapView
import com.yandex.runtime.ui_view.ViewProvider
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class YandexMapFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var mapView: MapView
    private lateinit var mapObjects: MapObjectCollection

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val mapViewModel by activityViewModels<ShopViewModel> {
        viewModelFactory
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as CoffeeActivity).appComponent.mapComponent.create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(activity as CoffeeActivity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_map,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = binding.mapview
        mapViewModel.shops.onEach {
            setMap(it)
        }.launchWhenStarted(lifecycleScope)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setMap(list: List<Shop>) {
        mapView.map.move(
            CameraPosition(
                Point(list.first().point.latitude, list.first().point.longitude),
                16.0f,
                0.0f,
                0.0f
            ),
            Animation(Animation.Type.SMOOTH, 0f),
            null
        )
        mapObjects = mapView.map.mapObjects
        list.forEach { locationItem ->
            mapObjects.addPlacemark(
                Point(locationItem.point.latitude, locationItem.point.longitude),
                createMarker(locationItem.name)
            )
        }
    }

    private fun createMarker(name: String): ViewProvider {
        val layout = layoutInflater.inflate(R.layout.map_marker, null) as LinearLayout
        val childAt = layout.getChildAt(1) as TextView
        childAt.text = name
        return ViewProvider(layout)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }
}