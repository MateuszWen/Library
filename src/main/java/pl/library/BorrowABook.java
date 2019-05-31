package pl.library;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class BorrowABook implements Initializable {

    @FXML private TableView<Readers> tableReaders;
    @FXML private TableColumn<Readers, Integer> col_readersID;
    @FXML private TableColumn<Readers, String> col_name;
    @FXML private TableColumn<Readers, String> col_surname;

    @FXML private TableView<Books> tableBooks;
    @FXML private TableColumn<Books, Integer> col_booksID;
    @FXML private TableColumn<Books, String> col_title;
    @FXML private TableColumn<Books, String> col_author;
    @FXML private TableColumn<Books, Integer> col_borrowedOrNot;

    public static ObservableList<Loans> loans_list = Functions.getLoansFunction();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_readersID.setCellValueFactory(new PropertyValueFactory<Readers, Integer>("readers_ID"));
        col_name.setCellValueFactory(new PropertyValueFactory<Readers, String>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<Readers, String>("surname"));
        tableReaders.setItems(AddDeleteReader.readers_list);

        col_booksID.setCellValueFactory(new PropertyValueFactory<Books, Integer>("books_ID"));
        col_title.setCellValueFactory(new PropertyValueFactory<Books, String>("title"));
        col_author.setCellValueFactory(new PropertyValueFactory<Books, String>("author"));
        col_borrowedOrNot.setCellValueFactory(new PropertyValueFactory<Books, Integer>("borrowedOrNot"));
        tableBooks.setItems(AddDeleteBook.books_list);
    }


    @FXML public void buttonBorrowClicked(){
        Books book = tableBooks.getSelectionModel().getSelectedItem();
        Readers reader = tableReaders.getSelectionModel().getSelectedItem();

        if(book.getBorrowedOrNot() == 1 ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("You can't borrow borrowed book.");
            alert.showAndWait();
        } else if(tableBooks.getSelectionModel().getSelectedItem()!=null && tableReaders.getSelectionModel().getSelectedItem()!=null){
            Functions.borrowABook(reader, book, loans_list);
        }else{
            //do nothink
        }




    }
}
