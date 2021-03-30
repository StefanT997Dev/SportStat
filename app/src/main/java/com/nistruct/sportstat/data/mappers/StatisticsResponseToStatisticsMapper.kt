package com.nistruct.sportstat.data.mappers

import com.nistruct.sportstat.data.models.api_models.StatisticsResponse
import com.nistruct.sportstat.data.models.ui_models.Statistics

class StatisticsResponseToStatisticsMapper:DataMapper<StatisticsResponse,Statistics> {
    override fun map(input: StatisticsResponse): Statistics {
        return Statistics(input.id,input.name,input.value)
    }
}