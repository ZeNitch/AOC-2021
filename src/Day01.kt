fun main() {
    fun part1(input: List<Int>): Int {
        return input.zipWithNext().count { (first, second) -> first < second }
    }

    fun part2(input: List<Int>): Int {
        return part1(input.windowed(3).map { it.sum() })
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").map { it.toInt() }
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    //https://adventofcode.com/2021/day/1/input
    val input = readInput("Day01").map { it.toInt() }
    println(part1(input))
    println(part2(input))
}
