/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apresentacao;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import modelo.Simulator;

/**
 *
 * @author Ana Sequeira
 */
public class SimuladorController implements Initializable {

    private Simulator simulador;
    @FXML
    private BorderPane border;

    @FXML
    private TextField txtElevador;
    @FXML
    private TextField txtPisos;
    @FXML
    private TextField txtMaxCap;
    @FXML
    private TextField txtTick;
    @FXML
    private TextField txtProbabilidade;
    @FXML
    private Slider sliderElevador;
    @FXML
    private Slider sliderPiso;
    @FXML
    private Slider sliderMaxCap;
    @FXML
    private Slider sliderTick;
    @FXML
    private Slider sliderProbabilidade;

    @FXML

    private void handleButtonAction(ActionEvent event) {
        simulador = new Simulator(Integer.valueOf((String) txtElevador.getText()),
                Integer.valueOf((String) txtPisos.getText()),
                Integer.valueOf((String) txtMaxCap.getText()),
                Integer.valueOf((String) txtTick.getText()),
                Integer.valueOf((String) txtProbabilidade.getText()));
        simulador.getEdificio().inserçãoInicial();
        simulador.run();
    }

    @FXML
    private void stop(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtElevador.textProperty().bindBidirectional(sliderElevador.valueProperty(), NumberFormat.getIntegerInstance());
        txtPisos.textProperty().bindBidirectional(sliderPiso.valueProperty(), NumberFormat.getIntegerInstance());
        txtMaxCap.textProperty().bindBidirectional(sliderMaxCap.valueProperty(), NumberFormat.getIntegerInstance());
        txtTick.textProperty().bindBidirectional(sliderTick.valueProperty(), NumberFormat.getIntegerInstance());
        txtProbabilidade.textProperty().bindBidirectional(sliderProbabilidade.valueProperty(), NumberFormat.getIntegerInstance());
    }

}
