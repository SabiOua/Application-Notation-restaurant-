package com.example.sabrina.projet;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ajouter extends AppCompatActivity {
    EditText nom,adr,sitW,Tel,HOuvert,JOuvert,nbrEtoiles,type,codePostal,ville,noteH,noteS,noteEm,noteN;
    Button valider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);
        nom = (EditText)findViewById(R.id.nom);
        adr = (EditText)findViewById(R.id.adresse);
        Tel = (EditText)findViewById(R.id.numTel);
        sitW = (EditText)findViewById(R.id.sw);
        JOuvert = (EditText)findViewById(R.id.JOu);
        HOuvert = (EditText)findViewById(R.id.HOu);
        type = (EditText)findViewById(R.id.TC);
        nbrEtoiles = (EditText)findViewById(R.id.etoile);
        codePostal = (EditText)findViewById(R.id.cp);
        ville = (EditText)findViewById(R.id.ville);
        noteH = (EditText)findViewById(R.id.hygiene);
        noteS = (EditText)findViewById(R.id.service);
        noteEm = (EditText)findViewById(R.id.employe);
        noteN = (EditText)findViewById(R.id.nourriture);
        valider= (Button)findViewById(R.id.bouton);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ajouter, menu);
        return true;
    }
    public void add(View v){
        ContentValues val = new ContentValues();
        if(nom.getText().toString().length()<1 | adr.getText().toString().length()<1 |
                codePostal.getText().toString().length()<1 | ville.getText().toString().length()<1)
            Toast.makeText(getBaseContext()," Veuillez saisir les champs obligatoires (*)", Toast.LENGTH_LONG).show();
        else{
        val.put(Base.NOM, nom.getText().toString());
        val.put(Base.ADRESSE, adr.getText().toString());
        val.put(Base.NUMERO_TEL, Tel.getText().toString());
        val.put(Base.SITE_WEB, sitW.getText().toString());
        val.put(Base.HEURE_OUVERTURE, HOuvert.getText().toString());
        val.put(Base.JOUR_OUVERTURE, JOuvert.getText().toString());
        val.put(Base.NOMBRE_ETOILES, nbrEtoiles.getText().toString());
        val.put(Base.TYPE_CUISINE, type.getText().toString());
        val.put(Base.CODE_POSTAL, codePostal.getText().toString());
        val.put(Base.VILLE, ville.getText().toString());
        val.put(Base.ETOILES_EMPLOYES, noteEm.getText().toString());
        val.put(Base.SERVICE, noteS.getText().toString());
        val.put(Base.HYGIENE, noteH.getText().toString());
        val.put(Base.NOURRITUE, noteN.getText().toString());
        Uri uri = getContentResolver().insert(MyContentProvider.CONTENT_URI, val);
        if(uri !=null) {
            Toast.makeText(getBaseContext(),
                    uri.toString() + " inserted!", Toast.LENGTH_LONG).show();


        }else {
            Toast annonce = Toast.makeText(this, "pas insere", Toast.LENGTH_LONG);
            annonce.show();
        }
        }
        Intent iii=new Intent(this,MainActivity.class);
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
