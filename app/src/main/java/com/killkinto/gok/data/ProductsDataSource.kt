package com.killkinto.gok.data

import com.killkinto.gok.data.model.Products

interface ProductsDataSource {
    /**
     * Obtem os produtos remotamente
     */
    suspend fun getProducts() : Products
}