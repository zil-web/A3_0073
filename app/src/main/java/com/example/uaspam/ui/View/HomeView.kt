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
import com.example.uaspam.model.Produk
import com.example.uaspam.ui.Customwidget.CustomTopAppBar
import com.example.uaspam.ui.Navigation.DestinasiNavigasi

import com.example.uaspam.ui.ViewModel.HomeUiState
import com.example.uaspam.ui.ViewModel.HomeViewModel
import com.example.uaspam.ui.ViewModel.PenyediaViewModel


object DestinasiHome: DestinasiNavigasi {
    override val route = "home"
    override val titleRes= "Home prdk"
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
 val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
 Scaffold (
     modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
     topBar = {
         CustomTopAppBar(
             title = DestinasiHome.titleRes,
             canNavigateBack = false,
             scrollBehavior = scrollBehavior,
             onRefresh = {
                 viewModel.getprdk()
             }

         )
     },
     floatingActionButton = {
         FloatingActionButton(
             onClick = navigateToItemEntry,
             shape = MaterialTheme.shapes.medium,
             modifier = Modifier.padding(18.dp)
         ){
             Icon(
                 imageVector = Icons.Default.Add,
                 contentDescription = "Add Produk"
             )
         }
     }
 ){
     innerPadding ->
     HomeStatus(
         homeUiState = viewModel.prdkUiState,
         retryAction = { viewModel.getprdk() },
         modifier = Modifier
             .padding(innerPadding),
         onDetailClick = onDetailClick,
         onDeleteClick = {
             viewModel.deleteprdk(it.id_produk)
             viewModel.getprdk()
         }
     )
 }
}


@Composable
fun HomeStatus(
    homeUiState: HomeUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Produk) -> Unit = {},
    onDetailClick: (String) -> Unit = {}
){
    when(homeUiState) {
        is HomeUiState.Loading -> Onloading(modifier = Modifier.fillMaxSize())

        is HomeUiState.Success ->
            if (homeUiState.produk.isEmpty()) {
                return Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data Produk")
                }
            }else{
                prdkLayout(
                    produk = homeUiState.produk,
                    modifier = modifier.fillMaxWidth(),
                    onDeleteClick = {
                        onDeleteClick(it)
                    },
                    onDetailClick = {
                        onDetailClick(it.id_produk)
                    }
                )
            }
        is HomeUiState.Error -> onError(retryAction, modifier = Modifier.fillMaxSize())
    }
}




@Composable
fun Onloading(modifier: Modifier =Modifier){
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.load),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun onError(
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
fun prdkLayout(
    produk: List<Produk>,
    modifier: Modifier = Modifier,
    onDeleteClick: (Produk) -> Unit = {},
    onDetailClick: (Produk) -> Unit
){
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(produk){produk ->
            prdkCard(
                produk = produk,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(produk) },
                onDeleteClick = {
                    onDeleteClick(produk)
                }
            )
        }
    }
}


@Composable
fun prdkCard(
    produk: Produk,
    modifier: Modifier = Modifier,
    onDeleteClick: (Produk) -> Unit = {}
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
                    text = produk.id_produk,
                    style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(produk) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Text(
                    text = produk.deskripsi_produk,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = produk.harga?.toString() ?: "N/A",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = produk.stok?.toString() ?: "N/A",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = produk.id_kategori?.toString() ?: "N/A",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = produk.id_pemasok?.toString() ?: "N/A",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = produk.id_merk?.toString() ?: "N/A",
                style = MaterialTheme.typography.titleMedium
            )

        }

    }
}