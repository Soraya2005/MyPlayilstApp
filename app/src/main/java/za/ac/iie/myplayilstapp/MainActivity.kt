package za.ac.iie.myplayilstapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/*
 * Q.1.1: Clear and concise source code, good programming practices, and meaningful comments.
 * This activity handles the display of playlist details.
 */
class DetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val btnDisplayList: Button = findViewById(R.id.btnDisplayList)
        val btnCalculateAverage: Button = findViewById(R.id.btnCalculateAverage)
        val btnReturn: Button = findViewById(R.id.btnReturn)
        val tvDetails: TextView = findViewById(R.id.tvDetails)

        // Button to display all songs in the playlist
        btnDisplayList.setOnClickListener {
            displayPlaylist(tvDetails)
        }

        // Button to calculate and show the average rating
        btnCalculateAverage.setOnClickListener {
            calculateAndDisplayAverage(tvDetails)
        }

        // Button to go back to the MainActivity
        btnReturn.setOnClickListener {
            finish() // Closes this activity and returns to the previous one
        }
    }

    // Q.1.3: A button that when clicked displays the list of songs with the corresponding details using a loop.
    @SuppressLint("SetTextI18n")
    private fun displayPlaylist(textView: TextView) {
        if (PlaylistData.songTitles.isEmpty()) {
            textView.text = "The playlist is currently empty."
            return
        }

        val detailsBuilder = StringBuilder()
        // Loop through the arrays to build the display string
        for (i in PlaylistData.songTitles.indices) {
            detailsBuilder.append("Song: ${PlaylistData.songTitles[i]}\n")
            detailsBuilder.append("Artist: ${PlaylistData.artistNames[i]}\n")
            detailsBuilder.append("Rating: ${PlaylistData.ratings[i]}/5\n")
            detailsBuilder.append("Comments: ${PlaylistData.comments[i]}\n\n")
        }
        textView.text = detailsBuilder.toString()
    }

    // Q.1.3: A button that when clicked calculates and displays the average rating for the songs in the playlist.
    @SuppressLint("SetTextI18n")
    private fun calculateAndDisplayAverage(textView: TextView) {
        if (PlaylistData.ratings.isEmpty()) {
            textView.text = "Cannot calculate average. The playlist is empty."
            return
        }

        var totalRating = 0
        for (rating in PlaylistData.ratings) {
            totalRating += rating
        }

        val average = totalRating.toDouble() / PlaylistData.ratings.size
        textView.text = "Average Rating: %.2f/5".format(average)
    }
}
