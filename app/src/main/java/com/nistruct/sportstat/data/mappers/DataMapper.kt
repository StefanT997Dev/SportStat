package com.nistruct.sportstat.data.mappers

interface DataMapper<I, O> {
    fun map(input: I): O
}