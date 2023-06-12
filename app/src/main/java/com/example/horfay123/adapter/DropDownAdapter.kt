package com.example.horfay123.adapter

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView


class DropDownAdapter(context: Context, layout: Int, country: Array<String>) :
    ArrayAdapter<String>(context, layout, country) {

    override fun isEnabled(position: Int): Boolean {
        return position != 0
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        // Get the item view
        val view = super.getDropDownView(
            position, convertView, parent
        )
        val textView = view as TextView
        if (position == 0) {
            // Set the hint text color gray
            textView.setTextColor(Color.GRAY)
        } else {
            textView.setTextColor(Color.BLACK)
        }
        return view
    }

    /*  override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

          return super.getView(position, convertView, parent)
      }*/
}
