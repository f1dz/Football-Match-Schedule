package `in`.khofid.schedule.home

import `in`.khofid.schedule.R
import `in`.khofid.schedule.match.PrevMatchFragment
import `in`.khofid.schedule.match.NextMatchFragment
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener {item ->
            when (item.itemId) {
                R.id.prev_match -> {
                    loadFragment(savedInstanceState, PrevMatchFragment())
                }
                R.id.next_match -> {
                    loadFragment(savedInstanceState, NextMatchFragment())
                }
                R.id.favorites -> {
                    loadFragment(savedInstanceState, PrevMatchFragment())
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.prev_match

    }

    private fun loadFragment(savedInstanceState: Bundle?, fragment: Fragment){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, fragment, fragment::class.java.simpleName)
                .commit()
        }
    }
}