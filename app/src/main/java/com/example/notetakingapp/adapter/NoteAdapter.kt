package com.example.notetakingapp.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notetakingapp.databinding.NoteLayoutBinding
import com.example.notetakingapp.fragments.HomeFragmentDirections
import com.example.notetakingapp.model.Note
import java.util.Random


class NoteAdapter():Adapter<NoteAdapter.MyViewModel>() {
    class MyViewModel(val binding: NoteLayoutBinding):ViewHolder(binding.root)
        private val differutilcallback= object :DiffUtil.ItemCallback<Note>(){
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id==newItem.id &&
                        oldItem.noteBody==newItem.noteBody &&
                        oldItem.noteTitle==newItem.noteTitle
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem==newItem
            }

        }


    val differ =AsyncListDiffer(this,differutilcallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        return MyViewModel(
            NoteLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        val currentnote=differ.currentList[position]

        holder.binding.Tvnotetitle.text=currentnote.noteTitle
        holder.binding.tvnotebody.text=currentnote.noteBody

        val random=Random()
        val color=Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )

        holder.binding.ibcolor.setBackgroundColor(color)

        holder.itemView.setOnClickListener(){
            val direction =HomeFragmentDirections.
            actionHomeFragmentToUpdateFragment(currentnote)
            it.findNavController().navigate(direction)


        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}