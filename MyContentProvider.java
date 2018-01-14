package com.example.sabrina.projet;

/**
 * Created by sabrina on 28/11/2015.
 */
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.net.URI;

/**
 * Created by sabrina on 28/11/2015.
 */
public class MyContentProvider extends ContentProvider{

    private static final int LISTE = 1;
    private static final int RECHERCHE = 2;
    private static final int AJOUT = 3;
    private static final int INFORMATION = 4;

    Base b;
    private static final String autority="fr.sabrina.restaurant";


    public static final Uri CONTENT_URI = Uri.parse("content://fr.sabrina.restaurant");
    private static final UriMatcher URI_MATCHER= new UriMatcher ( UriMatcher.NO_MATCH) ;

    static{
        URI_MATCHER.addURI(autority,"Liste",LISTE);
        URI_MATCHER.addURI(autority,"Rechercher",RECHERCHE);
        URI_MATCHER.addURI(autority,"Ajout/*",AJOUT);
        URI_MATCHER.addURI(autority,"Informations",INFORMATION);

    }

    public MyContentProvider(){

    }
    public boolean onCreate() {
         b = new Base(getContext());
        return true;
    }
    public String getType(Uri uri){
        throw new UnsupportedOperationException("Not yet implemented");
    }
    public Uri insert(Uri uri,ContentValues values){
        SQLiteDatabase db = b.getWritableDatabase();
        long val =db.insert(b.TABLENAME,null,values);

        if(val!=-1) return ContentUris.withAppendedId(CONTENT_URI, val);
        else return null;
    }
    public int delete(Uri uri, String selection,String[] arg){

        SQLiteDatabase db = b.getWritableDatabase();
        int val = db.delete(b.TABLENAME, selection, arg);
        return val;
    }
    public int update(Uri uri,ContentValues values,String s, String []strings){
        b = new Base(this.getContext());
        SQLiteDatabase db = b.getWritableDatabase();
        int val = db.update(b.TABLENAME, values, s, strings);
        return val;
    }
    public Cursor query(Uri uri,String[]projection,String selection,String[]selectionArgs,String sortOrder){

        int code = URI_MATCHER.match(uri);
        SQLiteDatabase db = b.getReadableDatabase();
        switch (code){
            case LISTE:
                return db.query(b.TABLENAME,projection,selection,selectionArgs,null,null,null);
            case RECHERCHE:
                return db.query(b.TABLENAME,projection,selection,selectionArgs,null,null,null);
            case AJOUT:
                return db.query(b.TABLENAME,null,null,null,null,null,null);
            case INFORMATION:
                return db.query(b.TABLENAME,projection,selection,selectionArgs,null,null,null);

        }
        return null;
    }
}
