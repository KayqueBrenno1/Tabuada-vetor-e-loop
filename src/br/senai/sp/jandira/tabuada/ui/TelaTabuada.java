package br.senai.sp.jandira.tabuada.ui;

import br.senai.sp.jandira.tabuada.model.Usuario;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Optional;

public class TelaTabuada extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Definir tamanho da tela
        //stage.setWidth(500);
        //stage.setHeight(500);
        stage.setTitle("Tabuada");
        stage.setResizable(false);

        //Criar o root - que é o componente de leiaute principal
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #555858;");

        //Criamos a cena e colocamos dentro o root dentro dela
        Scene scene = new Scene(root);

        //Criação do header da tela
        VBox header = new VBox();
        //header.setPrefHeight(100);
        header.setStyle("-fx-background-color: #7b7c80;");

        //colocar o conteúdo do header
        Label lbltitulo = new Label("Tabuada");
        lbltitulo.setStyle("-fx-text-fill: white;-fx-font-size: 20px;-fx-font-weight: bold;");
        lbltitulo.setPadding(new Insets(8, 8, 0, 8));

        //Ajuste do subtitulo
        Label lblSubtitulo = new Label("Crie um tipo de tabuada.");
        lblSubtitulo.setStyle("-fx-text-fill: white;");
        lblSubtitulo.setPadding(new Insets(8, 8, 8, 8));

        //Colocar os labels dentro do header
        header.getChildren().addAll(lbltitulo,lblSubtitulo);

        //criar grid de formulario
        GridPane gridFormulario = new GridPane();
        gridFormulario.setVgap(10);
        gridFormulario.setHgap(10);
        gridFormulario.setPadding(new Insets(16, 8, 16, 8));
        //gridFormulario.setPrefHeight(100);
        gridFormulario.setStyle("-fx-background-color: #737373;");

        //Colocando o conteúdo do gridFormulario
        Label lblMultiplicando = new Label("Multiplicando:");
        lblMultiplicando.setStyle("-fx-text-fill: white;");
        TextField tfMultiplicando = new TextField();

        Label lblMenorMultiplicador = new Label("Menor Multiplicador:");
        lblMenorMultiplicador.setStyle("-fx-text-fill: white;");
        TextField tfMenorMultiplicador = new TextField();

        Label lblMaiorMultiplicador = new Label("Maior Multiplicador:");
        lblMaiorMultiplicador.setStyle("-fx-text-fill: white;");
        TextField tfMaiorMultiplicador = new TextField();

        //Colocar os componentes no grid
        gridFormulario.add(lblMultiplicando,0,0);
        gridFormulario.add(tfMultiplicando,1,0);
        gridFormulario.add(lblMenorMultiplicador,0,1);
        gridFormulario.add(tfMenorMultiplicador,1,1);
        gridFormulario.add(lblMaiorMultiplicador,0,2);
        gridFormulario.add(tfMaiorMultiplicador,1,2);

        //criar a caixa dos botões
        Pane paneButtons = new Pane();
        //paneButtons.setPadding(new Insets(16, 0, 16, 8));

        HBox boxBotoes = new HBox();
        boxBotoes.setPadding(new Insets(8));
        boxBotoes.setSpacing(10);
        paneButtons.getChildren().add(boxBotoes);
        //boxBotoes.setPrefHeight(100);
        boxBotoes.setStyle("-fx-background-color: #535252;");

        //Colocando o conteúdo do boxBotoes
        Button btCalcular = new Button("Calcular");
        Button btLimpar = new Button("Limpar");
        Button btSair = new Button("Sair");

        //Colar os componentes no botão
        boxBotoes.getChildren().add(btCalcular);
        boxBotoes.getChildren().add(btLimpar);
        boxBotoes.getChildren().add(btSair);

        //Criar a caixa de resultado
        VBox boxResultados = new VBox();
        //boxResultados.setPrefHeight(300);
        boxResultados.setStyle("-fx-background-color: #5c5c5a;");

        //colocando o conteúdo do boxResultado
        Label lblResultados = new Label("Resultados:");
        lblResultados.setPadding(new Insets(8, 8, 8, 8));
        lblResultados.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;");
        ListView listaTabuada = new ListView();
        listaTabuada.setStyle("-fx-background-color: #5c5c5a;");
        listaTabuada.setPadding(new Insets(8));
        listaTabuada.setPrefHeight(200);
        listaTabuada.setPrefWidth(100);

        //Colocar os componentes do boxResultados
        boxResultados.getChildren().add(lblResultados);
        boxResultados.getChildren().add(listaTabuada);

        //Adicionar componentes ao root
        root.getChildren().add(header);
        root.getChildren().add(gridFormulario);
        root.getChildren().add(paneButtons);
        root.getChildren().add(boxResultados);


        //Colocamos a cena no palco
        stage.setScene(scene);

        stage.show();

        btCalcular.setOnAction(e -> {
            Usuario tabuada = new Usuario();

            tabuada.multiplicando =
                    Integer.parseInt(tfMultiplicando.getText()); //Transforma a String em Int

            tabuada.multiplicadorInicial =
                    Integer.parseInt(tfMenorMultiplicador.getText());

            tabuada.multiplicadorFinal =
                    Integer.parseInt(tfMaiorMultiplicador.getText());

            String[] resultado = tabuada.calcularTabuada();
            listaTabuada.getItems().clear();
            listaTabuada.getItems().addAll(resultado);

            //Gravar os dados da tabuada em arquivo
            Path arquivo = Path.of("c:\\Users\\25203698\\ds1t\\tabuada\\dados_tabuada.csv");

            String dados = tfMultiplicando.getText() + ";" + tfMenorMultiplicador.getText() + ";" + tfMaiorMultiplicador.getText() + ";" + LocalDateTime.now() + "\n";

            try{
                Files.writeString(arquivo, dados, StandardOpenOption.APPEND);
            }catch (IOException erro){
                System.out.println(erro.getMessage());
            }

        });
        btLimpar.setOnAction(e -> {
            tfMultiplicando.clear();
            tfMenorMultiplicador.clear();
            tfMaiorMultiplicador.clear();
            listaTabuada.getItems().clear();
            tfMultiplicando.requestFocus();
        });
        btSair.setOnAction(e -> {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "Sair da calculadora de tabuadas?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> resposta = alerta.showAndWait();

            if (resposta.get() == ButtonType.YES) {
                System.exit(0);
            }
        });
    }
}