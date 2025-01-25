package com.example.uaspam.ui.ViewKategori

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uaspam.ui.Customwidget.CustomTopAppBar
import com.example.uaspam.ui.Navigation.DestinasiEntryKategori
import com.example.uaspam.ui.ViewModel.PenyediaViewModel
import com.example.uaspam.ui.ViewModelKategori.InsertUiEventKategori
import com.example.uaspam.ui.ViewModelKategori.InsertUiStateKategori
import com.example.uaspam.ui.ViewModelKategori.InsertViewModelKategori
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryktgrScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModelKategori = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiEntryKategori.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    )
    { innerPadding ->
        EntryBodyKategori(
            InsertUiStateKategori = viewModel.uiStateKategori,
            onValueChange = viewModel::updateInsertktgrState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertktgr()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        )
    }
}

@Composable
fun EntryBodyKategori(
    InsertUiStateKategori: InsertUiStateKategori,
    onValueChange: (InsertUiEventKategori) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier.padding(3.dp)
    )  {
        FormInputKategori(
            InsertUiEventKategori = InsertUiStateKategori.insertUiEventKategori,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(text = "Simpan")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputKategori(
    InsertUiEventKategori: InsertUiEventKategori,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEventKategori) -> Unit = {},
    enabled: Boolean = true,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Kolom pertama
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(2.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = InsertUiEventKategori.id_kategori?.toString() ?: "",
                onValueChange = { onValueChange(InsertUiEventKategori.copy(id_kategori = it))},
                label = { Text(text = "id_kategori") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = InsertUiEventKategori.nama_kategori,
                onValueChange = { onValueChange(InsertUiEventKategori.copy(nama_kategori = it)) },
                label = { Text(text = " nama_produk") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = InsertUiEventKategori.deskripsi_kategori,
                onValueChange = { onValueChange(InsertUiEventKategori.copy(deskripsi_kategori = it)) },
                label = { Text(text = "alamat_kategori") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )

        }

      
    }

    // Pesan dan divider
    if (enabled) {
        Text(
            text = "Isi Semua Data!",
            modifier = Modifier.padding(2.dp)
        )
    }
    Divider(
        thickness = 2.dp,
        modifier = Modifier.padding(5.dp)
    )
}

