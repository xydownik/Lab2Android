package com.example.dogsapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.dogsapp.R
import com.example.dogsapp.adapter.DogAdapter
import com.example.dogsapp.databinding.FragmentDogListBinding
import com.example.dogsapp.net.ApiClient
import com.example.dogsapp.net.Dog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DogListFragment : Fragment() {

    companion object {
        fun newInstance() = DogListFragment()
    }
    private var original: List<Dog> = ArrayList()
    private var _binding: FragmentDogListBinding? = null
    private val binding
        get() = _binding!!

    private val adapter: DogAdapter by lazy {
        DogAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDogListBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.editText.addTextChangedListener {
            val searchQuery = it.toString()
            if (searchQuery.isEmpty()) {
                adapter.submitList(original)
            } else {
                val list = original.filter {
                    it.name.contains(searchQuery, ignoreCase = true)
                }
                adapter.submitList(ArrayList(list))

                fetchDogsByName(searchQuery)
            }
        }
        setupUI()

    }

    private fun fetchDogsByName(name: String) {
        val client = ApiClient.instance
        val response = client.fetchDogList(name)
        response.enqueue(object : Callback<List<Dog>> {
            override fun onResponse(call: Call<List<Dog>>, response: Response<List<Dog>>) {
                response.body()?.let {
                    adapter.submitList(it)
                    original = it
                }
            }

            override fun onFailure(call: Call<List<Dog>>, t: Throwable) {
                println("${t.message}")
            }
        })
    }





    private fun setupUI() {
        with(binding) {
            dogList.adapter = adapter

        }
    }
}