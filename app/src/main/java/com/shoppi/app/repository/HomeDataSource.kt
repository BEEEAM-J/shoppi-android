package com.shoppi.app.repository

import com.shoppi.app.ui.common.HomeData

interface HomeDataSource {

    fun getHomeData(): HomeData?
}