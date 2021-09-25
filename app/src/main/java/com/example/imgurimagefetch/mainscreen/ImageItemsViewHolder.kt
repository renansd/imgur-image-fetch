package com.example.imgurimagefetch.mainscreen


import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.imgurimagefetch.R
import com.example.imgurimagefetch.utils.ViewHolderBind
import com.example.network.imagefetch.model.ImageFetchResponse


class ImageItemsViewHolder(parent: RecyclerView) : ViewHolderBind<ImageFetchResponse.ImageFetchData?>(parent, R.layout.item_image_layout) {

    private var ivImageHolder: ImageView? = null

    init {
        ivImageHolder = itemView.findViewById(R.id.iv_image_holder)
    }

    override fun onBindViewHolder(model: ImageFetchResponse.ImageFetchData?, position: Int) {
        model?.let { modelData ->

            ivImageHolder?.let { imageView ->

                modelData.images?.let { images ->

                    if (images.size > 0) {
                        val options = RequestOptions()

                        Glide.with(context)
                            .load(images[0].link)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .thumbnail( 0.5f )
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .apply(options.centerCrop())
                            .apply(options.override(120, 120))
                            .apply(options.frame(3000))
                            .into(imageView)
                    }
                }
            }
        }
    }
}