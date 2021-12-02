fun main() {
    fun part1(input: List<List<String>>): Int {
        var (x, y) = listOf(0, 0)
        input.forEach {
            when (it[0]) {
                "forward" -> x += it[1].toInt()
                "down" -> y += it[1].toInt()
                "up" -> y -= it[1].toInt()
            }
        }
        return x * y
    }

    fun part2(input: List<List<String>>): Int {
        var (x, y, aim) = listOf(0, 0, 0)
        input.forEach {
            when (it[0]) {
                "forward" -> {
                    x += it[1].toInt();
                    y += it[1].toInt() * aim
                }
                "down" -> aim += it[1].toInt()
                "up" -> aim -= it[1].toInt()
            }
        }
        return x * y
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test").map { it.split(" ") }
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    //https://adventofcode.com/2021/day/2/input
    val input = readInput("Day02").map { it.split(" ") }
    println(part1(input))
    println(part2(input))
}
