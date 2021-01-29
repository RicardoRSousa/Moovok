package com.ricardojrsousa.movook.presentation.customviews

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.ricardojrsousa.movook.R
import com.ricardojrsousa.movook.utils.dp
import kotlinx.android.synthetic.main.vote_custom_view.view.*

class VoteView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var max: Int

    private var progressWidth: Float = 0f
    private var progressHeight: Float = 0f

    init {
        inflate(context, R.layout.vote_custom_view, this)

        val a = context.obtainStyledAttributes(attrs, R.styleable.VoteView, 0, 0)

        vote_label.textSize = a.getDimension(R.styleable.VoteView_label_textSize, 16f)
        vote_label.setTextColor(a.getColor(R.styleable.VoteView_label_textColor, resources.getColor(android.R.color.white)))
        max = a.getInt(R.styleable.VoteView_progressBar_Max, 10)
        vote_progress.max = max

        a.recycle()
    }

    fun setVote(value: Double?) {
        val vote = value ?: 0.0
        val jump: Double = max.div(4.0)

        val jumpTimes2 = jump.times(2)
        val jumpTimes3 = jump.times(3)

        val firstRange = 0.0..jump
        val secondRange = jump + 0.1..jumpTimes2
        val thirdRange = jumpTimes2 + 0.1..jumpTimes3

        val color = when (vote) {
            in firstRange -> R.color.votes_red
            in secondRange -> R.color.votes_orange
            in thirdRange -> R.color.votes_yellow
            else -> R.color.votes_green
        }

        vote_label.text = "$vote/$max"
        vote_progress.progressColor = resources.getColor(color)
        vote_progress.setProgress(vote.toFloat(), true)
    }

}
