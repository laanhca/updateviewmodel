package com.example.updateviewmodel.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.updateviewmodel.model.SongSearch
import com.example.updateviewmodel.databinding.ItemSongBinding
import com.example.updateviewmodel.viewmodel.SongViewModel

class SongAdapter(private val callBack:CallBack) :RecyclerView.Adapter<SongAdapter.SongHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongHolder {
        return SongHolder(
            ItemSongBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount():Int{
        if(  callBack.getModel().songs.value == null){
            return 0
        }else{
            return callBack.getModel().songs.value!!.size
        }
    }

    override fun onBindViewHolder(holder: SongHolder, position: Int) {
        holder.binding.itemData=callBack.getModel().songs.value!![position]
        holder.binding.root.setOnClickListener {
            callBack.onClickItem(
                holder.adapterPosition
            )
        }
    }


    interface CallBack{
        fun onClickItem(position:Int)
        fun getModel():SongViewModel
    }

    class SongHolder(val binding:ItemSongBinding) :RecyclerView.ViewHolder(binding.root)
}