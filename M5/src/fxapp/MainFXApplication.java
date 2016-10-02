package fxapp;

import controller.LoginScreenController;
import controller.MainScreenController;
import controller.RegisterScreenController;
import controller.WelcomeScreenController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
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
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/WelcomeScreen.fxml"));
            AnchorPane welcomeLayout = loader.load();

            // Give the controller access to the Login app.
            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);


            // Show the scene containing the root layout.
            Scene scene = new Scene(welcomeLayout);
            mainScreen.setScene(scene);
            // Set the Main App title
            mainScreen.setTitle("Welcome!");
            mainScreen.show();

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for WelcomeScreen!!");
            e.printStackTrace();
        }
    }

    public void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/LoginScreen.fxml"));
            AnchorPane page = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(page);
            mainScreen.setScene(scene);

            //Give the controller access to the main app.
            LoginScreenController controller = loader.getController();
            controller.setMainApp(this);

            // Set the Main App title
            mainScreen.setTitle("Log In!");
            mainScreen.show();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for LoginScreen!!");
            e.printStackTrace();
        }
    }

    public void showMain() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/MainScreen.fxml"));
            rootLayout = loader.load();

            //Give the controller access to the main app.
            MainScreenController controller = loader.getController();
            controller.setMainApp(this);
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.setTitle("Main Page");
            mainScreen.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for MainScreen!!");
            e.printStackTrace();
        }
    }

    public void showRegister() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/RegisterScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            RegisterScreenController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for RegisterScreen!!");
            e.printStackTrace();
        }
    }

    public void showWelcome() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/WelcomeScreen.fxml"));
            AnchorPane welcomeLayout = loader.load();

            // Give the controller access to the Login app.
            WelcomeScreenController controller = loader.getController();
            controller.setMainApp(this);


            // Show the scene containing the root layout.
            Scene scene = new Scene(welcomeLayout);
            mainScreen.setScene(scene);
            // Set the Main App title
            mainScreen.setTitle("Welcome!");
            mainScreen.show();

        } catch (IOException e) {
            //error on load, so log it
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for WelcomeScreen!!");
            e.printStackTrace();
        }
    }

    public void showProfile() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainFXApplication.class.getResource("../view/RegisterScreen.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Student");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainScreen);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            RegisterScreenController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to find the fxml file for RegisterScreen!!");
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
