import java.util.ArrayList;
/**
 * Klasa Słowo, której obiekty tworzone są podczas uruchomienia gry
 * @author Daniel Szuta
 */
public class Slowo{
 StringBuilder text;
 ArrayList<StringBuilder> zleSlowa = new ArrayList<>();



/**
 * Konstruktor, który zapisuje przesłany tekst jako pole text i wywołuje metodę generujZle()
 * @param text słowo przesyłane konstruktorowi
 */
Slowo(String text ){
    StringBuilder sb=new StringBuilder(text);
    this.text=sb;
    generujZle(sb);

}//konstruktor


    /**
     * Metoda generująca złe złowa i zapisujaca je do zleSlowa
     * @param slowo parametr na podstawie którego generowane są złe słowa
     */
    private void generujZle(StringBuilder slowo){
    if(slowo.toString().contains("u")){                                                             //z u na ó
        StringBuilder wyraz=Podmien(slowo,new StringBuilder("u"),new StringBuilder("ó"));
        zleSlowa.add(wyraz);
    }
    if(slowo.toString().contains("ó")){                                                             //z ó na u
        StringBuilder wyraz=Podmien(slowo,new StringBuilder("ó"),new StringBuilder("u"));
        zleSlowa.add(wyraz);
    }
    if(slowo.toString().contains("ż")){                                                             //z ż na rz
        StringBuilder wyraz=Podmien(slowo,new StringBuilder("ż"),new StringBuilder("rz"));
        zleSlowa.add(wyraz);
    }
    if(slowo.toString().contains("rz")){                                                             //z rz na ż
        StringBuilder wyraz=Podmien(slowo,new StringBuilder("rz"),new StringBuilder("ż"));
        zleSlowa.add(wyraz);
    }
    if(slowo.toString().contains("h")&&!slowo.toString().contains("ch")){                                                             //z h na ch
        StringBuilder wyraz=Podmien(slowo,new StringBuilder("h"),new StringBuilder("ch"));
        zleSlowa.add(wyraz);
    }
    if(slowo.toString().contains("ch")){                                                             //z ch na h
        StringBuilder wyraz=Podmien(slowo,new StringBuilder("ch"),new StringBuilder("h"));
        zleSlowa.add(wyraz);
    }
    if(slowo.toString().contains("ą")){                                                             //z ą na om
        StringBuilder wyraz=Podmien(slowo,new StringBuilder("ą"),new StringBuilder("om"));
        zleSlowa.add(wyraz);
    }
    if(slowo.toString().contains("om")){                                                             //z om na ą
        StringBuilder wyraz=Podmien(slowo,new StringBuilder("om"),new StringBuilder("ą"));
        zleSlowa.add(wyraz);
    }
    if(slowo.toString().contains("sz")){                                                             //z sz na rz
        StringBuilder wyraz=Podmien(slowo,new StringBuilder("sz"),new StringBuilder("rz"));
        zleSlowa.add(wyraz);
    }
}

    /**
     * Metoda wywoływana w metodzie generujZle() która podmienia litery w parametrze slowo
     * @param slowo słowo w którym trzeba podmienić litery
     * @param przed litery, które trzeba podmienić
     * @param po litery, które zastąpią miejsce poprzednich
     * @return zmienione słowo
     */
    private StringBuilder Podmien(StringBuilder slowo,StringBuilder przed,StringBuilder po){

    StringBuilder stringBuilder =new StringBuilder(slowo.toString()) ;

    int i=stringBuilder.indexOf(przed.toString());

    stringBuilder.delete(i,i+przed.length());

    stringBuilder.insert(i,po);

    return stringBuilder;
}//Podmien

    /**
     * Metoda zwracająca ilość wyrazów (poprawny+błędne)
     * @return ilość wyrazów razem z poprawnym wyrazem
     */
    public int ileWyrazow (){
        return zleSlowa.size()+1;
    }
}//klasa
