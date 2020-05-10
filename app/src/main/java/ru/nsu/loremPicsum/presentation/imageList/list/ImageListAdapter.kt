package ru.nsu.loremPicsum.presentation.imageList.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_image_details.view.*
import ru.nsu.loremPicsum.R
import ru.nsu.loremPicsum.data.model.ImageMetainfo

class ImageListAdapter(private val itemClickListener: OnImageClickListener)
    : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {
    var items: List<ImageMetainfo> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_image_details, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], itemClickListener)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val author: TextView = itemView.authorTextView
        private val width: TextView = itemView.widthTextView
        private var height: TextView = itemView.heightTextView

        fun bind(model: ImageMetainfo, clickListener: OnImageClickListener) {
            author.text = model.author
            width.text = itemView.context.getString(R.string.image_details_card_width, model.width)
            height.text = itemView.context.getString(R.string.image_details_card_height, model.height)

            itemView.setOnClickListener {
                clickListener.onImageClicked(model)
            }
        }
    }
}