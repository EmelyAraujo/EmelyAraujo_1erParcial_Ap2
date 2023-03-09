package com.ucne.proj_1erparcial_ap2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Ocupaciones")
data class OcupacionesEntity(
    @PrimaryKey(autoGenerate = true)
    val ocupacionId: Int? = null,
    val nombre :String
)