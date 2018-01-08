import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Klasa służąca do wyświetlania wyników w postaci tabeli
 * @author Daniel Szuta
 */

public  class Wyniki {

   static   TableView<Gracz> table;
   static ArrayList<Gracz> graczArrayList= new ArrayList<>();
    static ObservableList<Gracz> gracze = FXCollections.observableArrayList();

    Wyniki(){
        wyswietl();
    }

    private void wyswietl() {
        Stage stage = new Stage();
        stage.setTitle("Wyniki");


        TableColumn<Gracz, String> nazwaColumn = new TableColumn<>("Nazwa");
        nazwaColumn.setMinWidth(200);
        nazwaColumn.setCellValueFactory(new PropertyValueFactory<>("nazwa"));


        TableColumn<Gracz, Integer> wynikColumn = new TableColumn<>("Wynik");
        wynikColumn.setMinWidth(100);
        wynikColumn.setCellValueFactory(new PropertyValueFactory<>("wynik"));


        TableColumn<Gracz, String> trudnoscColumn = new TableColumn<>("Poziom trudności");
        trudnoscColumn.setMinWidth(100);
        trudnoscColumn.setCellValueFactory(new PropertyValueFactory<>("trudnosc"));

        table = new TableView<>();
        table.setItems(getPlayers());
        table.getColumns().addAll(nazwaColumn, wynikColumn, trudnoscColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.show();
    }

    private ObservableList<Gracz> getPlayers() {



        File plik = new File("Resources/gracze.txt");


        try {
            Scanner scanner = new Scanner(plik);

            while (scanner.hasNext()) {
                Gracz gracz = new Gracz(scanner.next(), scanner.next(), scanner.next());
                gracze.add(gracz);
                graczArrayList.add(gracz);
            }
            scanner.close();
        } catch (IOException ex1) {
            System.out.println(ex1.getMessage());
            //System.out.println("błąd");



        }
        return gracze;
    }
}
