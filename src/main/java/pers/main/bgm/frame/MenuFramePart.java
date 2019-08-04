package pers.main.bgm.frame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Created by 10673 on 2017/11/26.
 */
public class MenuFramePart {

    public void listenNewMenuItem(MenuItem newMenuItem,final Stage stage,final TabPane mainTabPane){
        newMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Tab currentTab = new TabPaneFramePart().initialSingleTab(stage,mainTabPane);
                mainTabPane.getTabs().addAll(currentTab);
                mainTabPane.getSelectionModel().select(currentTab);
            }
        });
    }

    public MenuBar initialMenuBar(final Stage root,final TabPane mainTabPane){
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("文件");
        MenuItem newMenuItem = new MenuItem("新建");
        MenuItem saveMenuItem = new MenuItem("保存");
        MenuItem saveOtherMenuItem = new MenuItem("另存为");
        MenuItem exitMenuItem = new MenuItem("退出");
        exitMenuItem.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        fileMenu.getItems().addAll(newMenuItem, saveMenuItem,saveOtherMenuItem,new SeparatorMenuItem(), exitMenuItem);

        Menu codeMenu = new Menu("编码");
        MenuItem utf8MenuItem = new MenuItem("转换成UTF-8编码");
        MenuItem asciiMenuItem = new MenuItem("转换成ASCII编码");
        MenuItem unicodeMenuItem = new MenuItem("转换成Unicode编码");
        MenuItem gbkMenuItem = new MenuItem("转换成GBK编码");
        MenuItem gb2312MenuItem = new MenuItem("转换成GB2312编码");
        codeMenu.getItems().addAll(utf8MenuItem,asciiMenuItem,unicodeMenuItem,gbkMenuItem,gb2312MenuItem);
        menuBar.getMenus().addAll(fileMenu,codeMenu);


        listenNewMenuItem(newMenuItem,root,mainTabPane);
        return menuBar;
    }
}
