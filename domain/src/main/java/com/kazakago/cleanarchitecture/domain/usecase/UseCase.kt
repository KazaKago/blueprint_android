package com.kazakago.cleanarchitecture.domain.usecase

interface UseCase<in E, out T> {

    operator fun invoke(input: E): T

}