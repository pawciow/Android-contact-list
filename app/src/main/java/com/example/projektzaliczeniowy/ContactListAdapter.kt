package com.example.projektzaliczeniowy

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

class ContactListAdapter : androidx.recyclerview.widget.ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(ContactsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ContactViewHolder{
        return ContactViewHolder.create(parent)
    }
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.firstName)
        holder.itemView.setOnClickListener {
            print("Clicked on this!" + current.firstName + current.lastName + current.number )
        }
    }
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private  val contactItemView: TextView = itemView.findViewById(R.id.textView)
        fun bind(text: String?){
            contactItemView.text = text
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


}