package com.example.updateviewmodel.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.updateviewmodel.R
import com.example.updateviewmodel.model.SongSearch

class ContainActivity:AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contain)
        addSongFragment()
    }

    private fun addSongFragment(){
        //fragmentManager quan ly cac fragment
        val fragmentManager = supportFragmentManager
        //quan ly phien lam viec cua transaction
        val tran = fragmentManager.beginTransaction()
        tran.replace(R.id.content, SongFragment(), SongFragment::class.java.name)
        //dong transaction
        tran.commit()
    }

    fun openDetailSong(songSearch: SongSearch) {
        //fragmentManager quan ly cac fragment
        val fragmentManager = supportFragmentManager
        //quan ly phien lam viec cua transaction
        val tran = fragmentManager.beginTransaction()
        val fr = DetailSongFragment()
        fr.setItemSong(songSearch)
        tran.replace(R.id.content, fr, SongFragment::class.java.name)
        //dong transaction
        tran.addToBackStack(null)
        tran.commit()
    }
}