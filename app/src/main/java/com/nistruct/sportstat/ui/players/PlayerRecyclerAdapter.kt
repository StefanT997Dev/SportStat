package com.nistruct.sportstat.ui.players

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nistruct.sportstat.R
import com.nistruct.sportstat.data.models.ui_models.Player
import kotlinx.android.synthetic.main.activity_player_list_item.view.*

class PlayerRecyclerAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var players: List<Player> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlayerViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.activity_player_list_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is PlayerViewHolder->{
                holder.bind(players.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return players.size
    }

    fun submitList(playerList:List<Player>){
        players=playerList
    }

    class PlayerViewHolder constructor(
            itemView: View
    ):RecyclerView.ViewHolder(itemView){
        val playerImage=itemView.playerImageView
        val playerName=itemView.playerNameTextView
        val playerPosition=itemView.playerPositionTextView

        fun bind(player:Player){
            playerName.setText(player.name)
            playerPosition.setText(player.position)

            val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(player.image)
                    .into(playerImage)
        }
    }
}