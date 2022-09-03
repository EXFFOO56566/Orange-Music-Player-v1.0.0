package com.tochycomputerservices.orangemusic.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.ToggleButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.tochycomputerservices.orangemusic.R
import com.tochycomputerservices.orangemusic.database.allSongs.SongEntity

class SongQueueAdapter (context: Context
) : RecyclerView.Adapter<SongQueueAdapter.SongQueueViewHolder>() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var songs: List<SongEntity>? = null

    var favClickCallback: ((id: Int) -> Unit)? = null
    var onSongClickCallback: (( song: SongEntity, allFavSongs: List<SongEntity>) -> Unit)? = null
    var currentPlayingSetSelected: ((currentSong: SongEntity, cardViewOfSong:RelativeLayout, cardView:CardView) -> Unit)? = null

    class SongQueueViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtSongName: TextView = view.findViewById(R.id.txtSongName)
        val txtSongArtistName: TextView = view.findViewById(R.id.txtSongArtistName)
        val btnFav: ToggleButton = view.findViewById(R.id.btnFav)
        var cardViewForSong: CardView = view.findViewById(R.id.cardViewForSong)
        var relativeLayoutCard:RelativeLayout = view.findViewById(R.id.relativeLayoutCard)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongQueueViewHolder {
        val songItemView: View = mInflater.inflate(R.layout.single_song_item, parent, false)
        return SongQueueViewHolder(
            songItemView
        )
    }

    override fun onBindViewHolder(holder: SongQueueViewHolder, position: Int) {
        if (songs != null) {

            val currentSong: SongEntity = songs!![position]
            holder.txtSongName.text = currentSong.songName
            holder.txtSongArtistName.text = currentSong.artistName
            holder.btnFav.isChecked = songs!![position].isFav > 0

            currentPlayingSetSelected?.invoke(currentSong,holder.relativeLayoutCard,holder.cardViewForSong)

            holder.btnFav.setOnClickListener {
                favClickCallback?.invoke(currentSong.songId)
                Log.d("SINGLE PLAYLIST INFO", songs.toString())
            }

            holder.cardViewForSong.setOnClickListener {
                onSongClickCallback?.invoke(
                    currentSong,
                    songs!!
                )
            }
        } else {
            holder.txtSongName.setText(R.string.NoSong)
        }
    }

    fun setSongs(mSongs: List<SongEntity>) {
        songs = mSongs
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (songs != null)
            songs!!.size;
        else 0;
    }
}