package controller;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import fxapp.MainFXApplication;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;


public class MapController implements Initializable, MapComponentInitializedListener {

    private GoogleMapView mapView;
    private GoogleMap map;
    private MainFXApplication application;
    private Stage mainStage;

    /**
     * Make a new constructor
     * @param app  reference to the FX application
     * @param stage the stage we want this map to be displayed in
     */
    public MapController(MainFXApplication app, Stage stage) {
        application = app;
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

        mapView.addMapInializedListener(this);
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

        MapOptions options = new MapOptions();

        //set up the center location for the map
        LatLong center = new LatLong(33.7490, -84.3880);

        options.center(center)
                .zoom(13)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .mapType(MapTypeIdEnum.ROADMAP);

        map = mapView.createMap(options);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

}
