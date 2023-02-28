module com.devops.lbnum_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.mariadb.jdbc;
    requires jbcrypt;
    requires java.desktop;

    opens com.devops.lbnum_project to javafx.fxml;
    exports com.devops.lbnum_project;
    exports com.devops.lbnum_project.services;
    exports com.devops.lbnum_project.Models;
    exports com.devops.lbnum_project.Views;
    exports com.devops.lbnum_project.services.client;
    exports com.devops.lbnum_project.services.authentication;
    exports com.devops.lbnum_project.services.fileExplore;
    exports com.devops.lbnum_project.services.chat;

}