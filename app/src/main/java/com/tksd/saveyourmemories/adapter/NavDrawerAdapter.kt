package com.tksd.saveyourmemories.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.tksd.saveyourmemories.R

/**
 * Created by Yago on 09/04/2016.
 */
class NavDrawerAdapter(var navTitles: Array<String>?, var icons: IntArray?, var name: String, var profile: Int, var email: String) :
        RecyclerView.Adapter<NavDrawerAdapter.ViewHolder>() {

    private val TYPE_HEADER = 0
    private val TYPE_ITEM = 1
    var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(itemView: View, position: Int)
    }

    inner class ViewHolder(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

        var holderId = 0
        var textView: TextView? = null
        var imageView: ImageView? = null
        var profile: ImageView? = null
        var name: TextView? = null
        var email: TextView? = null

        init {
            if (viewType === TYPE_ITEM) {
                textView = itemView.findViewById(R.id.drawer_row_item_text) as TextView
                imageView = itemView.findViewById(R.id.drawer_row_item_icon) as ImageView
                holderId = TYPE_ITEM
            } else {
                name = itemView.findViewById(R.id.name) as TextView
                email = itemView.findViewById(R.id.email) as TextView
                profile = itemView.findViewById(R.id.circleView) as ImageView
                holderId = TYPE_HEADER
            }
            itemView.setOnClickListener { onItemClickListener?.onItemClick(itemView, layoutPosition) }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        if (holder?.holderId == TYPE_ITEM) {
            holder?.textView?.text = navTitles!![position - 1]
            holder?.imageView?.setImageResource(icons!![position - 1])
        } else {
            holder?.profile?.setImageResource(profile)
            holder?.name?.text = name
            holder?.email?.text = email
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder? {
        val chosenLayout = if (viewType == TYPE_ITEM) R.layout.drawer_item_row else R.layout.drawer_header
        val v = LayoutInflater.from(parent?.context).inflate(chosenLayout, parent, false);
        return ViewHolder(v, viewType);
    }

    override fun getItemViewType(position: Int): Int = if (position == TYPE_HEADER) TYPE_HEADER else TYPE_ITEM

    override fun getItemCount(): Int = navTitles?.count()!!.plus(1)

}
