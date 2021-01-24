package com.example.projektzaliczeniowy
//https://android.jlelse.eu/android-room-using-kotlin-f6cc0a05bf23
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {


    private val contactsViewModel : ContactViewModel by viewModels{
        ContactViewModel.ContactViewModelFactory((application as ContactsApplication).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "database-name"
        ).build()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = ContactListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)



        contactsViewModel.allContacts.observe(this){ contacts ->
            contacts.let { adapter.submitList(it) }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == ACTIVITY_CODES.AddContactActivityCode && resultCode == Activity.RESULT_OK){
            val name = data?.getStringExtra("FIRST_NAME")
            val last_name = data?.getStringExtra("LAST_NAME")
            val number = Integer.parseInt(data?.getStringExtra("NUMBER"))
            val toAdd = Contact(firstName = name, lastName = last_name, number = number)
            contactsViewModel.insert(toAdd)
        }
    }

    companion object ACTIVITY_CODES{
        public  val AddContactActivityCode = 2
    }
}