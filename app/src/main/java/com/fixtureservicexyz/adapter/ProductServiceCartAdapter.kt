package com.fixtureservicexyz.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fixtureservicexyz.databinding.MyCartItemBinding
import com.fixtureservicexyz.models.ProductService

class ProductServiceCartAdapter(private val productServiceList:List<ProductService>) : RecyclerView.Adapter<ProductServiceCartAdapter.ProductServiceCatViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductServiceCatViewHolder {
        val binding = MyCartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductServiceCatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductServiceCatViewHolder, position: Int) {
        val productService = productServiceList[position]
        holder.bind(productService)
    }

    override fun getItemCount() = productServiceList.size

    inner class ProductServiceCatViewHolder(private val view: MyCartItemBinding) : RecyclerView.ViewHolder(view.root){
        @SuppressLint("SetTextI18n")
        fun bind(productService: ProductService){
            view.tvProductServiceTitle.text = productService.title
            view.tvProductServiceUnit.text = "${productService.toBuy} unit(s)"
        }
    }
}