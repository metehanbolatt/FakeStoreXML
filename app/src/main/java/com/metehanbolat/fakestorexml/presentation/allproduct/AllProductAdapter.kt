package com.metehanbolat.fakestorexml.presentation.allproduct

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metehanbolat.fakestorexml.ProductUIData
import com.metehanbolat.fakestorexml.databinding.AllProductItemBinding

class AllProductAdapter(private val productList: List<ProductUIData>, private val cardClickListener: (String) -> Unit): RecyclerView.Adapter<AllProductAdapter.AllProductViewHolder>() {
    class AllProductViewHolder(val binding: AllProductItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductViewHolder {
        val binding = AllProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AllProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllProductViewHolder, position: Int) {
        val item = productList[position]
        holder.binding.apply {
            itemName.text = item.name
            Glide.with(this.itemImage).load(item.imageUrl).into(this.itemImage)
            root.setOnClickListener {
                cardClickListener.invoke(item.id)
            }
        }
    }

    override fun getItemCount() = productList.size


}