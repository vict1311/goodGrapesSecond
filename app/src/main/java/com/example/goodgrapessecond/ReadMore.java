package com.example.goodgrapessecond;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ReadMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        // loop through all wines and if the wines ID is equal to the ID of currentWine then set currentWine
        // to be equal to tempWine. We do this because we don't want a new object that is identical
        // but instead want direct access to the wine in wineList
        //for (int i = 0; i < MainActivity.wineList.size(); i++) {
        //   Wine tempWine = MainActivity.wineList.get(i);
        //    if (tempWine.wineID.equals(MainActivity.currentWine.wineID)) {
        //         MainActivity.currentWine = tempWine;
        //     }
        // }
        //Wine.findWine(MainActivity.currentWine.wineID);
        // set the wineToShow to be the one stored in currentWine
        Wine wineToShow = MainActivity.currentWine;
        // retrieve traceabilityInformation and productInformation objects
        TraceabilityInformation newTrace = wineToShow.traceabilityRetrieval();
        ProductInformation newProduct = wineToShow.productRetrieval();

        super.onCreate(savedInstanceState);
        System.out.print(wineToShow.wineLinked[0]);
        System.out.println(wineToShow.wineLinked[1]);

        setContentView(R.layout.activity_read_more);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.favorites:
                        startActivity(new Intent(getApplicationContext(),Favorites.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),SearchStart.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(ReadMore.this,Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        // we define this String because it is easier to add the same variable than to write the right thing multiple times
        String impact = " kg CO2-equivalent";


        TextView textViewToChangeName = (TextView) findViewById(R.id.nameVAR);
        textViewToChangeName.setText(
                "" + wineToShow.name);


        // set textview for the traceability information and set text to be the traceability information categories
        TextView traceView = (TextView) findViewById(R.id.textTrace);
        traceView.setText("Agriculture: " + newTrace.agricultureImpact + impact + "\r\n \r\nILUC: " + newTrace.ILUCImpact + impact +
                "\r\n \r\nProcessing: " + newTrace.packagingImpact + impact + "\r\n \r\nPackaging: " + newTrace.packagingImpact + impact + "\r\n \r\nTransport: " +
                newTrace.transportImpact + impact + "\r\n \r\nRetail: " + newTrace.retailImpact + impact);
        traceView.setTextColor(getResources().getColor(R.color.black));

        // set textview for the product information
        TextView productView = (TextView) findViewById(R.id.textProduct);
        productView.setText("Region: " + newProduct.region
        + "\r\n \r\nSub-region: " + newProduct.subRegion + "\r\n \r\nProducer name: " + newProduct.producerName);
        productView.setTextColor(getResources().getColor(R.color.black));

        // button to add and remove
        Button button = (Button) findViewById(R.id.addRemove);

        if (wineToShow.libSaved == true) {
            button.setText("Remove from favorites");
        }
        else {
            button.setText("Add to favorites");
        }

        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if (wineToShow.libSaved == false) {
                                              //MainActivity.currentLibrary.removeWineFromLibrary(wineToShow);
                                              MainActivity.currentLibrary.addWineToLibrary(wineToShow);
                                              System.out.println(MainActivity.currentLibrary.winesInLib);
                                              System.out.println(wineToShow.libSaved);
                                              button.setText("Remove from favorites");
                                              // we start a new intent
                                              //Intent i = new Intent(wineDisplay.this, MainActivity.class);
                                              //startActivity(i);
                                          }
                                          else {
                                              MainActivity.currentLibrary.removeWineFromLibrary(wineToShow);
                                              System.out.println(MainActivity.currentLibrary.winesInLib);
                                              System.out.println(wineToShow.libSaved);
                                              button.setText("Add to favorites");
                                          }
                                      }
                                  }
        );


        //System.out.println(MainActivity.wineList.get(0).libSaved);
        //System.out.println(wineToShow.libSaved);
        // HERE IT IS INTERESTING bcos wineToShow is not saved, but the 0th wine, which should be wineToShow, is true!
        //for (int i = 0; i < MainActivity.wineList.size(); i++) {
        //  Wine tempWine = MainActivity.wineList.get(i);
        //  if (tempWine.wineID.equals(wineToShow.wineID)) {
        //     if (tempWine.wineID.equals(wineToShow.wineID)) {
        //             tempWine.libSaved = true;
        //          System.out.println(tempWine.libSaved);
        //      }
        //  }
        //   }

        // right now libSaved for wineToShow is always false????? the above will print false even if the wine is added to lib in MainActivity

        // when you print the contents of winesInLib for current Library you will always get the same thing
        // it will print (in this case) [0, 1, 0] every time you remove wine with ID 0 from the UserLibrary
        // which makes it seem like it has global access to the library, but the update does NOT happen globally



    }
}