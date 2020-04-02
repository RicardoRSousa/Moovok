package com.ricardojrsousa.movook.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.model.UpcomingMovie
import com.ricardojrsousa.movook.viewmodel.MainViewModel
import com.ricardojrsousa.movook.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.coroutines.MainCoroutineDispatcher

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModelFactory.create(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.movieName.observe(viewLifecycleOwner, Observer { .text = it.title })

        upcoming_movies_list.layoutManager =
            GridLayoutManager(context, 3)
        upcoming_movies_list.adapter =
    }
}
