package com.example.magicshop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShopAdapter_list: RecyclerView.Adapter<ShopAdapter_list.ViewHolder>() {
    private var buyListener : ItemClickListener? = null

    interface ItemClickListener{
        fun toClick(item: MagicItems)
    }
    fun setToClick(listener: ItemClickListener){
        buyListener = listener
    }

    private val magic_list: MutableList<MagicItems> = arrayListOf()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.list_itemview, parent, false)
            return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return magic_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(magic_list[position])
    }
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imageView = view.findViewById<ImageView>(R.id.img_magic1)
        val tv_name = view.findViewById<TextView>(R.id.tv_name)
        var tv_price = view.findViewById<TextView>(R.id.tv_price)
        val soldout = view.findViewById<TextView>(R.id.tv_soldout1)


        fun bind(item: MagicItems){
//            imageView.setImageResource(item.image)
            tv_name.setText(item.name)
            tv_price.text = "$ ${item.price}"

            if (item.store_id == 0){
                soldout.visibility = View.INVISIBLE
                itemView.isEnabled = true

            }else{
                soldout.visibility = View.VISIBLE
                itemView.isEnabled = false


            }
            itemView.setOnClickListener{
                buyListener?.toClick(item)
            }
        }
    }
    fun update(newList: MutableList<MagicItems>){
        magic_list.clear()
        magic_list.addAll(newList)
        notifyDataSetChanged()
    }
}