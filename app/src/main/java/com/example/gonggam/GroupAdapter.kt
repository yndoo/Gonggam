package com.example.gonggam

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class GroupAdapter(private val context: Context) : RecyclerView.Adapter<GroupAdapter.ViewHolder>() {

    var datas = mutableListOf<GroupData>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recycler_group,parent,false)
        return ViewHolder(view).apply{
            itemView.setOnClickListener {
                val curPos:Int = adapterPosition
                val group:GroupData = datas.get(curPos)

                // 그룹 하나 클릭시 할 것
                Toast.makeText(parent.context, "그룹 : ${group.name}", Toast.LENGTH_SHORT).show()
            }
       }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val groupName: TextView = itemView.findViewById(R.id.tv_groupname)
        private val groupLock: ImageView = itemView.findViewById(R.id.img_locked)
        private val groupTotal: TextView = itemView.findViewById(R.id.tv_grouptotal)
        private val groupMember: TextView = itemView.findViewById(R.id.tv_groupmember)

        fun bind(item: GroupData) {
            groupName.text = item.name
            groupTotal.text = item.total
            groupMember.text = item.member

            //만약 비밀그룹이면 아이콘.
            groupLock.setImageResource(R.drawable.icon_locked)

            if(item.lock == false){
                itemView.findViewById<ImageView>(R.id.img_locked).visibility = View.GONE
            }
            else {
                itemView.findViewById<ImageView>(R.id.img_locked).visibility = View.VISIBLE
            }

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size


}