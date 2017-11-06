package com.kazakago.cleanarchitecture.domain.mapper

interface ReversibleEntityMapper<T, R> : EntityMapper<T, R> {

    fun reverse(destination: R): T

}
