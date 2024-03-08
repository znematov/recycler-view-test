package com.example.recycleviewtest

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContactFragment : Fragment() {

    private val myAdapter by lazy { MyAdapter(getMessages()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = myAdapter

        val editText = view.findViewById<EditText>(R.id.searchInput)
        editText.apply {
            doAfterTextChanged {
                filterItems(it)
            }
        }
    }

    private fun filterItems(query: Editable?) {
        query?.let {
            if (it.isNotEmpty()) {
                val searchQuery = it.toString().lowercase()
                myAdapter.updateItems(getMessages().filter {
                    it.title.lowercase().contains(searchQuery) or it.body.lowercase()
                        .contains(searchQuery)
                })
            } else {
                myAdapter.updateItems(getMessages())
            }
        }
    }

    private fun getMessages(): ArrayList<HistoryBodyData> {
        val list: ArrayList<HistoryBodyData> = ArrayList()
        list.add(HistoryBodyData(R.drawable.img, "Mary", "Really?", "4.30 PM"))
        list.add(HistoryBodyData(R.drawable.img, "John", "No way!!!", "4.30 PM"))
        list.add(HistoryBodyData(R.drawable.img, "James", "Okay", "4.30 PM"))
        list.add(HistoryBodyData(R.drawable.img, "David", "What do u think", "4.30 PM"))
        return list
    }


}