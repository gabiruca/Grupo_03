/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Directorio.Directorio;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author bllv1
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    LinkedList<Directorio> treeMap;
    private TextField mRuta;
    private Button visualizar;
    private AnchorPane mostrar;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
     //Métodos necesarios para el manejo del treeMap
    public void iteration(int numero,LinkedList<Directorio> treeMap) {
        Iterator it = treeMap.iterator();
        while (it.hasNext()) {
            Directorio siguiente = (Directorio) it.next();
            if (siguiente.getDirectorios().size() > 0) {
                System.out.println(tmIdentation(numero)+siguiente.getNombre()+"("+siguiente.getTamaño()+")");
                iteration(numero+2,siguiente.getDirectorios());
            } else {
                System.out.println(tmIdentation(numero)+siguiente.getNombre()+"("+siguiente.getTamaño()+")");
            }
        }
    }
    public String tmIdentation(int numero) {
        String cad = String.valueOf(numero);
        if (numero > 0) {
            for (int i = 0; i < numero; i++) {
                cad =cad+"-";
            }
            return cad;
        } else {
            return "";
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
