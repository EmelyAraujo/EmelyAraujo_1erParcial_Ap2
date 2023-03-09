package com.ucne.proj_1erparcial_ap2.data.repository

import com.ucne.proj_1erparcial_ap2.data.local.dao.OcupacionesDao
import com.ucne.proj_1erparcial_ap2.data.local.entity.OcupacionesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OcupacionesRepository @Inject constructor(
    private  val ocupacionesDao: OcupacionesDao,

    ) {
    suspend fun insert(ocupacion: OcupacionesEntity) {
        return ocupacionesDao.insert(ocupacion)
    }
    fun getList(): Flow<List<OcupacionesEntity>> = ocupacionesDao.getList()

}