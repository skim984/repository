package fxapp;

import controller.WelcomeScreenController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainFXApplication extends Application {
    /** the java logger for this class */
    private static final Logger LOGGER = Logger.getLogger("MainFXApplication");



    /** the main container for the application window */
    private Stage mainScreen;

    /** the main layout for the main window */
    private BorderPane rootLayout;

    /**
     * return a reference to the main window stage
     * @return reference to main stage
     * */
    public Stage getMainScreen() {
        return mainScreen;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
    }

    /**
     * Initialize the main screen for the application.  Most other views will be shown in this screen.
     *
     * @param mainScreen  the main Stage window of the application
     */
    private void initRootLayout(Stage mainScreen) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../view/WelcomeScreen.fxml"));
            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            mainScreen.setScene(scene);
            // Set the Main App title
            mainScreen.setTitle("Welcome!");
            mainScreen.show();

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
