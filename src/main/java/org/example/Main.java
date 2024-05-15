package org.example;

import javax.swing.*;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException {

      /*  String choice = JOptionPane.showInputDialog(null,"Hola");*/

        JDialog.setDefaultLookAndFeelDecorated(true);
        Object[] selectionValues = { "Service Cat", "Salir"};
        Object selection = JOptionPane.showInputDialog(null, "Please, Select one option to Administrate",
                "Cat Api System", JOptionPane.WARNING_MESSAGE, null, selectionValues, selectionValues[0]);
        System.out.println(selection);
        if (selection != null){
            switch (selection.toString()){
                case "Service Cat":
                    CatService.seeCats();
                    break;
                case "salir":
                    break;
            }
        }else{
            System.out.println("Fue Nulo");
        }

    }
}