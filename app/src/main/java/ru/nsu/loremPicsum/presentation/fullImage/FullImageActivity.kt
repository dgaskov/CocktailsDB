package ru.nsu.loremPicsum.presentation.fullImage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_full_image.*
import ru.nsu.loremPicsum.R

class FullImageActivity: AppCompatActivity() {
    private lateinit var viewModel: FullImageViewModel
    private lateinit var circularProgressDrawable: CircularProgressDrawable
    private var systemUIVisible = false

    companion object {
        const val ID_KEY = "IMAGE_ID_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_image)
        setImageViewOnClickListener()
        setLoadingPlaceholder()

        initAndSubscribeViewModel()

        val args = intent.extras
        val imageId = args?.getInt(ID_KEY) ?: 0
        viewModel.fetchImageURL(imageId)
    }

    private fun initAndSubscribeViewModel() {
        viewModel = ViewModelProviders.of(this).get(FullImageViewModel::class.java)
        viewModel.getImage.observe(this, Observer {
            Glide.with(this)
                    .load(it.downloadURL.toString())
                    .placeholder(circularProgressDrawable)
                    .into(imageView)
        })
        viewModel.getErrors.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setImageViewOnClickListener() {
        imageView.setOnClickListener {
            if (systemUIVisible) {
                hideSystemUI()
            } else {
                showSystemUI()
            }
            systemUIVisible = !systemUIVisible
        }
    }

    private fun setLoadingPlaceholder() {
        circularProgressDrawable = CircularProgressDrawable(this)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}