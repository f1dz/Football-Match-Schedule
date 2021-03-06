package `in`.khofid.schedule.home

import `in`.khofid.schedule.R
import `in`.khofid.schedule.favorites.FavoritesFragment
import `in`.khofid.schedule.match.MatchesContainer
import `in`.khofid.schedule.team.TeamsFragment
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
                R.id.matches -> loadFragment(savedInstanceState, MatchesContainer())
                R.id.teams -> loadFragment(savedInstanceState, TeamsFragment())
                R.id.favorites -> loadFragment(savedInstanceState, FavoritesFragment())
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.matches

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