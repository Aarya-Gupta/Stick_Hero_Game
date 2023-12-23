module com.example.ap_2022006_2022009 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires net.synedra.validatorfx;
    requires javafx.media;

    opens com.example.ap_2022006_2022009 to javafx.fxml;
    exports com.example.ap_2022006_2022009;
}