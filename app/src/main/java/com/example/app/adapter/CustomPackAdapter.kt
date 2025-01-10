package com.example.app.adapter

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.databinding.LayoutListPlayersNameBinding
import com.example.app.fragment.CustomPackFragment
import com.example.app.helper.Const

class CustomPackAdapter(
    private val customPackFragment: CustomPackFragment
) : RecyclerView.Adapter<CustomPackAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding =
            LayoutListPlayersNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val player = customPackFragment.customList[holder.bindingAdapterPosition]

        with(holder.binding) {
            playerName.setText(player.name)

            delete.setOnClickListener {
                if (customPackFragment.customList.size > 2) {
                    customPackFragment.deletePlayer(customPackFragment.customList[holder.bindingAdapterPosition])
                }
            }

            if (player.gender == Const.MAN) {
                iconMan.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context, R.color.dirty_white
                    )
                )
                iconWomen.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context, R.color.gray_color
                    )
                )
            } else {
                iconMan.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context, R.color.gray_color
                    )
                )
                iconWomen.setColorFilter(
                    ContextCompat.getColor(
                        holder.itemView.context, R.color.dirty_white
                    )
                )
            }

            iconMan.setOnClickListener {
                if (player.gender != Const.MAN) {
                    player.gender = Const.MAN
                    iconMan.setColorFilter(
                        ContextCompat.getColor(
                            holder.itemView.context, R.color.dirty_white
                        )
                    )
                    holder.binding.iconWomen.setColorFilter(
                        ContextCompat.getColor(holder.itemView.context, R.color.gray_color)
                    )
                }
            }

            iconWomen.setOnClickListener {
                if (player.gender != Const.WOMAN) {
                    player.gender = Const.WOMAN
                    holder.binding.iconWomen.setColorFilter(
                        ContextCompat.getColor(
                            holder.itemView.context, R.color.dirty_white
                        )
                    )
                    holder.binding.iconMan.setColorFilter(
                        ContextCompat.getColor(
                            holder.itemView.context, R.color.gray_color
                        )
                    )
                }
            }

            playerName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                }
                override fun afterTextChanged(s: Editable?) {
                    customPackFragment.customList[holder.bindingAdapterPosition].name = s.toString()
                }
            })
        }    }


    override fun getItemCount(): Int = customPackFragment.customList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData() {
        notifyDataSetChanged()
    }

    class CustomViewHolder(val binding: LayoutListPlayersNameBinding) :
        RecyclerView.ViewHolder(binding.root)
}
