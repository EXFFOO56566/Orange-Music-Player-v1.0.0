package com.tochycomputerservices.orangemusic.uicomponents

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tochycomputerservices.orangemusic.R
import com.tochycomputerservices.orangemusic.adapters.PlaylistDialogAdapter
import com.tochycomputerservices.orangemusic.database.playlists.PlaylistEntity

class AddToPlaylist(
    context: Context
) : Dialog(context) {

    private var playlists: List<PlaylistEntity>? = null

    fun setDialogPlaylists(_playlists: List<PlaylistEntity>) {
        playlists = _playlists
    }


    var recyclerView: RecyclerView? = null
    var adapter: PlaylistDialogAdapter? = null

    var selectedPlaylistId = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_to_playlist_dialog)


        recyclerView = findViewById(R.id.recyclerViewPlaylists)

        adapter = PlaylistDialogAdapter(context)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(context)

        adapter?.playlistClickCallback = fun(id: Int) {
            selectedPlaylistId = id
            dismiss()
        }

        adapter?.setPlayLists(playlists!!)
    }
}