package chess.GUI;

import chess.engine.Board.Board;
import chess.engine.Board.BoardUtils;
import chess.engine.Board.Tile;
import chess.engine.Pieces.Piece;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static java.awt.event.MouseEvent.MOUSE_CLICKED;
import static javax.swing.SwingUtilities.*;


/**
 * Created by dania on 2/26/17.
 */
public class TableController {
    //GUI declaration section
    public MenuBar tableMenuBar;
    public Menu fileMenu;
    public MenuItem openPGN;
    public MenuItem Exit;
    public GridPane gridPane;
    public Pane tile_0;
    public Pane tile_1;
    public Pane tile_2;
    public Pane tile_3;
    public Pane tile_4;
    public Pane tile_5;
    public Pane tile_6;
    public Pane tile_7;
    public Pane tile_8;
    public Pane tile_9;
    public Pane tile_10;
    public Pane tile_11;
    public Pane tile_12;
    public Pane tile_13;
    public Pane tile_14;
    public Pane tile_15;
    public Pane tile_16;
    public Pane tile_17;
    public Pane tile_18;
    public Pane tile_19;
    public Pane tile_20;
    public Pane tile_21;
    public Pane tile_22;
    public Pane tile_23;
    public Pane tile_24;
    public Pane tile_25;
    public Pane tile_26;
    public Pane tile_27;
    public Pane tile_28;
    public Pane tile_29;
    public Pane tile_30;
    public Pane tile_31;
    public Pane tile_32;
    public Pane tile_33;
    public Pane tile_34;
    public Pane tile_35;
    public Pane tile_36;
    public Pane tile_37;
    public Pane tile_38;
    public Pane tile_39;
    public Pane tile_40;
    public Pane tile_41;
    public Pane tile_42;
    public Pane tile_43;
    public Pane tile_44;
    public Pane tile_45;
    public Pane tile_46;
    public Pane tile_47;
    public Pane tile_48;
    public Pane tile_49;
    public Pane tile_50;
    public Pane tile_51;
    public Pane tile_52;
    public Pane tile_53;
    public Pane tile_54;
    public Pane tile_55;
    public Pane tile_56;
    public Pane tile_57;
    public Pane tile_58;
    public Pane tile_59;
    public Pane tile_60;
    public Pane tile_61;
    public Pane tile_62;
    public Pane tile_63;

    //Logical Board declaration section .
    public Board gameBoard ;
    public Tile sourceTile = null ;
    public Tile destinationTile = null ;
    public Piece humanMovedPiece = null ;



    @FXML
    public void initialize() {
        gameBoard = Board.createStandardBoard() ;
    }

    @FXML
    public void exitAction(ActionEvent actionEvent) {
        System.exit(0);
    }


    public int getTileID(Object pane)
    {
        String tilePane = pane.toString() ;
        String[] parts = tilePane.split("_", 2);
        String tileId = parts[1];
        int id = Integer.parseInt(tileId.substring(0,tileId.length()-1));

        return id ;
    }



}
