/**
 * Klasa Gracz reprezentująca graczy
 * @author Daniel Szuta
 */
public class Gracz {
    String nazwa;
    int wynik;
    String trudnosc;

    Gracz(){this("",0,"");}
    Gracz(String nazwa){this(nazwa,0,"");}
    Gracz(String nazwa,int wynik){this(nazwa,wynik,"");}
    Gracz(String nazwa,String wynik,String trudnosc){
        try {
           int intWynik = Integer.parseInt(wynik);

            this.nazwa=nazwa;
            this.wynik=intWynik;
            this.trudnosc=trudnosc;

        }catch (NumberFormatException ex){
            this.nazwa=nazwa;
            this.wynik=0;
            this.trudnosc=trudnosc;
        }

    }
    Gracz(String nazwa,int wynik, String trudnosc){
        this.nazwa=nazwa;
        this.wynik=wynik;
        this.trudnosc=trudnosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getWynik() {
        return wynik;
    }

    public void setWynik(int wynik) {
        this.wynik = wynik;
    }

    public String getTrudnosc() {
        return trudnosc;
    }

    public void setTrudnosc(String trudnosc) {
        this.trudnosc = trudnosc;
    }
}
