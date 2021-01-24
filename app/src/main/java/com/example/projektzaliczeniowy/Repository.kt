package com.example.projektzaliczeniowy

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class Repository (private val contactDao: UserDao){
    val allContacts: Flow<List<Contact>> = contactDao.getAllFlow()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(contact: Contact) {
        contactDao.insertAll(contact)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun update(contact: Contact) {
        contactDao.update(contact)
    }

}