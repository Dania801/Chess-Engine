package chess.GUI;

import chess.engine.Board.Board;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by dania on 12/21/16.
 */
public class JChess extends Application {
    public static void main(String[] args)  {
        Board board = Board.createStandardBoard() ;
            System.out.print(board);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Table.fxml"));
        primaryStage.setTitle("JChess");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }
}
