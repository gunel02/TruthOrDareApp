package com.example.app.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.app.data_class.PlayersData

class PlayersDiffUtil(
    private val oldList: List<PlayersData>,
    private val newList: List<PlayersData>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}
