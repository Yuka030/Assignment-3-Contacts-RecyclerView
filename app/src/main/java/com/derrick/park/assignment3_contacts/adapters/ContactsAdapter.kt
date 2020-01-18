package com.derrick.park.assignment3_contacts.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.derrick.park.assignment3_contacts.R
import com.derrick.park.assignment3_contacts.models.Contact
import java.util.ArrayList

class ContactsAdapter(val context: Context, val contacts: ArrayList<Contact>): RecyclerView.Adapter<ContactsAdapter.Holder>() {

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fullName = itemView.findViewById<TextView>(R.id.fullName)
        val phoneNumber = itemView.findViewById<TextView>(R.id.phoneNumber)
        val alphaLetter = itemView.findViewById<TextView>(R.id.alphaLetter)

        fun bindContact(contact: Contact){
            fullName.text = contact.name.toString()
            phoneNumber.text = contact.cell

            if(contacts.indexOf(contact) == 0 || contacts.get(contacts.indexOf(contact) - 1).name.toString().substring(0, 1) != contact.name.toString().substring(0, 1)){
                alphaLetter.text = contact.name.toString().substring(0, 1).toUpperCase()
            }else{
                alphaLetter.visibility = View.GONE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context)
                .inflate(R.layout.contact_list_item, parent,false)

        return Holder(view)
    }

    override fun getItemCount(): Int {
     return contacts.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
       holder.bindContact(contacts.get(position))
    }
}