package com.example.mediatek86formations.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mediatek86formations.*;
import com.example.mediatek86formations.controleur.Controle;
import com.example.mediatek86formations.modele.Formation;

import java.util.ArrayList;
import java.util.Collections;

public class FavoriActivity extends AppCompatActivity {


    private Controle controle;
    private Button btnFiltrer;
    private EditText txtFiltre;
    private ArrayList<Formation> lesFormations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formations);
        init();
    }

    /**
     * initialisations
     */
    private void init() {
        controle = Controle.getInstance(null);
        btnFiltrer = (Button) findViewById(R.id.btnFiltrer);
        txtFiltre = (EditText) findViewById(R.id.txtFiltre);
        // On récupere la liste des favoris pour valoriser l'adapter
        lesFormations = controle.getLesFavoris();
        controle.setFavoriWindow(true);
        ecouteFiltre();
        creerListe();

    }

    /**
     * procedure evenementielle sur le bouton Filtre
     */

    private void ecouteFiltre() {
        btnFiltrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtFiltre.getText().toString() != "") {
                    lesFormations = controle.getLesFavorisFiltre(txtFiltre.getText().toString());
                } else {
                    lesFormations = controle.getLesFavoris();
                }
                creerListe();
            }

        });

    }

    /**
     * création de la liste adapter
     */
    private void creerListe() {

        if (lesFormations != null) {
            Collections.sort(lesFormations, Collections.<Formation>reverseOrder());
            ListView listView = (ListView) findViewById(R.id.lstFormations);
            FormationListAdapter adapter = new FormationListAdapter(lesFormations, FavoriActivity.this);
            listView.setAdapter(adapter);
        }

    }











}


