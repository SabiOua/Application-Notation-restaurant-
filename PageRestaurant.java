package com.example.sabrina.projet;

import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class PageRestaurant extends AppCompatActivity {

    Button rech,ajout,supp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_restaurant);
        rech = (Button) findViewById(R.id.recherche);
        ajout = (Button) findViewById(R.id.ajouter);
        supp = (Button) findViewById(R.id.supprimer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_page_restaurant, menu);
        return true;
    }
    public void recherche(View v){
        Intent iii=new Intent(this,Recherche.class);
        startActivity(iii);

    }
    public void lister(View v){
        ArrayList<String> List = new ArrayList<String>();
        Base base = new Base(getApplicationContext());
        String URl = "Liste";
        Uri uri = Uri.parse(MyContentProvider.CONTENT_URI.toString() + "/" + URl);
        String []column = new String []{base.NOM};
        Cursor cur = getContentResolver().query(uri,column,null,null,null);//recherche par nom
        if(cur!=null && cur.getCount()>0){
            if(cur.moveToFirst()){
                String name=null;
                do{
                    name=cur.getString(cur.getColumnIndex(base.NOM));
                    List.add(name);

                }while(cur.moveToNext());
            }}else
            Toast.makeText(this, " vide", Toast.LENGTH_LONG).show();




        Intent iii=new Intent(this,ResultatRecherche.class);
        iii.putExtra("liste",List);
        startActivity(iii);

    }
    public void ajout(View v){
        Intent iii=new Intent(this,Ajouter.class);
        startActivity(iii);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
