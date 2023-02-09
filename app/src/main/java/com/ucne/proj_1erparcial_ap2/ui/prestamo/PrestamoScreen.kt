package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrestamoScreen(){
    Column{
        OutlinedTextField( label = {}, value = "", onValueChange ={} )
        Spacer(modifier =Modifier.padding(8.dp))

        OutlinedTextField(label= {}, value ="" , onValueChange ={} )
        Spacer(modifier = Modifier.padding(8.dp))

        OutlinedTextField(label = {},value ="" , onValueChange ={} )
        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Guardar")
        }
    }

}