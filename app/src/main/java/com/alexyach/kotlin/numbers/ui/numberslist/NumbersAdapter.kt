package com.alexyach.kotlin.numbers.ui.numberslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexyach.kotlin.numbers.databinding.ItemNumberListBinding
import com.alexyach.kotlin.numbers.model.NumberModel

class NumbersAdapter(
    private var dataList: List<NumberModel>,
    private val listener: IOnItemClickAdapter
) : RecyclerView.Adapter<NumbersAdapter.NumbersViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumbersViewHolder {
        val binding = ItemNumberListBinding.inflate(LayoutInflater.from(parent.context))

        return NumbersViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NumbersViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    /** ViewHolder */
    inner class NumbersViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemNumberListBinding.bind(itemView)

        fun bind(number: NumberModel) {
            binding.itemNumber.text = number.number
            binding.itemFact.text = number.fact

            itemView.setOnClickListener {
                listener.onItemClickAdapter(number)
            }
        }
    }

}

