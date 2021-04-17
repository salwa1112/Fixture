package com.fixtureservicexyz.utils

import com.fixtureservicexyz.models.ProductService

object ShoppingDBTemp {
    private var productsServicesList = mutableListOf<ProductService>()


    fun addPlumbingService(productService: ProductService) {
        var isExit = false
        productsServicesList.find { p->p.id == productService.id}?.apply {
            toBuy = productService.toBuy
            isExit = true
        }
        if(!isExit) productsServicesList.add(productService)
    }

    fun removePlumbingService(productService: ProductService){
        productsServicesList.remove(productService)
    }

    fun fetchAllProductsServices() = productsServicesList

}