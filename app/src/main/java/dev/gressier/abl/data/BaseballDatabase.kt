package dev.gressier.abl.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import dev.gressier.abl.scoreboard.ScheduledGame
import dev.gressier.abl.standings.TeamStanding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [TeamStanding::class, ScheduledGame::class],
    exportSchema = false,
    version = 1,
)
@TypeConverters(BaseballConverters::class)
abstract class BaseballDatabase : RoomDatabase() {

    abstract fun baseballDao(): BaseballDao

    companion object {
        @Volatile private var instance: BaseballDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BaseballDatabase =
            instance ?: synchronized(this) {
                Room.databaseBuilder(context, BaseballDatabase::class.java, "BaseballDatabase")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            scope.launch {
                                instance?.baseballDao()
                                    ?.insertStandings(TeamStanding.mockTeamStandings)
                            }
                        }
                    })
                    .build()
                    .also { instance = it }
            }
    }
}