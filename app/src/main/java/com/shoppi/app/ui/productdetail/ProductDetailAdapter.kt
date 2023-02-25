package com.shoppi.app.ui.productdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shoppi.app.databinding.ItemProductDetailImageBinding
import com.shoppi.app.model.Descriptions
import com.shoppi.app.model.Product

class ProductDetailAdapter(): ListAdapter<Descriptions, ProductDetailAdapter.ProductDetailViewHolder>(ProductDescriptiomDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductDetailViewHolder {
        val binding = ItemProductDetailImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductDetailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ProductDetailViewHolder(private val binding: ItemProductDetailImageBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(descriptions: Descriptions){
            binding.imageUrl = descriptions.imageUrl
            binding.executePendingBindings()
        }
    }
}

class ProductDescriptiomDiffCallback: DiffUtil.ItemCallback<Descriptions>() {
    override fun areItemsTheSame(oldItem: Descriptions, newItem: Descriptions): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Descriptions, newItem: Descriptions): Boolean {
        return oldItem == newItem
    }

}