package ru.nsu.cocktailDB.presentation.cocktailCategories

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_list.*
import ru.nsu.cocktailDB.R
import ru.nsu.cocktailDB.common.ItemClickListener
import ru.nsu.cocktailDB.data.model.CocktailCategory
import ru.nsu.cocktailDB.presentation.cocktailCategories.list.CocktailCategoriesListAdapter
import ru.nsu.cocktailDB.presentation.cocktailsInCategory.CocktailsInCategoryActivity

class CocktailCategoriesActivity: AppCompatActivity(), ItemClickListener<CocktailCategory> {
    private lateinit var viewModel: CocktailCategoriesViewModel
    private lateinit var adapter: CocktailCategoriesListAdapter
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initRecyclerView()
        initAndSubscribeViewModel()
        title = getString(R.string.categories)

        disposable.add(viewModel.start())
    }

    private fun initAndSubscribeViewModel() {
        viewModel = ViewModelProviders.of(this).get(CocktailCategoriesViewModel::class.java)
        viewModel.getCocktailCategories.observe(this, Observer {
            adapter.items = it
        })
        viewModel.getErrors.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CocktailCategoriesListAdapter(this)
        recyclerView.adapter = adapter
    }

    override fun itemClicked(item: CocktailCategory) {
        val bundle = Bundle()

        val escapedName = item.name.replace(' ', '_', false)

        bundle.putString(CocktailsInCategoryActivity.CATEGORY_KEY, escapedName)
        val intent = Intent(this, CocktailsInCategoryActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }
}