package com.example.quizapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp.R
import com.example.quizapp.model.Question

class RecyclerViewAdapter(private val list : List<Question>, private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerViewAdapter.QuizHolder>() {

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

    inner class QuizHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val questionTextView: TextView = itemView.findViewById(R.id.text_view_question)
        private val answerText1 : TextView = itemView.findViewById(R.id.text_view_answer1)
        private val answerText2 : TextView = itemView.findViewById(R.id.text_view_answer2)
        private val answerText3 : TextView = itemView.findViewById(R.id.text_view_answer3)
        private val answerText4 : TextView = itemView.findViewById(R.id.text_view_answer4)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val currentPosition = this.adapterPosition
            listener.onItemClick(currentPosition)

        }

        fun bindQuestion(question: Question) {
            questionTextView.text = question.text
            answerText1.text = question.answers[0].first
            answerText2.text = question.answers[1].first
            answerText3.text = question.answers[2].first
            answerText4.text = question.answers[3].first
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

