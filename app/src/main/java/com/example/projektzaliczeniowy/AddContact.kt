package com.example.projektzaliczeniowy

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddContact : AppCompatActivity() {
    private lateinit var first_name_edittext : EditText
    private lateinit var last_name_edittext : EditText
    private lateinit var number_edittext : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        first_name_edittext = findViewById(R.id.first_name)
        last_name_edittext = findViewById(R.id.last_name)
        number_edittext = findViewById(R.id.number)

        val button = findViewById<Button>(R.id.button_add)
        button.setOnClickListener {
            if(TextUtils.isEmpty(first_name_edittext.text)
                || TextUtils.isEmpty(last_name_edittext.text )
                || TextUtils.isEmpty(number_edittext.text))
                Toast.makeText(this,
                    "Wypełnij proszę wszystkie pola", Toast.LENGTH_LONG).show()
            finish()

            val firstName = first_name_edittext.text.toString()
            val lastName = last_name_edittext.text.toString()
            val number = number_edittext.text.toString()

            val reply = Intent()
            setResult(Activity.RESULT_OK)
            reply.putExtra("FIRST_NAME", firstName)
            reply.putExtra("LAST_NAME", lastName)
            reply.putExtra("number", number)

            finish()
        }
    }
}