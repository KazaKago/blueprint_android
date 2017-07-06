package com.kazakago.cleanarchitecture.domain.usecase

/**
 * Created by tamura_k on 2017/04/27.
 */
interface UseCase<in E, out T> {

    fun execute(input: E): T

}