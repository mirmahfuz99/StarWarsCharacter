package com.starwarscharacter.app


import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.starwarscharacter.app.core.db.StarWarsDatabase
import com.starwarscharacter.app.features.starships.data.datasource.local.StarShipsDao
import com.starwarscharacter.app.features.starships.data.datasource.remote.StarShipsRemoteDataSource
import com.starwarscharacter.app.features.starships.data.repository.StarShipsRepositoryImpl
import com.starwarscharacter.app.features.starships.domain.entity.StarShips
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class StarshipRepositoryImplTest {

    private lateinit var starshipRepository: StarShipsRepositoryImpl
    private lateinit var starshipRemoteDataSource: StarShipsRemoteDataSource
    private lateinit var starWarsDatabase: StarWarsDatabase
    private lateinit var starshipDao: StarShipsDao

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        starshipRemoteDataSource = mock(StarShipsRemoteDataSource::class.java)
        starWarsDatabase = mock(StarWarsDatabase::class.java)
        starshipDao = mock(StarShipsDao::class.java)
        starshipRepository = StarShipsRepositoryImpl(starshipRemoteDataSource, starWarsDatabase)

        // Mock the behavior of starWarsDatabase to return starshipDao
        Mockito.`when`(starWarsDatabase.starshipsDao()).thenReturn(starshipDao)
    }

    @Test
    fun getStarships_returnsPagingData() = runBlocking {
        val pagingSource: PagingSource<Int, StarShips> = mock(PagingSource::class.java) as PagingSource<Int, StarShips>
        Mockito.`when`(starshipDao.getAll()).thenReturn(pagingSource)

        val result: Flow<PagingData<StarShips>> = starshipRepository.getStarShips()

        // Verify that the function behaves as expected
        assertNotNull(result)
    }
}

