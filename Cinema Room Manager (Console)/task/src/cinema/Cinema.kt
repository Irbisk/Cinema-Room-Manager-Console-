package cinema


const val FREE_SEAT = 'S'

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRows = readln().toInt()
    val grid = MutableList(rows) { MutableList(seatsInRows) { FREE_SEAT } }

    println("Total income:")
    println("$${countTotalIncome(grid)}")

}

fun printTheatre(grid: MutableList<MutableList<Char>>) {
    println("Cinema:")
    println("  1 2 3 4 5 6 7 8")
    for (i in grid.indices) {
        println("${i + 1} ${grid[i].joinToString(" ")} ")
    }
}

fun countTotalIncome(grid: MutableList<MutableList<Char>>): Int {
    var totalIncome = 0
    val totalSeats = grid.size * grid[0].size
    totalIncome = if (totalSeats < 60) {
        totalSeats * 10
    } else {
        val firstPart = grid.size / 2
        val secondPart = grid.size - firstPart
        (firstPart * 10 + secondPart * 8) * grid[0].size
    }
    return totalIncome
}
