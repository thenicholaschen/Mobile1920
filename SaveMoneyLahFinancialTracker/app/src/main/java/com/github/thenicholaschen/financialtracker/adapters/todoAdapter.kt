package com.github.thenicholaschen.financialtracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.thenicholaschen.financialtracker.R
import com.github.thenicholaschen.financialtracker.data.Expense
import kotlinx.android.synthetic.main.expense_item.view.*

class todoAdapter : ListAdapter<Expense, todoAdapter.ExpenseHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Expense>() {
            override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
                return oldItem.amount == newItem.amount && oldItem.description == newItem.description
            }
        }
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.expense_item, parent, false)
        return ExpenseHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseHolder, position: Int) {
        val currentExpense: Expense = getItem(position)

        holder.textViewAmount.text = currentExpense.amount.toString()
        holder.textViewDescription.text = currentExpense.description
        holder.textViewDate.text = currentExpense.date
    }

    fun getExpenseAt(position: Int): Expense {
        return getItem(position)
    }

    inner class ExpenseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        var textViewAmount: TextView = itemView.text_view_amount
        var textViewDescription: TextView = itemView.text_view_description
        var textViewDate: TextView = itemView.text_view_date
    }

    interface OnItemClickListener {
        fun onItemClick(expense: Expense)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
