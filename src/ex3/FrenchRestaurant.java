package ex3;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class Dish {
    private String name;
    private double price;

    Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    String getName() {
        return name;
    }

    double getPrice() {
        return price;
    }
}

public class FrenchRestaurant extends Application {

    @Override
    public void start(Stage stage) {

        //список блюд
        Dish[] dishes = {
                new Dish("Пицца", 500),
                new Dish("Суп", 100),
                new Dish("Салат", 200),
                new Dish("Чай (чёрный)", 150),
                new Dish("Чай (белый)", 150),
                new Dish("Кофе", 250)
        };

        VBox root = new VBox(10);
        root.setPadding(new Insets(20));

        //для каждого блюда создаём строку с чекбоксом и полем для количества
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        //и сохраняем чекбоксы и количество отдельных блюд
        CheckBox[] dishCheckboxes = new CheckBox[dishes.length];
        TextField[] qtyFields = new TextField[dishes.length];

        for (int i = 0; i < dishes.length; i++) {
            Dish dish = dishes[i];
            CheckBox cb = new CheckBox(dish.getName() + " (" + dish.getPrice() + " руб.)");
            TextField qty = new TextField();
            qty.setPrefWidth(50);
            qty.setText("1"); //по умолчанию 1 порция
            qty.setDisable(true); //блокируем, пока блюдо не выбрано

            cb.setOnAction(e -> qty.setDisable(!cb.isSelected()));

            dishCheckboxes[i] = cb;
            qtyFields[i] = qty;

            grid.add(cb, 0, i);
            grid.add(new Label("Количество:"), 1, i);
            grid.add(qty, 2, i);
        }

        //кнопка расчёта чека
        Button calculateButton = new Button("Рассчитать чек");
        TextArea receiptArea = new TextArea();
        receiptArea.setEditable(false);
        receiptArea.setPrefHeight(200);

        calculateButton.setOnAction(e -> {
            receiptArea.clear();

            double total = 0;

            for (int i = 0; i < dishes.length; i++) {
                if (dishCheckboxes[i].isSelected()) {
                    int qty;
                    try {
                        qty = Integer.parseInt(qtyFields[i].getText()); //строка -> целое
                        if (qty <= 0) { //проверка на дурака
                            qty = 1;
                            qtyFields[i].setText("1"); //значение по умолчанию
                            receiptArea.appendText(dishes[i].getName() + ": неверное количество. Установлено значение по умолчанию (1).\n");
                        }
                    } catch (NumberFormatException ex) { //ещё проверка
                        qty = 1;
                        qtyFields[i].setText("1"); //значение по умолчанию
                        receiptArea.appendText(dishes[i].getName() + ": неверное количество. Установлено значение по умолчанию (1).\n");
                    }

                    double cost = qty * dishes[i].getPrice();
                    receiptArea.appendText(dishes[i].getName() + " x" + qty + " = " + String.format("%.2f", cost) + " руб. \n");
                    total += cost;
                }
            }
            receiptArea.appendText("--------------------------\n");
            receiptArea.appendText("Итого: " + String.format("%.2f", total) + " руб.");
        });

        root.getChildren().addAll(grid, calculateButton, receiptArea);

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Заказ в ресторане");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}