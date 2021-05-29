package com.example.shualeduri2_anarusadze.users.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shualeduri2_anarusadze.R
import com.example.shualeduri2_anarusadze.users.model.UserModel

class UsersAdapter(private val list: List<UserModel>, private val userCallBack: (userId: Int, userName: String) -> Unit) :
    RecyclerView.Adapter<UsersAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        private val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
        private val emailTextView: TextView = itemView.findViewById(R.id.emailTextView)
        private val phoneTextView: TextView = itemView.findViewById(R.id.phoneTextView)
        private val websiteTextView: TextView = itemView.findViewById(R.id.websiteTextView)

        @SuppressLint("SetTextI18n")
        fun bindUser(user: UserModel, userCallBack: (userId: Int, userName: String) -> Unit) {
            nameTextView.text = user.name
            usernameTextView.text = "Username: ${user.username}"
            emailTextView.text = "Email: ${user.email}"
            phoneTextView.text = "Phone: ${user.phone}"
            websiteTextView.text = "Website: ${user.website}"
            itemView.setOnClickListener {
                userCallBack.invoke(user.id, user.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.users_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindUser(list[position], userCallBack )
    }

    override fun getItemCount() = list.size

}