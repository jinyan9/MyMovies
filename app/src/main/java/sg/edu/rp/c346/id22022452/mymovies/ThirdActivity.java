package sg.edu.rp.c346.id22022452.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {

    EditText title;
    EditText genre;
    EditText year;
    Spinner rating;
    Button update;
    Button cancel;
    Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main3);

        title = findViewById(R.id.editTextTitle);
        genre = findViewById(R.id.editTextGenre);
        year = findViewById(R.id.editTextYear);
        update = findViewById(R.id.buttonUpdate);
        cancel = findViewById(R.id.cancel_button);
        delete = findViewById(R.id.buttonDelete);
        rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ratingInp = parent.getItemAtPosition(position).toString();
                Toast.makeText(ThirdActivity.this, "Selected: " + ratingInp, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        Intent i = getIntent();
        Movie movie = (Movie) i.getSerializableExtra("movie");
        title.setText(movie.getTitle());
        genre.setText(movie.getGenre());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                movie.setTitle(title.getText().toString());
                movie.setGenre(genre.getText().toString());
                movie.setYear(Integer.parseInt(year.getText().toString()));
                rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String ratingInp = parent.getItemAtPosition(position).toString();
                        Toast.makeText(ThirdActivity.this, "Selected: " + ratingInp, Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Do nothing
                    }
                });
                db.updateMovie(movie);
                db.close();
                Intent i = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(ThirdActivity.this);
                db.deleteMovie(movie.getId());
                db.close();
                Intent i = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ThirdActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });

    }
}