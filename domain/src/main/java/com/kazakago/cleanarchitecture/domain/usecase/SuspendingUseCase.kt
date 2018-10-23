package com.kazakago.cleanarchitecture.domain.usecase

interface SuspendingUseCase<in E, out T> {

    suspend fun execute(input: E): T

}