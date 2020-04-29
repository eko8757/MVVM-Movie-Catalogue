package com.example.moviemvvm.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.moviemvvm.R
import com.example.moviemvvm.ui.favorite.FavoriteFragment
import com.example.moviemvvm.ui.movie.MovieListFragment
import com.example.moviemvvm.ui.tv.TvListFragment
import com.example.moviemvvm.utils.Constant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_bottom_nav.setOnNavigationItemSelectedListener {
            val fragment: Fragment? = when(it.itemId) {
                R.id.action_movie -> MovieListFragment.newInstance()
                R.id.action_tv -> TvListFragment.newInstance()
                R.id.action_favorite -> FavoriteFragment.newInstance()
                else -> null
            }

            if (fragment != null) {
                supportFragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.main_container, fragment)
                    .commit()
            }

            true
        }

        if (savedInstanceState != null) {
            savedInstanceState.getInt(Constant.SELECTED_MENU)
        } else {
            main_bottom_nav.selectedItemId = R.id.action_movie
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putInt(Constant.SELECTED_MENU, main_bottom_nav.selectedItemId)
    }
}
