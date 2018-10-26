package `in`.khofid.schedule.player

import `in`.khofid.schedule.R
import `in`.khofid.schedule.model.Player
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity: AppCompatActivity() {

    private lateinit var player: Player

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        player = intent.getParcelableExtra("player")

        supportActionBar?.setTitle(player.strPlayer)

        fillValue()
    }

    fun fillValue(){
        player_weight.text = player.strWeight
        player_height.text = player.strHeight
        player_position.text = player.strPosition
        player_description.text = player.strDescriptionEN
        Picasso.get().load(player.strFanart).placeholder(R.drawable.stadium).into(toolbarImage)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}