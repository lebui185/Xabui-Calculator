package core;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import scene.MainController;


public class Main extends Application {

    public static Calculator calculator = new Calculator();
    public static MainController mainController = new MainController();

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/scene/mainscene.fxml"));
        loader.setController(mainController);
        Parent mainScreen = loader.load();

        mainController.setMainStage(primaryStage);
        primaryStage.setTitle("Xabui Calculator");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/icon.png")));
        primaryStage.setScene(new Scene(mainScreen));
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
