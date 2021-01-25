package com.example.projektzaliczeniowy

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult

class ContactListAdapter : androidx.recyclerview.widget.ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(ContactsComparator()) {
    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ContactViewHolder{
        return ContactViewHolder.create(parent)
    }
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = getItem(position)
        holder.bindName(current.firstName)
        holder.bindSurname(current.lastName)
        holder.bindNumber(current.number)
        holder.itemView.setOnClickListener {
            mContext = it.context
            mContext = mContext as MainActivity
            val newIntent = Intent(mContext, EditContact::class.java)
            newIntent.putExtra("FIRST_NAME", current.firstName)
            newIntent.putExtra("LAST_NAME", current.lastName)
            newIntent.putExtra("NUMBER", current.number)
            newIntent.putExtra("ID", current.uid)

            it.context.startActivity(newIntent)
        }
    }
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private  val firstNameItemView: TextView = itemView.findViewById(R.id.textView)
        private  val lastNameItemView: TextView = itemView.findViewById(R.id.lastNameView)
        private  val numberItemView: TextView = itemView.findViewById(R.id.numberView)
        fun bindName(text: String?){
            firstNameItemView.text = text
        }
        fun bindSurname(text: String?){
            lastNameItemView.text = text
        }
        fun bindNumber(int: Int?){
            numberItemView.text = int.toString()
        }
        companion object{
            fun create(parent: ViewGroup) : ContactViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return  ContactViewHolder(view)
            }
        }
    }

    class ContactsComparator : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.firstName == newItem.firstName
                    && oldItem.lastName == newItem.lastName
                    && oldItem.number == newItem.number
        }
    }
    companion object {
        const val EDIT_CONTACT = "EDIT_CONTACT"
    }


}