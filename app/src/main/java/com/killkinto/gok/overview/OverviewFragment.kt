package com.killkinto.gok.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.killkinto.gok.GoKApplication
import com.killkinto.gok.databinding.OverviewFragmentBinding

class OverviewFragment : Fragment() {
    private val viewModel by viewModels<OverviewViewModel> {
        OverviewViweModelFactory(
            (requireContext().applicationContext as GoKApplication).productsRepository,
            requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = OverviewFragmentBinding.inflate(inflater).apply {
            lifecycleOwner = viewLifecycleOwner
            viewmodel = viewModel
            spotlightList.adapter = SpotlightAdapter()
            productsList.adapter = ProductAdapter()
        }

        return binding.root
    }
}