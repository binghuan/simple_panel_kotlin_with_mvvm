package com.bh.android.mysample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bh.android.mysample.ui.main.vo.Route

class RoutingFragmentViewModel : ViewModel() {

    var routeList: MutableLiveData<List<Route>> = MutableLiveData<List<Route>>()
    var isExpanded: MutableLiveData<Boolean> = MutableLiveData()

    fun createRoutingList() {
        var dataList = ArrayList<Route>()
        dataList.add(Route(1, "南京東路2段"))
        dataList.add(Route(2, "濱江路3段"))

        routeList.value = dataList
    }

    fun deleteStop(route: Route) {
        var dataList = ArrayList<Route>()
        dataList.addAll(routeList.value as ArrayList<Route>)
        dataList.remove(route)
        routeList.value = dataList
    }

    fun addNewStop() {
        var itemCount: Int = routeList.value?.size ?: 0

        if (itemCount < 3) {
            var dataList = ArrayList<Route>()
            routeList.value?.first()?.let { dataList.add(it) }
            dataList.add(Route(3, "台北101"))
            routeList.value?.last()?.let { dataList.add(it) }
            routeList.value = dataList
        } else if (itemCount < 4) {
            var dataList = ArrayList<Route>()
            routeList.value?.first()?.let { dataList.add(it) }
            routeList.value?.get(1)?.let { dataList.add(it) }
            dataList.add(Route(3, "三重湯城"))
            routeList.value?.last()?.let { dataList.add(it) }
            routeList.value = dataList
        }
    }

    fun openRoutingPanel() {
        isExpanded.value = true
    }

    fun closeRoutingPanel() {
        isExpanded.value = false
    }

}
