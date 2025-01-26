package com.example.uaspam.ui.Viewmerk

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
import com.example.uaspam.model.Merk
import com.example.uaspam.ui.Customwidget.CustomTopAppBar
import com.example.uaspam.ui.Navigation.DestinasiDetailMerk
import com.example.uaspam.ui.ViewModel.PenyediaViewModel
import com.example.uaspam.ui.ViewmodelMerk.DetailUiStateMerk
import com.example.uaspam.ui.ViewmodelMerk.DetailViewModelMerk


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreenMerk(
    navigateBack: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModelMerk = viewModel(factory = PenyediaViewModel.Factory)
) {
    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = DestinasiDetailMerk.titleRes,
                canNavigateBack = true,
                navigateUp = navigateBack,
                onRefresh = {
                    viewModel.getmerkbyid_merk()
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
        DetailStatusMerk(
            modifier = Modifier.padding(innerPadding),
            detailUiStateMerk = viewModel.merkDetailState,
            retryAction = { viewModel.getmerkbyid_merk() }
        )
    }
}

@Composable
fun DetailStatusMerk(
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    detailUiStateMerk: DetailUiStateMerk
) {
    when (detailUiStateMerk) {
        is DetailUiStateMerk.Loading -> OnloadingMerk(modifier = modifier.fillMaxSize())

        is DetailUiStateMerk.Success -> {
            if (detailUiStateMerk.merk.data.id_merk == 0) {
                Box(
                    modifier = modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Data tidak ditemukan.")
                }
            } else {
                ItemDetailmrk(
                    merk = detailUiStateMerk.merk.data,
                    modifier = modifier.fillMaxWidth()
                )
            }
        }

        is DetailUiStateMerk.Error -> onErrorMerk(retryAction , modifier = modifier.fillMaxSize())
    }
}

@Composable
fun ItemDetailmrk(
    modifier: Modifier = Modifier,
    merk: Merk
) {
    Card(
        modifier = modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ComponentDetailMrk(judul = "id_merk", isinya = merk.id_merk.toString())
            ComponentDetailMrk(judul = "nama_merk", isinya = merk.nama_merk)
            ComponentDetailMrk(judul = "deskripsi_merk", isinya = merk.deskripsi_merk)


        }
    }
}

@Composable
fun ComponentDetailMrk(
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