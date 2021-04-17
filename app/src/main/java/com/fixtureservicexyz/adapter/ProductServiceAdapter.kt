package com.fixtureservicexyz.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fixtureservicexyz.databinding.PlumbingServicesItemBinding
import com.fixtureservicexyz.models.ProductService

class ProductServiceAdapter(private val productServiceList:List<ProductService>, private val selectedProductService: OnProductServiceClickListener) : RecyclerView.Adapter<ProductServiceAdapter.PlumbingServiceViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlumbingServiceViewHolder {
        val binding = PlumbingServicesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return PlumbingServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlumbingServiceViewHolder, position: Int) {
        val plumbingService = productServiceList[position]
        holder.bind(plumbingService)
    }

    override fun getItemCount(): Int = productServiceList.size

    inner class PlumbingServiceViewHolder(private val binding: PlumbingServicesItemBinding) : RecyclerView.ViewHolder(binding.root){

        @SuppressLint("SetTextI18n")
        fun bind(productService: ProductService){
            binding.tvTitle.text = productService.title
            binding.tvShowPrice.text = "starts from ${productService.priceFrom.toString()}"
            binding.tvDescriptionOne.text = "~ ${productService.description[0]}"
            binding.tvDescriptionTwo.text = "~ ${productService.description[1]}"
            binding.ivPhoto.setImageResource(productService.photoUrl)
            binding.btnNext.setOnClickListener {
                selectedProductService.productServiceClick(productService)
            }


        }
    }

    interface OnProductServiceClickListener{
        fun productServiceClick(serviceSelected:ProductService)
    }
}