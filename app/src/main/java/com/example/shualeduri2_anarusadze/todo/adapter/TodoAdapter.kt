package com.example.shualeduri2_anarusadze.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shualeduri2_anarusadze.R
import com.example.shualeduri2_anarusadze.todo.model.TodoModel

class TodoAdapter(private val list: List<TodoModel>) :
    RecyclerView.Adapter<TodoAdapter.RecyclerViewHolder>() {

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val statusImageView: ImageView = itemView.findViewById(R.id.statusImageView)

        fun bindTodo(todo: TodoModel) {
            titleTextView.text = todo.title
            statusImageView.setBackgroundResource(
                if (todo.completed) R.drawable.ic_complete
                else R.drawable.ic_not_complete
            )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        return RecyclerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.todo_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bindTodo(list[position])
    }

    override fun getItemCount() = list.size

}