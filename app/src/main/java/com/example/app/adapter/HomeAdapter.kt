package com.example.app.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.databinding.LayoutCardBinding
import com.example.app.fragment.AddPeopleFragment
import com.example.app.fragment.HomeFragment
import com.example.app.fragment.SelectGameModeFragment
import com.example.app.view_model.PlayerViewModel

class HomeAdapter(
    private val fragment: HomeFragment,
    private val searchList: List<HomeList>,
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            LayoutCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = searchList[position]
        holder.binding.apply {
            icons.setImageResource(current.icon)
            title.text = fragment.getString(current.title)

            nextButton.setOnClickListener {
                val selectGameModeFragment = SelectGameModeFragment( )
                val fragmentName = selectGameModeFragment::class.java.name
                fragment.activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.fragment_container, selectGameModeFragment)
                    ?.addToBackStack(fragmentName)
                    ?.commit()

                val playerViewModel = ViewModelProvider(fragment)[PlayerViewModel::class.java]
                playerViewModel.readAllData.observe(fragment) { players ->
                    val playerNames = players.map { it.name }
                    val bundle = Bundle()
                    bundle.putStringArrayList("PLAYER_NAMES", ArrayList(playerNames))
                    selectGameModeFragment.arguments = bundle

                }

            }
        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    class ViewHolder(val binding: LayoutCardBinding) :
        RecyclerView.ViewHolder(binding.root)
}




