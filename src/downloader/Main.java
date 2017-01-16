package downloader;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {


    private class Downloader extends Task<Void> {

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
            String extension = url.substring(url.lastIndexOf("."), url.length());
            URLConnection urlConnection = new URL(url).openConnection();
            long fileLength = urlConnection.getContentLength();

            /**
             * try with resources:
             * InputStream is opened, and OutputStream is used to write the file
             */
            try(InputStream inputStream = urlConnection.getInputStream();
                OutputStream outputStream = Files.newOutputStream(Paths.get("filedownloaded" + extension))) {

                long fileRead = 0L;
                byte[] buffer = new byte[10000];
                int count;

                while((count = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer,0,count);
                    fileRead += count;
                    //updateProgress(fileRead, fileLength);
                }
            }

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
