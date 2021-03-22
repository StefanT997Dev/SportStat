package com.nistruct.sportstat.repository.sport

import com.nistruct.sportstat.api.RetrofitInstance
import com.nistruct.sportstat.data.mappers.DataMapper
import com.nistruct.sportstat.data.mappers.PlayerResponseToPlayerMapper
import com.nistruct.sportstat.data.mappers.SportResponseToSportMapper
import com.nistruct.sportstat.data.models.api_models.PlayerResponse
import com.nistruct.sportstat.data.models.api_models.SportResponse
import com.nistruct.sportstat.data.models.ui_models.Player
import com.nistruct.sportstat.data.models.ui_models.Sport
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SportRepositoryImpl:SportRepository {
    private val sportResponseToSportMapper: DataMapper<SportResponse, Sport> =
        SportResponseToSportMapper()

    override suspend fun getSports() = flow {
        emit(RetrofitInstance.sportApi.getSports())
    }
        .map { sportResponseList ->
            sportResponseList.map { sportResponse ->
                sportResponseToSportMapper.map(sportResponse)
            }
        }
}