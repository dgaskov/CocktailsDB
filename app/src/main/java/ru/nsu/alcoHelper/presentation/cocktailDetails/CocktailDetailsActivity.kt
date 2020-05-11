package ru.nsu.alcoHelper.presentation.cocktailDetails

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_cocktail_details_content.*
import kotlinx.android.synthetic.main.activity_cocktail_details_scrolling.*
import ru.nsu.alcoHelper.R
import ru.nsu.alcoHelper.presentation.cocktailDetails.list.CocktailIngredientsListAdapter
import ru.nsu.alcoHelper.presentation.fullImage.FullImageActivity

class CocktailDetailsActivity: AppCompatActivity() {
    private lateinit var viewModel: CocktailDetailsViewModel
    private lateinit var adapter: CocktailIngredientsListAdapter
    private val disposable = CompositeDisposable()
    companion object {
        const val ID_KEY = "COCKTAIL_ID_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cocktail_details_scrolling)
        setImageOnClickListener()
        initRecyclerView()
        initAndSubscribeViewModel()

        val args = intent.extras
        val cocktailId = args?.getString(ID_KEY) ?: "11007" // Margarita by default
        disposable.add(viewModel.start(cocktailId))
    }

    private fun initAndSubscribeViewModel() {
        viewModel = ViewModelProviders.of(this).get(CocktailDetailsViewModel::class.java)
        viewModel.getCocktailName.observe(this, Observer {
            title = it
            setSupportActionBar(toolbar)
        })
        viewModel.getCocktailIngredients.observe(this, Observer {
            adapter.items = it
        })
        viewModel.getCocktailReceiptBody.observe(this, Observer {
            receiptBody.text = it
        })
        viewModel.getCocktailImageURL.observe(this, Observer {
            Glide.with(this)
                .load(it)
                .into(toolbarBackground)
        })
        viewModel.getErrors.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CocktailIngredientsListAdapter()
        recyclerView.adapter = adapter
    }

    private fun setImageOnClickListener() {
        fab.setOnClickListener {
            val url = viewModel.getCocktailImageURL.value
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