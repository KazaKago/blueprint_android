package com.kazakago.cleanarchitecture.domain.mapper

/**
 * Created by tamura_k on 2017/04/27.
 */
interface EntityMapper<in T, out R> {

    fun map(source: T): R

}
