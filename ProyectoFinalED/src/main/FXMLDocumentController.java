/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Directorio.Directorio;
import java.awt.event.MouseEvent;
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
import javafx.stage.Stage;

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
    private double disX = 0;
    private double disY = 0;
    
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
    private void getVector(MouseEvent event) {
        disX = event.getX();
        disY = event.getY();
    }
    @FXML
    private void moverVentana(MouseEvent event) {
        Stage stage = (Stage) mostrar.getScene().getWindow();
        stage.setX(event.getX() - disX);
        stage.setY(event.getY() - disY);
    }
    
    public boolean isFile(File archivo) {
        return archivo.isFile();
    }
    
    public void setLabelSize(double cantidad,Label label) {
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 15");
        DecimalFormat two = new DecimalFormat("0.00");
        if (cantidad < 1024) {
            label.setText("(" + cantidad + " KB" + ")");
        } else if (cantidad > 1024 && cantidad < 1024 * 1024) {
            label.setText("(" + two.format(cantidad / 1024) + " MB" + ")");
        } else if (cantidad > 1024 * 1024 && cantidad < 1024 * 1024) {
            label.setText("(" + two.format(cantidad / 1024 * 1024) + " GB" + ")");
        } else {
            label.setText("(" + two.format(cantidad / 1024 * 1024 * 1024) + " TB" + ")");
        }
    }
    
    public Color getColorAleatorio() {
        Random rd = new Random();
        float r = rd.nextFloat();
        float g = rd.nextFloat();
        float b = rd.nextFloat();
        Color randomColor = new Color(r, g, b, 1);
        return randomColor;
    }
    
     public double getTamanio(File directorio) {
        double tamanio = 0.0;
        File[] files = directorio.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                tamanio += file.length();
            } else {
                File[] fileB = file.listFiles();
                for (File filel : fileB) {
                    tamanio += filel.length();
                }
            }
        }
        return tamanio;
    }
     
    
}
