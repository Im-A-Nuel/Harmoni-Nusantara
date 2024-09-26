package com.coding.harmoni_nusantara

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions



class ListInstrumentProcess(private val listInstrument: ArrayList<Instrument>) : RecyclerView.Adapter<ListInstrumentProcess.ListViewHolder>() {

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemPhoto : ImageView = itemView.findViewById(R.id.img_item_photo)
        val itemName: TextView = itemView.findViewById(R.id.tv_item_name)
        val itemDesc: TextView = itemView.findViewById(R.id.tv_item_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_instrument, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listInstrument.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listInstrument[position]
        holder.itemName.text = name
        holder.itemDesc.text = description
        Glide.with(holder.itemView.context)
            .load(photo)
            .apply(RequestOptions().transform(RoundedCorners(40)))
            .into(holder.itemPhoto)

        holder.itemView.setOnClickListener {
            val intentDetail = Intent(holder.itemView.context, DetailActivity::class.java)
            intentDetail.putExtra("key_instrument", listInstrument[holder.adapterPosition])
            holder.itemView.context.startActivity(intentDetail)
        }
    }
}