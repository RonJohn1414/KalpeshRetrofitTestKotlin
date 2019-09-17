package com.example.kalpeshretrofittestkotlin.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kalpeshretrofittestkotlin.model.DataList
import com.example.kalpeshretrofittestkotlin.model.DataService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel: ViewModel() {
    private val dataService = DataService()
    private val disposable = CompositeDisposable()

    val datas = MutableLiveData<List<DataList>>()
    val dataLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        fetchData()
    }

    private fun fetchData(){
        loading.value = true
        disposable.add(
            dataService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DataList>>(){
                    override fun onSuccess(value: List<DataList>?) {
                        datas.value = value
                        dataLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        dataLoadError.value = true
                        loading.value = false
                    }
                }))
    }
}