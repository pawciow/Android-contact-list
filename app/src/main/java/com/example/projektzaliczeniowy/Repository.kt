package com.example.projektzaliczeniowy

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class Repository (private val contactDao: UserDao){
    val allContacts: Flow<List<Contact>> = contactDao.getAllFlow()

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

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun findByName(first: String, last : String) :Contact? {
        return contactDao.findByName(first, last)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun findByNumber(number: Int) :Contact? {
        return contactDao.findByNumber(number)
    }
}