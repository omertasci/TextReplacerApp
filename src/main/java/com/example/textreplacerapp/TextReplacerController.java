package com.example.textreplacerapp;

import com.example.textreplacerapp.exception.InvalidInputException;
import com.example.textreplacerapp.exception.OldTextCountException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import org.apache.commons.lang3.StringUtils;

import java.io.File;

public class TextReplacerController {

    @FXML
    private VBox vBoxId;
    @FXML
    private TextField choosenFolderId;

    @FXML
    private TextField oldTextId;

    @FXML
    private TextField newTextId;

    @FXML
    private Label errorLblId;

    @FXML
    protected void onSelectDirectoryButtonClick() {

        final DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage)vBoxId.getScene().getWindow();
        File file = directoryChooser.showDialog(stage);

        if(file != null){
            choosenFolderId.setText(file.getAbsolutePath());
        }
    }
    @FXML
    protected void onChangeButtonClick() {
        try {
            validateInputs();

            int occurrence  = FileOperation.iterateFiles(choosenFolderId.getText(), oldTextId.getText(), newTextId.getText());
            errorLblId.setText("Aranan metin " + occurrence + " yerde bulundu, başarıyla değiştirildi.");
        } catch (Exception ex){
            errorLblId.setText(ex.getMessage());
        }

    }
    private void validateInputs() throws InvalidInputException {
        if(StringUtils.isBlank(oldTextId.getText())){
            throw new InvalidInputException("Aranacak metin giriniz!");
        }
        if(StringUtils.isBlank(newTextId.getText())){
            throw new InvalidInputException("Değiştirilecek metin giriniz!");
        }
        if(oldTextId.getText().length() > 300){
            throw new InvalidInputException("Aranacak metin uzunluğu 300 karakterden fazla!");
        }
        if(newTextId.getText().length() > 300){
            throw new InvalidInputException("Değiştirilecek metin uzunluğu  300 karakterden fazla!");
        }
        if(StringUtils.isBlank(choosenFolderId.getText())){
            throw new InvalidInputException("Değiştirilecek dosyaların bulunduğu dizini seçiniz!");
        }
    }

}