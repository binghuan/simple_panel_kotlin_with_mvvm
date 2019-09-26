package com.bh.android.mysample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bh.android.mysample.databinding.RoutingFragmentBinding
import com.bh.android.mysample.ui.main.common.RoutingListAdapter

class RoutingFragment : Fragment() {

    companion object {
        fun newInstance() = RoutingFragment()
    }

    private lateinit var viewModel: RoutingFragmentViewModel
    lateinit var binding: RoutingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(RoutingFragmentViewModel::class.java)

        binding = RoutingFragmentBinding.inflate(inflater, container, false)
        context ?: return binding.root
        val adapter = RoutingListAdapter() { route ->
            viewModel.deleteStop(route)
        }
        binding.routeList.adapter = adapter

//        binding.addPlant.setOnClickListener {
//            navigateToPlantListPage()
//        }


        binding.buttonExpand.setOnClickListener({ view -> viewModel.openRoutingPanel() })
        binding.buttonClosePanel.setOnClickListener({ view -> viewModel.closeRoutingPanel() })
        binding.buttonAdd.setOnClickListener({ view -> viewModel.addNewStop() })

        subscribeUi(adapter)

        viewModel.createRoutingList()

        return binding.root
    }

    private fun subscribeUi(adapter: RoutingListAdapter) {
        viewModel.routeList.observe(viewLifecycleOwner, Observer { newRoutingList ->
            adapter.submitList(newRoutingList)
            binding.isAbleToAddItem = newRoutingList.size < 4
        })

        viewModel.isExpanded.observe(viewLifecycleOwner, Observer { isExpanded ->
            binding.isExpanded = isExpanded

        })
    }

}
