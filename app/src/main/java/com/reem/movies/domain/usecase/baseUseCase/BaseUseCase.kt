package com.reem.movies.domain.usecase.baseUseCase
abstract class BaseUseCase<in Params, out Type> {
    abstract suspend fun execute(params: Params): Type
}