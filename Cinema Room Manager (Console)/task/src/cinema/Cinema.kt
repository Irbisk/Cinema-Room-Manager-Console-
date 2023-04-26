package cinema


const val FREE_SEAT = 'S'
const val OCCUPIED_SEAT = 'B'
var currentIncome = 0
var grid = mutableListOf(mutableListOf<Char>())


fun main() {
    startGame()
}

fun startGame() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seatsInRows = readln().toInt()

    grid = MutableList(rows) { MutableList(seatsInRows) { FREE_SEAT } }

    while (true) {
        println("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit")
        val command = readln().toInt()
        when (command) {
            1 -> printTheatre()
            2 -> grid = buyATicket()
            3 -> statistics()
            else -> break
        }
    }
}

fun buyATicket(): MutableList<MutableList<Char>> {
    while (true) {
        println("Enter a row number:")
        val row = readln().toInt()
        println("Enter a seat number in that row:")
        val seat = readln().toInt()
        //just for training purposes via exception
        try {
            if (grid[row - 1][seat - 1] == FREE_SEAT) {
                grid[row - 1][seat - 1] = OCCUPIED_SEAT
                println("Ticket price: $${getPrice(row)}")
                println()
                currentIncome += getPrice(row)
                break
            } else println("That ticket has already been purchased!")
        } catch (e: IndexOutOfBoundsException) {
            println("Wrong input!")
        }

    }
    return grid
}
fun statistics() {
    val countTickets = grid.joinToString("").count { it == 'B' }
    val totalIncome = countTotalIncome()
    val percentage = countTickets.toDouble() / (grid.size.toDouble() * grid[0].size.toDouble()) * 100

    println("Number of purchased tickets: $countTickets")
    println("Percentage: ${"%.2f".format(percentage)}%")
    println("Current Income: $$currentIncome")
    println("Total Income: $$totalIncome")
    println()
}

fun printTheatre() {
    println("Cinema:")
    println("  ${grid[0].indices.joinToString(" ") { (it + 1).toString() }}")
    for (i in grid.indices) {
        println("${i + 1} ${grid[i].joinToString(" ")} ")
    }
    println()
}

fun countTotalIncome(): Int {
    val totalSeats = grid.size * grid[0].size
    return if (totalSeats < 60) {
        totalSeats * 10
    } else {
        val firstPart = grid.size / 2
        val secondPart = grid.size - firstPart
        (firstPart * 10 + secondPart * 8) * grid[0].size
    }
}

fun getPrice( x: Int): Int {
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
