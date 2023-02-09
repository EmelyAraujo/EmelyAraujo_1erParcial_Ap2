package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoScreen(viewModel: PrestamoViewModel = hiltViewModel()) {

    Column(Modifier.fillMaxSize()) {
        PrestamoBody(viewModel)

        val uiState by viewModel.uiState.collectAsState()
        PrestamoListScreen(uiState.prestamosList)
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PrestamoBody(
    viewModel: PrestamoViewModel
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.deudor,
            onValueChange = { viewModel.deudor = it },
            label = { Text("Deudor") }
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.concepto,
            onValueChange = { viewModel.concepto = it },
            label = { Text("Concepto") }
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.monto,
            onValueChange = { viewModel.monto = it },
            label = { Text("Monto") }
        )

        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            text = { Text("Guardar") },
            icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "guardar") },
            onClick = { viewModel.insertar() }
        )
    }
}

@Composable
private fun PrestamoListScreen(prestamoList: List<PrestamoEntity>) {
    LazyColumn {
        items(prestamoList) { prestamo ->
            PrestamoRow(prestamo)
        }
    }
}

@Composable
private fun PrestamoRow(prestamo: PrestamoEntity) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier) {
                Text(
                    text = prestamo.deudor,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = prestamo.concepto,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(0.dp, 25.dp)
                )

            }

            Text(
                String.format("$ %.2f", prestamo.monto),
                textAlign = TextAlign.End,
                modifier = Modifier.weight(2f)
            )
        }
    }
}