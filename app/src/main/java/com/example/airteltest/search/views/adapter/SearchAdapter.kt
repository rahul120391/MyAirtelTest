package com.example.airteltest.search.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.airteltest.databinding.LayoutRowItemBinding
import com.example.airteltest.datamodel.Address
import com.example.airteltest.search.viewmodel.SearchItemViewModel

class SearchAdapter(private val lifeCycleOwner: LifecycleOwner):RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var context: Context? = null
    private val list = ArrayList<Address>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (context == null) {
            context = parent.context
        }
        return ViewHolder(LayoutRowItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
           holder.bind(list[position])
    }


    inner class ViewHolder(private val binding:LayoutRowItemBinding):RecyclerView.ViewHolder(binding.root){
        private var searchItemViewModel: SearchItemViewModel? = null
        fun bind(address: Address){
            searchItemViewModel=SearchItemViewModel(address)
            binding.apply {
                lifecycleOwner = lifeCycleOwner
                rowItemData = searchItemViewModel
                executePendingBindings()
            }
        }
    }

    fun updateData(addressList:List<Address>){
        list.apply {
            clear()
            addAll(addressList)
        }
        notifyDataSetChanged()
    }

}