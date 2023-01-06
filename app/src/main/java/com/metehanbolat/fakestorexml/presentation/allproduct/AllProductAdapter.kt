package com.metehanbolat.fakestorexml.presentation.allproduct

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.metehanbolat.fakestorexml.ProductUIData
import com.metehanbolat.fakestorexml.databinding.AllProductItemBinding
import com.metehanbolat.fakestorexml.util.adapterViewBinding
import com.metehanbolat.fakestorexml.util.loadImage

class AllProductAdapter :
    RecyclerView.Adapter<AllProductAdapter.AllProductViewHolder>() {

    private val items = mutableListOf<ProductUIData>()
    private var onItemClickListener: ((String) -> Unit)? = null

    class AllProductViewHolder(
        val binding: AllProductItemBinding,
        onItemClickListener: ((String) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener { onItemClickListener?.invoke(adapterPosition.toString()) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllProductViewHolder {
        return AllProductViewHolder(
            parent.adapterViewBinding(AllProductItemBinding::inflate),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: AllProductViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            itemName.text = item.name
            itemImage.loadImage(item.imageUrl)
        }
    }

    override fun getItemCount() = items.size

    fun setOnItemClickListener(onItemClickListener: ((String) -> Unit)?) {
        this.onItemClickListener = onItemClickListener
    }

    fun updateProductAdapter(newProducts: List<ProductUIData>) {
        items.apply {
            clear()
            addAll(newProducts)
        }
        notifyItemRangeChanged(0, items.size)
    }

}