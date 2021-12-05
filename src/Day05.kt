fun main() {


    data class Point(val x: Int, val y: Int)

    data class Line(val start: Point, val end: Point) {
        fun order(): Line {
            if (start.x < end.x) return this
            if (start.x == end.x && start.y < end.y) return this
            return Line(end, start)
        }
    }

    fun part1(input: List<Line>, diagonal: Boolean = false): Int {
        val allPoints = mutableListOf<String>()
        for (line in input) {
            if (line.start.x == line.end.x) {
                for (coord in line.start.y..line.end.y) {
                    allPoints.add("${line.start.x}.$coord")
                }
            } else if (line.start.y == line.end.y) {
                for (coord in line.start.x..line.end.x) {
                    allPoints.add("$coord.${line.start.y}")
                }
            } else if (diagonal) {
                val dir = if (line.end.y - line.start.y > 1) 1 else -1
                for (i in 0..line.end.x - line.start.x) {
                    allPoints.add("${line.start.x + i}.${line.start.y + i * dir}")
                }
            }
        }
        return allPoints.groupingBy { it }.eachCount().filter { it.value > 1 }.count()
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test").map { it.split(" -> ", ",") }.map { Line(Point(it[0].toInt(), it[1].toInt()), Point(it[2].toInt(), it[3].toInt())) }.map { it.order() }
    check(part1(testInput) == 5)
    check(part1(testInput, true) == 12)

    //https://adventofcode.com/2021/day/5/input
    val input = readInput("Day05").map { it.split(" -> ", ",") }.map { Line(Point(it[0].toInt(), it[1].toInt()), Point(it[2].toInt(), it[3].toInt())) }.map { it.order() }
    println(part1(input))
    println(part1(input, true))
}
