package com.example.googleanalytics01

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_product.view.*

class productAdapter(var activity: Activity?, var data: ArrayList<product>, val click: onClick) :
    RecyclerView.Adapter<productAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val imageProduct = itemView.imageProduct
        val nameProdict = itemView.nameProduct
        val priceProduct = itemView.priceProduct
        val detailsProduct = itemView.detailsProduct
        val cardProduct = itemView.cardView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(activity).inflate(R.layout.item_product, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nameProdict.text = data[position].name
        //holder.imageProduct.setImageResource(data[position].image)
        holder.priceProduct.text = data[position].price.toString()
        holder.detailsProduct.text = data[position].details
        holder.cardProduct.setOnClickListener {
            click.onClickItem(holder.adapterPosition)
        }


    }

    interface onClick {
        fun onClickItem(position: Int) {


        }
    }

}




