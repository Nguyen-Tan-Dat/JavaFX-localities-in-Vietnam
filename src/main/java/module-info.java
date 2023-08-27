module com.dat.javafxlocalitiesinvietnam {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.json;

    exports com;
    opens com to javafx.fxml;


    opens com.dat to javafx.fxml;
    exports com.dat;
    opens com.dat.controls to javafx.fxml;
    exports com.dat.controls;
}