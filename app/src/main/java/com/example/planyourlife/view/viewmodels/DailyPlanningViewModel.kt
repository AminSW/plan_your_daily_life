package com.example.planyourlife.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planyourlife.data.entity.Day
import com.example.planyourlife.data.repository.DailyPlanningRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyPlanningViewModel @Inject constructor(var repository: DailyPlanningRepository) : ViewModel() {
    private val _days = MutableLiveData<HashMap<String, Day>>()
    val days: LiveData<HashMap<String, Day>> = _days


    init {
        getDays()
    }

    fun getDays() {
        viewModelScope.launch {
            _days.value = repository.createDays()
        }
    }
}