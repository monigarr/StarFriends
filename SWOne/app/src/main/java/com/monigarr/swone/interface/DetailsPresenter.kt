package com.monigarr.swone.`interface`

import com.monigarr.swone.ui.view.DetailsView

interface DetailsPresenter {
    fun attachView(detailsView: DetailsView)
    fun detachView()
}