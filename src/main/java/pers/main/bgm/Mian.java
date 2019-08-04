package pers.main.bgm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pers.main.bgm.frame.MenuFramePart;
import pers.main.bgm.frame.TabPaneFramePart;

/**
 * Created by 10673 on 2017/11/26.
 */
public class Mian extends Application {
    public final static int SCENE_WIDTH = 1366;
    public final static int SCENE_HEIGHT = 700;
    @Override
    public void start(final Stage stage) throws Exception {
        stage.setTitle("文件操作平台");
        stage.show();
        stage.setResizable(false);
        stage.setMinWidth(SCENE_WIDTH);
        stage.setMinHeight(SCENE_HEIGHT);
        stage.setX(10);
        stage.setY(10);
        stage.setResizable(false);
        VBox vBox = new VBox();
        Scene scene = new Scene(vBox, SCENE_WIDTH, SCENE_HEIGHT, Color.WHITE);
        stage.setScene(scene);
        TabPane mainTabPane = new TabPaneFramePart().initialTabPane(stage);
        MenuBar mainMenuBar = new MenuFramePart().initialMenuBar(stage,mainTabPane);
        vBox.getChildren().add(mainMenuBar);
        vBox.getChildren().add(mainTabPane);
    }
    public static void main(String args[]) {
        Application.launch(args);
    }
}
