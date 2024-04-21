package com.example.aston_intensiv_final

import androidx.recyclerview.widget.DiffUtil

const val CONTENT_PAYLOAD_KEY_OLD = "CONTENT_PAYLOAD_KEY"

object ContactDiffUtil : DiffUtil.ItemCallback<NewsModelOld>() {

    override fun areItemsTheSame(oldItem: NewsModelOld, newItem: NewsModelOld): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: NewsModelOld, newItem: NewsModelOld): Boolean {
        return oldItem == newItem
    }

/*    override fun getChangePayload(oldItem: ContactModel, newItem: ContactModel): Any? {
        return if (oldItem.firstName != newItem.firstName) {
            val bundle = Bundle()
            bundle.putString(CONTENT_PAYLOAD_KEY, newItem.firstName)
            bundle
        } else {
            super.getChangePayload(oldItem, newItem)
        }
    }*/
}