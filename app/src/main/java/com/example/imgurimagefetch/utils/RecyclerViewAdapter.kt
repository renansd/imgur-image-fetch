package com.example.imgurimagefetch.utils

import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class RecyclerViewAdapter<M>(
    private val sourceTag: String?,
    type: Class<out ViewHolderBind<M>?>,
    list: ArrayList<M>?,
    listener: OnInteractionListener?) : RecyclerView.Adapter<ViewHolderBind<M>>() {

    interface OnInteractionListener {
        fun <M> onIemClicked(item: M)
    }

    private val listener: OnInteractionListener?
    protected val list: ArrayList<M>?
    protected val type: Class<*>

    constructor(
        type: Class<out ViewHolderBind<M>?>,
        list: ArrayList<M>?,
        interactor: OnInteractionListener?
    ) : this("", type, list, interactor)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderBind<M> {
        return create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolderBind<M>, position: Int) {
        val item: M? = list!![position]
        if (item != null) {
            holder.onBindViewHolder(item, position)
            if (listener != null) holder.itemView.setOnClickListener { view: View? ->
                listener.onIemClicked(
                    item
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.let {
            it.size
        } ?: 0
    }

    fun clearData() {
        list?.clear()
        notifyDataSetChanged()
    }

    fun insertData(items: ArrayList<M>?) {
        items?.let {
            list?.addAll(it)
            notifyDataSetChanged()
        }
    }

    private fun create(viewGroup: ViewGroup): ViewHolderBind<M> {
        return if (TextUtils.isEmpty(sourceTag)) type.getDeclaredConstructor(viewGroup.javaClass)
            .newInstance(viewGroup) as ViewHolderBind<M> else (type.getDeclaredConstructor(
            String::class.java, viewGroup.javaClass
        ).newInstance(sourceTag, viewGroup) as ViewHolderBind<M>)
    }

    init {
        this.type = type
        this.list = list
        this.listener = listener
    }
}