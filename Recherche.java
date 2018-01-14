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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Recherche extends AppCompatActivity {
    EditText nom;
    EditText heure;
    EditText type;
    EditText adr;
    EditText jour;
    EditText codeP;

    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
        nom = (EditText) findViewById(R.id.nom);
        heure = (EditText) findViewById(R.id.heure);
        type = (EditText) findViewById(R.id.type);
        adr = (EditText) findViewById(R.id.adr);
        jour = (EditText) findViewById(R.id.jour);
        codeP = (EditText) findViewById(R.id.Cp);
        b = (Button) findViewById(R.id.valider);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recherche, menu);
        return true;
    }

    //rechercher par critéres
    //les criteres:
    //
    public void rech(View v) {
        ArrayList<String> List = new ArrayList<String>();
        Base base = new Base(getApplicationContext());
        String URl = "Rechercher";
        Cursor cur = null;
        Uri uri = Uri.parse(MyContentProvider.CONTENT_URI.toString() + "/" + URl);
        if (nom.getText().toString().length()>0 ) {//rechercher par nom
            String[] column = new String[]{base.NOM};
            cur = getContentResolver().query(uri, column,
                    base.NOM + " = UPPER('"+ nom.getText().toString()+"')", null, null);
        }
        if (jour.getText().toString().length()>0) {//rechercher par jour
            String[] column = new String[]{base.NOM};
            cur = getContentResolver().query(uri, column, base.JOUR_OUVERTURE +
                    " LIKE '%"+jour.getText().toString()+"%'",null, null);
        }
        if (heure.getText().toString().length()>0) {//rechercher par heure
            String[] column = new String[]{base.NOM};
            cur = getContentResolver().query(uri, column, base.HEURE_OUVERTURE +
                    " LIKE '%"+heure.getText().toString()+"%'",null, null);
        }
        if (type.getText().toString().length()>0) {//rechercher par type
            String[] column = new String[]{base.NOM};
            cur = getContentResolver().query(uri, column, base.TYPE_CUISINE + " = ?  ", new String[]{type.getText().toString()}, null);
        }
        if (adr.getText().toString().length()>0) {//rechercher par adresse
            String[] column = new String[]{base.NOM};
            cur = getContentResolver().query(uri, column, base.ADRESSE +
                    " LIKE '%"+adr.getText().toString()+"%'",null, null);
        }
        if (jour.getText().toString().length()>0 && heure.getText().toString().length()>0) {//recherche par jour et heure
            String[] column = new String[]{base.NOM};
            cur = getContentResolver().query(uri, column,
                    base.JOUR_OUVERTURE + " LIKE '%"+jour.getText().toString()+"%'"+" AND "+
                            base.HEURE_OUVERTURE + " LIKE '%"+heure.getText().toString()+"%'", null, null);
        }
        if (jour.getText().toString().length()>0 && heure.getText().toString().length()>0 && type.getText().toString().length()>0) {
            String[] column = new String[]{base.NOM};//recherche par jour heure type
            cur = getContentResolver().query(uri, column,
                    base.JOUR_OUVERTURE + " LIKE '%"+jour.getText().toString()+"%'"+" AND "+
                            base.HEURE_OUVERTURE + " LIKE '%"+heure.getText().toString()+"%'"+" AND "+
                            base.TYPE_CUISINE + " = '"+type.getText().toString()+"'", null, null);
        }
        if (heure.getText().toString().length()>0 && type.getText().toString().length()>0) {
            String[] column = new String[]{base.NOM};//recherche par heure et type
            cur = getContentResolver().query(uri, column,
                            base.HEURE_OUVERTURE + " LIKE '%"+heure.getText().toString()+"%'"+" AND "+
                            base.TYPE_CUISINE + " = '"+type.getText().toString()+"'", null, null);
        }
        if (jour.getText().toString().length()>0 && type.getText().toString().length()>0) {
            String[] column = new String[]{base.NOM};//recherche par jour type
            cur = getContentResolver().query(uri, column,
                    base.JOUR_OUVERTURE + " LIKE '%"+jour.getText().toString()+"%'"+" AND "+
                            base.TYPE_CUISINE + " = '"+type.getText().toString()+"'", null, null);
        }
        if (codeP.getText().toString().length()>0) {//recherche par jour et heure
            String[] column = new String[]{base.NOM};
            cur = getContentResolver().query(uri, column,base.CODE_POSTAL + " LIKE '"+codeP.getText().toString()+"%'"+" AND ",null,null);
        }
        if (codeP.getText().toString().length()>0 && heure.getText().toString().length()>0) {//code postal et heure
            String[] column = new String[]{base.NOM};
            cur = getContentResolver().query(uri, column,
                    base.CODE_POSTAL + " LIKE '"+codeP.getText().toString()+"%'"+" AND "+
                            base.HEURE_OUVERTURE + " LIKE '%"+heure.getText().toString()+"%'", null, null);
        }
        if (jour.getText().toString().length()>0 && codeP.getText().toString().length()>0) {//recherche par jour et codePostal
            String[] column = new String[]{base.NOM};
            cur = getContentResolver().query(uri, column,
                    base.JOUR_OUVERTURE + " LIKE '%"+jour.getText().toString()+"%'"+" AND "+
                            base.CODE_POSTAL + " LIKE '"+heure.getText().toString()+"%'", null, null);
        }
        if (codeP.getText().toString().length()>0 && type.getText().toString().length()>0) {
            String[] column = new String[]{base.NOM};//recherche par jour type et code postal
            cur = getContentResolver().query(uri, column,
                    base.CODE_POSTAL+ " LIKE '"+jour.getText().toString()+"%'"+" AND "+
                            base.TYPE_CUISINE + " = '"+type.getText().toString()+"'", null, null);
        }
        if (jour.getText().toString().length()>0 && codeP.getText().toString().length()>0 && heure.getText().toString().length()>0) {//recherche par jour et codePostal
            String[] column = new String[]{base.NOM};//jour heure code postal
            cur = getContentResolver().query(uri, column,
                    base.JOUR_OUVERTURE + " LIKE '%"+jour.getText().toString()+"%'"+" AND "+
                            base.CODE_POSTAL + " LIKE '"+heure.getText().toString()+"%'"+" AND "+
                            base.HEURE_OUVERTURE + " LIKE '%"+heure.getText().toString()+"%'", null, null);
        }
        if (codeP.getText().toString().length()>0 && type.getText().toString().length()>0 && heure.getText().toString().length()>0) {
            String[] column = new String[]{base.NOM};//recherche par jour type et code postal et heure
            cur = getContentResolver().query(uri, column,
                    base.CODE_POSTAL+ " LIKE '"+jour.getText().toString()+"%'"+" AND "+
                            base.TYPE_CUISINE + " = '"+type.getText().toString()+"'"+" AND "+
                            base.HEURE_OUVERTURE + " LIKE '%"+heure.getText().toString()+"%'", null, null);
        }
        if (jour.getText().toString().length()>0 && type.getText().toString().length()>0 && heure.getText().toString().length()>0) {
            String[] column = new String[]{base.NOM};//recherche par jour type heure jour
            cur = getContentResolver().query(uri, column,
                    base.JOUR_OUVERTURE + " LIKE '%"+jour.getText().toString()+"%'"+" AND "+
                            base.TYPE_CUISINE + " = '"+type.getText().toString()+"'"+" AND "+
                            base.HEURE_OUVERTURE + " LIKE '%"+heure.getText().toString()+"%'", null, null);
        }


        if (cur != null && cur.getCount() > 0) {
            if (cur.moveToFirst()) {
                String name = null;
                do {
                    name = cur.getString(cur.getColumnIndex(base.NOM));
                    List.add(name);
                } while (cur.moveToNext());
            }
            Intent iii = new Intent(this, ResultatRecherche.class);
            iii.putExtra("liste", List);
            startActivity(iii);
        } else {
            Toast.makeText(this, " vide", Toast.LENGTH_LONG).show();
            this.finish();
        }


    }

    public void rechercher(View v) {

        switch (v.getId()) {
            case R.id.nomClick:
                nom.setVisibility(EditText.VISIBLE);
                break;
            case R.id.heureClick:
                heure.setVisibility(EditText.VISIBLE);
                break;
            case R.id.jourClick:
                jour.setVisibility(EditText.VISIBLE);
                break;
            case R.id.typeClick:
                type.setVisibility(EditText.VISIBLE);
                break;
            case R.id.adresseClick:
                adr.setVisibility(EditText.VISIBLE);
                break;

        }

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
