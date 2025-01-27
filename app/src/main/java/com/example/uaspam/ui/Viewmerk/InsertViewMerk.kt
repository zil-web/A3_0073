package com.example.uaspam.ui.Viewmerk

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
import com.example.uaspam.ui.Navigation.DestinasiEntryMerk
import com.example.uaspam.ui.ViewModel.PenyediaViewModel
import com.example.uaspam.ui.ViewmodelMerk.InsertUiEventMerk
import com.example.uaspam.ui.ViewmodelMerk.InsertUiStateMerk
import com.example.uaspam.ui.ViewmodelMerk.InsertViewModelMerk
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntrymrkScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertViewModelMerk = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomTopAppBar(
                title = DestinasiEntryMerk.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    )
    { innerPadding ->
        EntryBodyMerk(
            InsertUiStateMerk = viewModel.uiStateMerk,
            onValueChange = viewModel::updateInsertmrkState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertmrk()
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
fun EntryBodyMerk(
    InsertUiStateMerk: InsertUiStateMerk,
    onValueChange: (InsertUiEventMerk) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(2.dp),
        modifier = modifier.padding(3.dp)
    )  {
        FormInputMerk(
            InsertUiEventMerk = InsertUiStateMerk.insertUiEventMerk,
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
fun FormInputMerk(
    InsertUiEventMerk: InsertUiEventMerk,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEventMerk) -> Unit = {},
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
                value = InsertUiEventMerk.nama_merk,
                onValueChange = { onValueChange(InsertUiEventMerk.copy(nama_merk = it)) },
                label = { Text(text = " nama_produk") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enabled,
                singleLine = true
            )
            OutlinedTextField(
                value = InsertUiEventMerk.deskripsi_merk,
                onValueChange = { onValueChange(InsertUiEventMerk.copy(deskripsi_merk = it)) },
                label = { Text(text = "alamat_merk") },
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

