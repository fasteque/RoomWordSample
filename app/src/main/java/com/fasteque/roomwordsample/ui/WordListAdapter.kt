package com.fasteque.roomwordsample.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fasteque.roomwordsample.R
import com.fasteque.roomwordsample.db.entity.Word


/**
 * Created by taaalda5 on 26.08.18.
 * Project: RoomWordSample
 */
class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words: List<Word>? = null // Cached copy of words

    inner class WordViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val wordItemView: TextView = itemView.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        if (words != null) {
            val current = words!![position]
            holder.wordItemView.text = current.word
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.text = "No Word"
        }
    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    override fun getItemCount(): Int {
        return if (words != null)
            words!!.size
        else
            0
    }
}