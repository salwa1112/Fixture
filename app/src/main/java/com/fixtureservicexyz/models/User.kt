package com.fixtureservicexyz.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val username: String = "",
    val password: String = "",
    var address: Address? = null
) : Parcelable


@Parcelize
data class Address(
    val apartment: String = "",
    val street: String = "",
    val city: String = "",
    val province: String = "",
    val zipCode: String = ""
) : Parcelable

@Parcelize
data class Guest(
    val phoneNumber: String = "",
    var address: Address? = null
) : Parcelable

@Parcelize
data class GoogleAccount(
    val email: String = "",
    var address: Address? = null
): Parcelable