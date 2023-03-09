package com.ucne.proj_1erparcial_ap2.ui.prestamo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.proj_1erparcial_ap2.data.local.entity.OcupacionesEntity
import com.ucne.proj_1erparcial_ap2.data.local.entity.PrestamoEntity
import com.ucne.proj_1erparcial_ap2.data.repository.OcupacionesRepository
import com.ucne.proj_1erparcial_ap2.data.repository.PrestamoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PrestamoUiState(
    val prestamosList: List<PrestamoEntity> = emptyList(),
    val ocupacionesList: List<OcupacionesEntity> = emptyList()
)

@HiltViewModel
class PrestamoViewModel @Inject constructor(
    private val prestamoRepository: PrestamoRepository,
    private val ocupacionesRepository: OcupacionesRepository
) : ViewModel() {

    var deudor by mutableStateOf("")
    var deudorError by mutableStateOf("")

    var concepto by mutableStateOf("")

    var monto by mutableStateOf("")
    var montoError by mutableStateOf("")

    var uiState = MutableStateFlow(PrestamoUiState())
        private set

    init {
        getList()
    }

    fun getList() {
        viewModelScope.launch(Dispatchers.IO) {
            prestamoRepository.getList().collect { lista ->
                uiState.update {
                    it.copy(prestamosList = lista)
                }
            }

            ocupacionesRepository.insert(OcupacionesEntity(nombre = "Ingeniero"))
            ocupacionesRepository.insert(OcupacionesEntity(nombre = "Abogado"))

            uiState.update {
                it.copy(ocupacionesList = ocupacionesRepository.getList())
            }
        }

    }

    fun onDeudorChanged(deudor: String) {
        this.deudor = deudor
        HayErrores()
    }

    fun onMontoChanged(monto: String) {
        this.monto = monto
        HayErrores()
    }

    fun insertar() {

        if (HayErrores())
            return

        val prestamo = PrestamoEntity(
            deudor = deudor,
            concepto = concepto,
            monto = monto.toDoubleOrNull() ?: 0.0
        )

        viewModelScope.launch(Dispatchers.IO) {
            prestamoRepository.insert(prestamo)
            Limpiar()
        }
    }

    private fun HayErrores(): Boolean {
        var hayError = false
        deudorError = ""
        if (deudor.isBlank()) {
            deudorError = "Debe indicar el deudor"
            hayError = true
        }

        montoError = ""
        if ((monto.toDoubleOrNull() ?: 0.0) <= 0.0) {
            montoError = "Debe indicar un monto mayor que cero"
            hayError = true
        }
        return hayError
    }

    private fun Limpiar() {
        deudor = ""
        concepto = ""
        monto = ""
    }

}