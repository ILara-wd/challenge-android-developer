package mx.openpay.challenge.ui.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mx.openpay.challenge.tools.DataState
import mx.openpay.challenge.data.model.entity.Place
import mx.openpay.challenge.domain.PlaceUseCase
import mx.openpay.challenge.domain.SavePlaceUseCase
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    val placeUseCase: PlaceUseCase,
    val savePlaceUseCase: SavePlaceUseCase,
) : ViewModel() {

    private val _getPlaceState: MutableLiveData<DataState<List<Place>>> = MutableLiveData()
    val getPlaceState: LiveData<DataState<List<Place>>>
        get() = _getPlaceState

    private val _savePlaceState: MutableLiveData<DataState<Boolean>> = MutableLiveData()
    val savePlaceState: LiveData<DataState<Boolean>>
        get() = _savePlaceState

    fun getAllPlace() {
        viewModelScope.launch {
            placeUseCase()
                .onEach { dataState ->
                    _getPlaceState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

    fun savePlace(place: Place){
        viewModelScope.launch {
            savePlaceUseCase(place)
                .onEach { dataState ->
                    _savePlaceState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

}