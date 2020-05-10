package ru.nsu.loremPicsum.presentation.imageList

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_image_list.*
import ru.nsu.loremPicsum.R
import ru.nsu.loremPicsum.data.model.ImageDetails
import ru.nsu.loremPicsum.presentation.fullImage.FullImageActivity
import ru.nsu.loremPicsum.presentation.imageList.list.ImageListAdapter
import ru.nsu.loremPicsum.presentation.imageList.list.OnImageClickListener

class ImageListActivity: AppCompatActivity(), OnImageClickListener {
    private lateinit var viewModel: ImageListViewModel
    private lateinit var adapter: ImageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_list)
        initRecyclerView()

        viewModel = ViewModelProviders.of(this).get(ImageListViewModel::class.java)
        viewModel.getImageList.observe(this, Observer {
            adapter.items = it
        })
        viewModel.fetchImages()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ImageListAdapter(this)
        recyclerView.adapter = adapter
    }

    override fun onImageClicked(model: ImageDetails) {
        val url = model.downloadURL

        val bundle = Bundle()
        bundle.putSerializable(FullImageActivity.URL_KEY, url.toString())
        val intent = Intent(this, FullImageActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}