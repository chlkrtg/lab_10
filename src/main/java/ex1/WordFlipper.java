package ex1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WordFlipper extends Application {

    private boolean leftToRight = true; //стандартное направление

    @Override
    public void start(Stage stage) {

        //поле слева
        TextField leftField = new TextField();
        leftField.setPrefWidth(400);

        //поле справа
        TextField rightField = new TextField();
        rightField.setPrefWidth(400);

        //кнопочка
        Button flipButton = new Button("->");
        flipButton.setPrefWidth(100);

        //собираем всё воедино с отступами
        HBox root = new HBox(20, leftField, flipButton, rightField);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        flipButton.setOnAction(e -> {

            TextField source = leftToRight ? leftField : rightField;
            TextField target = leftToRight ? rightField : leftField;

            String text = source.getText();
            if (text.isEmpty()) {
                return;
            }

            //блокируем кнопку от спама
            flipButton.setDisable(true);

            // перенос текста
            target.setText(text);
            source.clear();

            // меняем направление
            leftToRight = !leftToRight;
            flipButton.setText(leftToRight ? "->" : "<-");

            // разблокировка кнопки
            flipButton.setDisable(false);
        });

        stage.setScene(new Scene(root));
        stage.setTitle("Перекидыватель слов");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}