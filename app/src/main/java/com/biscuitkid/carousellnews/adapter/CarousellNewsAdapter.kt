package com.biscuitkid.carousellnews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.biscuitkid.carousellnews.R
import com.biscuitkid.carousellnews.api.ResponseCarousellNews
import com.biscuitkid.carousellnews.utils.getDateAgo
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

enum class Sort {
    RECENT, POPULAR
}

class CarousellNewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var list =  listOf<ResponseCarousellNews>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_news, parent, false
        )
        return CarousellNewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return this.list.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        Glide.with(holder.itemView).load(list[position].getBannerUrl()).centerCrop().into(holder.itemView.imageView)

        holder.itemView.title.text = list[position].getTitle()
        holder.itemView.desc.text = list[position].getDescription()
        list[position].getTimeCreated()?.let {
            holder.itemView.date.text = getDateAgo(it)
        }
    }


    fun addAll(itemList: List<ResponseCarousellNews>) {
        list = itemList.sortedBy { it.getTimeCreated() }
        notifyDataSetChanged()
    }

    fun sortBy(sort: Sort) {
        list = if (sort == Sort.RECENT) {
            list.sortedByDescending { it.getTimeCreated() }
        } else {
            list.sortedWith(compareBy({it.getRank()},{it.getTimeCreated()}))
        }

        notifyDataSetChanged()
    }

    internal class CarousellNewsViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
