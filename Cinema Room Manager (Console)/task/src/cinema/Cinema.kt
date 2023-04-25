package cinema


const val FREE_SEAT = 'S'
const val OCCUPIED_SEAT = 'B'

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRows = readln().toInt()
    var grid = MutableList(rows) { MutableList(seatsInRows) { FREE_SEAT } }
    println()
    printTheatre(grid)
    println("Enter a row number:")
    val row = readln().toInt()
    println("Enter a seat number in that row:")
    val seat = readln().toInt()

    println("Ticket price: $${getPrice(row, grid)}")
    println()
    grid = buySeat(row, seat, grid)
    printTheatre(grid)

}

fun printTheatre(grid: MutableList<MutableList<Char>>) {
    println("Cinema:")
    println("  ${grid[0].indices.joinToString(" ") { (it + 1).toString() }}")
    for (i in grid.indices) {
        println("${i + 1} ${grid[i].joinToString(" ")} ")
    }
    println()
}

fun buySeat(x: Int, y: Int, grid: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
    grid[x - 1][y - 1] = OCCUPIED_SEAT
    return grid
}

fun countTotalIncome(grid: MutableList<MutableList<Char>>): Int {
    val totalSeats = grid.size * grid[0].size
    return if (totalSeats < 60) {
        totalSeats * 10
    } else {
        val firstPart = grid.size / 2
        val secondPart = grid.size - firstPart
        (firstPart * 10 + secondPart * 8) * grid[0].size
    }
}

fun getPrice( x: Int, grid: MutableList<MutableList<Char>>): Int {
    val totalSeats = grid.size * grid[0].size
    return if (totalSeats < 60) {
        10
    } else {
        val firstPart = grid.size / 2
        if (x <= firstPart) {
            10
        } else 8
    }
}
