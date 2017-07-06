package com.kazakago.cleanarchitecture.domain.mapper

/**
 * Created by tamura_k on 2017/04/27.
 */
interface ReversibleEntityMapper<T, R> : EntityMapper<T, R> {

    fun reverse(destination: R): T

}
