package controller;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;

import fxapp.MainFXApplication;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.Initializable;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import netscape.javascript.JSObject;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class acts as the controller for the GMapFX library that lets a google map be
 * displayed in an FX window.
 *
 * Ideally, we could use fxml, but I could not get it to work with my setup, so I just
 * programatically make the window.
 */

public class MapController implements Initializable, MapComponentInitializedListener {

    /** a gui view provided by the GMapFX library */
    private GoogleMapView mapView;

    /** the actual javascript interface for the google map itself */
    private GoogleMap map;

    /** a reference back to the main application object in case we need something or to transition to another window */
    private MainFXApplication theApp;

    /**remember stage for dialogs */
    private Stage mainStage;

    /**
     * Make a new constructor
     * @param app  reference to the FX application
     * @param stage the stage we want this map to be displayed in
     */
    public MapController(MainFXApplication app, Stage stage) {
        theApp = app;
        mainStage = stage;
        setUpMapView(stage);
    }

    /**
     * Construct the google map, set up the different parts of the layout
     *
     * @param stage the stage to put the map scene into
     */
    private void setUpMapView(Stage stage) {
        //construct the view
        mapView = new GoogleMapView();
        //we cannot do anything until the map is constructed, so we need a callback
        //this is provided by the listener.  this class implements the MapComponentInitializedListener
        //interface
        mapView.addMapInializedListener(this);

        //Create the top level layout
        BorderPane bp = new BorderPane();


        //put the map into the center of the border layout
        bp.setCenter(mapView);

        //put the map into the scene
        Scene scene = new Scene(bp);
        stage.setScene(scene);
    }


    @Override
    public void mapInitialized() {
        mapView.addMapInializedListener(this);

        //Set the initial properties of the map

        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(33.7, -84.3);

        options.center(center)
                .zoom(9)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.TERRAIN);

        map = mapView.createMap(options);

      //  borderLayout.setCenter(mapView);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

}
