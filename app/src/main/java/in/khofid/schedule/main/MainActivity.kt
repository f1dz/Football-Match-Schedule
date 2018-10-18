package `in`.khofid.schedule.main

import `in`.khofid.schedule.R
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var tabAdapter: TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabAdapter = TabAdapter(this, supportFragmentManager)
        view_pager.adapter = tabAdapter

        tab_layout.setupWithViewPager(view_pager)
    }
}
