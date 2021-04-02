package com.killkinto.gok.data.remote

import com.killkinto.gok.data.ProductsDataSource
import com.killkinto.gok.data.model.Products
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ProductsRemoteDataSource(
    private val api: ProductsApi,
    private val dispatcher: CoroutineDispatcher
) : ProductsDataSource {

    override suspend fun getProducts(): Products = withContext(dispatcher) {
        return@withContext try {
            api.retrofitService.getProducts()
        } catch (e: Exception) {
            throw e
        }
    }
}