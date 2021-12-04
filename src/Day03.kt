
fun main() {
    fun part1(input: List<String>): Int {
        var gammaString = input.first().indices.map { column -> input.groupingBy { it[column] }.eachCount() }.map { it -> it.maxByOrNull { it.value }!!.key }.joinToString("")
        var epsilonString = gammaString.map { if (it == '0') '1' else '0' }.joinToString("")
        return gammaString.toInt(2) * epsilonString.toInt(2)
    }

    fun part2(input: List<String>): Int {
        var minInput = input
        var maxInput  = input
        for (column in minInput.first().indices) {
            if(minInput.size>1) {
                minInput = minInput.filter { line -> line[column] == minInput.groupingBy { it[column] }.eachCount().minWithOrNull(compareBy({ it.value }, { it.key }))!!.key }
            }
            if(maxInput.size>1) {
                maxInput = maxInput.filter { line -> line[column] == maxInput.groupingBy { it[column] }.eachCount().maxWithOrNull(compareBy({ it.value }, { it.key }))!!.key }
            }
            if (minInput.size == 1 && maxInput.size==1) {
                break
            }
        }
        return minInput.joinToString("").toInt(2)*maxInput.joinToString("").toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    //https://adventofcode.com/2021/day/3/input
    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
