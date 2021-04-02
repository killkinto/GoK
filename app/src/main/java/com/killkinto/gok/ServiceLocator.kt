package com.killkinto.gok

import com.killkinto.gok.data.IProductsRepository
import com.killkinto.gok.data.ProductsRepository
import com.killkinto.gok.data.remote.ProductsApi
import com.killkinto.gok.data.remote.ProductsRemoteDataSource
import com.killkinto.gok.data.remote.retrofit
import kotlinx.coroutines.Dispatchers

object ServiceLocator {
    private var productsRepository: IProductsRepository? = null

    fun provideProductsRepository() = productsRepository ?: createProductRepository()

    private fun createProductRepository(): IProductsRepository {
        return ProductsRepository(createProductsRemoteDataSource()).also {
            this.productsRepository = it
        }
    }

    private fun createProductsRemoteDataSource() =
        ProductsRemoteDataSource(ProductsApi(retrofit), Dispatchers.Default)
}