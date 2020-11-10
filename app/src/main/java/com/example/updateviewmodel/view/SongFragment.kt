package com.example.updateviewmodel.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.updateviewmodel.databinding.FragmentSongBinding
import com.example.updateviewmodel.model.SongSearch
import com.example.updateviewmodel.viewmodel.SongViewModel
import io.reactivex.disposables.Disposable

class SongFragment : Fragment(), TextWatcher, SongAdapter.CallBack {
    companion object {
        val TAG = SongFragment::class.java.simpleName
    }

    private lateinit var model: SongViewModel
    private var mDispose: Disposable? = null
    private lateinit var binding: FragmentSongBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView.............")
        binding = FragmentSongBinding.inflate(inflater, container, false)
        inits()
        return binding.root
    }

    private fun inits() {
        model = SongViewModel()

        binding.rcSong.apply {
            layoutManager = LinearLayoutManager(this@SongFragment.context)
            adapter =
                SongAdapter(this@SongFragment)
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


    override fun onResume() {
        Log.d(TAG, "onResume.............")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause.............")
        super.onPause()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView.............")
        mDispose?.dispose()
        super.onDestroyView()
    }

    override fun onClickItem(position: Int) {
        //phai tim duoc activity
        //lay activity chua fragment
        (activity as ContainActivity).openDetailSong(
            model.songs.value!![position]
        )
    }

    override fun getModel() = model
}