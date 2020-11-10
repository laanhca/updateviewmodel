package com.example.updateviewmodel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.updateviewmodel.R
import com.example.updateviewmodel.model.SongSearch
import com.example.updateviewmodel.databinding.ActivityMainBinding
import com.example.updateviewmodel.viewmodel.SongViewModel
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity(), TextWatcher, SongAdapter.CallBack {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: SongViewModel
    private var mDispose: Disposable? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        model = SongViewModel()

        binding.rcSong.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter =
                SongAdapter(this@MainActivity)
        }
        binding.data = model
        binding.lifecycleOwner = this

        binding.btnSearch.setOnClickListener {
            mDispose?.dispose()
            mDispose = model.searchSong(binding.edtSearch.text.toString().trim())
        }
        binding.edtSearch.addTextChangedListener(this)

        mDispose = model.searchSong("")

        model.songs.observe(this, object : Observer<MutableList<SongSearch>> {
            override fun onChanged(t: MutableList<SongSearch>?) {
                (binding.rcSong.adapter as SongAdapter).notifyDataSetChanged()
            }
        })

    }

    override fun afterTextChanged(text: Editable) {
        mDispose?.dispose()
        mDispose = model.searchSong(binding.edtSearch.text.toString().trim())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onDestroy() {
        mDispose?.dispose()
        super.onDestroy()
    }

    override fun onClickItem(position: Int) {

    }

    override fun getModel() = model
}
