
package model;

import java.util.Calendar;

public class Sconto {
    
    public enum verificabile{TRUE, FALSE};
    public enum tipo{PERCENTUALE,SCONTO};
    public enum disponibile{TRUE, FALSE};
    public enum tipologia{CAT_PERSONE,GIORNO_SETTIMANA,TERMINE,DATA};
    
    private int idSconto;
    private String nome;
    private int percentuale;
    private float prezzo;
    private String parametroTipologia;
    private verificabile verificabile;
    private tipo tipo;
    private disponibile disponibile;
    private tipologia tipologia;
    
    public Sconto(int idSconto, String nome, int percentuale, float prezzo, String parametroTipologia,verificabile verificabile,tipo tipo,disponibile disponibile, tipologia tipologia ) {
        this.idSconto = idSconto;
        this.nome=nome;
        this.percentuale=percentuale;
        this.prezzo=prezzo;
        this.parametroTipologia=parametroTipologia;
        this.verificabile=verificabile;
        this.tipo=tipo;
        this.disponibile=disponibile;
        this.tipologia=tipologia;
        
    }
    
    public Sconto( String nome, int percentuale, float prezzo, String parametroTipologia,verificabile verificabile,tipo tipo,disponibile disponibile, tipologia tipologia ) {
        
        this.nome=nome;
        this.percentuale=percentuale;
        this.prezzo=prezzo;
        this.parametroTipologia=parametroTipologia;
        this.verificabile=verificabile;
        this.tipo=tipo;
        this.disponibile=disponibile;
        this.tipologia=tipologia;
        
    }
    
    public Sconto(){
        
    }

    public int getIdSconto() {
        return idSconto;
    }

    public String getNome() {
        return nome;
    }

    public int getPercentuale() {
        return percentuale;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public String getParametroTipologia() {
        return parametroTipologia;
    }

    public verificabile getVerificabile() {
        return verificabile;
    }

    public tipo getTipo() {
        return tipo;
    }

    public disponibile getDisponibile() {
        return disponibile;
    }

    public tipologia getTipologia() {
        return tipologia;
    }

    public void setIdSconto(int idSconto) {
        this.idSconto = idSconto;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPercentuale(int percentuale) {
        this.percentuale = percentuale;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public void setParametroTipologia(String parametroTipologia) {
        this.parametroTipologia = parametroTipologia;
    }

    public void setVerificabile(verificabile verificabile) {
        this.verificabile = verificabile;
    }

    public void setTipo(tipo tipo) {
        this.tipo = tipo;
    }

    public void setDisponibile(disponibile disponibile) {
        this.disponibile = disponibile;
    }

    public void setTipologia(tipologia tipologia) {
        this.tipologia = tipologia;
    }

    @Override
    public String toString() {
        return getClass().getName()+"{" + "idSconto=" + idSconto + ", nome=" + nome + ", percentuale=" + percentuale + ", prezzo=" + prezzo + ", parametroTipologia=" + parametroTipologia + ", verificabile=" + verificabile + ", tipo=" + tipo + ", disponibile=" + disponibile + ", tipologia=" + tipologia + '}';
    }
 
}
