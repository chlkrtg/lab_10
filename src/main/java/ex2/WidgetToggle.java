package ex2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class WidgetToggle extends Application {

    @Override
    public void start(Stage stage) {

        //виджеты
        Label label = new Label("Лейбл");
        Button button = new Button("Нажми на меня");
        TextField textField = new TextField("Текстовое поле");

        //чекбоксы для показа / скрытия
        CheckBox showLabel = new CheckBox("Показать лейбл");
        showLabel.setSelected(true);
        CheckBox showButton = new CheckBox("Показать кнопку");
        showButton.setSelected(true);
        CheckBox showTextField = new CheckBox("Показать текстовое поле");
        showTextField.setSelected(true);

        //обработчики для чекбоксов
        showLabel.setOnAction(e -> label.setVisible(showLabel.isSelected()));
        showButton.setOnAction(e -> button.setVisible(showButton.isSelected()));
        showTextField.setOnAction(e -> textField.setVisible(showTextField.isSelected()));

        //размещение элементов в "матрице" (для удобства положения элдементов)
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setHgap(20);
        grid.setVgap(10);

        //строка 0: виджеты
        grid.add(label, 0, 0);
        grid.add(button, 1, 0);
        grid.add(textField, 2, 0);

        //строка 1: чекбоксы
        grid.add(showLabel, 0, 1);
        grid.add(showButton, 1, 1);
        grid.add(showTextField, 2, 1);

        Scene scene = new Scene(grid, 600, 150);
        stage.setScene(scene);
        stage.setTitle("Показ / скрытие виджетов через чекбоксы");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}