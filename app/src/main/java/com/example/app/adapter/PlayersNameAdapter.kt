package com.example.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.data_class.PlayersData
import com.example.app.databinding.LayoutListPlayersNameBinding
import com.example.app.fragment.AddPeopleFragment

class PlayersNameAdapter(
    private val addPeopleFragment: AddPeopleFragment
) : RecyclerView.Adapter<PlayersNameAdapter.PlayerViewHolder>() {

    private val playersList = mutableListOf<PlayersData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val binding =
            LayoutListPlayersNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val player = playersList[position]

        with(holder.binding) {
            playerName.setText(player.name)

            delete.setOnClickListener {
                if (position >= 1 && position < playersList.size) {
                    addPeopleFragment.deletePlayer(playersList[position])

                    playersList.removeAt(position)

                    notifyItemRemoved(position)

                    notifyItemRangeChanged(position, playersList.size)
                }
            }

            if (player.gender == "man") {
                holder.iconMan.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context,
                        R.color.dirty_white
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
                        R.color.dirty_white
                    )
                )
            }

            holder.iconMan.setOnClickListener {
                if (player.gender != "man") {
                    player.gender = "man"
                    holder.iconMan.setColorFilter(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            R.color.dirty_white
                        )
                    )
                    holder.iconWomen.setColorFilter(
                        ContextCompat.getColor(holder.itemView.context, R.color.gray_color)
                    )
                    addPeopleFragment.updatePlayer(player)
                }
            }

            holder.iconWomen.setOnClickListener {
                if (player.gender != "women") {
                    player.gender = "women"
                    holder.iconWomen.setColorFilter(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            R.color.dirty_white
                        )
                    )
                    holder.iconMan.setColorFilter(
                        ContextCompat.getColor(
                            holder.itemView.context,
                            R.color.gray_color
                        )
                    )
                    addPeopleFragment.updatePlayer(player)
                }
            }

            playerName.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    player.name = playerName.text.toString()
                    addPeopleFragment.updatePlayer(player)
                }
            }
        }
    }

    override fun getItemCount(): Int = playersList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newPlayers: List<PlayersData>) {
        playersList.clear()
        playersList.addAll(newPlayers)
        notifyDataSetChanged()
    }

    class PlayerViewHolder(val binding: LayoutListPlayersNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val iconMan = binding.iconMan
        val iconWomen = binding.iconWomen

    }

}
