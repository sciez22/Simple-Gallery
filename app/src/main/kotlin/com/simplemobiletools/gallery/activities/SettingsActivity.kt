package com.simplemobiletools.gallery.activities

import android.content.Intent
import android.os.Bundle
import com.simplemobiletools.commons.extensions.updateTextColors
import com.simplemobiletools.gallery.R
import com.simplemobiletools.gallery.dialogs.ShowMediaDialog
import com.simplemobiletools.gallery.extensions.beVisibleIf
import com.simplemobiletools.gallery.extensions.config
import com.simplemobiletools.gallery.helpers.IMAGES
import com.simplemobiletools.gallery.helpers.IMAGES_AND_VIDEOS
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : SimpleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
    }

    override fun onResume() {
        super.onResume()

        setupCustomizeColors()
        setupManageExcludedFolders()
        setupShowHiddenFolders()
        setupAutoplayVideos()
        setupLoopVideos()
        setupAnimateGifs()
        setupShowMedia()
        updateTextColors(settings_holder)
    }

    private fun setupCustomizeColors() {
        settings_customize_colors_holder.setOnClickListener {
            startCustomizationActivity()
        }
    }

    private fun setupManageExcludedFolders() {
        val excludedFolders = config.excludedFolders
        settings_manage_excluded_folders_holder.beVisibleIf(excludedFolders.isNotEmpty())
        settings_manage_excluded_folders_holder.setOnClickListener {
            startActivity(Intent(this, ExcludedFoldersActivity::class.java))
        }
    }

    private fun setupShowHiddenFolders() {
        settings_show_hidden_folders.isChecked = config.showHiddenFolders
        settings_show_hidden_folders_holder.setOnClickListener {
            settings_show_hidden_folders.toggle()
            config.showHiddenFolders = settings_show_hidden_folders.isChecked
        }
    }

    private fun setupAutoplayVideos() {
        settings_autoplay_videos.isChecked = config.autoplayVideos
        settings_autoplay_videos_holder.setOnClickListener {
            settings_autoplay_videos.toggle()
            config.autoplayVideos = settings_autoplay_videos.isChecked
        }
    }

    private fun setupLoopVideos() {
        settings_loop_videos.isChecked = config.loopVideos
        settings_loop_videos_holder.setOnClickListener {
            settings_loop_videos.toggle()
            config.loopVideos = settings_loop_videos.isChecked
        }
    }

    private fun setupAnimateGifs() {
        settings_animate_gifs.isChecked = config.animateGifs
        settings_animate_gifs_holder.setOnClickListener {
            settings_animate_gifs.toggle()
            config.animateGifs = settings_animate_gifs.isChecked
        }
    }

    private fun setupShowMedia() {
        settings_show_media.text = getShowMediaText()
        settings_show_media_holder.setOnClickListener {
            ShowMediaDialog(this) {
                config.showMedia = it
                settings_show_media.text = getShowMediaText()
            }
        }
    }

    private fun getShowMediaText() = getString(when (config.showMedia) {
        IMAGES_AND_VIDEOS -> R.string.images_and_videos
        IMAGES -> R.string.images
        else -> R.string.videos
    })
}
