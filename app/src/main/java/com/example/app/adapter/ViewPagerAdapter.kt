package com.example.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.app.databinding.LayoutLightBinding

class ViewPagerAdapter(
    private var title: List<String>,
    private var details: List<String>,
    private var images: List<String>
) : RecyclerView.Adapter<ViewPagerAdapter.PageViewHolder>() {

    @Suppress("DEPRECATION")
    class PageViewHolder(val binding: LayoutLightBinding) : RecyclerView.ViewHolder(binding.root){

        init {
            binding.image.setOnClickListener{v:View ->
                val position = adapterPosition
                Toast.makeText(v.context, "You clicked on item #${position + 1}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PageViewHolder {
        val binding = LayoutLightBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PageViewHolder, position: Int) {
        val item = title[position]
        holder.binding.title.text = item
        holder.binding.details.text = details[position]
        holder.binding.image.setImageResource(images[position].toInt())
    }

    override fun getItemCount(): Int {
        return title.size
    }
}
