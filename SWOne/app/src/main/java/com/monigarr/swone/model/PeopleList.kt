package com.monigarr.swone.model

import com.monigarr.swone.data.PeopleData

data class PeopleList(
    var count: Int? = 0,
    var next: String? = null,
    var results: ArrayList<People>? = null
)