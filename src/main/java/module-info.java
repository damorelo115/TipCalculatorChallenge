module lauren.tipcalculatorchallenge {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens lauren.tipcalculatorchallenge to javafx.fxml;
    exports lauren.tipcalculatorchallenge;
}