package com.example.uaspam.ui.View

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.model.Pemasok
import com.example.uaspam.ui.Customwidget.CustomTopAppBar
import com.example.uaspam.ui.Navigation.DestinasiDetailPemasok
import com.example.uaspam.ui.ViewModel.DetailUiStatePemasok
import com.example.uaspam.ui.ViewModel.DetailViewModelPemasok
import com.example.uaspam.ui.ViewModel.PenyediaViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenPemasok(
    navigateBack: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModelPemasok = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = DestinasiDetailPemasok.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getpemasokbyid_pemasok()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onEditClick,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit Kontak"
                )
            }
        }
    ) { innerPadding ->
        DetailStatusPemasok(
            modifier = Modifier.padding(innerPadding),
            detailUiStatePemasok = viewModel.pemasokDetailState,
            retryAction = { viewModel.getpemasokbyid_pemasok() }
        )
    }
}

@Composable
fun DetailStatusPemasok(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailUiStatePemasok: DetailUiStatePemasok
) {
    when (detailUiStatePemasok) {
        is DetailUiStatePemasok.Loading -> OnloadingPemasok(modifier = modifier.fillMaxSize())

        is DetailUiStatePemasok.Success -> {
            if (detailUiStatePemasok.pemasok.data.id_pemasok == 0) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Data tidak ditemukan.")
                }
            } else {
                ItemDetailpmsk(
                    pemasok = detailUiStatePemasok.pemasok.data,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }

        is DetailUiStatePemasok.Error -> onErrorPemasok(retryAction , modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailpmsk(
    modifier: Modifier = Modifier,
    pemasok: Pemasok
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailPmsk(judul = "id_pemasok", isinya = pemasok.id_pemasok.toString())
            ComponentDetailPmsk(judul = "nama_produk", isinya = pemasok.nama_produk)
            ComponentDetailPmsk(judul = "nama_pemasok", isinya = pemasok.nama_pemasok)
            ComponentDetailPmsk(judul = "alamat_pemasok", isinya = pemasok.alamat_pemasok)
            ComponentDetailPmsk(judul = "telepon_pemasok", isinya = pemasok.telepon_pemasok.toString())
            

        }
    }
}

@Composable
fun ComponentDetailPmsk(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
    Column(modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start)
    {
        Text(
            text = "$judul : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = isinya,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}