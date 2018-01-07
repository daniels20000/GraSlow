import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * W tej klasie odbywa się rozgrywka
 * @author Daniel Szuta
 */
public class Gra {

    private Stage stage = Main.okno;

    private int licznik;
    private Random rnd = new Random();
    private ArrayList index = new ArrayList<Integer>();

    private ArrayList<Slowo> slowoArrayList = Main.slowoArrayList;
    private Timer timer = new Timer(true);
    private Gracz gracz;

    Gra() {
        rozpocznij();

    }

    public void rozpocznij() {


        stage.setTitle("Gra Słów");

        VBox vBox = new VBox(40);
        HBox hBox = new HBox(40);

        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);

        TextField nazwaGracza = new TextField();
        nazwaGracza.setPromptText("Nazwa gracza");
        nazwaGracza.setMaxWidth(200);
        nazwaGracza.setPadding(new Insets(3));

        Button zatwNazw = new Button("Zatwierdź");
        zatwNazw.setOnAction(event -> {
        gracz=new Gracz(nazwaGracza.getText());
        });

        HBox nazwaHBox = new HBox(40,nazwaGracza,zatwNazw);
        nazwaHBox.setAlignment(Pos.CENTER);
        borderPane.setTop(nazwaHBox);
        borderPane.setPadding(new Insets(15));

        Label label1 = new Label("Cześć ");
        Label label2 = new Label();
        label2.textProperty().bind(nazwaGracza.textProperty());

        Label label3 = new Label("Wybierz poziom trudności");

        HBox powitanieHBox = new HBox(label1,label2);
        VBox powitanieVBox = new VBox(15,powitanieHBox,label3);
        powitanieHBox.setAlignment(Pos.CENTER);
        powitanieVBox.setAlignment(Pos.CENTER);

        Button bLatwy = new Button("Latwy");
        Button bSredni = new Button("Sredni");
        Button bTrudny = new Button("Trudny");

        bLatwy.setOnAction(e -> {
            licznik = 60;
            gracz.setTrudnosc("łatwy");
            tura();
        });

        bSredni.setOnAction(e -> {

            licznik = 50;
            gracz.setTrudnosc("średni");
            tura();
        });

        bTrudny.setOnAction(e -> {
            licznik = 40;
            gracz.setTrudnosc("trudny");
            tura();
        });

        hBox.getChildren().addAll(bLatwy, bSredni, bTrudny);
        vBox.getChildren().addAll(powitanieVBox, hBox);


        Scene scene = new Scene(borderPane, 400, 250);

        stage.setScene(scene);

        stage.setOnCloseRequest(e -> {


            e.consume();

            stage.setScene(Main.scene);
            stage.setOnCloseRequest(event -> this.close());

        });

    }

    private void tura() {


        VBox vBox1 = new VBox(50);
        VBox vBox2 = new VBox(50);

        Label label = new Label();

        HBox hBox = new HBox(25);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(25));
        hBox.getChildren().addAll(vBox1, vBox2);
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(hBox);
        borderPane.setTop(label);



        Scene turaScene = new Scene(borderPane, 400, 300);

        kolejneSlowo(turaScene,vBox1, vBox2);

        timer.schedule(new TimerTask() {

            int licznikTura = licznik;

            @Override
            public void run() {

                System.out.println(licznikTura);
                // System.out.println(licznik);
                licznikTura--;
                label.setText(Integer.toString(licznikTura));
                if (licznikTura == 0) {

                    cancel();
                    timer.cancel();
                }

            }
        }, 2000, 1000);


    }

    private boolean kolejneSlowo(Scene scene,VBox vBox1, VBox vBox2) {

        int i = rnd.nextInt(slowoArrayList.size());

        while (index.contains(i))
            i=rnd.nextInt(slowoArrayList.size());
        if(index.size()>slowoArrayList.size()) return false;

        index.add(i);
        Slowo slowo = slowoArrayList.get(i);

        Button button1 = new Button();
        Button button2 = new Button();
        Button button3 = new Button();
        Button button4 = new Button();

        switch (slowo.ileWyrazow()){
            case 1:{
                button1.setText(slowo.text.toString());
                vBox1.getChildren().add(button1);

            }break;
            case 2:{
                button1.setText(slowo.text.toString());
                button1.setText(slowo.zleSlowa.get(0).toString());



                if (rnd.nextBoolean())
                vBox1.getChildren().addAll(button1,button2);
                else
                    vBox1.getChildren().addAll(button2,button1);

            }break;

        }





        return true;

        }




    public void close() {
        Main.okno.setScene(Main.scene);
    }

}