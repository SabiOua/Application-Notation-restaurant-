package com.example.sabrina.projet;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InfoRestaurant extends AppCompatActivity {
    TextView sit,nom,heure,jour,type,note,adrr,avs2;
    String site="",no="",hr="",jr="",tp="",etoile="",numero="",adresse="",avis="",codePos="",ville="",
                hyg="",serv="",empl="",nour="";
    Button add,noter,don,detail;
    LinearLayout ly,layout;
    EditText nomRes,jH,hH,ty,sw,ne,adr,num,nouvel;
    List<Address> adresses;
    LocationManager lm;
    Geocoder geo;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_restaurant);
        Toolbar toolbar =   (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        don = (Button)findViewById(R.id.done);
        sit = (TextView)findViewById(R.id.site);
        nom = (TextView)findViewById(R.id.nom);
        adrr = (TextView)findViewById(R.id.adr2);
        heure = (TextView)findViewById(R.id.heure);
        jour = (TextView)findViewById(R.id.jour);
        type = (TextView)findViewById(R.id.type);
        avs2 = (TextView)findViewById(R.id.avs);
        ly = (LinearLayout)findViewById(R.id.layout);
        layout = (LinearLayout)findViewById(R.id.lay);
        nomRes = (EditText)findViewById(R.id.nomRes);
        jH = (EditText)findViewById(R.id.JOu);
        hH = (EditText)findViewById(R.id.HOu);
        adr = (EditText)findViewById(R.id.adr);
        nouvel = (EditText)findViewById(R.id.NouvAvis);
        ty = (EditText)findViewById(R.id.TC);
        sw= (EditText)findViewById(R.id.sw);
        ne= (EditText)findViewById(R.id.etoile);
        num= (EditText)findViewById(R.id.numTel);
        add = (Button)findViewById(R.id.bouton);
        note = (TextView)findViewById(R.id.note);
        noter = (Button)findViewById(R.id.noter);
        detail = (Button)findViewById(R.id.detail);
        geo = new Geocoder(this, Locale.FRENCH);
        lm=(LocationManager) getSystemService(LOCATION_SERVICE);
        adresses= new ArrayList<Address>();
        iv =(ImageView) findViewById(R.id.imageToc);

        Intent ii=getIntent();
        site =ii.getStringExtra("site");
        no =ii.getStringExtra("nom");
        hr =ii.getStringExtra("heure");
        jr =ii.getStringExtra("jour");
        tp =ii.getStringExtra("type");
        avis=ii.getStringExtra("avis");
        empl=ii.getStringExtra("employe");
        nour=ii.getStringExtra("nourriture");
        hyg=ii.getStringExtra("hygiene");
        serv=ii.getStringExtra("service");
        etoile=ii.getStringExtra("etoile");
        adresse=ii.getStringExtra("adresse");
        numero=ii.getStringExtra("numero");
        codePos=ii.getStringExtra("code");
        ville=ii.getStringExtra("ville");
        nom.setText(no);
        sit.setText("Site Web : "+site);
        heure.setText("Heures d'ouvertures : "+hr);
        jour.setText("Jours d'ouvertures : "+jr);
        type.setText("Type de cuisine : "+tp);
        adrr.setText("Adresse :"+adresse+","+codePos+","+ville);
        avs2.setText(avis);
        adrr.setTextColor(Color.parseColor("#008000"));
        sit.setTextColor(Color.parseColor("#008000"));
        double moyenne=(Integer.parseInt(etoile)+Integer.parseInt(serv)+Integer.parseInt(hyg)+Integer.parseInt(nour)+Integer.parseInt(empl))/5;
        Log.v("note",Integer.toString(Integer.parseInt(serv)));
        Log.v("note",Integer.toString(Integer.parseInt(hyg)));

        if(moyenne==0)
            note.setText("Vous n'avez pas encore donner de notes à ce restaurant");
        else {
            note.setText("Le nombre de tocs : " + etoile + " /5");
            if(moyenne==(5)) {
                Uri uri = Uri.parse("android.resource://com.example.sabrina.projet/drawable/toc5");
                iv.setImageURI(uri);
            }else {
                if (moyenne == (4)) {
                    Uri uri = Uri.parse("android.resource://com.example.sabrina.projet/drawable/toc4");
                    iv.setImageURI(uri);
                }else {
                    if (moyenne == (3)) {
                        Uri uri = Uri.parse("android.resource://com.example.sabrina.projet/drawable/toc3");
                        iv.setImageURI(uri);
                    }else {
                        if (moyenne == (2)) {
                            Uri uri = Uri.parse("android.resource://com.example.sabrina.projet/drawable/toc2");
                            iv.setImageURI(uri);
                        }
                        else {
                            if (moyenne == (1)) {
                                Uri uri = Uri.parse("android.resource://com.example.sabrina.projet/drawable/toc");
                                iv.setImageURI(uri);
                            }
                        }
                    }
                }
            }
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info_restaurant, menu);
        //Intent iii = new Intent(Intent.ACTION_VIEW);

        return true;
    }
    public void siteWeb(View v){
        Intent iii = new Intent(Intent.ACTION_VIEW);
        iii.setData(Uri.parse(site));
        startActivity(iii);
    }
    public void suppression(){

        Base base = new Base(getApplicationContext());
        String URl = "Suppression";
        Uri uri = Uri.parse(MyContentProvider.CONTENT_URI.toString() + "/" + URl);
        int k=getContentResolver().delete(uri, base.NOM + " = ? ", new String[]{no});
        if(k>0) {
            Toast.makeText(this, "supprimé", Toast.LENGTH_LONG).show();
            this.finish();
        }else
            Toast.makeText(this, "non supprimé", Toast.LENGTH_LONG).show();
    }
    public void modifier(){
        ly.setVisibility(LinearLayout.VISIBLE);
    }
    public void add(View v){
        Base base = new Base(getApplicationContext());
        String URl = "Ajout";
        ContentValues val = new ContentValues();

        Uri uri = Uri.parse(MyContentProvider.CONTENT_URI.toString() + "/" + URl);

        if(nomRes.getText().toString().length()>0) {
            val.put(Base.NOM, nomRes.getText().toString());
            getContentResolver().update(uri, val, base.NOM + " = ? ", new String[]{no});
            Toast.makeText(this, "modifié", Toast.LENGTH_LONG).show();
            this.finish();
        }
        if(ne.getText().toString().length()>0) {

            val.put(Base.NOMBRE_ETOILES, ne.getText().toString());
            getContentResolver().update(uri, val, base.NOM + " = ? ", new String[]{no});
            Toast.makeText(this, "modifié", Toast.LENGTH_LONG).show();
            this.finish();
        }
        if(adr!=null) {
            val.put(Base.ADRESSE, adr.getText().toString());
            getContentResolver().update(uri, val, base.NOM + " = ? ", new String[]{no});
            Toast.makeText(this, "modifié", Toast.LENGTH_LONG).show();
            this.finish();
        }
        if(jH.getText().toString().length()>0) {
            val.put(Base.JOUR_OUVERTURE, jH.getText().toString());
            getContentResolver().update(uri, val, base.NOM + " = ? ", new String[]{no});
            Toast.makeText(this, "modifié", Toast.LENGTH_LONG).show();
            this.finish();
        }
        if(hH.getText().toString().length()>0) {
            val.put(Base.HEURE_OUVERTURE, hH.getText().toString());
            getContentResolver().update(uri, val, base.NOM + " = ? ", new String[]{no});
            Toast.makeText(this, "modifié", Toast.LENGTH_LONG).show();
            this.finish();
        }if(ty.getText().toString().length()>0) {
            val.put(Base.TYPE_CUISINE, ty.getText().toString());
            getContentResolver().update(uri, val, base.NOM + " = ? ", new String[]{no});
            Toast.makeText(this, "modifié", Toast.LENGTH_LONG).show();
            this.finish();
        }
        if(sw.getText().toString().length()>0) {
            val.put(Base.SITE_WEB, sw.getText().toString());
            getContentResolver().update(uri, val, base.NOM + " = ? ", new String[]{no});
            Toast.makeText(this, "modifié", Toast.LENGTH_LONG).show();
            this.finish();
        }



    }
    public void ajoutAvis(View v){
        Base base = new Base(getApplicationContext());
        String URl = "Ajout";
        ContentValues val = new ContentValues();

        Uri uri = Uri.parse(MyContentProvider.CONTENT_URI.toString() + "/" + URl);
        if(nouvel.getText().toString().length()>0) {
            String res="- "+avis+"\n"+nouvel.getText().toString();
            val.put(Base.AVIS, res);
            getContentResolver().update(uri, val, base.NOM + " = ? ", new String[]{no});
            Toast.makeText(this, "ajouté", Toast.LENGTH_LONG).show();
            this.finish();
        }

    }
    public void noter(View v){
        if(v==detail){
            CharSequence notes[] = new CharSequence[] {"Note service :  "+serv,"Note hygiene :  "+hyg,
                                                        "Note employes :  "+empl, "Note nourriture :  "+nour,"Note globale : "+etoile};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Detail Des Notes ");
            builder.setItems(notes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            builder.show();
        }

       else{ CharSequence notes[] = new CharSequence[] {"0", "1", "2", "3","4","5"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choisis une Note ");
        builder.setItems(notes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Base base = new Base(getApplicationContext());
                String URl = "Ajout";
                ContentValues val = new ContentValues();
                Uri uri = Uri.parse(MyContentProvider.CONTENT_URI.toString() + "/" + URl);
                Log.v("here", Integer.toString(which));
                val.put(Base.NOMBRE_ETOILES, Integer.toString(which));
                getContentResolver().update(uri, val, base.NOM + " = ? ", new String[]{no});
            }
        });

        builder.show();}
    }

    public void Adresse(View v){
        try {
            Toast.makeText(this, adresse, Toast.LENGTH_LONG).show();
            adresses=geo.getFromLocationName(adresse, 1);

        }catch(Exception e){
            Toast annonce = Toast.makeText(this, "erreur saisie coordonées", Toast.LENGTH_LONG);
            annonce.show();

        }
        Log.v("addresse", Integer.toString(adresses.size()));
        if(adresses.size()>0) {
            Address address = adresses.get(0);
            double lat = address.getLatitude();
            double lng = address.getLongitude();

            Uri uri = Uri.parse("geo:"+lat+","+lng+"?q=" + Uri.encode(adresse));
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setPackage("com.google.android.apps.maps");
            intent.setData(uri);
            startActivity(intent);
        }


    }

        public void onComposeAction(MenuItem mi) {
            // handle click here
        }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/

        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_modifier:
                modifier();
                break;
            // action with ID action_settings was selected
            case R.id.action_supprimer:
                suppression();
                break;
            default:
                break;
        }

        return true;
    }
}
