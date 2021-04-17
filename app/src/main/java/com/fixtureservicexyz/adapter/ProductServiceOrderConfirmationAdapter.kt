package com.fixtureservicexyz.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fixtureservicexyz.databinding.OrderConfirmationItemBinding
import com.fixtureservicexyz.models.ProductService

class ProductServiceOrderConfirmationAdapter(private val productServiceList: List<ProductService>) :
    RecyclerView.Adapter<ProductServiceOrderConfirmationAdapter.ProductServiceOrderConfirmationViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductServiceOrderConfirmationViewHolder {
        val binding = OrderConfirmationItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductServiceOrderConfirmationViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductServiceOrderConfirmationViewHolder,
        position: Int
    ) {
        val productService = productServiceList[position]
        holder.bind(productService)
    }

    override fun getItemCount(): Int = productServiceList.size

     inner class ProductServiceOrderConfirmationViewHolder(private val binding: OrderConfirmationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(productService: ProductService) {
            binding.tvProductServiceOrderConfirmationTitle.text = "${productService.title} X${productService.toBuy}"
            binding.tvProductServiceOrderConfirmationTotalPrice.text = "${productService.toBuy*productService.priceFrom}$"
        }
    }
}