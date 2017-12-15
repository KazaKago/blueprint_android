package com.kazakago.cleanarchitecture.domain.mapper

interface ReverseEntityMapper<out T, in R> {

    fun reverse(destination: R): T

}
