package ex5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TextFlag extends Application {

    private HBox createRow(String labelText, ToggleGroup group, String[] colors) {
        HBox row = new HBox(10);
        row.getChildren().add(new Label(labelText));

        for (String color : colors) {
            RadioButton rb = new RadioButton(color);
            rb.setToggleGroup(group);
            row.getChildren().add(rb);
        }

        return row;
    }

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(15);
        root.setPadding(new Insets(20));

        //варианты цветов
        String[] colors = {"Красный", "Зелёный", "Синий", "Белый", "Фиолетовый"};

        //создание полос
        ToggleGroup group1 = new ToggleGroup();
        ToggleGroup group2 = new ToggleGroup();
        ToggleGroup group3 = new ToggleGroup();

        HBox row1 = createRow("Полоса 1:", group1, colors);
        HBox row2 = createRow("Полоса 2:", group2, colors);
        HBox row3 = createRow("Полоса 3:", group3, colors);


        //кнопка "Нарисовать" и метка для результата
        Button drawButton = new Button("Нарисовать");
        Label resultLabel = new Label();

        drawButton.setOnAction(e -> {
            Toggle t1 = group1.getSelectedToggle();
            Toggle t2 = group2.getSelectedToggle();
            Toggle t3 = group3.getSelectedToggle();

            if (t1 != null && t2 != null && t3 != null) {
                String color1 = ((RadioButton) t1).getText();
                String color2 = ((RadioButton) t2).getText();
                String color3 = ((RadioButton) t3).getText();
                resultLabel.setText("Флаг: " + color1 + ", " + color2 + ", " + color3);
            } else {
                resultLabel.setText("Пожалуйста, выберите цвет для всех полос!");
            }
        });

        root.getChildren().addAll(row1, row2, row3, drawButton, resultLabel);

        Scene scene = new Scene(root, 500, 200);
        stage.setScene(scene);
        stage.setTitle("Текстовый флаг");

        //запрет изменения размера окна
        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}