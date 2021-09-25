package com.example.imgurimagefetch.utils

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater

import androidx.annotation.LayoutRes

import android.view.ViewGroup


abstract class ViewHolderBind<M>(parent: ViewGroup, @LayoutRes layout: Int?) :
    RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layout!!, parent, false)) {

    protected var context: Context = parent.context

    abstract fun onBindViewHolder(model: M, position: Int)
}