package com.ucne.proj_1erparcial_ap2.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.proj_1erparcial_ap2.data.local.dao.OcupacionesDao
import com.ucne.proj_1erparcial_ap2.data.local.dao.PrestamoDao
import com.ucne.proj_1erparcial_ap2.data.local.entity.OcupacionesEntity
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity

@Database(
    entities = [
        PrestamoEntity::class, OcupacionesEntity::class
    ],
    version = 3
)
abstract  class PrestamoDb: RoomDatabase(){
    abstract val prestamoDao: PrestamoDao
    abstract val ocupacionesDao:OcupacionesDao

}