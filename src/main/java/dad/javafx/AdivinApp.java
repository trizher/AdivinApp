package dad.javafx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	private TextField comprobarText;
	private Button comprobarButton;
	private Label resultadoLabel;
	private int intentos;
	private int numSecreto;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		generarNumAleatorio();
		intentos = 0;
		
		resultadoLabel = new Label();
		resultadoLabel.setText("Introduce un número del 1 al 100");
		
		comprobarText = new TextField();
		comprobarText.setMaxWidth(150);
		
		comprobarButton = new Button("Comprobar");
		comprobarButton.setDefaultButton(true);
		comprobarButton.setOnAction(e -> onComprobarButtonAction(e));
		
		VBox root = new VBox();
		Scene scene = new Scene(root, 420, 200);
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(resultadoLabel, comprobarText, comprobarButton);
		
		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	private void generarNumAleatorio() {
		numSecreto = (int)(Math.random() * 100) + 1;
	}

	private void onComprobarButtonAction(ActionEvent e) {
		String texto = comprobarText.getText();
		String mensaje = "";
		
		AlertType tipo = null;
		String titulo = "AdivinApp";
		String cabecera = "";
		String contenido = "";
		
		Pattern p = Pattern.compile("^[0-9]+$");
	    Matcher m = p.matcher(texto);
	    
	    intentos++;
	    if (!m.matches()) {
	    	 tipo = AlertType.ERROR;
	    	 cabecera = "Error";
	    	 contenido = "Debe introducir un número";
	    } else {
	    	int num = Integer.parseInt(texto);
	    	if(numSecreto == num) {
	    		tipo = AlertType.INFORMATION;
		    	cabecera = "¡¡Has acertado!!";
		    	contenido = "Has necesitado " + intentos + " intentos.\nVuelve a jugar y hazlo mejor";
		    	//Reseteo de datos para comenzar nueva partida
				generarNumAleatorio();
				intentos = 0;
				comprobarText.setText("");
	    	} else {
	    		if (num < numSecreto) {
	    			mensaje = num + " es menor que el número secreto";
	    		} else {
	    			mensaje = num + " es mayor que el número secreto";
	    		}
	    		tipo = AlertType.WARNING;
		    	cabecera = "¡¡Has fallado!!";
		    	contenido = mensaje + ".\nVuelve a intentarlo";
	    	}
	    }
	    
	    Alert alert = new Alert(tipo);
	    alert.setTitle(titulo + numSecreto);
	    alert.setHeaderText(cabecera);
	    alert.setContentText(contenido);

	    alert.showAndWait();
	}

	public static void main(String[] args) {
		launch();

	}

}
