package com.example.dogsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.dogsapp.R
import com.example.dogsapp.databinding.ItemDogBinding
import com.example.dogsapp.net.Dog
import java.lang.reflect.Type

class DogAdapter: ListAdapter<Dog,DogAdapter.ViewHolder>(DogItemCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder{

        return ViewHolder(
            ItemDogBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding:ItemDogBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(dog:Dog){
            with(binding){
                name.text = dog.name
                trainability.text ="Trainability: "  + dog.trainability
                protectiveness.text = "Protectiveness: " + dog.protectiveness
                energy.text = "Energy: " + dog.energy
                minLifeExpectancy.text =  "Minimum Life Expectancy : "+ dog.minLifeExpectancy
                Glide.with(dogImage)
                    .load(dog.imageLink)
                    .into(dogImage)

            }
        }
    }

    private class DogItemCallback: DiffUtil.ItemCallback<Dog>() {
        override fun areItemsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Dog, newItem: Dog): Boolean {
            return oldItem == newItem
        }

    }
}