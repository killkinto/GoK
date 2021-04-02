package com.killkinto.gok.data

import com.killkinto.gok.data.model.Products

class ProductsRepository(private val remoteDataSource: ProductsDataSource) : IProductsRepository {
    override suspend fun getProducts(): Products {
        return remoteDataSource.getProducts()
    }
}