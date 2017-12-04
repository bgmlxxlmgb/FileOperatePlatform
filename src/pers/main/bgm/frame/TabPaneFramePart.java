package pers.main.bgm.frame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.ProgressBarTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pers.main.bgm.entity.MatchedResultEntity;

import java.io.*;

/**
 * Created by 10673 on 2017/11/26.
 */
public class TabPaneFramePart {
    public TabPane initialTabPane(final Stage stage) {
        TabPane tabPane = new TabPane();

        Tab contentMatch = new Tab();
        contentMatch.setText("内容匹配");

        Label inputHeader = new Label("  请输入需要匹配的内容：");
        inputHeader.setPrefSize(300, 50);
        final TextArea contentInput = new TextArea();
        contentInput.setMaxWidth(1200);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("请选择文件：");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );

        Button choiceFile = new Button("选择文件");
        choiceFile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All Type File", "*.*");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    BufferedReader br = null;
                    StringBuffer fileContent = new StringBuffer();
                    try {
                        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                        String value = "";
                        while ((value = br.readLine()) != null) {
                            fileContent.append(value + "\n");
                        }
                        br.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    contentInput.clear();
                    contentInput.appendText(fileContent.toString());
                    System.out.println(file);
                }
            }
        });

        Button clearContent = new Button("清空内容");
        clearContent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                contentInput.clear();
            }
        });

        // CheckBox
        CheckBox checkBox = new CheckBox("Check Box");
        //root.getChildren().add(checkBox);
        // RadioButton
        RadioButton radioButton = new RadioButton("Radio Button");
        //root.getChildren().add(radioButton);

        VBox mainBox = new VBox();
        VBox manualInput = new VBox();
        manualInput.getChildren().add(contentInput);

        VBox toolsInput = new VBox(15);
        toolsInput.setAlignment(Pos.BASELINE_CENTER);
        toolsInput.getChildren().add(choiceFile);
        toolsInput.getChildren().add(clearContent);
        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        ColumnConstraints column1 = new ColumnConstraints(1200);
        ColumnConstraints column2 = new ColumnConstraints(100);
        gridpane.getColumnConstraints().addAll(column1, column2);
        gridpane.add(manualInput, 0, 0);
        gridpane.add(toolsInput, 1, 0);
        mainBox.setPadding(new Insets(3));
        mainBox.getChildren().add(inputHeader);
        mainBox.getChildren().add(gridpane);
        mainBox.getChildren().add(new Separator());

        //匹配到的内容进行显示
        Label showMatched = new Label("匹配到的内容：");
        showMatched.setPrefSize(300, 50);
        VBox tableShow = new VBox();
        TableView<MatchedResultEntity> resultTableView=new TableView<>();
        TableColumn title_1 = new TableColumn("匹配内容");title_1.setPrefWidth(200);
        TableColumn title_2 = new TableColumn("命中内容所在行数");title_2.setPrefWidth(100);
        TableColumn title_3 = new TableColumn("命中内容所在路径");title_3.setPrefWidth(200);
        TableColumn title_4 = new TableColumn("命中文件名称");title_4.setPrefWidth(200);
        TableColumn title_5 = new TableColumn("命中文件内容");title_5.setPrefWidth(647);
        resultTableView.setMaxWidth(1350);
        resultTableView.setMaxHeight(200);
        resultTableView.getColumns().addAll(title_1,title_2,title_3,title_4,title_5);

        final ObservableList<MatchedResultEntity> data = FXCollections.observableArrayList();
        for(int i=0;i<1000;i++){
            data.add(new MatchedResultEntity("a"+i,"b"+i,"c"+i,"d"+i,"e"+i));
        }
        title_1.setCellValueFactory(new PropertyValueFactory<MatchedResultEntity, String>("matchRegion"));
        title_2.setCellValueFactory(new PropertyValueFactory<MatchedResultEntity, String>("matchedLineNumber"));
        title_3.setCellValueFactory(new PropertyValueFactory<MatchedResultEntity, String>("matchedFilePath"));
        title_4.setCellValueFactory(new PropertyValueFactory<MatchedResultEntity, String>("matchedFileName"));
        title_5.setCellValueFactory(new PropertyValueFactory<MatchedResultEntity, String>("matchedValue"));
        resultTableView.setItems(data);
        tableShow.getChildren().addAll(showMatched,resultTableView);

        //开始按钮
        VBox orderBox = new VBox();
        Button startMatch = new Button("开始匹配");
        Button saveFile = new Button("保存结果");
        GridPane gridpane_01 = new GridPane();
        //gridpane_01.setGridLinesVisible(true);
        gridpane_01.setPadding(new Insets(5));
        ColumnConstraints column1_1 = new ColumnConstraints(130);
        ColumnConstraints column2_1 = new ColumnConstraints(130);
        ColumnConstraints column3_1 = new ColumnConstraints(130);
        ColumnConstraints column4_1 = new ColumnConstraints(130);
        ColumnConstraints column5_1 = new ColumnConstraints(130);
        ColumnConstraints column6_1 = new ColumnConstraints(130);
        ColumnConstraints column7_1 = new ColumnConstraints(130);
        ColumnConstraints column8_1 = new ColumnConstraints(130);
        ColumnConstraints column9_1 = new ColumnConstraints(130);column9_1.setHalignment(HPos.RIGHT);
        ColumnConstraints column0_1 = new ColumnConstraints(130);column0_1.setHalignment(HPos.RIGHT);
        gridpane_01.getColumnConstraints().addAll(column1_1, column2_1, column3_1, column4_1, column5_1,column6_1, column7_1, column8_1, column9_1, column0_1);
        gridpane_01.add(new Label(), 0, 0);
        gridpane_01.add(new Label(), 0, 1);
        gridpane_01.add(new Label(), 0, 2);
        gridpane_01.add(new Label(), 0, 3);
        gridpane_01.add(startMatch, 8, 4);
        gridpane_01.add(saveFile, 9, 4);
        orderBox.getChildren().add(gridpane_01);

        //汇总添加模板
        mainBox.getChildren().add(tableShow);
        mainBox.getChildren().add(new Separator());
        mainBox.getChildren().add(orderBox);
        mainBox.setSpacing(5);
        contentMatch.setContent(mainBox);
        tabPane.getSelectionModel().select(0);
        tabPane.getTabs().addAll(contentMatch);
        return tabPane;
    }
}
