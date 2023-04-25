package cinema

fun main() {
    val grid = MutableList(7) { MutableList(8) { 'S' } }
    printTheatre(grid)
}

fun printTheatre(grid: MutableList<MutableList<Char>>) {
    println("Cinema:")
    println("  1 2 3 4 5 6 7 8")
    for (i in grid.indices) {
        println("${i + 1} ${grid[i].joinToString(" ")} ")
    }
}
