package com.example.moviemvvm.ui.tv


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.moviemvvm.R
import com.example.moviemvvm.adapter.TvAdapter
import com.example.moviemvvm.data.model.DiscoverTv
import com.example.moviemvvm.ui.detail.DetailTvShowActivity
import com.example.moviemvvm.utils.InfiniteScrollListener
import com.example.moviemvvm.utils.gone
import com.example.moviemvvm.utils.visible
import com.example.moviemvvm.viewmodel.TvShowViewModel
import com.example.moviemvvm.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_tv_list.*

/**
 * A simple [Fragment] subclass.
 */
class TvListFragment : Fragment() {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var adapter: TvAdapter
    private var page = 1
    private var mShows: MutableList<DiscoverTv> = mutableListOf()

    companion object {
        fun newInstance() : Fragment {
            return TvListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBar.visible()

        if (activity != null) {
            viewModel = obtainViewModel(requireActivity())
            adapter = TvAdapter(context!!) {
                val i = Intent(context, DetailTvShowActivity::class.java)
                i.putExtra("tvShowID", it.id)
                startActivity(i)
            }

            loadShows()
            rvTvShow.adapter = adapter
            rvTvShow.layoutManager = LinearLayoutManager(context)
            rvTvShow.setHasFixedSize(true)
        }

        rvTvShow.addOnScrollListener(scrollData())

    }

    private fun obtainViewModel(requireActivity: FragmentActivity): TvShowViewModel {
        val factory = ViewModelFactory.getInstance(requireActivity.application)
        return ViewModelProviders.of(requireActivity, factory).get(TvShowViewModel::class.java)
    }

    private fun loadShows() {
        progressBar.visible()
        viewModel.page = page
        viewModel.getTvShows().observe(this, Observer { tvShows ->
            tvShows?.let {
                mShows.addAll(tvShows)
                adapter.setTvShows(mShows)
                progressBar.gone()
            }
        })
    }

    private fun scrollData(): InfiniteScrollListener {
        return object : InfiniteScrollListener() {
            override fun onLoadMore() {
                page += 1
                loadShows()
            }
        }
    }


}
