package com.example.projektzaliczeniowy

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

class EditContact : AppCompatActivity() {

    private lateinit var first_name_edittext : EditText
    private lateinit var last_name_edittext : EditText
    private lateinit var number_edittext : EditText

    private val contactsViewModel : ContactViewModel by viewModels{
        ContactViewModel.ContactViewModelFactory((application as ContactsApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_contact)

        val current_intent = intent
        val object_id = intent.getIntExtra("ID",0)
        val object_name = intent.getStringExtra("FIRST_NAME")
        val object_last_name = intent.getStringExtra("LAST_NAME")
        val object_number = intent.getIntExtra("NUMBER",0)

        first_name_edittext = findViewById(R.id.edit_first_name)
        last_name_edittext = findViewById(R.id.edit_last_name)
        number_edittext = findViewById(R.id.edit_number)

        first_name_edittext.setText(object_name)
        last_name_edittext.setText(object_last_name)
        number_edittext.setText(object_number.toString())

        val button = findViewById<Button>(R.id.button_edit)
        button.setOnClickListener {
            if(TextUtils.isEmpty(first_name_edittext.text)
                || TextUtils.isEmpty(last_name_edittext.text)
                || TextUtils.isEmpty(number_edittext.text)) {
                Toast.makeText(
                        this,
                        "Wypełnij proszę wszystkie pola", Toast.LENGTH_LONG
                ).show()
                return@setOnClickListener
            }
            val firstName = first_name_edittext.text.toString()
            val lastName = last_name_edittext.text.toString()
            val number = Integer.parseInt(number_edittext.text.toString())

            val toUpdate = Contact(object_id, firstName, lastName, number)
            contactsViewModel.update(toUpdate)
            val reply = Intent()

            setResult(Activity.RESULT_OK)
            reply.putExtra("FIRST_NAME", firstName)
            reply.putExtra("LAST_NAME", lastName)
            reply.putExtra("NUMBER", number)

            finish()
        }


    }
}