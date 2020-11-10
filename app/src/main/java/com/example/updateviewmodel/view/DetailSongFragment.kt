package com.example.updateviewmodel.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.updateviewmodel.databinding.FragmentDetailSongBinding
import com.example.updateviewmodel.model.SongSearch

class DetailSongFragment : Fragment() {
    private lateinit var binding: FragmentDetailSongBinding
    private var songSearch: SongSearch? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailSongBinding.inflate(
            inflater, container, false
        )
        inits()
        return binding.root
    }

    private fun inits() {
        binding.data = songSearch
    }

    fun setItemSong(songSearch: SongSearch) {
        this.songSearch = songSearch
    }
}