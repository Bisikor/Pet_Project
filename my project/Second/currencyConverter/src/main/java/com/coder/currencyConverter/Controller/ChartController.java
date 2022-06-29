package com.coder.currencyConverter.Controller;

import com.coder.currencyConverter.Models.CourseModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

import static com.coder.currencyConverter.ParserTask.courseParser.*;
import static com.coder.currencyConverter.allURL.addNewUrl;

@Component

public class ChartController implements Initializable {

    @FXML
    public TableView<CourseModel> allCurrency;

    @FXML
    public TableColumn<CourseModel, String> CurrencyName;

    @FXML
    public TableColumn<CourseModel, String> purchase;

    @FXML
    public ComboBox currencyFromBox;

    @FXML
    public ComboBox CurrencyToBox;

    @FXML
    public Label forTesting;

    @FXML
    public TextField CurrencyFromText;

    @FXML
    public TextField CurrencyToText;

    @FXML
    public TextField NewHrefEdit;

    @FXML
    void SelectFromBox(ActionEvent event) {

    }

    @FXML
    void SelectToBox(ActionEvent event) {

    }

    public ObservableList<CourseModel> makeTable() {

        parseNewCourse();
        ObservableList<CourseModel> output = FXCollections.observableArrayList();
        for (int i = 0; i < massName.length; i++) {
            output.add(i, new CourseModel(massName[i], currencyInformation.get(massName[i])));
        }
        return output;
    }


    @FXML
    void clickConvert(ActionEvent event) {
        CurrencyToText.setText(String.valueOf(calculating()));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        CurrencyName.setCellValueFactory(new PropertyValueFactory<CourseModel, String>("CurrencyName"));
        purchase.setCellValueFactory(new PropertyValueFactory<CourseModel, String>("CurrencyPurchase"));
        allCurrency.setItems(makeTable());
        ObservableList<String> combBoxList = FXCollections.observableArrayList(massName);
        currencyFromBox.setItems(combBoxList);
        currencyFromBox.getSelectionModel().selectFirst();
        CurrencyToBox.setItems(combBoxList);
        CurrencyToBox.getSelectionModel().selectLast();
        CurrencyToText.setEditable(false);
    }

    public Double calculating() {
        String nameFrom = currencyFromBox.getSelectionModel().getSelectedItem().toString();
        String nameTo = CurrencyToBox.getSelectionModel().getSelectedItem().toString();
        double courseFrom = (currencyInformation.get(nameFrom));
        double courseTo = (currencyInformation.get(nameTo));
        String lineWithoutComa = rePlaced(CurrencyFromText.getText());
        if (isDigit(lineWithoutComa)) {
            double result = Double.parseDouble(lineWithoutComa) / courseFrom;
            result = result * courseTo;
            return result;
        } else AlertMessage("Invalid input format", "Please enter the number to be converted");
        return 0.0;


    }

    @FXML
    public void ClickEdit(ActionEvent event) {
        if (addLickChecker(NewHrefEdit.getText())) {
            addNewUrl(NewHrefEdit.getText());
            AlertMessage("Successful", "Applications need to be restarted for the changes to take effect.");
        } else
            AlertMessage("The link should look like->https://www.exchange-rates.org/Rate/USD/YourCurrency", "Invalid link");

    }

    public String rePlaced(String line) {
        return line.replaceAll(",", "\\.");
    }

    public boolean isDigit(String line) throws NumberFormatException {
        try {
            Double.parseDouble(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean addLickChecker(String link) {
        if (link.length() < 41)
            return false;
        String partLink = link.substring(0, 40);
        return partLink.equals("https://www.exchange-rates.org/Rate/USD/");
    }

    public void AlertMessage(String testAlert, String contextAlert) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(contextAlert);
        alert.setHeaderText(testAlert);
        alert.show();
    }
}
