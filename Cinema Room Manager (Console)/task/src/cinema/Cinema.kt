package cinema


const val FREE_SEAT = 'S'
const val OCCUPIED_SEAT = 'B'

fun main() {
    startGame()
}

fun startGame() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRows = readln().toInt()

    var grid = MutableList(rows) { MutableList(seatsInRows) { FREE_SEAT } }

    while (true) {
        println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "0. Exit")
        val command = readln().toInt()
        when (command) {
            1 -> printTheatre(grid)
            2 -> grid = buyATicket(grid)
            else -> break
        }
    }
}

fun buyATicket(grid: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
    while (true) {
        println("Enter a row number:")
        val row = readln().toInt()
        println("Enter a seat number in that row:")
        val seat = readln().toInt()
        if (grid[row - 1][seat - 1] == 'S') {
            grid[row - 1][seat - 1] = 'B'
            println("Ticket price: $${getPrice(row, grid)}")
            break
        } else println("Seat is occupied")
    }
    return grid
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
