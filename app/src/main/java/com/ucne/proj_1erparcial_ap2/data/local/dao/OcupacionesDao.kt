package com.ucne.proj_1erparcial_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ucne.proj_1erparcial_ap2.data.local.entity.OcupacionesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OcupacionesDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamoEntity: OcupacionesEntity)

    @Query(
        """
        SELECT * 
        FROM Ocupaciones
    """
    )
    fun getList(): Flow<List<OcupacionesEntity>>
}