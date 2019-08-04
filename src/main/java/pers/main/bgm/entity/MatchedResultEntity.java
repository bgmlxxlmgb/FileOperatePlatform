package pers.main.bgm.entity;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by 10673 on 2017/11/26.
 */
public class MatchedResultEntity {
    private final SimpleStringProperty matchRegion = new SimpleStringProperty();
    private final SimpleStringProperty matchedLineNumber = new SimpleStringProperty();
    private final SimpleStringProperty matchedFilePath = new SimpleStringProperty();
    private final SimpleStringProperty matchedFileName = new SimpleStringProperty();
    private final SimpleStringProperty matchedValue = new SimpleStringProperty();

    public MatchedResultEntity(String matchRegion,String matchedLineNumber,String matchedFilePath,String matchedFileName,String matchedValue){
        this.setMatchRegion(matchRegion);
        this.setMatchedLineNumber(matchedLineNumber);
        this.setMatchedFilePath(matchedFilePath);
        this.setMatchedFileName(matchedFileName);
        this.setMatchedValue(matchedValue);
    }
    public String getMatchRegion() {
        return matchRegion.get();
    }

    public SimpleStringProperty matchRegionProperty() {
        return matchRegion;
    }

    public void setMatchRegion(String matchRegion) {
        this.matchRegion.set(matchRegion);
    }

    public String getMatchedLineNumber() {
        return matchedLineNumber.get();
    }

    public SimpleStringProperty matchedLineNumberProperty() {
        return matchedLineNumber;
    }

    public void setMatchedLineNumber(String matchedLineNumber) {
        this.matchedLineNumber.set(matchedLineNumber);
    }

    public String getMatchedFilePath() {
        return matchedFilePath.get();
    }

    public SimpleStringProperty matchedFilePathProperty() {
        return matchedFilePath;
    }

    public void setMatchedFilePath(String matchedFilePath) {
        this.matchedFilePath.set(matchedFilePath);
    }

    public String getMatchedFileName() {
        return matchedFileName.get();
    }

    public SimpleStringProperty matchedFileNameProperty() {
        return matchedFileName;
    }

    public void setMatchedFileName(String matchedFileName) {
        this.matchedFileName.set(matchedFileName);
    }

    public String getMatchedValue() {
        return matchedValue.get();
    }

    public SimpleStringProperty matchedValueProperty() {
        return matchedValue;
    }

    public void setMatchedValue(String matchedValue) {
        this.matchedValue.set(matchedValue);
    }
}
