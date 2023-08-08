package com.example.boris


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.boris.databinding.RecyclerviewItemBinding
import com.squareup.picasso.Picasso


class CustomRecyclerAdapter(private var context: Context, var catList: List<Cats>): RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {


    class MyViewHolder(binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val largeTextView = binding.textViewLarge
        val smallTextView = binding.textViewSmall
        val image = binding.imView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val binding =
            RecyclerviewItemBinding
                .inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView.text = catList[position].name
        holder.smallTextView.text = "кот"
        Picasso.get()
            .load(catList[position].img)
            .into(holder.image)
    }

}