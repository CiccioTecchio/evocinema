/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control.programmazioneCNT;

import model.Sala;
import model.Spettacolo;

/**
 *Una classe per effettuare sulla stringa dei posti di una sala le elaborazioni necessarie alla rappresentazione 
 * @author luca
 */
public class RappresentazioneSala {
    public RappresentazioneSala(Spettacolo spettacolo, Sala sala, int offset){
        this.spettacolo = spettacolo;
        this.sala = sala;
        this.offset = offset;
        execute();
    }
    
    /**
     * Calcola gli indici di prima ed ultima riga e prima ed ultima colonna da stampare a video per rappresentare
     * l'intera sala.
     */
    private void execute(){
        char[] matricePostiSala = (sala.getConfigPosti()).toCharArray();
        matricePostiSpettacolo = (spettacolo.getMatricePosti()).toCharArray();
        
        //Conversione dell'array in una matrice
        matSala = new char[30][30];
        int z = 0;
        for(int i = 0; i < 30; i++){
            for(int j = 0; j < 30; j++){
                matSala[i][j] = matricePostiSala[z];
                z++;
            }
        }
        
        //Calcolo della prima riga dei posti da cui stampare
        boolean flag = true;
        for(int i = 0; flag && i < 30; i++){
            for(int j = 0; j < 30; j++){
                if(matSala[i][j] != '0'){
                    beginRiga = i;
                    flag = false;
                }    
            }   
        }
        
        //Calcolo dell'ultima riga dei posti da stampare
        flag = true;
        for(int i = 29; flag && i >= 0; i--){
            for(int j = 0; j < 30; j++){
                if(matSala[i][j] != '0'){
                    endRiga = i;
                    flag = false;
                }
            }   
        }
        
        //Calcolo della prima colonna dei posti da stampare
        flag = true;
        for(int j = 0; flag && j < 30; j++){
            for(int i = 0; i < 30; i++){
                if(matSala[i][j] != '0') {
                    beginColonna = j;    
                    flag = false;
                }
            }   
        }
        
        //Calcolo dell'ultima colonna dei posti da stampare
        flag = true;
        for(int j = 29; flag && j >= 0; j--){
            for(int i = 0; i < 30; i++){
                if(matSala[i][j] != '0'){
                    endColonna = j;
                    flag = false;
                }
            }   
        }
    }

    /**
     * Restituisce la matrice dei posti dello spettacolo
     * @return String rappresentante la matrice dei posti dello spettacolo
     */
    public char[] getMatricePostiSpettacolo() {
        return matricePostiSpettacolo;
    }

    /**
     * Restituisce la matrice dei posti della sala.
     * @return un array bidimensionale
     */
    public char[][] getMatSala() {
        return matSala;
    }

    /**
     * Restituisce l'indice della prima riga da stampare
     * @return un int
     */
    public int getBeginRiga() {
        return beginRiga;
    }

    /**
     * Restituisce l'indice dell'ultima riga da stampare
     * @return un int
     */    
    public int getEndRiga() {
        return endRiga;
    }
    
    /**
     * Restituisce l'indice della prima colonna da stampare
     * @return un int
     */
    public int getBeginColonna() {
        return beginColonna;
    }
    
    /**
     * Restituisce l'indice dell'ultima colonna da stampare
     * @return un int
     */
    public int getEndColonna() {
        return endColonna;
    }
    
    
        
    private int offset, beginRiga = 0, endRiga = 0, beginColonna = 0, endColonna = 0;
    private Spettacolo spettacolo;
    private Sala sala;
    private char[][] matSala;
    char[] matricePostiSpettacolo;
}
