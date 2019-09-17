package com.example.kalpeshretrofittestkotlin.view

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kalpeshretrofittestkotlin.R
import com.example.kalpeshretrofittestkotlin.model.DataList
import com.example.kalpeshretrofittestkotlin.viewModel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    lateinit var viewModel: ListViewModel
    private val dataAdapter = DataListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
Log.d(TAG,"onCreate: starts _______-----+++++")
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        rv_DataList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dataAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.datas.observe(this, Observer {datas->
            datas?.let{
                rv_DataList.visibility = View.VISIBLE
                dataAdapter.updateData(it) }
        })

        viewModel.dataLoadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it){
                    list_error.visibility = View.GONE
                    rv_DataList.visibility = View.GONE
                }
            }
        })
    }
}
