package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.model.Question

class RecyclerViewAdapter(private val list : List<Question>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.QuizHolder>() {

    interface OnItemClickListener{
        //fun onItemClick(position: Int)
        fun onDetailsButtonClick(position: Int)
        fun onDeleteButtonClick(position: Int)
    }

    inner class QuizHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val questionTextView: TextView = itemView.findViewById(R.id.text_view_question)
        private val correctAnswer : TextView = itemView.findViewById(R.id.text_view_correct_answer)
        private val detailsButton : Button = itemView.findViewById(R.id.detailsButton)
        private val deleteButton : Button = itemView.findViewById(R.id.deleteButton)

        init{
            //itemView.setOnClickListener(this)
            detailsButton.setOnClickListener(this)
            deleteButton.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val currentPosition = this.adapterPosition
            when(v?.id){
                detailsButton.id -> listener.onDetailsButtonClick(currentPosition)
                deleteButton.id -> listener.onDeleteButtonClick(currentPosition)
                //else -> listener.onItemClick(currentPosition) set listener to the whole recyclerview item
            }
        }

        fun bindQuestion(question: Question) {
            questionTextView.text = question.text
            correctAnswer.text = question.answers[0].first
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizHolder {
        val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.question_item, parent, false)
        return QuizHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuizHolder, position: Int) {
        val currentItem = list[position]
        holder.bindQuestion(currentItem)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}