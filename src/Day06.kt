fun main() {

    data class Fish(var timer: Int) {
        fun passDay(): Fish? {
            if (timer == 0) {
                timer = 6
                return Fish(8)
            }
            timer--
            return null
        }
    }

    //Don't try the second solution with this...
    //Leaving this also for the sake of having it
    fun part1(testInput: MutableList<Int>, numberOfDays: Int): Int {
        val cupHolder = testInput.map { Fish(it) }.toMutableList()
        repeat(numberOfDays) {
            cupHolder.addAll(cupHolder.mapNotNull { it.passDay() })
        }
        return cupHolder.size
    }

    fun part2(testInput: MutableList<Int>, numberOfDays: Int): Long {
        var total = testInput.groupingBy { it }.eachCount().mapValues { it.value }.toMutableMap()
        var newMap = mutableMapOf<Int, Int>()
        repeat(numberOfDays) {
            for ((key, value) in total) {
                if (key == 0) {
                    newMap.merge(6, value) { a, b -> a + b }
                    newMap.putIfAbsent(8, value)
                } else {
                    newMap.merge(key - 1, value) { a, b -> a + b }
                }
            }
            total = newMap
            newMap = mutableMapOf()
        }
        return total.values.fold(0L) { a, b -> a + b }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test").map { it.split(",") }.flatten().map { it.toInt() }.toMutableList()
    check(part1(testInput, 18) == 26)
    check(part1(testInput, 80) == 5934)
    check(part2(testInput, 18) == 26L)
    check(part2(testInput, 80) == 5934L)

    //https://adventofcode.com/2021/day/6/input
    val input = readInput("Day06").map { it.split(",") }.flatten().map { it.toInt() }.toMutableList()
    println(part1(input, 80))
    println(part2(input, 80))
    println(part2(input, 256))
}
