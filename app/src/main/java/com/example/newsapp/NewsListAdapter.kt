package com.example.newsapp

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter( private val listener: NewsItemClicked):RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_news, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
           return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
            val currentItem = items[position]
           holder.titleView.text = currentItem.title

        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.imageView)
    }

    fun updateNews(updatedNews: ArrayList<News>){
       items.clear()
       items.addAll(updatedNews)
        notifyDataSetChanged()
   }

}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView = itemView.findViewById<TextView>(R.id.title)
    val imageView = itemView.findViewById<ImageView>(R.id.iv)

}

interface NewsItemClicked{
    fun onItemClicked(item:News)
}