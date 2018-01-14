package com.example.sabrina.projet;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultatRecherche extends AppCompatActivity {
    ListView lv;

    ArrayAdapter<String> lesPhrases;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultat_recherche);
        lv = (ListView)findViewById(R.id.liste);
        Intent intent = getIntent();
        ArrayList<String> list = (ArrayList<String>)intent.getSerializableExtra("liste");
        lesPhrases=new ArrayAdapter<String>(this,R.layout.list_item);
        for (String message : list) {
            lesPhrases.add(message);
        }

        lv.setAdapter(lesPhrases);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                show(lesPhrases.getItem(position));
            }
        });

    }
    public void show(String s){
        String chaine="http://",nom="",type="",jour="",heure="",etoile="",adresse="",
                numero="",avis="",codePos="",ville="",hygiene="",nourriture="",service="",employe="";
        Base base = new Base(getApplicationContext());
        String URl = "Informations";
        Uri uri = Uri.parse(MyContentProvider.CONTENT_URI.toString() + "/" + URl);
        String []column = new String []{base.NOM,base.SITE_WEB,base.HEURE_OUVERTURE,base.JOUR_OUVERTURE,base.TYPE_CUISINE,
                                        base.NOMBRE_ETOILES,base.ADRESSE,base.NUMERO_TEL,base.AVIS,base.CODE_POSTAL,base.VILLE,
                                        base.ETOILES_EMPLOYES,base.NOURRITUE,base.HYGIENE,base.SERVICE};
        Cursor cur = getContentResolver().query(uri, column, base.NOM + " = ? ", new String[]{s}, null);//recherche par nom
        if(cur!=null && cur.getCount()>0){
            if(cur.moveToFirst()){
                do{
                    chaine = chaine+cur.getString(cur.getColumnIndex(base.SITE_WEB));
                    nom=nom+cur.getString(cur.getColumnIndex(base.NOM));
                    heure=heure+cur.getString(cur.getColumnIndex(base.HEURE_OUVERTURE));
                    jour=jour+cur.getString(cur.getColumnIndex(base.JOUR_OUVERTURE));
                    type=type+cur.getString(cur.getColumnIndex(base.TYPE_CUISINE));
                    etoile=etoile+cur.getString(cur.getColumnIndex(base.NOMBRE_ETOILES));
                    adresse=adresse+cur.getString(cur.getColumnIndex(base.ADRESSE));
                    numero=numero+cur.getString(cur.getColumnIndex(base.NUMERO_TEL));
                    codePos=codePos+cur.getString(cur.getColumnIndex(base.CODE_POSTAL));
                    ville=ville+cur.getString(cur.getColumnIndex(base.VILLE));
                    service=service+cur.getString(cur.getColumnIndex(base.SERVICE));
                    employe=employe+cur.getString(cur.getColumnIndex(base.ETOILES_EMPLOYES));
                    nourriture=nourriture+cur.getString(cur.getColumnIndex(base.NOURRITUE));
                    hygiene=hygiene+cur.getString(cur.getColumnIndex(base.HYGIENE));

                }while(cur.moveToNext());
            }}else
            Toast.makeText(this, " vide", Toast.LENGTH_LONG).show();



        //Intent iii = new Intent(Intent.ACTION_VIEW);
        Intent iii=new Intent(this,InfoRestaurant.class);
        iii.putExtra("site",chaine);
        iii.putExtra("nom",nom);
        iii.putExtra("heure",heure);
        iii.putExtra("jour",jour);
        iii.putExtra("type",type);
        iii.putExtra("etoile",etoile);
        iii.putExtra("adresse",adresse);
        iii.putExtra("numero",numero);
        iii.putExtra("avis",avis);
        iii.putExtra("code",codePos);
        iii.putExtra("ville",ville);
        iii.putExtra("service",service);
        iii.putExtra("hygiene",hygiene);
        iii.putExtra("employe",employe);
        iii.putExtra("nourriture",nourriture);
        //iii.setData(Uri.parse(chaine));
        startActivity(iii);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resultat_recherche, menu);
        return true;
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
