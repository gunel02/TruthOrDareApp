package com.example.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data_class.PlayersData

class PlayersNameAdapter : RecyclerView.Adapter<PlayersNameAdapter.PlayerViewHolder>() {

    private var playerList = emptyList<PlayersData>()
    private var onDeleteClickListener: ((PlayersData) -> Unit)? = null

    fun setOnDeleteClickListener(listener: (PlayersData) -> Unit) {
        onDeleteClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_list_players_name, parent, false)
        return PlayerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playerList[position]
        holder.playerName.text = player.name
        holder.deleteButton.setOnClickListener {
            onDeleteClickListener?.invoke(player)
        }
    }

    override fun getItemCount(): Int = playerList.size


    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerName = itemView.findViewById<TextView>(R.id.player_name)
        val deleteButton = itemView.findViewById<ImageView>(R.id.delete)

    }

    fun setData(players: List<PlayersData>) {
        this.playerList = players
        notifyDataSetChanged()
    }
}
