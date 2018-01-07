
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;

/**
 * Klasa Main
 * @author Daniel Szuta
 */
public class Main extends Application  {
    public static ArrayList<Slowo> slowoArrayList = new ArrayList<>();
    public static ArrayList<String> strings = new ArrayList<>();
    public static Thread thread;
    public static Stage okno;//stage okno jako static, żeby nie trzeba było tworzyć nowych Stage'ów tylko podmienić scenę
    public static Scene scene;


    private boolean ukonczono =false;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        okno=stage;

        thread= new Thread(this::initialize);//wątek uruchaminający metodę initialize()
        thread.setDaemon(true);//ustawienie wątku jako daemon, żeby program mógł zamknąć się nawet jeśli wątek trwa
        thread.start();


        BorderPane menu = new BorderPane();//Layout glówny
        VBox vBox=new VBox(20);//Layout pionowy
        vBox.setAlignment(Pos.CENTER);

        //region deklaracja buttonów
        Button bStart=new Button("Start");
        bStart.setMinWidth(70);
        Button bInstrukcja=new Button("Instrukcja");
        bInstrukcja.setMinWidth(70);
        Button bWyniki=new Button("Wyniki");
        bWyniki.setMinWidth(70);
        Button bZakoncz=new Button("Zakończ");
        bZakoncz.setMinWidth(70);
        //endregion

        //region setOnAction buttonów
        bStart.setOnAction(e->{
            if(!thread.isAlive()) {
                Gra gra = new Gra();
            }else {
                Okno okno = new Okno();
                okno.wyswietl("ładowanie..","Gra ładuje się. Spróbuj za chwilę");

            }
// Gra gra = new Gra();


        });//setOnAction bStart

        bInstrukcja.setOnAction(e->{
            Okno okno = new Okno();
            okno.wyswietl("Instrukcja",
                    "Gracz ma za zadanie...",
                    "Zamknij"
            );
            okno.setFirstAction(event -> okno.okno.close());
        });//setOnAction bInstrukcja

        bWyniki.setOnAction(e->{

            Wyniki wyniki = new Wyniki();


        });////setOnAction bWyniki

        bZakoncz.setOnAction(e->{

            okno.close();
        });//setOnAction bZakoncz

        //endregion





        vBox.getChildren().addAll(bStart,bInstrukcja,bWyniki,bZakoncz);//dodawanie przycisków do vBox
        menu.setCenter(vBox);//dodawanie vBox do menu



        scene=new Scene(menu,400,300);//deklaracja sceny wraz z ustaleniem layoutu i wymiarów początkowych
        okno.setScene(scene);
        okno.show();
    }//start



    /**
     * Metoda inicjalizująca program.
     * Wybierane są losowo słowa, tworzone na ich podstawie obiekty klasy Slowo i zapisywane do slowoArrayList
     */


    private void initialize() {
       //Plik ze słowami



        File plik = new File("Resources/slowa.txt");
        //Zmienna losowa do wybierania słów z pliku
        Random rnd = new Random();
        //Wybieranie wyrazow  z pliku
        try {
            Scanner scanner = new Scanner(plik);
            System.out.println("udało się");
           while (scanner.hasNext()){

               String s = scanner.nextLine();
               if(rnd.nextInt(30000)==0)strings.add(s);

               //System.out.println(strings.size());//do debugowania
         }
            System.out.println("koniec");
         scanner.close();
        }  catch (IOException ex1){
            System.out.println(ex1.getMessage());
        //System.out.println("błąd");
        }
        //pętla while tworząca 50 obiektów klasy Slowo
            while (slowoArrayList.size()<50) {
                int i = rnd.nextInt(strings.size());
                if(!slowoArrayList.contains(new Slowo(strings.get(i))))
                slowoArrayList.add(new Slowo(strings.get(i)));

            }
        strings.clear();
        Collections.shuffle(slowoArrayList);
        ukonczono = true;

        }

    }

