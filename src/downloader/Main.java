package downloader;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    private class Donwloader extends Task<Void> {

        //URL of the file to be downloaded
        private String url;

        //Constructor of the Downloader class that receives url as an argument
        public Downloader(String url) {
            this.url = url;
        }


        /**
         * call method is overriden which:
         * Receives the extension of the file to be downloaded
         * Opens the url connection to the file link
         * Determines the length of the file
         * Reads the file and writes it in the directory provided
         * Updates the progressbar with respect to the size of bytes downloaded
         *
         * @return
         * @throws Exception
         */
        @Override
        protected Void call() throws Exception {
            return null;
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("downloader_ui.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
