package ru.nsu.cocktailDB.presentation.cocktailsInCategory

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
import ru.nsu.cocktailDB.data.model.DrinkCompactInfo
import ru.nsu.cocktailDB.presentation.cocktailsInCategory.list.CocktailsInCategoryListAdapter
import ru.nsu.cocktailDB.presentation.cocktailDetails.CocktailDetailsActivity

class CocktailsInCategoryActivity: AppCompatActivity(), ItemClickListener<DrinkCompactInfo> {
    private lateinit var viewModel: CocktailsInCategoryViewModel
    private val disposable = CompositeDisposable()
    private lateinit var adapter: CocktailsInCategoryListAdapter
    companion object {
        const val CATEGORY_KEY = "COCKTAIL_CATEGORY_KEY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initRecyclerView()
        initAndSubscribeViewModel()

        val args = intent.extras
        val cocktailCategory = args?.getString(CATEGORY_KEY) ?: "Ordinary_Drink"
        val categoryUnescaped = cocktailCategory.replace('_', ' ', false)
        title = categoryUnescaped
        disposable.add(viewModel.start(cocktailCategory))
    }

    private fun initAndSubscribeViewModel() {
        viewModel = ViewModelProviders.of(this).get(CocktailsInCategoryViewModel::class.java)
        viewModel.getCocktailsInCategory.observe(this, Observer {
            adapter.items = it
        })
        viewModel.getErrors.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CocktailsInCategoryListAdapter(this)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    override fun itemClicked(item: DrinkCompactInfo) {
        val bundle = Bundle()
        bundle.putString(CocktailDetailsActivity.ID_KEY, item.id)
        val intent = Intent(this, CocktailDetailsActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}