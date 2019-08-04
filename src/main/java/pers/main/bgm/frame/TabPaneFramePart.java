package pers.main.bgm.frame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.*;
import pers.main.bgm.entity.MatchedResultEntity;

import java.io.*;

/**
 * Created by 10673 on 2017/11/26.
 */
public class TabPaneFramePart {


    public final static TabPane tabPane = new TabPane();

    public void setSaveFileAction(Button btn, final Stage stage) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            //@Override
            public void handle(ActionEvent arg0) {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All Type File", "*.*");
                fileChooser.getExtensionFilters().add(extFilter);
                fileChooser.setTitle("保存扫描结果");
                File file = fileChooser.showSaveDialog(stage);
                if (file != null) {
                    System.out.println(file.getAbsolutePath());
                }
            }
        });
    }

    public void setDirAction(Button btn, final Stage stage,final TextField dirShow) {
        btn.setOnAction(new EventHandler<ActionEvent>() {
            //@Override
            public void handle(ActionEvent arg0) {
                DirectoryChooserBuilder builder = DirectoryChooserBuilder.create();
                builder.title("选择扫描目录");
                String cwd = System.getProperty("user.dir");
                File file = new File(cwd);
                builder.initialDirectory(file);
                DirectoryChooser chooser = builder.build();
                File chosenDir = chooser.showDialog(stage);
                if (chosenDir != null) {
                    dirShow.clear();
                    dirShow.appendText(chosenDir.getAbsolutePath());
                } else {
                    System.out.print("未选择任何目录！");
                }
            }
        });
    }

    public void chooseMatchedFile(Button btn, final Stage stage,final TextArea contentInput){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            //@Override
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
                }
            }
        });
    }

    public void startMatch(Button btn, final Stage stage,final ObservableList<MatchedResultEntity> data,final TextArea contentInput,final CheckBox fileType_1,final CheckBox fileType_2,final CheckBox fileType_3,final CheckBox fileType_4,final CheckBox fileType_5,final TextField manualTargetDir){
        btn.setOnAction(new EventHandler<ActionEvent>() {
            //@Override
            public void handle(ActionEvent arg0) {
                if(contentInput.getText()!=null && contentInput.getText()!="" && contentInput.getText()!=" "){
                    contentInput.setFocusTraversable(true);
                    Stage window = new Stage();
                    window.setTitle("title");
                    //modality要使用Modality.APPLICATION_MODEL
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.setMinWidth(50);
                    window.setMinHeight(30);
                    window.setMaxWidth(50);
                    window.setMaxHeight(30);
                    window.showAndWait();
                }else{

                }

                if(fileType_1.isSelected()==false&&fileType_2.isSelected()==false&&fileType_3.isSelected()==false&&fileType_4.isSelected()==false&&fileType_5.isSelected()==false){

                }else{}

                for (int i = 0; i < 10; i++) {
                    data.add(new MatchedResultEntity("a" + i, "b" + i, "c" + i, "d" + i, "e" + i));
                }
            }
        });
    }

    public Tab initialSingleTab(final Stage stage,final TabPane tabPane){
        final ObservableList<MatchedResultEntity> data = FXCollections.observableArrayList();

        Tab contentMatch = new Tab();
        contentMatch.setText("内容匹配");

        Label inputHeader = new Label("  请输入需要匹配的内容：");
        inputHeader.setPrefSize(300, 30);
        final TextArea contentInput = new TextArea();
        contentInput.setMaxWidth(1200);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("请选择文件：");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home"))
        );

        Button choiceFile = new Button("选择文件");
        chooseMatchedFile(choiceFile,stage,contentInput);

        Button clearContent = new Button("清空内容");
        clearContent.setOnAction(new EventHandler<ActionEvent>() {
            //@Override
            public void handle(ActionEvent arg0) {
                contentInput.clear();
            }
        });

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

        CheckBox fileType_1 = new CheckBox(".sh");
        CheckBox fileType_2 = new CheckBox(".hql");
        CheckBox fileType_3 = new CheckBox(".txt");
        CheckBox fileType_4 = new CheckBox(".sbt");
        CheckBox fileType_5 = new CheckBox("所有类型");
        fileType_5.setSelected(true);
        Label matchFileType = new Label("匹配的文件类型：");


        Label matchSchedule = new Label("匹配进度：");
        ProgressBar pg = new ProgressBar();
        pg.autosize();

        Label choseTargetDir = new Label("选择目标目录：");
        final TextField manualTargetDir = new TextField();
        Button choseDir = new Button("浏览");
        setDirAction(choseDir,stage,manualTargetDir);

        //匹配到的内容进行显示
        Label showMatched = new Label("  匹配到的内容：");


        VBox tableShow = new VBox();
        TableView<MatchedResultEntity> resultTableView = new TableView<MatchedResultEntity>();
        TableColumn title_1 = new TableColumn("匹配内容");
        title_1.setPrefWidth(200);
        TableColumn title_2 = new TableColumn("命中内容所在行数");
        title_2.setPrefWidth(100);
        TableColumn title_3 = new TableColumn("命中内容所在路径");
        title_3.setPrefWidth(200);
        TableColumn title_4 = new TableColumn("命中文件名称");
        title_4.setPrefWidth(200);
        TableColumn title_5 = new TableColumn("命中文件内容");
        title_5.setPrefWidth(647);
        resultTableView.setMaxWidth(1350);
        resultTableView.setMaxHeight(200);

        resultTableView.getColumns().addAll(title_1, title_2, title_3, title_4, title_5);
        title_1.setCellValueFactory(new PropertyValueFactory<MatchedResultEntity, String>("matchRegion"));
        title_2.setCellValueFactory(new PropertyValueFactory<MatchedResultEntity, String>("matchedLineNumber"));
        title_3.setCellValueFactory(new PropertyValueFactory<MatchedResultEntity, String>("matchedFilePath"));
        title_4.setCellValueFactory(new PropertyValueFactory<MatchedResultEntity, String>("matchedFileName"));
        title_5.setCellValueFactory(new PropertyValueFactory<MatchedResultEntity, String>("matchedValue"));
        resultTableView.setItems(data);

        tableShow.getChildren().addAll(resultTableView);
        tableShow.setAlignment(Pos.CENTER);
        //开始按钮
        VBox orderBox = new VBox();
        Button startMatch = new Button("开始匹配");
        startMatch(startMatch,stage,data,contentInput,fileType_1,fileType_2,fileType_3,fileType_4,fileType_5,manualTargetDir);
        Button saveFile = new Button("保存结果");
        setSaveFileAction(saveFile,stage);

        GridPane gridpane_01 = new GridPane();
        gridpane_01.setPadding(new Insets(5));
        ColumnConstraints column1_1 = new ColumnConstraints(130);
        ColumnConstraints column2_1 = new ColumnConstraints(130);
        ColumnConstraints column3_1 = new ColumnConstraints(130);
        ColumnConstraints column4_1 = new ColumnConstraints(130);
        ColumnConstraints column5_1 = new ColumnConstraints(130);
        ColumnConstraints column6_1 = new ColumnConstraints(130);
        ColumnConstraints column7_1 = new ColumnConstraints(130);
        ColumnConstraints column8_1 = new ColumnConstraints(130);
        ColumnConstraints column9_1 = new ColumnConstraints(130);
        column9_1.setHalignment(HPos.RIGHT);
        ColumnConstraints column0_1 = new ColumnConstraints(130);
        column0_1.setHalignment(HPos.RIGHT);
        gridpane_01.getColumnConstraints().addAll(column1_1, column2_1, column3_1, column4_1, column5_1, column6_1, column7_1, column8_1, column9_1, column0_1);


        gridpane_01.add(choseTargetDir, 0, 0);
        gridpane_01.add(manualTargetDir, 1, 0);
        gridpane_01.add(choseDir, 2, 0);
        gridpane_01.add(new Label(), 0, 1);

        gridpane_01.add(matchFileType, 0, 2);
        gridpane_01.add(fileType_1, 1, 2);
        gridpane_01.add(fileType_2, 2, 2);
        gridpane_01.add(fileType_3, 3, 2);
        gridpane_01.add(fileType_4, 4, 2);
        gridpane_01.add(fileType_5, 5, 2);

        gridpane_01.add(new Label(), 0, 3);

        gridpane_01.add(matchSchedule, 0, 4);
        gridpane_01.add(pg, 1, 4);

        gridpane_01.add(new Label(), 0, 5);
        gridpane_01.add(new Label(), 0, 6);
        gridpane_01.add(new Label(), 0, 7);
        gridpane_01.add(startMatch, 8, 7);
        gridpane_01.add(saveFile, 9, 7);


        orderBox.getChildren().add(gridpane_01);

        //汇总添加模板
        mainBox.setPadding(new Insets(3));
        mainBox.getChildren().add(inputHeader);
        mainBox.getChildren().add(gridpane);
        mainBox.getChildren().add(new Separator());

        mainBox.getChildren().add(showMatched);
        mainBox.getChildren().add(tableShow);
        mainBox.getChildren().add(new Separator());
        mainBox.getChildren().add(orderBox);
        mainBox.setSpacing(5);
        contentMatch.setContent(mainBox);
        contentMatch.setOnClosed(new EventHandler<Event>() {
            //@Override
            public void handle(Event event) {
                if(tabPane.getTabs().size()==0) {
                    Tab currentTab = initialSingleTab(stage, tabPane);
                    tabPane.getSelectionModel().select(currentTab);
                    tabPane.getTabs().addAll(currentTab);
                }else{
                    tabPane.getSelectionModel().select(tabPane.getTabs().size()-1);
                }
            }
        });
        tabPane.getSelectionModel().select(contentMatch);
        return contentMatch;
    }
    public TabPane initialTabPane(final Stage stage) {
        Tab contentMatch = initialSingleTab(stage,tabPane);
        tabPane.getTabs().addAll(contentMatch);
        return tabPane;
    }
}
