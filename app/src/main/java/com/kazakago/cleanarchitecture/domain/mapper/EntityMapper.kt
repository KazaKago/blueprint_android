package com.kazakago.cleanarchitecture.domain.mapper

/**
 * Created by tamura_k on 2017/04/27.
 */
abstract class EntityMapper<T, R> {

    abstract fun map(source: T): R

    abstract fun reverse(destination: R): T

    fun map(sources: List<T>): List<R> {
        return sources.map { map(it) }
    }

    fun reverse(destinations: List<R>): List<T> {
        return destinations.map { reverse(it) }
    }

}
