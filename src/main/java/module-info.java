module calculator {
    requires javafx.controls;
    requires javafx.fxml;

    opens calculator to javafx.fxml;
    exports calculator;
}
