package ru.nsu.alcoHelper.presentation.coctailCategories.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kotlinx.android.synthetic.main.item_coctail_compact_info.view.*
import ru.nsu.alcoHelper.R
import ru.nsu.alcoHelper.common.ItemClickListener
import ru.nsu.alcoHelper.data.model.DrinkCompactInfo

class CoctailCategoryListAdapter(private val clickListener: ItemClickListener<DrinkCompactInfo>)
    : RecyclerView.Adapter<CoctailCategoryListAdapter.ViewHolder>() {
    var items: List<DrinkCompactInfo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_coctail_compact_info, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val coctailImage: AppCompatImageView = itemView.coctailImage
        private val coctailName: TextView = itemView.coctailName
        private val circularPlaceholder = CircularProgressDrawable(itemView.context)

        init {
            circularPlaceholder.strokeWidth = 5f
            circularPlaceholder.centerRadius = 30f
        }

        fun bind(model: DrinkCompactInfo, clickListener: ItemClickListener<DrinkCompactInfo>) {
            circularPlaceholder.start()
            Glide.with(itemView.context)
                .load(model.url.toString())
                .placeholder(circularPlaceholder)
                .transform(RoundedCorners(100))
                .into(coctailImage)

            coctailName.text = model.name

            itemView.setOnClickListener {
                clickListener.itemClicked(model)
            }
        }
    }
}