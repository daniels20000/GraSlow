import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;



/**
 * Klasa odpowiedzialna za tworzenie okienek z możliwością wyświetlania informacji i wykonywania czynności przez wciśnięcie przycisków
 * @author Daniel Szuta
 */

public class Okno  {

     public   Button b1;
     public   Button b2;
     public   Stage okno;

    //region warianty
    public  void wyswietl(){
        wyswietl("","","","");
    }
    public  void wyswietl(String tytul){
        wyswietl(tytul,"","","");
    }
    public  void wyswietl(String tytul,String wiadomosc){
        wyswietl(tytul,wiadomosc,"","");
    }
    public  void wyswietl(String tytul,String wiadomosc,String przycisk1){
        wyswietl(tytul,wiadomosc,przycisk1,"");
    }
    //endregion
    /**Metoda wyświetlanjąca okienko**/
   public  void wyswietl(String tytul,String wiadomosc,String przycisk1,String przycisk2){

    okno =new Stage();
    okno.setTitle(tytul);
       HBox hBox = new HBox(10);
        Label label = new Label(wiadomosc);

       if(!przycisk1.isEmpty()){
         b1 = new Button(przycisk1);
        hBox.getChildren().add(b1);
        }
        if(!przycisk2.isEmpty()){
             b2 = new Button(przycisk2);
            hBox.getChildren().add(b2);
        }


       VBox vBox = new VBox(10);
       hBox.setAlignment(Pos.CENTER);
       vBox.setAlignment(Pos.CENTER);
       vBox.getChildren().addAll(label,hBox);

       Scene scene = new Scene(vBox,250,100);
       okno.setScene(scene);
    okno.show();

    }

    /**
     * Metoda ustawiająca zachowanie przycisku pierwszego
     * @param value zachowanie, które ma zostać przypisane do przycisku
     */
    public  void setFirstAction (javafx.event.EventHandler<ActionEvent> value) {
        b1.onActionProperty().set(value);
    }

    /**
     * Metoda ustawiająca zachowanie przycisku drugiego
     * @param value zachowanie, które ma zostać przypisane do przycisku
     */
    public  void setSecondAction (javafx.event.EventHandler<ActionEvent> value) {
        b2.onActionProperty().set(value);
    }

    public void close(){
        okno.close();
    }

    }

