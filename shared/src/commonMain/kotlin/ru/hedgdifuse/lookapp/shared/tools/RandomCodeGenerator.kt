package ru.hedgdifuse.lookapp.shared.tools

import kotlin.random.Random

/**
 * [RandomCodeGenerator] - generates 6 digit random int
 */
object RandomCodeGenerator {

    fun generate() = Random.nextInt(100000, 999999)
}