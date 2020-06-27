package com.ricardojrsousa.movook.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.framework.MainViewModel
import com.ricardojrsousa.movook.viewmodel.MainViewModelFactory
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var upcomingMoviesListAdapter: UpcomingMoviesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            MainViewModelFactory(requireActivity().applicationContext).create(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upcomingMoviesListAdapter = UpcomingMoviesListAdapter()

        with(upcoming_movies_list) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = upcomingMoviesListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movieName.observe(
            viewLifecycleOwner,
            Observer { upcomingMoviesListAdapter.addItems(it) })
    }
}
