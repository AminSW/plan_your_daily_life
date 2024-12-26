package com.example.planyourlife.view.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.planyourlife.data.entity.Day
import com.example.planyourlife.data.repository.DailyPlanningRepository
import kotlinx.coroutines.launch

//class DailyPlanningViewModel: ViewModel() {
//    val days = MutableLiveData<HashMap<String, Day>> ()
//
//    private val repository = CalendarRepository()
//
//    fun getDays() {
//        CoroutineScope(Dispatchers.Main).launch {
//            days.value = repository.createDays()
//        }
//    }
//}

class DailyPlanningViewModel : ViewModel() {
    private val _days = MutableLiveData<HashMap<String, Day>>()
    val days: LiveData<HashMap<String, Day>> = _days

    private val repository = DailyPlanningRepository()

    init {
        getDays()
    }

    fun getDays() {
        viewModelScope.launch {
            _days.value = repository.createDays()
        }
    }
}