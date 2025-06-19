package za.ac.iie.myplayilstapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

/*
 * STUDENT NUMBER: [ST10493837]
 * FULL NAME: [Soraya PECHERA]
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Q.1.1: Create methods/functions required.
        setupButtons()
    }

    private fun setupButtons() {
        val btnAddSong: Button = findViewById(R.id.btnAddSong)
        val btnViewDetails: Button = findViewById(R.id.btnViewDetails)
        val btnExit: Button = findViewById(R.id.btnExit)

        // Listener for the "Add to Playlist" button
        btnAddSong.setOnClickListener {
            showAddSongDialog()
        }

        // Listener for the "View Playlist Details" button
        btnViewDetails.setOnClickListener {
            // Navigate to the second screen (DetailedActivity)
            val intent = Intent(this, DetailedActivity::class.java)
            startActivity(intent)
        }

        // Listener for the "Exit App" button
        btnExit.setOnClickListener {
            finishAffinity() // Closes the app completely
        }
    }

    // Q.1.2: When the user clicks the button, the user must be asked to enter details.
    private fun showAddSongDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add a New Song")

        // Create the layout for the dialog
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(40, 40, 40, 40)

        // Create EditText fields for user input
        val titleInput = EditText(this).apply { hint = "Song Title" }
        val artistInput = EditText(this).apply { hint = "Artist's Name" }
        val ratingInput = EditText(this).apply { hint = "Rating (1-5)"; inputType = android.text.InputType.TYPE_CLASS_NUMBER }
        val commentsInput = EditText(this).apply { hint = "Comments" }

        layout.addView(titleInput)
        layout.addView(artistInput)
        layout.addView(ratingInput)
        layout.addView(commentsInput)

        builder.setView(layout)

        // Set up the buttons for the dialog
        builder.setPositiveButton("Add") { dialog, _ ->
            val title = titleInput.text.toString()
            val artist = artistInput.text.toString()
            val ratingStr = ratingInput.text.toString()
            val comments = commentsInput.text.toString()

            // Q.1.1: Error handling.
            if (title.isBlank() || artist.isBlank() || ratingStr.isBlank()) {
                Toast.makeText(this, "Title, Artist, and Rating cannot be empty.", Toast.LENGTH_LONG).show()
            } else {
                try {
                    val rating = ratingStr.toInt()
                    if (rating in 1..5) {
                        // Add data to our parallel ArrayLists in PlaylistData
                        PlaylistData.songTitles.add(title)
                        PlaylistData.artistNames.add(artist)
                        PlaylistData.ratings.add(rating)
                        PlaylistData.comments.add(comments)
                        Toast.makeText(this, "Song added successfully!", Toast.LENGTH_SHORT).show()
                        dialog.dismiss()
                    } else {
                        Toast.makeText(this, "Rating must be between 1 and 5.", Toast.LENGTH_LONG).show()
                    }
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Please enter a valid number for the rating.", Toast.LENGTH_LONG).show()
                }
            }
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}