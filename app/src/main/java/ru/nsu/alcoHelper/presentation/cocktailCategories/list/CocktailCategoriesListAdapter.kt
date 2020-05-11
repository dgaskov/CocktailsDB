package ru.nsu.alcoHelper.presentation.cocktailCategories.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cocktail_category.view.*
import ru.nsu.alcoHelper.R
import ru.nsu.alcoHelper.common.ItemClickListener
import ru.nsu.alcoHelper.data.model.CocktailCategory

class CocktailCategoriesListAdapter(private val clickListener: ItemClickListener<CocktailCategory>)
    : RecyclerView.Adapter<CocktailCategoriesListAdapter.ViewHolder>() {
    var items: List<CocktailCategory> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_cocktail_category, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryName: TextView = itemView.categoryName

        fun bind(model: CocktailCategory, clickListener: ItemClickListener<CocktailCategory>) {
            categoryName.text = model.name

            itemView.setOnClickListener {
                clickListener.itemClicked(model)
            }
        }
    }
}