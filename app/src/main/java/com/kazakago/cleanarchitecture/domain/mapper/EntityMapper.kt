package com.kazakago.cleanarchitecture.domain.mapper

interface EntityMapper<in T, out R> {

    fun map(source: T): R

}
