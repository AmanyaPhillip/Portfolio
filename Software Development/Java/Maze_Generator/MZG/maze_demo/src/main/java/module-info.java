module com.mazegener {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mazegener to javafx.fxml;
    exports com.mazegener;
}
