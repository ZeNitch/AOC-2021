import kotlin.math.abs

fun main() {

    fun part1(testInput: List<Int>): Int {
        val min = testInput.minOrNull()!!
        val max = testInput.maxOrNull()!!
        var least = Int.MAX_VALUE
        (min..max).forEach { least = minOf(testInput.sumOf { num -> abs(it - num) }, least) }
        return least
    }

    fun part2(testInput: List<Int>): Int {
        val min = testInput.minOrNull()!!
        val max = testInput.maxOrNull()!!
        var least = Int.MAX_VALUE
        (min..max).forEach { least = minOf(testInput.sumOf { num -> val x = abs(it - num); x * (x + 1) / 2 }, least) }
        return least
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test").map { it.split(",") }.flatten().map { it.toInt() }
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    //https://adventofcode.com/2021/day/7/input
    val input = readInput("Day07").map { it.split(",") }.flatten().map { it.toInt() }
    println(part1(input))
    println(part2(input))
}

