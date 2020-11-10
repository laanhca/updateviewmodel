package com.example.updateviewmodel.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.updateviewmodel.databinding.FragmentDetailSongBinding
import com.example.updateviewmodel.databinding.FragmentSongBinding

class DetailFragmentSong:Fragment() {
    private lateinit var binding: FragmentDetailSongBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailSongBinding.inflate(inflater,container, false )
        inits()
        return binding.root
    }
    private fun inits(){

    }
}