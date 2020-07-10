package com.ricardojrsousa.movook.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.viewmodel.MovieDetailsViewModelFactory
import com.ricardojrsousa.movook.wrappers.loadMovieBackdrop
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import com.squareup.picasso.Callback
import kotlinx.android.synthetic.main.fragment_movie_details.*


class MovieDetailsFragment : Fragment() {
    private lateinit var viewModel: MovieDetailsViewModel
    private var movieId: Int = 0

    val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieId = args.movieId
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        viewModel =
            MovieDetailsViewModelFactory(requireActivity().applicationContext, movieId).create(
                MovieDetailsViewModel::class.java
            )

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setSharedElementTransitionOnEnter()
        postponeEnterTransition()
        observeViewModel()
    }


    private fun observeViewModel() {
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
            movie_backdrop_image.loadMovieBackdrop(it.backdropPath)
            movie_poster_image.transitionName = it.posterPath
            movie_poster_image.loadMoviePoster(it.posterPath, object : Callback {
                override fun onSuccess() {
                    startPostponedEnterTransition()
                }

                override fun onError(e: Exception?) {
                    startPostponedEnterTransition()
                }

            })
            movie_description.text = it.overview
            movie_title.text = it.title
            movie_tagline.text = it.tagline
            movie_year.text = it.getReleaseYear()

        })
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_element_transition)
    }


}