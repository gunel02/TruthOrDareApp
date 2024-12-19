    package com.example.app.adapter

    import android.util.Log
    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import com.example.app.databinding.LayoutCardBinding
    import com.example.app.fragment.TypesOfLevelsFragment

    class TypesOfLevelsAdapter(
        private val fragment: TypesOfLevelsFragment,
        private val searchList: List<ListOfTypes>,

    ) :
        RecyclerView.Adapter<TypesOfLevelsAdapter.ViewHolder>() {

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

            }

        }

        override fun getItemCount(): Int {
            return searchList.size
        }


        class ViewHolder(val binding: LayoutCardBinding) :
            RecyclerView.ViewHolder(binding.root) {
        }
    }