package org.example.examenfx.Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.example.examenfx.Model.User;
import org.example.examenfx.Utils.JavaFXUtil;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

/**
 * Controlador principal para la gestión de usuarios en la interfaz gráfica.
 * Maneja la visualización y manipulación de datos de usuarios en una TableView.
 *
 * @author Sergio Vegas Acosta
 */
public class MainController implements Initializable {

    @FXML private TableView<User> table;
    @FXML private TableColumn<User, String> cEmail;
    @FXML private TableColumn<User, String> cPlatform;
    @FXML private TableColumn<User, String> cRole;
    @FXML private TableColumn<User, String> cVersion;
    @FXML private TableColumn<User, String> cCreationDate;

    @FXML private TextField txtEmail;
    @FXML private TextField txtPlatform;
    @FXML private TextField txtVersion;
    @FXML private CheckBox chkAdmin;

    @FXML private Button btnAdd;
    @FXML private Button btnClearAll;

    /** Lista observable que contiene los usuarios mostrados en la tabla */
    private ObservableList<User> users = FXCollections.observableArrayList();

    /**
     * Inicializa el controlador después de que se haya cargado su elemento raíz.
     * Configura las columnas de la tabla y asigna los datos.
     *
     * @param url Ubicación relativa del archivo FXML
     * @param resourceBundle Recursos específicos del locale
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupTableColumns();
        table.setItems(users);
    }

    /**
     * Configura las columnas de la tabla y sus valores de celda.
     * También establece un listener para la selección de elementos.
     */
    private void setupTableColumns() {
        cEmail.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getEmail()));
        cPlatform.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getPlatform()));
        cRole.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getIsAdmin() ? "Admin" : "Usuario"));
        cVersion.setCellValueFactory(row -> new SimpleStringProperty(row.getValue().getVersion()));
        cCreationDate.setCellValueFactory(row -> new SimpleStringProperty(
                row.getValue().getCreationDate().toString()
        ));

        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showUserDetails(newValue);
            }
        });
    }

    /**
     * Muestra los detalles completos de un usuario en un diálogo modal.
     *
     * @param user El usuario cuyos detalles se mostrarán
     */
    private void showUserDetails(User user) {
        String details = "Email: " + user.getEmail() + "\n" +
                "Plataforma: " + user.getPlatform() + "\n" +
                "Rol: " + (user.getIsAdmin() ? "Admin" : "Usuario") + "\n" +
                "Versión: " + user.getVersion() + "\n" +
                "Fecha creación: " + user.getCreationDate();

        JavaFXUtil.showModal(Alert.AlertType.INFORMATION,
                "Detalles del Usuario",
                "Información completa del usuario",
                details);
    }

    /**
     * Maneja la acción de agregar un nuevo usuario a la tabla.
     * Valida los campos requeridos y muestra mensajes de error/éxito.
     *
     * @param event El evento de acción del botón
     */
    @FXML
    private void addUser(ActionEvent event) {
        if (txtEmail.getText().trim().isEmpty()) {
            JavaFXUtil.showModal(Alert.AlertType.ERROR,
                    "Error",
                    "Faltan datos",
                    "El campo correo es obligatorio");
            return;
        }

        User newUser = new User(
                txtEmail.getText().trim(),
                txtPlatform.getText().trim(),
                chkAdmin.isSelected(),
                txtVersion.getText().trim(),
                LocalDateTime.now()
        );

        users.add(newUser);

        resetForm();

        JavaFXUtil.showModal(Alert.AlertType.INFORMATION,
                "Éxito",
                null,
                "Usuario añadido correctamente");
    }

    /**
     * Maneja la acción de eliminar todos los usuarios de la tabla.
     * Muestra un diálogo de confirmación antes de realizar la acción.
     *
     * @param event El evento de acción del botón
     */
    @FXML
    private void clearAllUsers(ActionEvent event) {
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmar limpieza");
        confirmation.setHeaderText("¿Está seguro de que desea eliminar todos los usuarios?");
        confirmation.setContentText("Esta acción no se puede deshacer.");

        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                users.clear();
                JavaFXUtil.showModal(Alert.AlertType.INFORMATION,
                        "Limpieza completada",
                        null,
                        "Todos los usuarios han sido eliminados");
            }
        });
    }

    /**
     * Limpia todos los campos del formulario de entrada de usuarios.
     */
    private void resetForm() {
        txtEmail.clear();
        txtPlatform.clear();
        txtVersion.clear();
        chkAdmin.setSelected(false);
    }
}