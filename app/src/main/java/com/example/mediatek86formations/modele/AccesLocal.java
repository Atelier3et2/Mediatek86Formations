package com.example.mediatek86formations.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.outils.MesOutils;
import com.example.mediatek86formations.outils.MySQLiteOpenHelper;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;

public class AccesLocal {

    private String nomBase = "bdmediatek.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;
    private Controle controle;

    /**
     * constructeur : valorise l'accès à la BDD
     * @param context
     */
    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
        controle = Controle.getInstance(null);

    }

    /**
     * ajout d'un formation dans la BDD favori
     * @param idformation
     */
    public void ajout(Integer idformation){

        bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("idformation", idformation);
        bd.insert("favori", null, values);
        bd.close(); 
    }

    /**
     * Supprime une formation d'un favori
     * @param idformationS
     */

    public void remove(Integer idformation){

        bd = accesBD.getWritableDatabase();
        bd.delete("Favori", "idformation=?", new String[]{idformation.toString()});
    }




    /**
     * retourne une liste idformation de favori et met à jour la liste des favoris
     * @return dernier profil
     */
    public void recupFavori(){
        ArrayList<Integer> listeFavori = new ArrayList<Integer>();
        bd = accesBD.getReadableDatabase();
        String req = "select * from favori";
        Cursor curseur = bd.rawQuery(req, null);
        while(curseur.moveToNext()){
            Integer idformation = curseur.getInt(0);
            listeFavori.add(idformation);
        }
        curseur.close();
        controle.setLesFavoris(listeFavori);
    }

}
