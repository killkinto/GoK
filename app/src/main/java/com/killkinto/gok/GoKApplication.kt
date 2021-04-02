package com.killkinto.gok

import android.app.Application
import com.killkinto.gok.data.IProductsRepository

class GoKApplication : Application() {
    val productsRepository: IProductsRepository
        get() = ServiceLocator.provideProductsRepository()
}