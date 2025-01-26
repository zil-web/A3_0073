package com.example.uaspam.ui.View

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.R
import com.example.uaspam.model.Pemasok
import com.example.uaspam.ui.Customwidget.CustomTopAppBar
import com.example.uaspam.ui.Navigation.DestinasiHomePemasok

import com.example.uaspam.ui.ViewModel.HomeUiStatePemasok
import com.example.uaspam.ui.ViewModel.HomeViewModelPemasok
import com.example.uaspam.ui.ViewModel.PenyediaViewModel





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenPemasok(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    navigateBack: () -> Unit,
    viewModel: HomeViewModelPemasok = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomePemasok.titleRes,
                navigateUp = navigateBack,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getpmsk()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Pemasok"
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            HomeStatusPemasok(
                homeUiStatePemasok = viewModel.pmskUiState,
                retryAction = { viewModel.getpmsk() },
                modifier = Modifier.fillMaxSize(),
                onDetailClick = onDetailClick,
                onDeleteClick = {
                    viewModel.deletepmsk(it.id_pemasok.toString())
                    viewModel.getpmsk()
                }
            )
        }
    }
}



@Composable
fun HomeStatusPemasok(
    homeUiStatePemasok: HomeUiStatePemasok,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pemasok) -> Unit = {},
    onDetailClick: (String) -> Unit = {}
){
    when(homeUiStatePemasok) {
        is HomeUiStatePemasok.Loading -> OnloadingPemasok(modifier = Modifier.fillMaxSize())

        is HomeUiStatePemasok.Success ->
            if (homeUiStatePemasok.pemasok.isEmpty()) {
                return Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Pemasok")
                }
            }else{
                pmskLayout(
                    pemasok = homeUiStatePemasok.pemasok,
                    modifier = modifier.fillMaxWidth(),
                    onDeleteClick = {
                        onDeleteClick(it)
                    },
                    onDetailClick = {
                        onDetailClick(it.id_pemasok.toString())
                    }
                )
            }
        is HomeUiStatePemasok.Error -> onErrorPemasok(retryAction, modifier = Modifier.fillMaxSize())
    }
}




@Composable
fun OnloadingPemasok(modifier: Modifier =Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.load),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun onErrorPemasok(
    retryAction: () -> Unit,modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ai), contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}


@Composable
fun pmskLayout(
    pemasok: List<Pemasok>,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pemasok) -> Unit = {},
    onDetailClick: (Pemasok) -> Unit
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(pemasok){pemasok ->
            pmskCard(
                pemasok = pemasok,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(pemasok) },
                onDeleteClick = {
                    onDeleteClick(pemasok)
                }
            )
        }
    }
}


@Composable
fun pmskCard(
    pemasok: Pemasok,
    modifier: Modifier = Modifier,
    onDeleteClick: (Pemasok) -> Unit = {}
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = pemasok.id_pemasok?.toString() ?: "N/A",
                    style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(pemasok) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Text(
                    text = pemasok.nama_produk,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = pemasok.nama_pemasok?.toString() ?: "N/A",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pemasok. alamat_pemasok?.toString() ?: "N/A",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = pemasok.telepon_pemasok?.toString() ?: "N/A",
                style = MaterialTheme.typography.titleMedium
            )


        }

    }
}