module com.bcastle.lfsrtests {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.bcastle.lfsrtests to javafx.fxml;
    exports com.bcastle.lfsrtests;
}