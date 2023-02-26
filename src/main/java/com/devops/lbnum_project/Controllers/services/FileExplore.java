package com.devops.lbnum_project.Controllers.services;

import java.io.File;
import java.util.Objects;

public class FileExplore {

    private String path = "C:\\Users\\etulyon1\\Desktop\\projects\\doc";
    private File[] liste;

    public FileExplore (){
        loadFolderContent();
    }

    private void loadFolderContent() {
        File dir = new File(path);
        if (dir.listFiles() != null) {
            liste = dir.listFiles();
        }else {
            goBackFolder();
        }

    }

    public void moveFolder(String folderName){
        path += "\\" + folderName;
        loadFolderContent();
    }

    public void goBackFolder(){
        String newPath = "";
        String[] tabPath = path.split("\\\\");
        if(tabPath.length <2){
            path = "D:\\";
        }
        else
        {
            for (int i = 0; i < tabPath.length-1; i++) {        // -2 car on ne veut pas prendre la dernière case du tableau
                newPath += tabPath[i] + "\\";
            }
        }
        path = newPath;
        loadFolderContent();
    }

    public File[] getListe(){
        return liste;
    }
}
