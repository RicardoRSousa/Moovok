package com.ricardojrsousa.movook.presentation.discover.wizard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.presentation.discover.DiscoverViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.discover_fragment_3.*
import kotlinx.android.synthetic.main.discover_fragment_3.view.*
import java.util.*
import kotlin.math.floor

/**
 * Created by Ricardo Sousa on 21/01/2021.
 */

@AndroidEntryPoint
class DiscoverFragmentStep3 : Fragment(), OnRangeChangedListener {

    private lateinit var viewModel: DiscoverViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireParentFragment().requireParentFragment(), defaultViewModelProviderFactory).get(DiscoverViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.discover_fragment_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setYearRange(year_range_seek_bar)
        setRuntimeRange(runtime_range_seek_bar)
        setMinVoteAvgRange(min_vote_agv_seekbar)

        discover_button.setOnClickListener {
            val lteYear = floor(year_range_seek_bar.leftSeekBar.progress).toInt()
            val gteYear = floor(year_range_seek_bar.rightSeekBar.progress).toInt()
            val lteRuntime = floor(runtime_range_seek_bar.leftSeekBar.progress).toInt()
            val gteRuntime = floor(runtime_range_seek_bar.rightSeekBar.progress).toInt()
            val gteVoteAvg = min_vote_agv_seekbar.leftSeekBar.progress.toDouble()

            viewModel.setYearRange(lteYear, gteYear)
            viewModel.setRangeRuntime(lteRuntime, gteRuntime)
            viewModel.setMinVoteAvg(gteVoteAvg)
            viewModel.finishWizard()
        }
    }

    private fun setRuntimeRange(view: RangeSeekBar) {
        with(view) {
            setIndicatorTextDecimalFormat("0")
            setRange(minRuntime.toFloat(), maxRuntime.toFloat(), 1f)
            setProgress(minRuntime.toFloat(), maxRuntime.toFloat())
            steps = minRuntime - maxRuntime
            setOnRangeChangedListener(this@DiscoverFragmentStep3)
        }
        setRuntimeRangeLabels(minRuntime, maxRuntime)
    }

    private fun setYearRange(view: RangeSeekBar) {
        with(view) {
            setIndicatorTextDecimalFormat("0")
            setRange(minYear.toFloat(), maxYear.toFloat(), 1f)
            setProgress(defaultMinYearValue.toFloat(), currentYear.toFloat())
            steps = maxYear - minYear
            setOnRangeChangedListener(this@DiscoverFragmentStep3)
        }
        setYearRangeLabels(defaultMinYearValue, currentYear)
    }

    private fun setMinVoteAvgRange(view: RangeSeekBar) {
        with(view) {
            setIndicatorTextDecimalFormat("0.0")
            setOnRangeChangedListener(this@DiscoverFragmentStep3)
        }
        setMinVoteAvgLabels(0.0)
    }

    private fun setYearRangeLabels(from: Int, to: Int) {
        year_range_from_label.text = resources.getString(R.string.year_range_from, from)
        year_range_to_label.text = resources.getString(R.string.year_range_to, to)
    }

    private fun setRuntimeRangeLabels(from: Int, to: Int) {
        runtime_range_from_label.text = resources.getString(R.string.runtime_range_from, from)
        runtime_range_to_label.text = resources.getString(R.string.runtime_range_to, to)
    }

    private fun setMinVoteAvgLabels(from: Double) {
        min_vote_avg_from_label.text = resources.getString(R.string.min_vote_avg_from, from)
    }

    override fun onRangeChanged(view: RangeSeekBar?, leftValue: Float, rightValue: Float, isFromUser: Boolean) {
        if (view?.id == R.id.year_range_seek_bar) {
            setYearRangeLabels(leftValue.toInt(), rightValue.toInt())
        } else if (view?.id == R.id.runtime_range_seek_bar) {
            setRuntimeRangeLabels(leftValue.toInt(), rightValue.toInt())
        } else if(view?.id == R.id.min_vote_agv_seekbar){
            setMinVoteAvgLabels(leftValue.toDouble())
        }
    }

    override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}

    override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}

    companion object {
        private val currentYear: Int = Calendar.getInstance().get(Calendar.YEAR)
        private const val defaultMinYearValue: Int = 1970
        private const val minYear: Int = 1910
        private val maxYear: Int = currentYear

        private const val minRuntime: Int = 60
        private const val maxRuntime: Int = 240
    }

}