package com.biscuitkid.carousellnews

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.biscuitkid.carousellnews.adapter.CarousellNewsAdapter
import com.biscuitkid.carousellnews.adapter.Sort
import com.biscuitkid.carousellnews.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val vm by viewModel<MainViewModel>()
    private val carousellNewsAdapter = CarousellNewsAdapter();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindObserver()
        setupAdapter()
        vm.getCarousellNews()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_popular -> {
                carousellNewsAdapter.sortBy(Sort.POPULAR)
                return true
            }
            R.id.action_recent -> {
                carousellNewsAdapter.sortBy(Sort.RECENT)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupAdapter() {
        val viewManager = LinearLayoutManager(this)

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = carousellNewsAdapter
        }
    }

    private fun bindObserver() {
        vm.resultCarousellNews.observe(this, {
            carousellNewsAdapter.addAll(it)
        })
    }
}