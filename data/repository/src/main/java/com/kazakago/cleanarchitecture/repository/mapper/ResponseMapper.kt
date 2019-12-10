package com.kazakago.cleanarchitecture.repository.mapper

interface ResponseMapper<in T, out R> {

    fun map(source: T): R

    fun map(sources: List<T>): List<R> {
        return sources.map { map(it) }
    }

}
