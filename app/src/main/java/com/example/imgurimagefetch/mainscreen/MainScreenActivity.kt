package com.example.imgurimagefetch.mainscreen

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.imgurimagefetch.R
import com.example.imgurimagefetch.utils.RecyclerViewAdapter
import com.example.network.imagefetch.model.ImageFetchResponse
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject


class MainScreenActivity : AppCompatActivity(), MainScreenContract.View, RecyclerViewAdapter.OnInteractionListener {
    private val viewModel: MainScreenContract.ViewModel? by inject(MainScreenContract.ViewModel::class.java) { parametersOf(this) }

    private var recyclerView: RecyclerView? = null
    private var loading: View? = null

    private var items: ArrayList<ImageFetchResponse.ImageFetchData?> = ArrayList()
    private var adapter: RecyclerViewAdapter<ImageFetchResponse.ImageFetchData?>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        recyclerView = findViewById(R.id.rv_image_list)
        loading = findViewById(R.id.ll_loading_layout)

        adapter = RecyclerViewAdapter(ImageItemsViewHolder::class.java, items, this)
        recyclerView?.adapter = adapter

        val gridLayoutManager = GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false)
        recyclerView?.layoutManager = gridLayoutManager
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getCatImages(0)
    }

    override fun showLoading() {
        loading?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loading?.visibility = View.GONE
    }

    override fun onSuccess(result: ImageFetchResponse) {
        adapter?.clearData()
        adapter?.insertData(result.data)
    }

    override fun onError(throwable: Throwable) {
        Toast.makeText(this, resources.getString(R.string.network_error), Toast.LENGTH_LONG).show()
    }

    override fun <M> onIemClicked(item: M) {
        if (item is ImageFetchResponse.ImageFetchData?) {
            item?.images?.get(0)?.let { showImage(it.link) }
        }
    }

    private fun showImage(imagePath: String) {
        val builder = Dialog(this)
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE)
        builder.window?.setBackgroundDrawable(
            ColorDrawable(Color.TRANSPARENT)
        )
        builder.window?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.image_placeholder))

        val imageView = ImageView(this)

        val options = RequestOptions()

        Glide.with(this)
            .load(imagePath)
            .apply(options.skipMemoryCache(true))
            .apply(options.diskCacheStrategy(DiskCacheStrategy.NONE))
            .apply(options.frame(3000))
            .apply(options.override(1000, 1000))
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(imageView)

        builder.addContentView(
            imageView, RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        builder.show()
    }
}