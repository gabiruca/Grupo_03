/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Directorio.Directorio;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author bllv1
 */
public class FXMLDocumentController implements Initializable {
    
    LinkedList<Directorio> treeMap;
    @FXML
    private TextField mRuta;
    @FXML
    private Button visualizarBoton;
    @FXML
    private AnchorPane mostrar;
    
    @FXML
    public void ButtonActionADirectorio(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setInitialDirectory(new File("src"));
        File directorioSeleccionado = dc.showDialog(null);
        if (directorioSeleccionado != null) {
            Directorio dir = new Directorio(directorioSeleccionado.getName());
            dir.setTamaño(redondear(recorrerDirectorio(0.0,dir,directorioSeleccionado.listFiles()),2));
            mRuta.setText(directorioSeleccionado.getAbsolutePath());
            visualizarBoton.setDisable(false);
            treeMap = new LinkedList<>();
            treeMap.add(dir);
            iteration(0,treeMap);
        }else{
            System.out.print("Seleccione un directorio");
        }
        mostrar.getChildren().clear();
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
    
    //Métodos para selección de directorio
    public double recorrerDirectorio(double tot, Directorio dir,File[] contenido) {
        for (File file : contenido) {
            if (file.isFile()) {
                tot +=redondear(file.length() / 1024.0, 2);
                Directorio directorio = new Directorio(file.getName(),redondear(file.length()/1024.0, 2));
                dir.getDirectorios().add(directorio);
            } else {
                Directorio direct = new Directorio(file.getName());
                double size=redondear(recorrerDirectorio(0.0, dir,file.listFiles()),2);
                direct.setTamaño(size);
                dir.getDirectorios().add(dir);
                tot+=size;
            }
        }
        return tot;
    }
    public double redondear(double tam, int decimales) {
        return new BigDecimal(tam).setScale(decimales, RoundingMode.HALF_EVEN).doubleValue();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
