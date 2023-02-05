package com.ucne.proj_1erparcial_ap2.data.repository

import com.ucne.proj_1erparcial_ap2.data.local.dao.PrestamoDao
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity
import javax.inject.Inject

class OcupacionRepository @Inject constructor(
    private  val prestamoDao: PrestamoDao
) {
    suspend fun insert(prestamo: PrestamoEntity) {
        return prestamoDao.insert(prestamo)
    }


}