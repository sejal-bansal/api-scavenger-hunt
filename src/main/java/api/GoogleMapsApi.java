package api;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GoogleMapsApi extends Application {
    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String HTML_CONTENT =
            "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>Google Maps Example</title>\n" +
                    "    <script src=\"https://maps.googleapis.com/maps/api/js?key=%s\"></script>\n" +
                    "    <script>\n" +
                    "        function initialize() {\n" +
                    "            var mapOptions = {\n" +
                    "                center: new google.maps.LatLng(40.712776, -74.005974),\n" +
                    "                zoom: 8\n" +
                    "            };\n" +
                    "            var map = new google.maps.Map(document.getElementById(\"map-canvas\"), mapOptions);\n" +
                    "\n" +
                    "            var directionsService = new google.maps.DirectionsService();\n" +
                    "            var directionsRenderer = new google.maps.DirectionsRenderer();\n" +
                    "            directionsRenderer.setMap(map);\n" +
                    "\n" +
                    "            var request = {\n" +
                    "                origin: 'San Francisco, USA',\n" +
                    "                destination: 'Los Angeles, USA',\n" +
                    "                travelMode: 'DRIVING'\n" +
                    "            };\n" +
                    "            directionsService.route(request, function(result, status) {\n" +
                    "                if (status == 'OK') {\n" +
                    "                    directionsRenderer.setDirections(result);\n" +
                    "                }\n" +
                    "            });\n" +
                    "        }\n" +
                    "    </script>\n" +
                    "</head>\n" +
                    "<body onload=\"initialize()\">\n" +
                    "    <div id=\"map-canvas\" style=\"width:800px;height:600px;\"></div>\n" +
                    "</body>\n" +
                    "</html>";


    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(String.format(HTML_CONTENT, API_KEY));

        Scene scene = new Scene(webView, 800, 600);

        primaryStage.setTitle("Google Maps Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

