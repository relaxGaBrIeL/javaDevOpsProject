package com.devops.lbnum_project.Controllers.fileExplore;

import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.Objects;

public class  FileTreeCell extends TreeCell<File> {
    @Override
    protected void updateItem(File item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.getName());
            setGraphic(getIcon(item));
        }
    }
    private ImageView getIcon(File file) {
        ImageView imageView = null;
        if (file.isDirectory()) {
            imageView = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/folder.png"))));
        } else if (file.isFile()) {
            String fileName = file.getName();
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            imageView = switch (extension) {
                case "txt" ->
                        new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/txt.jpg"))));
                case "pdf" ->
                        new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/pdf.png"))));
                case "doc", "docx" ->
                        new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/doc.png"))));
                case "xls", "xlsx" ->
                        new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/xls.png"))));
                case "ppt", "pptx" ->
                        new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/ppt.png"))));
                case "jpg", "jpeg", "png", "bmp", "gif" ->
                        new ImageView(new Image(file.toURI().toString(), 16, 16, true, false));
                default ->
                        new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/file.png"))));
            };
        }
        assert imageView != null;
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        return imageView;
    }

}
