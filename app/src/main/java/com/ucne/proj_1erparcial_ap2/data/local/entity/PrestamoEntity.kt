package com.ucne.proj_1erparcial_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Prestamos")
data class PrestamoEntity(
    @PrimaryKey(autoGenerate = true)
    val prestamoId: Int? = null,
    val deudor: String,
    val concepto: String,
    val nota: String = "",
    val monto: Double
)

