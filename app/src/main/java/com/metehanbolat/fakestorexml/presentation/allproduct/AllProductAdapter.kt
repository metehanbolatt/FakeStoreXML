package com.metehanbolat.fakestorexml.presentation.allproduct

import android.view.View
import com.metehanbolat.fakestorexml.ProductUIData
import com.metehanbolat.fakestorexml.R
import com.metehanbolat.fakestorexml.databinding.AllProductItemBinding
import com.metehanbolat.fakestorexml.presentation.base.BaseAdapter
import com.metehanbolat.fakestorexml.util.loadImage

class AllProductAdapter : BaseAdapter<ProductUIData>(R.layout.all_product_item) {

    override fun bind(
        itemView: View,
        item: ProductUIData,
        position: Int,
        viewHolder: BaseViewHolderImpl
    ) {
        val binding =  AllProductItemBinding.bind(itemView)
        binding.apply {
            itemName.text = item.name
            itemImage.loadImage(item.imageUrl)
        }
    }
}