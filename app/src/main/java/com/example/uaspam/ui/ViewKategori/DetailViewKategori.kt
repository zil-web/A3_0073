package com.example.uaspam.ui.ViewKategori

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
import com.example.uaspam.model.Kategori
import com.example.uaspam.ui.Customwidget.CustomTopAppBar
import com.example.uaspam.ui.Navigation.DestinasiDetailKategori
import com.example.uaspam.ui.ViewModel.PenyediaViewModel
import com.example.uaspam.ui.ViewModelKategori.DetailUiStateKategori
import com.example.uaspam.ui.ViewModelKategori.DetailViewModelKategori


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenKategori(
    navigateBack: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModelKategori = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = DestinasiDetailKategori.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getkategoribyid_kategori()
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
        DetailStatusKategori(
            modifier = Modifier.padding(innerPadding),
            detailUiStateKategori = viewModel.kategoriDetailState,
            retryAction = { viewModel.getkategoribyid_kategori() }
        )
    }
}

@Composable
fun DetailStatusKategori(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailUiStateKategori: DetailUiStateKategori
) {
    when (detailUiStateKategori) {
        is DetailUiStateKategori.Loading -> OnloadingKategori(modifier = modifier.fillMaxSize())

        is DetailUiStateKategori.Success -> {
            if (detailUiStateKategori.kategori.data.id_kategori == 0) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Data tidak ditemukan.")
                }
            } else {
                ItemDetailktgr(
                    kategori = detailUiStateKategori.kategori.data,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }

        is DetailUiStateKategori.Error -> onErrorKategori(retryAction , modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailktgr(
    modifier: Modifier = Modifier,
    kategori: Kategori
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailKtgr(judul = "id_kategori", isinya = kategori.id_kategori.toString())
            ComponentDetailKtgr(judul = "nama_kategori", isinya = kategori.nama_kategori)
            ComponentDetailKtgr(judul = "deskripsi_kategori", isinya = kategori.deskripsi_kategori)
            

        }
    }
}

@Composable
fun ComponentDetailKtgr(
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