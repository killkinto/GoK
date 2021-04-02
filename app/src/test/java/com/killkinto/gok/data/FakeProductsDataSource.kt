package com.killkinto.gok.data

import com.killkinto.gok.data.model.Products

class FakeProductsDataSource(private val products: Products) : ProductsDataSource {
    override suspend fun getProducts(): Products {
        return products
    }
}