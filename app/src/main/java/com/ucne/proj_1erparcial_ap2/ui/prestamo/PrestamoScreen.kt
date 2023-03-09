package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.twotone.Error
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.proj_1erparcial_ap2.data.local.entity.OcupacionesEntity
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoScreen(viewModel: PrestamoViewModel = hiltViewModel()) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        val uiState by viewModel.uiState.collectAsState()
        PrestamoBody(viewModel,uiState.ocupacionesList)


        PrestamoListScreen(uiState.prestamosList)
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun PrestamoBody(
    viewModel: PrestamoViewModel, ocupacionesList:List<OcupacionesEntity>
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = viewModel.deudor,
            onValueChange = viewModel::onDeudorChanged,
            label = { Text("Deudor") },
            isError = viewModel.deudorError.isNotBlank(),
            trailingIcon = {
                if (viewModel.deudorError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (viewModel.deudorError.isNotBlank()) {
            Text(
                text = viewModel.deudorError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = viewModel.concepto,
            onValueChange = { viewModel.concepto = it },
            label = { Text("Concepto") }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = viewModel.monto,
            onValueChange = viewModel::onMontoChanged,
            label = { Text("Monto") },
            isError = viewModel.montoError.isNotBlank(),
            trailingIcon = {
                if (viewModel.montoError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        if (viewModel.montoError.isNotBlank()) {
            Text(
                text = viewModel.montoError,
                color = MaterialTheme.colorScheme.error
            )
        }


        MyDropdownMenu(ocupacionesList)

        Spacer(modifier = Modifier.padding(8.dp))
        ExtendedFloatingActionButton(
            modifier = Modifier
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

//poner icono en rojo.✔️
//poner borde rojo ✔️
//poner mensaje de validacion ✔️
//poner teclado numerico.

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun MyPreview() {
    var deudor by remember {
        mutableStateOf("")
    }
    val deudorError by remember {
        mutableStateOf("")
    }
    var monto by remember {
        mutableStateOf("")
    }
    val montoError by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = deudor,
            onValueChange = { deudor = it },
            label = { Text("Deudor") },
            isError = deudorError.isNotBlank(),
            trailingIcon = {
                if (deudorError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            }
        )
        if (deudorError.isNotBlank()) {
            Text(
                text = deudorError,
                color = MaterialTheme.colorScheme.error
            )
        }

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = monto,
            onValueChange = { monto = it },
            label = { Text("Monto") },
            isError = deudorError.isNotBlank(),
            trailingIcon = {
                if (deudorError.isNotBlank()) {
                    Icon(imageVector = Icons.TwoTone.Error, contentDescription = "error")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            )
        )
        if (deudorError.isNotBlank()) {
            Text(
                text = montoError,
                color = MaterialTheme.colorScheme.error
            )
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenu(items: List<OcupacionesEntity> ) {
    var expandio by remember { mutableStateOf(false) }

    var selectedText by remember { mutableStateOf("") }

    OutlinedTextField(
        label = { Text("Seleccionar ocupacion") },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null
            )
        },
        value = selectedText,
        onValueChange = { selectedText = it },
        readOnly = true, enabled = true,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                expandio = true
            }

    )
    DropdownMenu(
        expanded = expandio,
        onDismissRequest = { expandio = false },
        modifier = Modifier.fillMaxWidth()
    ) {
        items.forEach { item ->
            DropdownMenuItem(
                text = {
                    Text(text = item.nombre)
                },
                onClick = {
                    expandio = false
                    selectedText = item.nombre
                }
            )
        }
    }
}
