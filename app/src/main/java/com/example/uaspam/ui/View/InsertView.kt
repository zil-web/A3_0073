package com.example.uaspam.ui.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
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
import com.example.uaspam.model.Kategori
import com.example.uaspam.model.Merk
import com.example.uaspam.model.Pemasok
import com.example.uaspam.ui.Customwidget.CustomTopAppBar
import com.example.uaspam.ui.Customwidget.Dropdown
import com.example.uaspam.ui.Navigation.DestinasiEntry
import com.example.uaspam.ui.ViewModel.InsertUiEvent
import com.example.uaspam.ui.ViewModel.InsertUiState
import com.example.uaspam.ui.ViewModel.InsertViewModel
import com.example.uaspam.ui.ViewModel.PenyediaViewModel
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryprdkScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){
        innerPadding ->
        EntryBody(
            insertUiState = viewModel.uiState,
            onSiswaValueChange = viewModel::updateInsertprdkState,
            pmskList = viewModel.pmskList,
            ktgrList = viewModel.ktgrList,
            mrkList = viewModel.mrkList,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertprdk()
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
fun EntryBody(
    insertUiState: InsertUiState,
    onSiswaValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
    pmskList: List<Pemasok>,
    ktgrList: List<Kategori>,
    mrkList: List<Merk>
){
  Column (
      verticalArrangement = Arrangement.spacedBy(2.dp),
      modifier = modifier.padding(3.dp)
  )  {
      FormInput(
          insertUiEvent = insertUiState.insertUiEvent,
          onValueChange = onSiswaValueChange,
          insertUiState = insertUiState,
          modifier = Modifier.fillMaxWidth(),
          pmskList = pmskList,
          ktgrList = ktgrList,
          mrkList = mrkList
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
fun FormInput(
    insertUiEvent: InsertUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent) -> Unit = {},
    enabled: Boolean = true,
    insertUiState: InsertUiState,
    pmskList: List<Pemasok>,
    ktgrList: List<Kategori>,
    mrkList: List<Merk>
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
                value = insertUiEvent.nama_produk,
                onValueChange = { onValueChange(insertUiEvent.copy(nama_produk = it)) },
                label = { Text(text = "Nama Produk") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )

            OutlinedTextField(
                value = insertUiEvent.deskripsi_produk,
                onValueChange = { onValueChange(insertUiEvent.copy(deskripsi_produk = it)) },
                label = { Text(text = "Deskripsi Produk") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = insertUiEvent.harga?.toString() ?: "",
                onValueChange = { onValueChange(insertUiEvent.copy(harga = it.toIntOrNull())) },
                label = { Text(text = "Harga") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
        }

        // Kolom kedua
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(2.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = insertUiEvent.stok?.toString() ?: "",
                onValueChange = { onValueChange(insertUiEvent.copy(stok = it.toIntOrNull())) },
                label = { Text(text = "Stok") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )

            Dropdown(
                // untuk memanggil sesui  forenkey
                selectedValue = pmskList.find { it.id_pemasok == insertUiState.insertUiEvent.id_pemasok }?.nama_pemasok ?: "",
                options = pmskList.map { it.nama_pemasok },
                label = "Pemasok",
                onValueChangedEvent = {id_pemasok ->
                val selectedPemasok = pmskList.find { it.nama_pemasok == id_pemasok }
                    selectedPemasok?.let {
                        onValueChange(insertUiEvent.copy(id_pemasok = it.id_pemasok)) }
                }
            )

            Dropdown(
                // untuk memanggil sesui  forenkey
                selectedValue = ktgrList.find { it.id_kategori== insertUiState.insertUiEvent.id_kategori }?.nama_kategori ?: "",
                options = ktgrList.map { it.nama_kategori },
                label = "kategori",
                onValueChangedEvent = {id_kategori->
                    val selectedKategori = ktgrList.find { it.nama_kategori == id_kategori}
                    selectedKategori?.let {
                        onValueChange(insertUiEvent.copy(id_kategori = it.id_kategori)) }
                }
            )

            Dropdown(
                // untuk memanggil sesui  forenkey
                selectedValue = mrkList.find { it.id_merk== insertUiState.insertUiEvent.id_merk }?.nama_merk ?: "",
                options = mrkList.map { it.nama_merk },
                label = "merk",
                onValueChangedEvent = {id_merk ->
                    val selectedMerk = mrkList.find { it.nama_merk == id_merk }
                    selectedMerk?.let {
                        onValueChange(insertUiEvent.copy(id_merk= it.id_merk)) }
                }
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

