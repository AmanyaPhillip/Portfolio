module com.taxidispatch {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.taxidispatch to javafx.fxml;
    exports com.taxidispatch;
}
