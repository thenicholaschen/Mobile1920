package com.github.thenicholaschen.financialtracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.thenicholaschen.financialtracker.R
import com.github.thenicholaschen.financialtracker.data.Balance
import kotlinx.android.synthetic.main.balance_item.view.*

class todoAdapterBalance : ListAdapter<Balance, todoAdapterBalance.BalanceHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Balance>() {
            override fun areItemsTheSame(oldItem: Balance, newItem: Balance): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Balance, newItem: Balance): Boolean {
                return oldItem.amount == newItem.amount && oldItem.description == newItem.description
            }
        }
    }

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BalanceHolder {
        val itemView: View = LayoutInflater.from(parent.context).inflate(R.layout.balance_item, parent, false)
        return BalanceHolder(itemView)
    }

    override fun onBindViewHolder(holder: BalanceHolder, position: Int) {
        val currentBalance: Balance = getItem(position)

        holder.textViewAmount.text = currentBalance.amount.toString()
        holder.textViewDescription.text = currentBalance.description
        holder.textViewDate.text = currentBalance.date
    }

    fun getBalanceAt(position: Int): Balance {
        return getItem(position)
    }

    inner class BalanceHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(getItem(position))
                }
            }
        }

        var textViewAmount: TextView = itemView.text_view_amount_balance
        var textViewDescription: TextView = itemView.text_view_description_balance
        var textViewDate: TextView = itemView.text_view_date_balance
    }

    interface OnItemClickListener {
        fun onItemClick(does: Balance)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}
