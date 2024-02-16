module com.example.proyectotareasfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.proyectotareasfx to javafx.fxml;
    exports com.example.proyectotareasfx;
    exports com.example.proyectotareasfx.controllerviews;
    opens com.example.proyectotareasfx.controllerviews to javafx.fxml;
}