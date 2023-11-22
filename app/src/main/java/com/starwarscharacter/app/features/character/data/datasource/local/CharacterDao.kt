
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.starwarscharacter.app.features.character.domain.entity.Character


@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<Character>)

    @Query("SELECT * FROM characters")
    fun getAll(): PagingSource<Int, Character>

    @Query("DELETE FROM characters")
    suspend fun clearCharacters()
}