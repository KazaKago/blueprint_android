package com.kazakago.cleanarchitecture.domain.usecase

interface UseCase<in E, out T> {

    fun execute(input: E): T

}