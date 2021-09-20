package dev.gressier.abl.scoreboard

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import dev.gressier.abl.R

@BindingAdapter("isOccupied")
fun bindIsOccupied(imageView: ImageView, isOccupied: Boolean) {
    imageView.setImageResource(
        if (isOccupied) R.drawable.scoreboard_status_base_occupied
        else R.drawable.scoreboard_status_base_empty
    )
}

@BindingAdapter("isVisible")
fun bindIsVisible(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) VISIBLE else GONE
}