package com.monigarr.swone.model

class People(
    var name: String? = null,
    var height: String? = null,
    var birth_year: String? = null,
    var mass: String? = null,
    var hair_color: String? = null,
    var skin_color: String? = null,
    var eye_color: String? = null,
    var gender: String? = null,
    var fave: String? = null
) {
    override fun toString(): String {
        return "PeopleInfo(name=$name, height=$height, birth_year=$birth_year, mass=$mass, hair_color=$hair_color, skin_color=$skin_color, eye_color=$eye_color, gender=$gender, fave=$fave)"
    }
}