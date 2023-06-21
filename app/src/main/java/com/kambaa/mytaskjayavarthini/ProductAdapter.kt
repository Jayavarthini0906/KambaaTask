package com.kambaa.mytaskjayavarthini

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter(val context: Context,val productList:List<ProductDataItem>):RecyclerView.Adapter<ProductAdapter.ViewHolder>(){

    class ViewHolder(productView:View):RecyclerView.ViewHolder(productView){
        var image:ImageView
        var pId:TextView
        var title:TextView
        var price:TextView
        var description:TextView
        var category:TextView
        var rating:TextView
        val checkbox: CheckBox


        init{
            image=productView.findViewById(R.id.imageView)
            pId=productView.findViewById(R.id.idTextView)
            title=productView.findViewById(R.id.titleTextView)
            price=productView.findViewById(R.id.priceTextView)
            description=productView.findViewById(R.id.descriptionTextView)
            category=productView.findViewById(R.id.categoryTextView)
            rating=productView.findViewById(R.id.ratingTextView)
            checkbox=productView.findViewById(R.id.checkbox)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var productView=LayoutInflater.from(context).inflate(R.layout.product_items,parent,false)
        return ViewHolder(productView)
    }

    override fun getItemCount(): Int {
        return  productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // holder.image.setImageResource(productList[position].getImageUrl());
        val currentItem = productList[position]

        Glide.with(holder.image)
            .load(currentItem.image)
            .into(holder.image)

        holder.pId.text=productList[position].id.toString()
        holder.title.text=productList[position].title
        holder.price.text=productList[position].price.toString()
        holder.description.text=productList[position].description
        holder.category.text=productList[position].category
        holder.rating.text=productList[position].rating.toString()


        holder.checkbox.setOnCheckedChangeListener(null) // Remove previous listener

        holder.checkbox.isChecked = currentItem.isChecked

        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            currentItem.isChecked = isChecked
        }


    }
}