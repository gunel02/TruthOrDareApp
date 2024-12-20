package com.example.app.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.app.R
import com.example.app.adapter.ListOfTypes
import com.example.app.adapter.TypesOfLevelsAdapter
import com.example.app.databinding.FragmentTypesOfLevelsBinding
import com.example.app.fragment.viepager_fragments.FriendlyFireFragment

class TypesOfLevelsFragment : Fragment() {

    private lateinit var binding: FragmentTypesOfLevelsBinding
    private lateinit var typesOfLevelsAdapter: TypesOfLevelsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTypesOfLevelsBinding.inflate(inflater, container, false)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        typesOfLevelsAdapter = TypesOfLevelsAdapter( this,getItems() )
        binding.recyclerView.adapter = typesOfLevelsAdapter


        initListener()

        return binding.root
    }

    private fun getItems(): List<ListOfTypes> {
        val searchList = mutableListOf<ListOfTypes>()
        searchList.add(ListOfTypes(R.string.text_fun, R.drawable.icon_fun))
        searchList.add(ListOfTypes(R.string.text_crazy, R.drawable.icon_fire))
        searchList.add(ListOfTypes(R.string.text_extreme, R.drawable.icon_match))
        searchList.add(ListOfTypes(R.string.text_party_mode, R.drawable.icon_parties))
        searchList.add(ListOfTypes(R.string.text_custom_pack, R.drawable.icon_custom_pack))
        return searchList
    }

    private fun initListener(){
        binding.addPlayerButton.setOnClickListener{
            val fragment = SetPlayersFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
            transaction.commit()
        }

        binding.settingsButton.setOnClickListener{
            val fragment = SettingsFragment()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
            transaction.commit()
        }
    }
}