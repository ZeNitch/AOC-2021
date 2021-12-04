fun main() {
    data class BoardCell(val number: Int, var visited: Boolean = false)

    fun checkRow(it: List<BoardCell>): Boolean {
        return it.stream().allMatch { cell -> cell.visited }
    }

    fun checkColumn(board: List<List<BoardCell>>): Boolean {
        for (col in board.first().indices) {
            var checkedCol = true
            for (row in board.indices) {
                checkedCol = checkedCol && board[row][col].visited

            }
            if (checkedCol) {
                return true
            }

        }
        return false
    }

    fun checkBoard(board: List<List<BoardCell>>, number: Int): Boolean {
        board.forEach {
            it.forEach { cell ->
                if (cell.number == number) {
                    cell.visited = true
                }
            }
        }
        board.forEach {
            if (checkRow(it)) return true
        }
        return checkColumn(board)

    }

    fun calculateUnmarked(board: List<List<BoardCell>>): Int {
        return board.flatten().filter { !it.visited }.sumOf { it.number }
    }

    fun cleanInput(testInput: List<String>): Pair<List<Int>, MutableList<List<List<BoardCell>>>> {
        return Pair(testInput[0].split(',').map { it.toInt() }, testInput.asSequence().drop(1).chunked(6).map { it.filter { line -> line.isNotBlank() } }.map {
            it.map { line ->
                line.split(" ").filter { cell -> cell.isNotBlank() }.map { cell -> cell.toInt() }
            }
        }.map { it.map { row -> row.map { cell -> BoardCell(cell) } } }.toMutableList()
        )
    }

    fun part1(cleanInput: Pair<List<Int>, MutableList<List<List<BoardCell>>>>, first: Boolean = true): Int {
        var boards = cleanInput.second
        var cupHolder = emptyList<List<List<BoardCell>>>().toMutableList()
        for (number in cleanInput.first) {
            for (board in boards) {
                if (checkBoard(board, number)) {
                    if (first) {
                        return calculateUnmarked(board) * number
                    } else if (boards.size == 1) {
                        return calculateUnmarked(board) * number
                    }
                    cupHolder.add(board)
                }
            }
            boards.removeAll(cupHolder)
        }
        return -1
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    part1(cleanInput(testInput))
    check(part1(cleanInput(testInput)) == 4512)
    check(part1(cleanInput(testInput), false) == 1924)

    //https://adventofcode.com/2021/day/4/input
    val input = readInput("Day04")
    println(part1(cleanInput(input)))
    println(part1(cleanInput(input), false))
}
