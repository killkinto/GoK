package com.killkinto.gok

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.killkinto.gok.data.model.Product
import com.killkinto.gok.data.model.Spotlight
import com.killkinto.gok.overview.ProductAdapter
import com.killkinto.gok.overview.SpotlightAdapter
import com.killkinto.gok.overview.Status

@BindingAdapter("imageUrl")
fun ImageView.setPropertyImage(imageUrl: String?) {
    imageUrl?.let {
        val imageUri = imageUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(this.context)
            .load(imageUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(this)
    }
}

@BindingAdapter("statusImage")
fun ImageView.setStatusImage(status: Status) {
    when (status) {
        Status.LOADING -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.loading_animation)
        }
        Status.ERROR, Status.ERROR_CONNECTED  -> {
            this.visibility = View.VISIBLE
            this.setImageResource(R.drawable.ic_baseline_cloud_off_48)
        }
        Status.DONE -> this.visibility = View.GONE
    }
}

@BindingAdapter("listSpotlight")
fun RecyclerView.setListSpotlight(list: List<Spotlight>) {
    val adapter = this.adapter as SpotlightAdapter
    adapter.submitList(list)
}

@BindingAdapter("listProduct")
fun RecyclerView.setListProduct(list: List<Product>) {
    val adapter = this.adapter as ProductAdapter
    adapter.submitList(list)
}