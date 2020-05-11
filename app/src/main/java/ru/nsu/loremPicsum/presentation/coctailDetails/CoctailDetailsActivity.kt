package ru.nsu.loremPicsum.presentation.coctailDetails

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_coctail_details.*
import ru.nsu.loremPicsum.R
import ru.nsu.loremPicsum.presentation.coctailDetails.list.CoctailIngredientsListAdapter

class CoctailDetailsActivity: AppCompatActivity() {
    private lateinit var viewModel: CoctailDetailsViewModel
    private lateinit var adapter: CoctailIngredientsListAdapter

    companion object {
        const val ID_KEY = "COCTAIL_ID_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coctail_details)
        initRecyclerView()
        initAndSubscribeViewModel()

        val args = intent.extras
        val coctailId = args?.getInt(ID_KEY) ?: 0
        viewModel.start(coctailId)
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
        viewModel.getErrors.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = CoctailIngredientsListAdapter()
        recyclerView.adapter = adapter
    }
}