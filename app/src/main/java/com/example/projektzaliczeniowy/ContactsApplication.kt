package com.example.projektzaliczeniowy

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ContactsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { AppDatabase.getDatabase(this, applicationScope)}
    val repository by lazy { Repository(database.userDao()) }
}