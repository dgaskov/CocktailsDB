package ru.nsu.alcoHelper.presentation.coctailCategories

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_coctail_categories.*
import ru.nsu.alcoHelper.R
import ru.nsu.alcoHelper.common.ItemClickListener
import ru.nsu.alcoHelper.data.model.DrinkCompactInfo
import ru.nsu.alcoHelper.presentation.coctailCategories.list.CoctailCategoryListAdapter
import ru.nsu.alcoHelper.presentation.coctailDetails.CoctailDetailsActivity

class CoctailCategoriesActivity: AppCompatActivity(), ItemClickListener<DrinkCompactInfo> {
    private lateinit var viewModel: CoctailCategoriesViewModel
    private val disposable = CompositeDisposable()
    private lateinit var adapter: CoctailCategoryListAdapter
    companion object {
        private val CATEGORY_KEY = "COCTAIL_CATEGORY_KEY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coctail_categories)
        initRecyclerView()
        initAndSubscribeViewModel()

        val args = intent.extras
        val coctailCategory = args?.getString(CATEGORY_KEY) ?: "Ordinary_Drink"
        disposable.add(viewModel.start(coctailCategory))
    }

    private fun initAndSubscribeViewModel() {
        viewModel = ViewModelProviders.of(this).get(CoctailCategoriesViewModel::class.java)
        viewModel.getCoctailCategories.observe(this, Observer {
            adapter.items = it
        })
        viewModel.getErrors.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CoctailCategoryListAdapter(this)
        recyclerView.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
    }

    override fun itemClicked(item: DrinkCompactInfo) {
        val bundle = Bundle()
        bundle.putString(CoctailDetailsActivity.ID_KEY, item.id)
        val intent = Intent(this, CoctailDetailsActivity::class.java)
        intent.putExtras(bundle)
        startActivity(intent)
    }
}