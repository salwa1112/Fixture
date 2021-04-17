package com.fixtureservicexyz.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductService(
    val id:String = "",
    val title:String = "",
    val description:List<String> = mutableListOf<String>(),
    val photoUrl:Int = 0,
    val priceFrom:Int = 0,
    var toBuy:Int = 1
):Parcelable