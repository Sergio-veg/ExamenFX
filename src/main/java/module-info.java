module org.example.examenfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires javafx.media;


    opens org.example.examenfx to javafx.fxml;
    exports org.example.examenfx;
    exports org.example.examenfx.Controllers;
    opens org.example.examenfx.Controllers to javafx.fxml;
    exports org.example.examenfx.Model;
    opens org.example.examenfx.Model to javafx.fxml;
    exports org.example.examenfx.Utils;
    opens org.example.examenfx.Utils to javafx.fxml;
}