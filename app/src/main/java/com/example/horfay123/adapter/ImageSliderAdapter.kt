package com.example.horfay123.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.horfay123.R
import com.smarteist.autoimageslider.SliderView
import com.smarteist.autoimageslider.SliderViewAdapter

class ImageSliderAdapter(val imageView:ArrayList<Int>):SliderViewAdapter<ImageSliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(view: View):SliderViewAdapter.ViewHolder(view){

        val image=view.findViewById<ImageView>(R.id.slider_view)

    }

    override fun getCount(): Int {
        return imageView.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): SliderViewHolder {
        val layoutInflater=LayoutInflater.from(parent?.context).inflate(R.layout.slider_view_item,null)
        return SliderViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(viewHolder: SliderViewHolder?, position: Int) {
        val image=imageView[position]
        viewHolder?.image?.setImageResource(image)
    }
}