package ex4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Calculator extends Application {

    private double currentResult = 0;
    private boolean isFirstInput = true; //чтобы знать, когда ввод первого числа

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        TextField inputField = new TextField();
        inputField.setPromptText("Введите число: ");
        inputField.setPrefWidth(200);

        Label resultLabel = new Label("Результат: 0.0");

        //операции
        Button addButton = new Button("+");
        Button subButton = new Button("-");
        Button mulButton = new Button("*");
        Button divButton = new Button("/");

        //очистка
        Button clearButton = new Button("C");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(addButton, 0, 0);
        grid.add(subButton, 1, 0);
        grid.add(mulButton, 0, 1);
        grid.add(divButton, 1, 1);
        grid.add(clearButton, 0, 2);

        //задаём размеры кнопок
        grid.getChildren().forEach(node -> {
            if (node instanceof Button) {
                ((Button) node).setPrefWidth(100);
            }
        });

        addButton.setOnAction(e -> calculate(inputField, resultLabel, '+'));
        subButton.setOnAction(e -> calculate(inputField, resultLabel, '-'));
        mulButton.setOnAction(e -> calculate(inputField, resultLabel, '*'));
        divButton.setOnAction(e -> calculate(inputField, resultLabel, '/'));
        clearButton.setOnAction(e -> {
            currentResult = 0;
            isFirstInput = true;
            resultLabel.setText("Результат: 0.0");
            inputField.clear();
        });

        root.getChildren().addAll(inputField, grid, resultLabel);

        Scene scene = new Scene(root, 250, 200);
        stage.setScene(scene);
        stage.setTitle("Калькулятор");
        stage.show();
    }

    private void calculate(TextField inputField, Label resultLabel, char op) {
        String text = inputField.getText();
        double num;
        try {
            num = Double.parseDouble(text); //строка в число
        } catch (NumberFormatException e) {
            showAlert("Ошибка", "Введите корректное число!");
            return;
        }

        if (isFirstInput) {
            currentResult = num;
            isFirstInput = false;
        } else {
            switch (op) {
                case '+': {
                    currentResult += num;
                } break;
                case '-': {
                    currentResult -= num;
                } break;
                case '*': {
                    currentResult *= num;
                } break;
                case '/':
                    if (num == 0) {
                        showAlert("Ошибка", "Деление на 0 невозможно!");
                        return;
                    }
                    currentResult /= num; break;
            }
        }

        resultLabel.setText("Результат: " + currentResult);
        inputField.clear();
    }

    //предупреждения
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}