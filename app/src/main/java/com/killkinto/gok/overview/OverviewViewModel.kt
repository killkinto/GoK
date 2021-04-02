package com.killkinto.gok.overview

import android.app.Application
import androidx.lifecycle.*
import com.killkinto.gok.data.IProductsRepository
import com.killkinto.gok.data.model.Cash
import com.killkinto.gok.data.model.Product
import com.killkinto.gok.data.model.Spotlight
import com.killkinto.gok.util.isInternetAvailable
import kotlinx.coroutines.launch

enum class Status { LOADING, ERROR, ERROR_CONNECTED, DONE }

class OverviewViewModel(private val repositoy: IProductsRepository, private val app: Application) :
    ViewModel() {

    private val _status = MutableLiveData<Status>()
    val status: LiveData<Status>
        get() = _status

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>>
        get() = _products

    private val _spolight = MutableLiveData<List<Spotlight>>()
    val spolight: LiveData<List<Spotlight>>
        get() = _spolight

    private val _cash = MutableLiveData<Cash>()
    val cash: LiveData<Cash>
        get() = _cash

    init {
        _products.value = emptyList()
        _spolight.value = emptyList()
        this.loadProducts()
    }

    private fun loadProducts() {
        if (isInternetAvailable(app.applicationContext)) {
            viewModelScope.launch {
                _status.value = Status.LOADING
                try {
                    val products = repositoy.getProducts()
                    _products.value = products.products
                    _spolight.value = products.spotlight
                    _cash.value = products.cash
                    _status.value = Status.DONE
                } catch (e: Exception) {
                    _status.value = Status.ERROR
                }
            }
        } else {
            _status.value = Status.ERROR_CONNECTED
        }
    }
}

class OverviewViweModelFactory(
    private val repositoy: IProductsRepository,
    private val app: Application
) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        (OverviewViewModel(repositoy, app) as T)
}