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
import com.example.uaspam.ui.Navigation.DestinasiEntryPemasok
import com.example.uaspam.ui.ViewModel.InsertUiEventPemasok
import com.example.uaspam.ui.ViewModel.InsertUiStatePemasok
import com.example.uaspam.ui.ViewModel.InsertViewModelPemasok
import com.example.uaspam.ui.ViewModel.PenyediaViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntrypmskScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModelPemasok = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiEntryPemasok.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    )
    { innerPadding ->
        EntryBodyPemasok(
            InsertUiStatePemasok = viewModel.uiStatePemasok,
            onValueChange = viewModel::updateInsertpmskState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertpmsk()
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
fun EntryBodyPemasok(
    InsertUiStatePemasok: InsertUiStatePemasok,
    onValueChange: (InsertUiEventPemasok) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier.padding(3.dp)
    )  {
        FormInputPemasok(
            InsertUiEventPemasok = InsertUiStatePemasok.insertUiEventPemasok,
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
fun FormInputPemasok(
    InsertUiEventPemasok: InsertUiEventPemasok,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEventPemasok) -> Unit = {},
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
                value = InsertUiEventPemasok.nama_produk,
                onValueChange = { onValueChange(InsertUiEventPemasok.copy(nama_produk = it))},
                label = { Text(text = "nama_produk") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = InsertUiEventPemasok.nama_pemasok,
                onValueChange = { onValueChange(InsertUiEventPemasok.copy(nama_pemasok = it)) },
                label = { Text(text = " nama_pemasok") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = InsertUiEventPemasok.alamat_pemasok,
                onValueChange = { onValueChange(InsertUiEventPemasok.copy(alamat_pemasok = it)) },
                label = { Text(text = " alamat_pemasok") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = InsertUiEventPemasok.telepon_pemasok,
                onValueChange = { onValueChange(InsertUiEventPemasok.copy(telepon_pemasok = it)) },
                label = { Text(text = "telepon_pemasok") },
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

