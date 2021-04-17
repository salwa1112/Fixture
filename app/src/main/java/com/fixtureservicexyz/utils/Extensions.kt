package com.fixtureservicexyz.utils

import android.content.Context
import android.text.Editable
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.fixtureservicexyz.R
import com.fixtureservicexyz.models.GoogleAccount
import com.fixtureservicexyz.models.Guest
import com.fixtureservicexyz.models.User

fun AppCompatActivity.GoForwardTransition(){
    this.overridePendingTransition(R.anim.slide_in_right,  R.anim.slide_out_left)
}
fun AppCompatActivity.GoBackTransition(){
    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right)
}

fun Context.addToSharedPrefs(guest: Guest):Boolean{
    try {
        val preferences = this.getSharedPreferences(StrKeys.SHARED_PREFERENCES_DB_GUEST,Context.MODE_PRIVATE)
        preferences.edit{
            putString(StrKeys.LOGGED_STATUS_SHARED_PREFS,StrKeys.GUEST_STATUS_SHARED_PREFS)
            putString(StrKeys.GUEST_PHONE_NUMBER_SHARED_PREFS,guest.phoneNumber)
            putString(StrKeys.APARTMENT_ADDRESS_SHARED_PREFS,guest.address!!.apartment)
            putString(StrKeys.STREET_ADDRESS_SHARED_PREFS,guest.address!!.street)
            putString(StrKeys.CITY_ADDRESS_SHARED_PREFS,guest.address!!.city)
            putString(StrKeys.PROVINCE_ADDRESS_SHARED_PREFS,guest.address!!.province)
            putString(StrKeys.ZIP_CODE_ADDRESS_SHARED_PREFS,guest.address!!.zipCode)
            apply()
        }
        return true
    }catch (ex:Exception){
        return false
    }
}

fun Context.addToSharedPrefs(googleAccount: GoogleAccount):Boolean{
    try {
        val preferences = this.getSharedPreferences(StrKeys.SHARED_PREFERENCES_DB_GUEST,Context.MODE_PRIVATE)
        preferences.edit{
            putString(StrKeys.LOGGED_STATUS_SHARED_PREFS,StrKeys.GOOGLE_STATUS_SHARED_PREFS)
            putString(StrKeys.GOOGLE_EMAIL_SHARED_PREFS,googleAccount.email)
            putString(StrKeys.APARTMENT_ADDRESS_SHARED_PREFS,googleAccount.address!!.apartment)
            putString(StrKeys.STREET_ADDRESS_SHARED_PREFS,googleAccount.address!!.street)
            putString(StrKeys.CITY_ADDRESS_SHARED_PREFS,googleAccount.address!!.city)
            putString(StrKeys.PROVINCE_ADDRESS_SHARED_PREFS,googleAccount.address!!.province)
            putString(StrKeys.ZIP_CODE_ADDRESS_SHARED_PREFS,googleAccount.address!!.zipCode)
            apply()
        }
        return true
    }catch (ex:Exception){
        return false
    }
}

fun Context.addToSharedPrefs(user: User):Boolean{
    try {
        val preferences = this.getSharedPreferences(StrKeys.SHARED_PREFERENCES_DB_REGISTER_USER,Context.MODE_PRIVATE)
        preferences.edit{
            putString(StrKeys.LOGGED_STATUS_SHARED_PREFS,StrKeys.USER_REGISTERED_STATUS_SHARED_PREFS)
            putString(StrKeys.USER_FULL_NAME_SHARED_PREFS,user.fullName)
            putString(StrKeys.USER_EMAIL_SHARED_PREFS,user.email)
            putString(StrKeys.USER_NAME_SHARED_PREFS,user.username)
            putString(StrKeys.USER_PASS_SHARED_PREFS,user.password)
            putString(StrKeys.USER_PHONE_SHARED_PREFS,user.phone)
            putString(StrKeys.APARTMENT_ADDRESS_SHARED_PREFS,user.address!!.apartment)
            putString(StrKeys.STREET_ADDRESS_SHARED_PREFS,user.address!!.street)
            putString(StrKeys.CITY_ADDRESS_SHARED_PREFS,user.address!!.city)
            putString(StrKeys.PROVINCE_ADDRESS_SHARED_PREFS,user.address!!.province)
            putString(StrKeys.ZIP_CODE_ADDRESS_SHARED_PREFS,user.address!!.zipCode)
            apply()
        }
        return true
    }catch (ex:Exception){
        return false
    }
}

fun Context.fetchToSharedPrefs(key:String):String?{
    val preferences = this.getSharedPreferences(StrKeys.SHARED_PREFERENCES_DB_REGISTER_USER,Context.MODE_PRIVATE)
    return preferences.getString(key,null)
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun String.toEditable():Editable = Editable.Factory.getInstance().newEditable(this)