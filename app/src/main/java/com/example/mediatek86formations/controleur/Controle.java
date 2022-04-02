package com.example.mediatek86formations.controleur;

import android.content.Context;

import com.example.mediatek86formations.modele.AccesDistant;
import com.example.mediatek86formations.modele.AccesLocal;
import com.example.mediatek86formations.modele.Formation;

import java.util.ArrayList;

public class Controle {

    private static Controle instance = null ;
    private ArrayList<Formation> lesFormations = new ArrayList<Formation>();
    private Formation formation = null;
    private static AccesLocal accesLocal;
    private ArrayList<Formation> lstFavoris = new ArrayList<Formation>();
    private ArrayList<Integer> lstIdFavoris = new ArrayList<Integer>();
    private boolean FavoriWindow = false;

    /**
     * constructeur privé
     */
    private Controle(Context contexte){
        super();

    }

    /**
     * récupération de l'instance unique de Controle
     * @Param contexte
     * @return instance
     */
    public static final Controle getInstance(Context contexte){
        if(Controle.instance == null){
            Controle.instance = new Controle(contexte);
            AccesDistant accesDistant = new AccesDistant();
            accesDistant.envoi("tous", null);
            accesLocal = new AccesLocal(contexte);
            accesLocal.recupFavori();


        }
        return Controle.instance;
    }


    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    /**
     *
     * @return lesFormations
     */
    public ArrayList<Formation> getLesFormations() {
        return lesFormations;
    }

    public void setLesFormations(ArrayList<Formation> lesFormations) {
        this.lesFormations = lesFormations;
    }

    /**
     * retourne true si une Formation appartient à la table Favori
     * @param uneFormation
     * @return boolean
     */
    public boolean isFormationFavori(Formation uneFormation){
        if(lstFavoris != null)
        {
            for (Formation unFavori : lstFavoris) {
                if (unFavori == uneFormation) {
                    return true;
                }
            }
        }

        // retourne false si idformation n'est pas dans favori ou si favori est vide.
        return false;
    }

    /**
     * Ajoute un Favori à la table favori et à liste
     * @param unFavori
     */
    public void addFavori(Formation unFavori)
    {
        accesLocal.ajout(unFavori.getId());
        addLstFavoris(unFavori);

    }

    /**
     * Ajoute un Favori à la liste
     * @param unFavori
     */
    public void addLstFavoris(Formation unFavori){
        lstFavoris.add(unFavori);
    }

    /**
     * Supprime un Favori de la liste
     * @param unFavori
     */
    public void removeLstFavoris(Formation unFavori){
      lstFavoris.remove(unFavori);

    }

    /**
     * Supprime un favori de la liste est de la table
     * @param unFavori
     */

    public void removeFavori(Formation unFavori)
    {
        accesLocal.remove(unFavori.getId());
        removeLstFavoris(unFavori);

    }


    /**
     * Met le boolean de FavoriActivity à true ou à false selon si on est dedans ou non
     * @param favoriWindow
     */

    public void setFavoriWindow(boolean favoriWindow) {
        FavoriWindow = favoriWindow;
    }

    /**
     * recupere le boolean qui test si nous somme dans FavoriActivity
     * @return boolean
     */
    public boolean getFavoriWindow(){
        return FavoriWindow;
    }

    /**
     * Retourne la liste des favoris
     * @return
     */
    public ArrayList<Formation> recupFavoris(){

        if(lstIdFavoris != null) {

            for (Formation uneFormation : lesFormations) {
                for (Integer idformation : lstIdFavoris) {

                    if (idformation == uneFormation.getId()) {
                        lstFavoris.add(uneFormation);
                    }
                }
            }

        }
        return lstFavoris;
    }


    /**
     * retourne les formations filtrés
     * @param filtre
     * @return
     */
    public ArrayList<Formation> getLesFormationFiltre(String filtre){
        return this.getLstFiltres(filtre, lesFormations);
    }

    /**
     *retourne les favoris filtrés
     * @param filtre
     * @return
     */
    public ArrayList<Formation> getLesFavorisFiltre(String filtre){
        return this.getLstFiltres(filtre, lstFavoris);
    }

    /**
     *Filtre pour Favori et Formations
     * @param filtre
     * @param lesFormations
     * @return
     */
    public ArrayList<Formation> getLstFiltres(String filtre, ArrayList<Formation> lesFormations)
    {
        ArrayList<Formation> lstFiltres = new ArrayList<>();
        for(Formation uneFormation : lesFormations){
            if(uneFormation.getTitle().toUpperCase().contains(filtre.toUpperCase())){
                lstFiltres.add(uneFormation);
            }
        }
        return lstFiltres;
    }

    /**
     * getter sur la liste des Favoris
     * @return lstFavoris
     */

    public ArrayList<Formation> getLesFavoris(){
        return lstFavoris;

    }

    /**
     * Setter sur la liste des Favori
     * @param lstIdFavoris
     */
    public void setLesFavoris(ArrayList<Integer> lstIdFavoris){
        this.lstIdFavoris = lstIdFavoris;

    }

}
