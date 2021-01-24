package com.example.projektzaliczeniowy

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: Repository) : ViewModel() {
    var returnedContact : Contact? = Contact(0,"","", 0)
    val allContacts: LiveData<List<Contact>> = repository.allContacts.asLiveData()

    fun insert(contact: Contact) = viewModelScope.launch{
        repository.insert(contact)
    }

    fun update(contact: Contact) = viewModelScope.launch {
        repository.update(contact)
    }
    fun  findByName(first: String, last: String) = viewModelScope.launch {
        returnedContact = repository.findByName(first, last)
    }
    fun findByNumber(number: Int) = viewModelScope.launch {
        returnedContact = repository.findByNumber(number)
    }

    class ContactViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ContactViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}