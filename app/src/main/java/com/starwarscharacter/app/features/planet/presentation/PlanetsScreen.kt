package com.starwarscharacter.app.features.planet.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.starwarscharacter.app.features.planet.domain.entity.Planets
import com.starwarscharacter.app.features.planet.presentation.component.PlanetsItem
import com.starwarscharacter.app.utils.ErrorMessage
import com.starwarscharacter.app.utils.LoadingNextPageItem
import com.starwarscharacter.app.utils.PageLoader

@Composable
fun PlanetsScreen(
    viewModel: PlanetsViewModel = hiltViewModel()
) {
    val starShipsPagingItems: LazyPagingItems<Planets> = viewModel.starShipsState.collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }

        items(starShipsPagingItems.itemCount) { index ->
            PlanetsItem(starShipsPagingItems[index]!!)
        }

        starShipsPagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = starShipsPagingItems.loadState.refresh as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier.fillParentMaxSize(),
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { LoadingNextPageItem(modifier = Modifier) }
                }

                loadState.append is LoadState.Error -> {
                    print("inside_load_state_error");
                    val error = starShipsPagingItems.loadState.append as LoadState.Error
                    item {
                        ErrorMessage(
                            modifier = Modifier,
                            message = error.error.localizedMessage!!,
                            onClickRetry = { retry() })
                    }
                }
            }
        }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
    }
}
