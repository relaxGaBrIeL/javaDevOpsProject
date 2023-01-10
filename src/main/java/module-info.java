module com.devops.lbnum_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.devops.lbnum_project to javafx.fxml;
    exports com.devops.lbnum_project;
}