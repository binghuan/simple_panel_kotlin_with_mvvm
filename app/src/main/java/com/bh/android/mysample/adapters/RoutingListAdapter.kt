package com.bh.android.mysample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bh.android.mysample.R
import com.bh.android.mysample.databinding.RoutingItemBinding
import com.bh.android.mysample.data.Route

class RoutingListAdapter(
    private val deleteButtonClickCallback: ((Route) -> Unit)?
) : ListAdapter<Route, RecyclerView.ViewHolder>(RoutingDiffCallback()) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val route = getItem(position)

        (holder as RouteViewHolder).bind(route)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RouteViewHolder(
            RoutingItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ),
            deleteButtonClickCallback
        )
    }

    class RouteViewHolder(
        private val binding: RoutingItemBinding,
        private val deleteButtonClickCallback: ((Route) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Route) {

            binding.apply {
                route = item

                when (route?.type) {
                    1 -> imageView.setImageResource(R.mipmap.no_path)
                    2 -> imageView.setImageResource(R.mipmap.ic_car_for_destination)
                    3 -> imageView.setImageResource(R.mipmap.icon_22)
                    else -> imageView.setImageResource(R.mipmap.no_path)
                }

                buttonDelete.setOnClickListener {
                    binding.route?.let {
                        deleteButtonClickCallback?.invoke(it)
                    }
                }

                executePendingBindings()
            }
        }
    }
}

private class RoutingDiffCallback : DiffUtil.ItemCallback<Route>() {

    override fun areItemsTheSame(oldItem: Route, newItem: Route): Boolean {
        return oldItem.address == newItem.address
    }

    override fun areContentsTheSame(oldItem: Route, newItem: Route): Boolean {
        return oldItem == newItem
    }
}