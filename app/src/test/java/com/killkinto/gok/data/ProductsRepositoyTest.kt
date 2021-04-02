package com.killkinto.gok.data

import com.killkinto.gok.data.model.Cash
import com.killkinto.gok.data.model.Product
import com.killkinto.gok.data.model.Products
import com.killkinto.gok.data.model.Spotlight
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.*

@ExperimentalCoroutinesApi
class ProductsRepositoyTest {

    private lateinit var repository: IProductsRepository
    private lateinit var remoteDataSource: ProductsDataSource

    @Before
    fun init() {
        remoteDataSource = FakeProductsDataSource(createProducts())
        repository = ProductsRepository(remoteDataSource)
    }

    @Test
    fun `obter produtos remotos`() = runBlockingTest {
        //Quando solitar os produtos remotos
        val products = repository.getProducts()

        assertThat(products, IsEqual(createProducts()))
    }


    private fun createProducts() : Products {
        val spotlight = listOf<Spotlight>(
            Spotlight("Recarga", "dsf fsd", "ds fds f"),
            Spotlight("Uber", "dsf fsd", "ds fds f"),
        )
        val products = listOf<Product>(
            Product("XBOX", "dsf fsd", "ds fds f"),
            Product("Google Play", "dsf fsd", "ds fds f"),
            Product("Level up", "dsf fsd", "ds fds f"),
        )
        val cash = Cash("digio Cash", "dfsafds", "fasdfs fsdf")

        return Products(spotlight, products, cash)
    }
}