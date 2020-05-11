package ru.nsu.alcoHelper.presentation.coctailDetails.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_ingredient.view.*
import ru.nsu.alcoHelper.R
import ru.nsu.alcoHelper.data.model.Ingredient

class CoctailIngredientsListAdapter: RecyclerView.Adapter<CoctailIngredientsListAdapter.ViewHolder>() {
    var items: List<Ingredient> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_ingredient, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ingredientName: TextView = itemView.ingredientName
        private val ingredientAmount: TextView = itemView.ingredientAmount

        fun bind(model: Ingredient) {
            ingredientName.text = model.name
            ingredientAmount.text = itemView.context.getString(R.string.coctail_details_amount, model.amount)
        }
    }
}