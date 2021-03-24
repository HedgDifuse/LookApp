package ru.hedgdifuse.lookapp.shared.usecase

class UseCaseResult<T>(
    val result: T,
    val error: Exception?
)