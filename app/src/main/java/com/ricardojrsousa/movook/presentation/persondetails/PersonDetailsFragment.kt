package com.ricardojrsousa.movook.presentation.persondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.adapters.PersonDetailsViewPagerAdapter
import com.ricardojrsousa.movook.presentation.main.MainViewModel
import com.ricardojrsousa.movook.presentation.moviedetails.MovieDetailsFragmentDirections
import com.ricardojrsousa.movook.wrappers.loadCastProfileThumbnail
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_person_details.*

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<PersonDetailsViewModel>() {

    override val viewModel: PersonDetailsViewModel by viewModels()
    private lateinit var personId: String

    private val args: PersonDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personId = args.personId
        viewModel.init(personId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_person_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val infoViewPagerAdapter = createInfoViewPagerAdapter()
        person_info_pager.adapter = infoViewPagerAdapter
        // person_info_pager.recyclerView.enforceSingleScrollDirection()

        TabLayoutMediator(tab_layout, person_info_pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Biography"
                1 -> "Known for"
                else -> "Other Credits"
            }
        }.attach()

        /* val similarMoviesAdapter = createSimilarMoviesAdapter()
         val relatedBooksAdapter = createRelatedBooksAdapter()
         val castAdapter = createCastAdapter()

         setupRecyclerView(view, similarMoviesAdapter, relatedBooksAdapter, castAdapter)
         observeViewModel(similarMoviesAdapter, relatedBooksAdapter, castAdapter)
         postponeEnterTransition()*/

        observeViewModel(infoViewPagerAdapter)
    }

    private fun createInfoViewPagerAdapter(): PersonDetailsViewPagerAdapter {
        return PersonDetailsViewPagerAdapter { view, movies ->
            if (movies != null) {
                val action = PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(movies.first().id)
                if (view != null) {
                    val extras = FragmentNavigatorExtras(view to movies.first().id)
                    navController.navigate(action, extras)
                } else {
                    navController.navigate(action)
                }
            }
        }
    }

    private fun observeViewModel(inforViewPagerAdapter: PersonDetailsViewPagerAdapter) {
        viewModel.personDetails.observe(viewLifecycleOwner, Observer {
            setupView(it)
            inforViewPagerAdapter.setPerson(it)
        })
    }

    private fun setupView(person: Person) {
        person_name.text = person.name
        person_profile_image.loadCastProfileThumbnail(person.profilePath)
        if (!person.birthday.isNullOrBlank()) {
            person_date_of_birth.text = getString(R.string.person_dob_age, person.birthday, person.getAge())
        }
        person_place_of_birth.text = person.placeOfBirth
    }
}