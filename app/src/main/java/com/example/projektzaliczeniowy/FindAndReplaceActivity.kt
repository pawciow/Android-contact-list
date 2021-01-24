package com.example.projektzaliczeniowy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels

class FindAndReplaceActivity : AppCompatActivity() {

    private val contactsViewModel : ContactViewModel by viewModels{
        ContactViewModel.ContactViewModelFactory((application as ContactsApplication).repository)
    }

    private lateinit var first_name_edittext : EditText
    private lateinit var last_name_edittext : EditText
    private lateinit var number_edittext : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_and_replace)

        first_name_edittext = findViewById(R.id.find_first_name)
        last_name_edittext = findViewById(R.id.find_last_name)
        number_edittext = findViewById(R.id.find_number)

        var nameFindButton = findViewById<Button>(R.id.button_find_by_name)
        nameFindButton.setOnClickListener {
            if(TextUtils.isEmpty(first_name_edittext.text)
                || TextUtils.isEmpty(last_name_edittext.text)){
                Toast.makeText(
                    this@FindAndReplaceActivity,
                    "Wypełnij proszę wszystkie pola dla wyszukiwania po imieniu", Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            var lookingForFirstName = first_name_edittext?.text.toString()
            var lookingForLastName = last_name_edittext?.text.toString()

            contactsViewModel.findByName(lookingForFirstName, lookingForLastName)
            if(contactsViewModel.returnedContact == null){
                Toast.makeText(this@FindAndReplaceActivity, "Nie znaleziono kontaktu", Toast.LENGTH_LONG).show()
            }
            contactsViewModel.returnedContact?.let { it1 -> startEditActivity(it1) }

        }

        var numberFindButton = findViewById<Button>(R.id.button_find_by_number)
        numberFindButton.setOnClickListener {
            if(TextUtils.isEmpty(number_edittext.text)){
                Toast.makeText(
                    this@FindAndReplaceActivity,
                    "Wypełnij proszę pole dla wyszukiwania po numerze", Toast.LENGTH_LONG
                ).show()
            }

            var lookingForNumber = number_edittext?.text.toString().toInt()
            contactsViewModel.findByNumber(lookingForNumber)
            if(contactsViewModel.returnedContact == null){
                Toast.makeText(this@FindAndReplaceActivity, "Nie znaleziono kontaktu", Toast.LENGTH_LONG).show()
            }
            contactsViewModel.returnedContact?.let { it1 -> startEditActivity(it1) }
        }


    }

    fun startEditActivity(contact: Contact){

        val intent = Intent(this@FindAndReplaceActivity, EditContact::class.java)
        intent.putExtra("ID",contact.uid)
        intent.putExtra("FIRST_NAME", contact.firstName)
        intent.putExtra("LAST_NAME", contact.lastName)
        intent.putExtra("NUMBER",contact.number)

        startActivity(intent)
    }
}