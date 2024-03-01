package com.example.common_utils.utils

import android.annotation.SuppressLint
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("StaticFieldLeak")
lateinit var FIRE_STORE_DATABASE : FirebaseFirestore
lateinit var REF_DATABASE_ROOT : DatabaseReference

const val TOP_LIST_COLLECTION ="TOP_LIST"
const val TOP_WORDS = "Top words"

fun initFirebase(){
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    FIRE_STORE_DATABASE = FirebaseFirestore.getInstance()
}