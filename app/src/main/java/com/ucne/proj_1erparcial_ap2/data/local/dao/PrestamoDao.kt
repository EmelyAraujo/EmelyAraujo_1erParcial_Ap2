package com.ucne.proj_1erparcial_ap2.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity
import kotlinx.coroutines.flow.Flow

interface PrestamoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(prestamoEntity: PrestamoEntity)

    @Query(
        """
        SELECT * 
        FROM Prestamos
        WHERE prestamoId=:PrestamoId
        LIMIT 1
    """
    )
    fun getList(): Flow<List<PrestamoEntity>>

}

class dao {
    fun save(): Boolean {
        return true
    }
}
