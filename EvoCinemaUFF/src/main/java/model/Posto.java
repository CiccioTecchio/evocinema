
package model;

public class Posto {
   private String idPosto;
   private String fila;
   private String numero;
   private String statoPosto;

    public Posto(String idPosto, String fila, String numero, String statoPosto) {
        this.idPosto = idPosto;
        this.fila = fila;
        this.numero = numero;
        this.statoPosto = statoPosto;
    }

    public String getIdPosto() {
        return idPosto;
    }

    public String getFila() {
        return fila;
    }

    public String getNumero() {
        return numero;
    }

    public String getStatoPosto() {
        return statoPosto;
    }

    public void setIdPosto(String idPosto) {
        this.idPosto = idPosto;
    }

    public void setFila(String fila) {
        this.fila = fila;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setStatoPosto(String statoPosto) {
        this.statoPosto = statoPosto;
    }
   
     @Override
    public String toString(){
        
        return(getClass().getName()+"{id posto="+idPosto+", fila="+fila+", numero="+numero+", stato posto="+statoPosto);
    } 
}
