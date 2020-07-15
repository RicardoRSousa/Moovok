package com.ricardojrsousa.movook.presentation.persondetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.presentation.adapters.PersonDetailsViewPagerAdapter
import com.ricardojrsousa.movook.wrappers.loadCastProfileThumbnail
import com.ricardojrsousa.movook.wrappers.loadMoviePoster
import kotlinx.android.synthetic.main.fragment_person_details.*

class PersonDetailsFragment : BaseFragment<PersonDetailsViewModel>() {

    override lateinit var viewModel: PersonDetailsViewModel
    private var personId: Int = 0

    private val args: PersonDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personId = args.personId
        viewModel = PersonDetailsViewModelFactory(requireActivity().applicationContext, personId).create(PersonDetailsViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_person_details, container, false)



        return view
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

    private fun createInfoViewPagerAdapter() = PersonDetailsViewPagerAdapter()

    private fun observeViewModel(inforViewPagerAdapter: PersonDetailsViewPagerAdapter) {
        viewModel.personDetails.observe(viewLifecycleOwner, Observer {
            setupView(it)
            inforViewPagerAdapter.setPerson(it)
        })
    }

    private fun setupView(person: Person) {
        person_name.text = person.name
        person_profile_image.loadCastProfileThumbnail(person.profilePath)
        person_date_of_birth.text = person.birthday
        person_place_of_birth.text = person.placeOfBirth
    }
}