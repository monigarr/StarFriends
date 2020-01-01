package com.monigarr.swone.data

import android.os.Parcel
import android.os.Parcelable
import com.monigarr.swone.model.People

/*
*   represents a single Star Wars Person
*   from the Star Wars API
*   https://swapi.co/
 */
class PeopleData : Parcelable {
    var name: String? = null
    var height: String? = null
    var birth_year: String? = null
    var mass: String? = null
    var hair_color: String? = null
    var skin_color: String? = null
    var eye_color: String? = null
    var gender: String? = null
    var fave: String? = null

    constructor(people: People) {
        name = people.name
        height = people.height
        birth_year = people.birth_year
        mass = people.mass
        hair_color = people.hair_color
        skin_color = people.skin_color
        eye_color = people.eye_color
        gender = people.gender
        fave = people.fave
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(height)
        dest?.writeString(birth_year)
        dest?.writeString(mass)
        dest?.writeString(hair_color)
        dest?.writeString(skin_color)
        dest?.writeString(eye_color)
        dest?.writeString(gender)
        dest?.writeString(fave)
    }

    override fun describeContents(): Int {
        return 0
    }

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        height = parcel.readString()
        birth_year = parcel.readString()
        mass = parcel.readString()
        hair_color = parcel.readString()
        skin_color = parcel.readString()
        eye_color = parcel.readString()
        gender = parcel.readString()
        fave = parcel.readString()
    }

    companion object CREATOR : Parcelable.Creator<PeopleData> {
        override fun createFromParcel(parcel: Parcel): PeopleData {
            return PeopleData(parcel)
        }

        override fun newArray(size: Int): Array<PeopleData?> {
            return arrayOfNulls(size)
        }
    }
}