package ru.nsu.alcoHelper.presentation.coctailDetails

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_coctail_details.*
import ru.nsu.alcoHelper.R
import ru.nsu.alcoHelper.presentation.coctailDetails.list.CoctailIngredientsListAdapter
import ru.nsu.alcoHelper.presentation.fullImage.FullImageActivity

class CoctailDetailsActivity: AppCompatActivity() {
    private lateinit var viewModel: CoctailDetailsViewModel
    private lateinit var adapter: CoctailIngredientsListAdapter
    private val disposable = CompositeDisposable()
    companion object {
        const val ID_KEY = "COCTAIL_ID_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coctail_details)
        setImageOnClickListener()
        initRecyclerView()
        initAndSubscribeViewModel()

        val args = intent.extras
        val coctailId = args?.getString(ID_KEY) ?: "11007" // Margarita by default
        disposable.add(viewModel.start(coctailId))
    }

    private fun initAndSubscribeViewModel() {
        viewModel = ViewModelProviders.of(this).get(CoctailDetailsViewModel::class.java)
        viewModel.getCoctailName.observe(this, Observer {
            coctailName.text = it
        })
        viewModel.getCoctailIngredients.observe(this, Observer {
            adapter.items = it
        })
        viewModel.getCoctailReceiptBody.observe(this, Observer {
            receiptBody.text = it
        })
        viewModel.getCoctailImageURL.observe(this, Observer {
            Glide.with(this)
                .load(it)
                .transform(RoundedCorners(100))
                .into(imageView)
        })
        viewModel.getErrors.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CoctailIngredientsListAdapter()
        recyclerView.adapter = adapter
    }

    private fun setImageOnClickListener() {
        imageView.setOnClickListener {
            val url = viewModel.getCoctailImageURL.value
            if (url == null) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle()
                bundle.putString(FullImageActivity.URL_KEY, url)
                val intent = Intent(this, FullImageActivity::class.java)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}