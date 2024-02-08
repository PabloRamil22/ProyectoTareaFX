module com.example.proyectotareasfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectotareasfx to javafx.fxml;
    exports com.example.proyectotareasfx;
}