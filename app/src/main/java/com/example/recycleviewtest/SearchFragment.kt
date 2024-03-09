package com.example.recycleviewtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragment = ContactSearchFragment()
        val simpleName = fragment::class.simpleName

        val editText = view.findViewById<EditText>(R.id.searchInput)
        editText.apply {
            setOnClickListener {
                requireActivity().supportFragmentManager.apply {
                    val hasFragment = findFragmentByTag(simpleName)
                    beginTransaction().apply {
                        if (hasFragment != null) {
                            show(hasFragment)
                        } else {
                            replace(R.id.container, fragment, simpleName)
                        }
                        addToBackStack(null)
                        commit()
                    }
                }
            }
        }

        val button = view.findViewById<Button>(R.id.secondButton)

        button.setOnClickListener {
            requireActivity().supportFragmentManager.apply {
                val hasFragment = findFragmentByTag(simpleName)
                beginTransaction().apply {
                    if (hasFragment != null) {
                        show(hasFragment)
                    } else {
                        replace(R.id.container, fragment, simpleName)
                    }
                    addToBackStack(null)
                    commit()
                }
            }
        }
    }
}