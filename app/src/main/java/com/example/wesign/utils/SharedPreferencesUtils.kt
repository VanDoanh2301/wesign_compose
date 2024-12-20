package com.example.wesign.utils

import android.content.Context
import android.content.SharedPreferences


object SharedPreferencesUtils {
    private var mPref: SharedPreferences? = null
    const val NAME = "WeSignCache";

    fun init(context: Context) {
        mPref = context.getSharedPreferences(NAME, Context.MODE_PRIVATE)
    }
    fun setBoolean(_key: String?, _value: Boolean) {
        if (_key == null) {
            return
        }
        if (mPref != null) {
            val edit = mPref!!.edit()
            edit.putBoolean(_key, _value)
            edit.commit()
        }
    }

    fun getBoolean(_key: String?): Boolean {
        if (mPref == null || !mPref!!.contains(_key)) {
            val edit = mPref!!.edit()
            edit.putBoolean(_key, false)
            edit.commit()
        }
        return mPref!!.getBoolean(_key, false)
    }

    fun getBoolean(_key: String?, _value: Boolean): Boolean {
        if (mPref == null || !mPref!!.contains(_key)) {
            val edit = mPref!!.edit()
            edit.putBoolean(_key, _value)
            edit.commit()
        }
        return mPref!!.getBoolean(_key, _value)
    }

    fun setString(_key: String?, _value: String?) {
        if (_key == null) {
            return
        }
        if (mPref != null) {
            val edit = mPref!!.edit()
            edit.putString(_key, _value)
            edit.commit()
        }
    }

    fun getString(_key: String?): String? {
        if (mPref == null || !mPref!!.contains(_key)) {
            val edit = mPref!!.edit()
            edit.putString(_key, null)
            edit.commit()
        }
        return mPref!!.getString(_key, null)
    }

    fun getString(_key: String?, _value: String?): String? {
        if (mPref == null || !mPref!!.contains(_key)) {
            val edit = mPref!!.edit()
            edit.putString(_key, _value)
            edit.commit()
        }
        return mPref!!.getString(_key, _value)
    }

    fun setInt(_key: String?, _value: Int) {
        if (_key == null) {
            return
        }
        if (mPref != null) {
            val edit = mPref!!.edit()
            edit.putInt(_key, _value)
            edit.commit()
        }
    }

    fun getInt(_key: String?, _value: Int): Int {
        if (mPref == null || !mPref!!.contains(_key)) {
            val edit = mPref!!.edit()
            edit.putInt(_key, _value)
            edit.commit()
        }
        return mPref!!.getInt(_key, _value)
    }

    fun getInt(_key: String?): Int {
        return mPref!!.getInt(_key, -1)
    }

    fun getLong(_key: String?, _value: Long): Long{
        if (mPref == null || !mPref!!.contains(_key)) {
            val edit = mPref!!.edit()
            edit.putLong(_key, _value)
            edit.commit()
        }
        return mPref!!.getLong(_key, _value)
    }

    fun setLong(_key: String?, _value: Long) {
        if (_key == null) {
            return
        }
        if (mPref != null) {
            val edit = mPref!!.edit()
            edit.putLong(_key, _value)
            edit.commit()
        }
    }

    fun getLong(_key: String?): Long {
        return mPref!!.getLong(_key, -1)
    }

    fun setFloat(_key: String?, _value: Float) {
        if (_key == null) {
            return
        }
        if (mPref != null) {
            val edit = mPref!!.edit()
            edit.putFloat(_key, _value)
            edit.commit()
        }
    }

    fun getFloat(_key: String?, _value: Float): Float {
        if (mPref == null || !mPref!!.contains(_key)) {
            val edit = mPref!!.edit()
            edit.putFloat(_key, _value)
            edit.commit()
        }
        return mPref!!.getFloat(_key, _value)
    }




}