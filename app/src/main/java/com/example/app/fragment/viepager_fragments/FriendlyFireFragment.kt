package com.example.app.fragment.viepager_fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.app.R
import com.example.app.databinding.FragmentFriendlyFireBinding
import com.example.app.fragment.StartFragment

class FriendlyFireFragment : Fragment() {

    private var _binding: FragmentFriendlyFireBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFriendlyFireBinding.inflate(inflater, container, false)

        initListener()

        return binding.root
    }

    private fun initListener(){
        binding.startButton.setOnClickListener{
            val fragment = StartFragment()
            val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment).addToBackStack(fragment.tag)
            transaction.commit()
        }
    }
}