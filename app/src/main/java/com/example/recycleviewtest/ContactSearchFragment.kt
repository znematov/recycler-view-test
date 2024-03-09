package com.example.recycleviewtest

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ContactSearchFragment : Fragment() {

    private val myAdapter by lazy { MyAdapter(getMessages()) }

    private fun getMessages(): ArrayList<Contact> {
        val list: ArrayList<Contact> = ArrayList()
        list.add(Contact("Mary"))
        list.add(Contact("John"))
        list.add(Contact("James"))
        list.add(Contact("David"))
        return list
    }

    private val TAG = this::class.simpleName

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewContact)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        recyclerView.adapter = myAdapter


        val searchInput = view.findViewById<EditText>(R.id.searchInputContact)
        searchInput.requestFocus()
        val imm = requireContext().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
        searchInput.apply {
            doAfterTextChanged {
                filterItems(it)
            }
        }

    }

    private fun filterItems(query: Editable?) {
        query?.let {
            if (it.isNotEmpty()) {
                val searchQuery = it.toString().lowercase()
                myAdapter.updateItems(getMessages().filter { contact ->
                    contact.name.lowercase().contains(searchQuery)
                })
            } else {
                myAdapter.updateItems(getMessages())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }
}