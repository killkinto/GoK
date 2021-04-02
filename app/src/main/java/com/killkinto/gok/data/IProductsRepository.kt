package com.killkinto.gok.data

import com.killkinto.gok.data.model.Products

interface IProductsRepository {
    suspend fun getProducts() : Products
}