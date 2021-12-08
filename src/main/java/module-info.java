module com.example.jolicitefx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.jolicitefx to javafx.fxml;
    exports com.example.jolicitefx;
    exports com.example.jolicitefx.Domain;
    opens com.example.jolicitefx.Domain to javafx.fxml;
}