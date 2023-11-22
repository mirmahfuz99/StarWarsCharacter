package com.starwarscharacter.app.core

interface BaseUseCase<In, Out> {
    suspend fun execute(input: In): Out
}