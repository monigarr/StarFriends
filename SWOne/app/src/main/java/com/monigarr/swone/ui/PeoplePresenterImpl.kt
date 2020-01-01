package com.monigarr.swone.ui

import android.content.Context
import com.monigarr.swone.`interface`.PeoplePresenter
import com.monigarr.swone.api.RetrofitClient
import com.monigarr.swone.data.PeopleData
import com.monigarr.swone.model.PeopleList
import com.monigarr.swone.ui.view.PeopleView
import com.monigarr.swone.utilities.Constants
import com.monigarr.swone.utilities.NetworkUtilities
import com.monigarr.swone.utilities.SharedPreferencesManager
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

//TODO: convert to MVVM,  remove mvp architecture
class PeoplePresenterImpl : PeoplePresenter {

    private var peopleView: PeopleView? = null

    //TODO integrate fave features: crud
    override fun addFavesClick(){}
    override fun showFavesClick() {}
    override fun removeFavesClick() {}

    override fun searchPeopleByName(query: String, peopleDataList: ArrayList<PeopleData>) {
        val queryData = query.toLowerCase()
        val resultDataList: ArrayList<PeopleData> = ArrayList()
        val iterator = peopleDataList.listIterator()
        for (item in iterator) {
            if (item.name?.toLowerCase()?.startsWith(queryData)!!) {
                resultDataList.add(item)
            }
        }
        if (peopleView != null) {
            peopleView?.updateRecyclerViewForSearch(resultDataList)
        }
    }

    override fun attachView(peopleView: PeopleView) {
        this.peopleView = peopleView
    }

    override fun detachView() {
        this.peopleView = null
    }

    override fun sendPeopleListRequest(url: String) {
        if (peopleView == null) {
            return
        }
        val context = peopleView as Context
        if (!NetworkUtilities.isNetworkConnectionAvailable(context)) {
            (peopleView as PeopleActivity).showNetworkErrorDialog()
            return
        }

        val observer = getPeopleListObserver()
        val observable = RetrofitClient.getAPIService()?.getPeopleList(url)

        observable?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(observer)
    }

    private fun getPeopleListObserver(): Observer<PeopleList> {
        var disposable: Disposable?= null
        return object : Observer<PeopleList> {
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(s: PeopleList) {
                when (s.next) {
                    null -> { SharedPreferencesManager.putString(Constants.NEXT_URL_PEOPLE_LIST, "") }
                    else -> {
                        SharedPreferencesManager.putString(Constants.NEXT_URL_PEOPLE_LIST, s.next.toString())
                    }
                }

                val peoples : ArrayList<PeopleData> = ArrayList()
                val iterator = s.results?.listIterator()
                if (iterator != null) {
                    for (item in iterator) {
                        peoples.add(PeopleData(item))
                    }

                    if (peopleView != null) {
                        peopleView?.updateRecyclerView(peoples)
                    }
                }
            }



            override fun onError(e: Throwable) {
                if (peopleView == null) {
                    return
                }
                (peopleView as PeopleActivity).showDataFetchingErrorDialog()
            }

            override fun onComplete() {
                disposable?.dispose()
            }
        }
    }
}