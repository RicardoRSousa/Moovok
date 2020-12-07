package com.ricardojrsousa.movook.presentation.persondetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.core.data.Person
import com.ricardojrsousa.movook.presentation.BaseFragment
import com.ricardojrsousa.movook.wrappers.loadCastProfileThumbnail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_person_details.*

@AndroidEntryPoint
class PersonDetailsFragment : BaseFragment<PersonDetailsViewModel>(R.layout.fragment_person_details) {

    override val viewModel: PersonDetailsViewModel by viewModels()
    private lateinit var personId: String

    private val args: PersonDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        personId = args.personId
        viewModel.init(personId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPostponedEnterTransition()

        val infoViewPagerAdapter = createInfoViewPagerAdapter()
        person_info_pager.adapter = infoViewPagerAdapter

        TabLayoutMediator(tab_layout, person_info_pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Biography"
                1 -> "Known for"
                else -> "Other Credits"
            }
        }.attach()

        observeViewModel(infoViewPagerAdapter)
    }

    private fun createInfoViewPagerAdapter(): PersonDetailsViewPagerAdapter {
        return PersonDetailsViewPagerAdapter { view, movies ->
            showLoading()
            if (movies != null) {
                val action = PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(movies.first().id)
                if (view != null) {
                    val extras = FragmentNavigatorExtras(view to movies.first().id)
                    navigate(action, extras)
                } else {
                    navigate(action)
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
            person_date_of_birth.text = getString(R.string.person_dob_age, person.getDateFormated(person.birthday), person.getDateFormated(person.deathday), person.getAge())
        }
        person_place_of_birth.text = person.placeOfBirth
    }
}