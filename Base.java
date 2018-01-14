package com.example.sabrina.projet;

/**
 * Created by sabrina on 28/11/2015.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.AbstractMap;

/**
 * Created by sabrina on 18/11/2015.
 */
public class Base extends SQLiteOpenHelper{
    private static final String RESTAURANT ="Base.db";//nom de la base de donnée
    public static final String TABLENAME = "Resaturant"; // nom de la table
    public static String NOM="Nom";
    public static String ADRESSE="Adresse";
    public static String CODE_POSTAL="CP";
    public static String VILLE="Ville";
    public static String NUMERO_TEL="Numero_Telephone";
    public static String SITE_WEB="Site_Web";
    public static String JOUR_OUVERTURE="Jour_Ouverture";
    public static String HEURE_OUVERTURE="Heure_Ouverture";
    public static String TYPE_CUISINE="Type_Cuisine";
    public static String NOMBRE_ETOILES="Nombre_etoile";//note en general
    public static String ETOILES_EMPLOYES="Note_employe";
    public static String NOURRITUE="Nourriture";
    public static String SERVICE="Service";
    public static String HYGIENE="Hygienne";
    public static String AVIS="Avis";

    private static final int VERSION = 1;

    private static final String CREATE_RESTAURANT = "CREATE TABLE "+TABLENAME+" ( "+
            NOM+" STRING,"+
            ADRESSE+" STRING,"+
            CODE_POSTAL+" STRING,"+
            VILLE+" STRING,"+
            NUMERO_TEL+" STRING,"+
            SITE_WEB+" STRING,"+
            JOUR_OUVERTURE+" STRING,"+
            HEURE_OUVERTURE+" STRING,"+
            TYPE_CUISINE+" STRING,"+
            AVIS+" STRING,"+
            ETOILES_EMPLOYES+" STRING,"+
            NOURRITUE+" STRING,"+
            SERVICE+" STRING,"+
            HYGIENE+" STRING,"+
            NOMBRE_ETOILES+" STRING);";

    public Base(Context context){

        super(context,RESTAURANT,null,VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        super.onConfigure(db);//faut toujours faire ça
        db.execSQL(CREATE_RESTAURANT);
        insertRestau(db, "al caratello", "5 Rue Audran","75018","Paris", "01 42 62 24 23", "",
                "12:00 – 14:30, 19:00 – 23:30", "lundi-dimanche", "italienne", "3","Tres bien","4","2","4","3");
        insertRestau(db, "antoine", "10, avenue de New York","75018", "Paris", "01 53 28 45 20", "www.antoine-paris.fr",
                " 12:00 – 14:30, 19:00 – 23:30", "mardi-samedi", "française", "2","Pas mal","2","1","2","3");
        insertRestau(db, "le balm", "6 rue Valois","75001", "Paris", "01 42 62 24 23", "www.balm.fr",
                "12:00 – 14:30, 19:00 – 23:30", "lundi-vendredi", "française", "3","Tres bien","4","2","4","3");
        insertRestau(db, "le bauhinia", "10 Avenue d'Iéna","75116" ,"Paris", "01 42 62 24 23", "",
                "06:30 – 12:00, 18:30 – 23:30", "lundi-dimanche", "asiatique", "1","Bof","0","1","2","0");
        insertRestau(db,"loriental","47 Avenue Trudaine","75009", "Paris","01 42 64 39 80","www.loriental-restaurant.com",
                "12:00 – 15:00, 19:00 – 23:00","lundi-dimanche","oriental", "0","Null","0","0","0","0");


    }
    public long insertRestau(SQLiteDatabase db,String nom,String adresse,String codePostal,String ville,String numero, String site
            ,String heure,String jour,String type,String etoile,String avis,String employe,String hygiene,String service,String nourriture){
        ContentValues values = new ContentValues();
        values.put(NOM,nom);
        values.put(ADRESSE,adresse);
        values.put(CODE_POSTAL,codePostal);
        values.put(VILLE,ville);
        values.put(NUMERO_TEL,numero);
        values.put(HEURE_OUVERTURE,heure);
        values.put(JOUR_OUVERTURE,jour);
        values.put(TYPE_CUISINE,type);
        values.put(NOMBRE_ETOILES,etoile);
        values.put(SITE_WEB, site);
        values.put(AVIS, avis);
        values.put(ETOILES_EMPLOYES, employe);
        values.put(HYGIENE, hygiene);
        values.put(SERVICE, service);
        values.put(NOURRITUE, nourriture);
        return db.insert(TABLENAME, null, values);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        if(newVersion>oldVersion){
            db.execSQL("drop table if exists "+TABLENAME);
            onCreate(db);
        }
    }

}
