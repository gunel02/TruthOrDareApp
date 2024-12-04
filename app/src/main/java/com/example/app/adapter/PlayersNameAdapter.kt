package com.example.app.adapter

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data_class.PlayersData
import com.example.app.fragment.SetPlayersFragment

class PlayersNameAdapter(val setPlayersFragment: SetPlayersFragment) :
    RecyclerView.Adapter<PlayersNameAdapter.PlayerViewHolder>() {

    private var playerList = emptyList<PlayersData>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_players_name, parent, false)
        return PlayerViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]
        holder.playerName.text = player.name

        holder.deleteButton.setOnClickListener {
            val position = holder.bindingAdapterPosition
            if (playerList.size > 1) {
                setPlayersFragment.usersList.removeAt(position)
                setPlayersFragment.deletePlayer(player)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, playerList.size)
            }
        }


        if (player.gender == "man") {
            holder.iconMan.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
            holder.iconWomen.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.gray_color
                )
            )
        } else {
            holder.iconMan.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.gray_color
                )
            )
            holder.iconWomen.setColorFilter(
                ContextCompat.getColor(
                    holder.itemView.context,
                    R.color.white
                )
            )
        }

        holder.iconMan.setOnClickListener {
            if (player.gender != "man") {
                player.gender = "man"
                holder.iconMan.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.white
                    )
                )
                holder.iconWomen.setColorFilter(
                    ContextCompat.getColor(holder.itemView.context, R.color.gray_color)
                )
                setPlayersFragment.updatePlayer(player)
            }
        }

        holder.iconWomen.setOnClickListener {
            if (player.gender != "women") {
                player.gender = "women"
                holder.iconWomen.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.white
                    )
                )
                holder.iconMan.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.gray_color
                    )
                )
                setPlayersFragment.updatePlayer(player)
            }
        }

        holder.playerName.tag = player
        holder.playerName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val playerData = holder.playerName.tag as PlayersData
                playerData.name = s.toString()
                setPlayersFragment.updatePlayer(playerData)
            }
        })
    }


    override fun getItemCount(): Int = playerList.size

    fun setData(newPlayerList: List<PlayersData>) {
        val diffUtilCallback = PlayersDiffUtil(playerList, newPlayerList)
        val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
        playerList = newPlayerList
        diffResult.dispatchUpdatesTo(this)
    }


    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName = itemView.findViewById<TextView>(R.id.player_name)
        val deleteButton = itemView.findViewById<ImageView>(R.id.delete)
        val iconMan = itemView.findViewById<ImageView>(R.id.icon_man)
        val iconWomen = itemView.findViewById<ImageView>(R.id.icon_women)
    }
}
