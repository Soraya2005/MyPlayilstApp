package za.ac.iie.myplayilstapp

// A simple object to hold our shared data (the parallel arrays)
object PlaylistData {
    // Q.1.1: Declare and initialise four parallel arrays
    // We use ArrayLists because they are more flexible for adding new items.
    val songTitles = ArrayList<String>()
    val artistNames = ArrayList<String>()
    val ratings = ArrayList<Int>()
    val comments = ArrayList<String>()
}