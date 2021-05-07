package com.example.newsexpress.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsexpress.R
import com.example.newsexpress.model.SavedNewsData

class SaveAdapter  (val context: Context, val data: ArrayList<SavedNewsData>):
    RecyclerView.Adapter<SaveAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.rvNewsImage)
        val title = view.findViewById<TextView>(R.id.rvNewsTitle)
        val source = view.findViewById<TextView>(R.id.rvNewsSource)
        val date = view.findViewById<TextView>(R.id.rvNewsDate)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_itemview,parent,false)
        return SaveAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
     return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val article = data[position]
            holder.itemView.apply {
                holder.title.text = article.title
                holder.date.text = article.pulished_at.substring(0,10)
                if (article.image != null){
                    Glide.with(this).load(article.image).into(holder.image)
                }
                if (article.author != null){
                    holder.source.text = article.author
                }
                else{
                    holder.source.text = "No Source Available"
                }
                setOnClickListener {
                    val bundle = Bundle().apply {
                        putSerializable("article",article)
                    }
                }
            }

        }

    }