package css.cis3334.fishtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonAdd, buttonDetails, buttonDelete;            // two button widgets
    ListView listViewFish;                      // listview to display all the fish in the database
    FishDataSource fishDataSource;              // provides interaction to the SQLite fish table
    ArrayAdapter<Fish> fishAdapter;
    List<Fish> fishList;
    int positionSelected;
    Fish fishSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fishDataSource = new FishDataSource(this);          // set up the fish data source
        fishDataSource.open();                              // open up this data source--close before we leave

        // Set up the listVeiw to display all the fish using a custam adapter
        listViewFish = (ListView) findViewById(R.id.ListViewFish);
        fishList = fishDataSource.getAllFish();              // Get the list of fish from the database
        // Instantiate a custom adapter for displaying each fish
        fishAdapter = new FishAdapter(this, android.R.layout.simple_list_item_single_choice, fishList);
        // Apply the adapter to the list
        listViewFish.setAdapter(fishAdapter);
        listViewFish.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapter, View parent,
                                    int position, long id) {
                positionSelected = position;
                Log.d("MAIN", "Fish selected at position " + positionSelected);
            }
        });

        // Set up the button to add a new fish using a seperate activity
        buttonAdd = (Button) findViewById(R.id.buttonAddFish);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Start up the add fish activity with an intent
                Intent detailActIntent = new Intent(view.getContext(), AddFishActivity.class);
                finish();
                startActivity(detailActIntent);
            }
        });
        // Set up the button to display details on one fish using a seperate activity
        buttonDetails = (Button) findViewById(R.id.buttonDetails);
        buttonDetails.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.d("MAIN", "onClick for Details");
                Intent detailActIntent = new Intent(view.getContext(), DetailActivity.class);
                detailActIntent.putExtra("Fish", fishList.get(positionSelected));
                finish();
                startActivity(detailActIntent);
            }
        });
        // Set up the button to display details on one fish using a seperate activity
        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MAIN", "onClick for Delete");
                Log.d("MAIN", "Delete at position " + positionSelected);
                fishDataSource.deleteFish(fishList.get(positionSelected));
                fishAdapter.remove( fishList.get(positionSelected) );
                fishAdapter.notifyDataSetChanged();
            }
        });

    }


    @Override
    protected void onResume() {
        // re-open the database after a resume
        fishDataSource.open();
        Log.d("MAIN", "onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        // close the database when the app goes into the background incase it doesn't come back
        fishDataSource.close();
        Log.d("MAIN", "onPause");
        super.onPause();
    }
}
