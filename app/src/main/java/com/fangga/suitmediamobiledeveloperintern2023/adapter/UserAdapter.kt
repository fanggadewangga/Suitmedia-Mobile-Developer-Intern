package com.fangga.suitmediamobiledeveloperintern2023.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fangga.suitmediamobiledeveloperintern2023.databinding.ItemListUserBinding
import com.fangga.suitmediamobiledeveloperintern2023.model.User
import com.fangga.suitmediamobiledeveloperintern2023.util.Constant

class UserAdapter : PagingDataAdapter<User, UserAdapter.UserViewHolder>(DIFF_CALLBACK) {
    class UserViewHolder(private val binding: ItemListUserBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bindView(user: User) {
            binding.apply {
                val fullname =  "${user.firstName} ${user.lastName}"
                tvFullname.text = fullname
                tvEmail.text = user.email
                Glide.with(itemView.context)
                    .load(user.avatar)
                    .circleCrop()
                    .into(ivUserAvatar)

                itemView.setOnClickListener {
                    Constant.SELECTED_USERNAME = fullname
                }
            }
        }
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        user?.let { holder.bindView(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}

