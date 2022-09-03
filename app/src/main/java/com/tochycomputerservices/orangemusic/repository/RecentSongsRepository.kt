package com.tochycomputerservices.orangemusic.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.tochycomputerservices.orangemusic.database.recentSongs.RecentSongEntity
import com.tochycomputerservices.orangemusic.database.recentSongs.RecentSongsDao
import com.tochycomputerservices.orangemusic.database.recentSongs.RecentSongsDatabase

class RecentSongsRepository (application: Application) {

        private var mRecentSongsDao: RecentSongsDao

        val db: RecentSongsDatabase

        init {
            db = RecentSongsDatabase.getRecentSongsDatabase(application)
            mRecentSongsDao = db.recentSongsDao()
            //db.close()
        }

        suspend fun deleteSong(recentSongEntity: RecentSongEntity) = mRecentSongsDao.removeRecentSong(recentSongEntity)

        suspend fun insertSong(recentSongEntity: RecentSongEntity) = mRecentSongsDao.addRecentSong(recentSongEntity)

        val mRecentSongs: LiveData<List<RecentSongEntity>>
        get() {
            return mRecentSongsDao.allSongs
        }

}