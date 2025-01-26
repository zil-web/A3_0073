package com.example.uaspam.ui.ViewKategori

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
import com.example.uaspam.model.Kategori
import com.example.uaspam.ui.Customwidget.CustomTopAppBar
import com.example.uaspam.ui.Navigation.DestinasiHomeKategori
import com.example.uaspam.ui.ViewModel.PenyediaViewModel
import com.example.uaspam.ui.ViewModelKategori.HomeUiStateKategori
import com.example.uaspam.ui.ViewModelKategori.HomeViewModelKategori


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenKategori(
    navigateToItemEntry: () -> Unit,
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModelKategori = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiHomeKategori.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getktgr()
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
                    contentDescription = "Tambah Kategori"
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            HomeStatusKategori(
                homeUiStateKategori = viewModel.ktgrUiState,
                retryAction = { viewModel.getktgr() },
                modifier = Modifier.fillMaxSize(),
                onDetailClick = onDetailClick,
                onDeleteClick = {
                    viewModel.deletektgr(it.id_kategori.toString())
                    viewModel.getktgr()
                }
            )
        }
    }
}



@Composable
fun HomeStatusKategori(
    homeUiStateKategori: HomeUiStateKategori,
    retryAction: () -> Unit,

    modifier: Modifier = Modifier,
    onDeleteClick: (Kategori) -> Unit = {},
    onDetailClick: (String) -> Unit = {}
){
    when(homeUiStateKategori) {
        is HomeUiStateKategori.Loading -> OnloadingKategori(modifier = Modifier.fillMaxSize())

        is HomeUiStateKategori.Success ->
            if (homeUiStateKategori.kategori.isEmpty()) {
                return Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Kategori")
                }
            }else{
                ktgrLayout(
                    kategori = homeUiStateKategori.kategori,
                    modifier = modifier.fillMaxWidth(),
                    onDeleteClick = {
                        onDeleteClick(it)
                    },
                    onDetailClick = {
                        onDetailClick(it.id_kategori.toString())
                    }
                )
            }
        is HomeUiStateKategori.Error -> onErrorKategori(retryAction, modifier = Modifier.fillMaxSize())
    }
}




@Composable
fun OnloadingKategori(modifier: Modifier =Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.load),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun onErrorKategori(
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
fun ktgrLayout(
    kategori: List<Kategori>,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kategori) -> Unit = {},
    onDetailClick: (Kategori) -> Unit
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(kategori){kategori ->
            ktgrCard(
                kategori = kategori,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(kategori) },
                onDeleteClick = {
                    onDeleteClick(kategori)
                }
            )
        }
    }
}


@Composable
fun ktgrCard(
    kategori: Kategori,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kategori) -> Unit = {}
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
                    text = kategori.id_kategori?.toString() ?: "N/A",
                    style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(kategori) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Text(
                    text = kategori.nama_kategori,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = kategori.deskripsi_kategori?.toString() ?: "N/A",
                style = MaterialTheme.typography.titleMedium
            )




        }

    }
}