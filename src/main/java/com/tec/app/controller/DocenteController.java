package com.tec.app.controller;

import com.tec.app.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class DocenteController implements Initializable {

    @FXML
    private ImageView add_imagen_docente;

    @FXML
    private ImageView add_imagen_estudiante;

    @FXML
    private Button btn_actualizar_docente;

    @FXML
    private Button btn_actualizar_estudiante;

    @FXML
    private Button btn_agregar_docente;

    @FXML
    private Button btn_agregar_estudiante;

    @FXML
    private Button btn_docentes;

    @FXML
    private Button btn_eliminar_docente;

    @FXML
    private Button btn_eliminar_estudiante;

    @FXML
    private Button btn_estudiantes;

    @FXML
    private Button btn_imagen_docente;

    @FXML
    private Button btn_imagen_estudiante;

    @FXML
    private Button btn_inicio;

    @FXML
    private Button btn_limpiar_docente;

    @FXML
    private Button btn_limpiar_estudiante;

    @FXML
    private Button btn_salir;

    @FXML
    private ChoiceBox<String> chb_area_docente;

    @FXML
    private ChoiceBox<String> chb_cargo_docente;

    @FXML
    private ChoiceBox<String> chb_curso_estudiante;

    @FXML
    private ChoiceBox<String> chb_jornada_estudiante;

    @FXML
    private ChoiceBox<String> chb_sede_estudiante;

    @FXML
    private DatePicker date_inicio;

    @FXML
    private AnchorPane form_docentes;

    @FXML
    private AnchorPane form_estudiantes;

    @FXML
    private AnchorPane form_inicio;

    @FXML
    private ImageView img_usuario;

    @FXML
    private Label label_total_estudiantes_ausentes;

    @FXML
    private Label label_total_estudiantes_dia;

    @FXML
    private Label label_total_estudiantes_presentes;

    @FXML
    private Label lbl_usuario;

    @FXML
    private TableColumn<docentesDatos, String> tb_apellido_docentes;

    @FXML
    private TableColumn<docentesDatos, String> tb_area_docentes;

    @FXML
    private TableColumn<docentesDatos, String> tb_cargo_docentes;

    @FXML
    private TableColumn<docentesDatos, String> tb_email_docentes;

    @FXML
    private TableColumn<docentesDatos, String> tb_id_docentes;

    @FXML
    private TableColumn<docentesDatos, String> tb_nombre_docentes;

    @FXML
    private TableColumn<docentesDatos, String> tb_telefono_docentes;

    @FXML
    private TableView<docentesDatos> tb_docentes;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_id_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_apellidos_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_nombres_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_curso_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_jornada_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_sede_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_fecha_nacimiento_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_edad_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_direccion_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_barrio_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_telefono_1_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_telefono_2_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_email_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_acudiente_1_estudiante;

    @FXML
    private TableColumn<estudiantesDatos, String> tb_acudiente_2_estudiante;

    @FXML
    private TableView<estudiantesDatos> tb_estudiantes;

    @FXML
    private TextField txt_apellido_docente;

    @FXML
    private TextField txt_apellidos_estudiante;

    @FXML
    private TextField txt_buscar_docente;

    @FXML
    private TextField txt_buscar_estudiante;

    @FXML
    private TextField txt_email_docente;

    @FXML
    private TextField txt_id_docente;

    @FXML
    private TextField txt_id_estudiante;

    @FXML
    private TextField txt_nombre_docente;

    @FXML
    private TextField txt_nombres_estudiante;

    @FXML
    private TextField txt_telefono_docente;

    @FXML
    private TextField txt_telefono_1_estudiante;

    @FXML
    private TextField txt_telefono_2_estudiante;

    @FXML
    private TextField txt_edad_estudiante;

    @FXML
    private TextField txt_direccion_estudiante;

    @FXML
    private TextField txt_barrio_estudiante;

    @FXML
    private TextField txt_email_estudiante;

    @FXML
    private TextField txt_acudiente_1_estudiante;

    @FXML
    private TextField txt_acudiente_2_estudiante;

    @FXML
    private DatePicker dp_fecha_nacimiento_estudiante;




    //Variables
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    ConexionDB conexionBD = null;

    //Variable para guardar el id original al seleccionar un docente
    private String idOriginalDocente;

    //Variable para guardar el id original al seleccionar un estudiante
    private String idOriginalEstudiante;

    //Variable para almacenar la imagen seleccionada docente
    private byte[] imagenSeleccionadaDocente = null;

    //Variable para almacenar la imagen seleccionada docente
    private byte[] imagenSeleccionadaEstudiante = null;

    //Configuras area docentes
    private void areDocente() {
        chb_area_docente.getItems().addAll("MÚSICA", "ARTES MARCIALES", "AUDIOVISUALES", "VOLEIBOL", "ADMINISTRATIVA");
    }

    //Configura cargo docente
    private void cargoDocente() {
        chb_cargo_docente.getItems().addAll("PROFESOR", "GESTOR", "LÍDER");
    }

    //Configurar curso estudiante
    private void cursoEstudiante() {
        chb_curso_estudiante.getItems().addAll("601", "602", "603", "604", "605", "701", "702", "703", "704", "705", "706", "801", "802", "803", "804", "805");
    }

    //Configurar jornada estudiante
    private void jornadaEstudiante() {
        chb_jornada_estudiante.getItems().addAll("MAÑANA", "TARDE");
    }

    //Configurar sede estudiante
    private void sedeEstudiante() {
        chb_sede_estudiante.getItems().addAll("A", "B", "C");
    }


    //MÉTODO PARA SELECCIONAR IMAGEN DEL DOCENTE
    @FXML
    public void seleccionarImagenDocente() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");

        // Filtros para tipos de archivo
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg", "*.jpeg")
        );

        File archivoSeleccionado = fileChooser.showOpenDialog(btn_imagen_docente.getScene().getWindow());

        if (archivoSeleccionado != null) {
            try {
                // Leer el archivo y convertirlo a bytes
                FileInputStream fis = new FileInputStream(archivoSeleccionado);
                imagenSeleccionadaDocente = fis.readAllBytes();
                fis.close();

                // Mostrar la imagen en el ImageView
                Image imagen = new Image(new FileInputStream(archivoSeleccionado));
                add_imagen_docente.setImage(imagen);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Imagen Cargada");
                alert.setHeaderText(null);
                alert.setContentText("Imagen cargada exitosamente");
                alert.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al cargar la imagen: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    //MÉTODO PARA SELECCIONAR IMAGEN DEL ESTUDIANTE
    @FXML
    public void seleccionarImagenEstudiante() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");

        // Filtros para tipos de archivo
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg", "*.gif"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg", "*.jpeg")
        );

        File archivoSeleccionado = fileChooser.showOpenDialog(btn_imagen_estudiante.getScene().getWindow());

        if (archivoSeleccionado != null) {
            try {
                // Leer el archivo y convertirlo a bytes
                FileInputStream fis = new FileInputStream(archivoSeleccionado);
                imagenSeleccionadaEstudiante = fis.readAllBytes();
                fis.close();

                // Mostrar la imagen en el ImageView
                Image imagen = new Image(new FileInputStream(archivoSeleccionado));
                add_imagen_estudiante.setImage(imagen);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Imagen Cargada");
                alert.setHeaderText(null);
                alert.setContentText("Imagen cargada exitosamente");
                alert.showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al cargar la imagen: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    //MÉTODO PARA BUSCAR EN LA BASE DE DATOS DOCENTE
    public ObservableList<docentesDatos> addDatosDocentes() {
        ObservableList<docentesDatos> listaDocentes = FXCollections.observableArrayList();
        String consulta = "SELECT * FROM docente";
        try {
            conexionBD = new ConexionDB();
            Connection conn = conexionBD.getConnection();
            ps = conn.prepareStatement(consulta);
            rs = ps.executeQuery();

            docentesDatos docentes_Datos;
            while (rs.next()) {
                byte[] imagen = rs.getBytes("imagen");
                docentes_Datos = new docentesDatos(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("cargo"),
                        rs.getString("area"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        imagen
                );
                listaDocentes.add(docentes_Datos);
            }
            System.out.println("Total docentes cargados: " + listaDocentes.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaDocentes;
    }

    //MÉTODO PARA BUSCAR EN LA BASE DE DATOS ESTUDIANTE
    public ObservableList<estudiantesDatos> addDatosEstudiantes() {
        ObservableList<estudiantesDatos> listaEstudiantes = FXCollections.observableArrayList();
        String consulta = "SELECT * FROM estudiante";
        try {
            conexionBD = new ConexionDB();
            Connection conn = conexionBD.getConnection();
            ps = conn.prepareStatement(consulta);
            rs = ps.executeQuery();

            estudiantesDatos estudiantes_Datos;
            while (rs.next()) {
                byte[] imagen = rs.getBytes("imagen");
                estudiantes_Datos = new estudiantesDatos(
                        rs.getString("id"),
                        rs.getString("apellidos"),
                        rs.getString("nombres"),
                        rs.getString("curso"),
                        rs.getString("jornada"),
                        rs.getString("sede"),
                        rs.getDate("fecha_nacimiento"),
                        rs.getString("edad"),
                        rs.getString("direccion"),
                        rs.getString("barrio"),
                        rs.getString("telefono_1"),
                        rs.getString("telefono_2"),
                        rs.getString("email"),
                        rs.getString("acudiente_1"),
                        rs.getString("acudiente_2"),
                        imagen
                );
                listaEstudiantes.add(estudiantes_Datos);
            }
            System.out.println("Total estudiantes cargados: " + listaEstudiantes.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaEstudiantes;
    }

    //MÉTODO PARA TOMAR LOS VALORES DE LA BASE DE DATOS DOCENTE
    private ObservableList<docentesDatos> addDatosD;
    public void addDocentesMuestraLista() {
        addDatosD = addDatosDocentes();

        tb_id_docentes.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_nombre_docentes.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tb_apellido_docentes.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tb_cargo_docentes.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        tb_area_docentes.setCellValueFactory(new PropertyValueFactory<>("area"));
        tb_email_docentes.setCellValueFactory(new PropertyValueFactory<>("email"));
        tb_telefono_docentes.setCellValueFactory(new PropertyValueFactory<>("telefono"));

        tb_docentes.setItems(addDatosD);
    }

    //MÉTODO PARA TOMAR LOS VALORES DE LA BASE DE DATOS ESTUDIANTE
    private ObservableList<estudiantesDatos> addDatosE;
    public void addEstudiantesMuestraLista() {
        addDatosE = addDatosEstudiantes();

        tb_id_estudiante.setCellValueFactory(new PropertyValueFactory<>("id"));
        tb_nombres_estudiante.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        tb_apellidos_estudiante.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tb_curso_estudiante.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tb_jornada_estudiante.setCellValueFactory(new PropertyValueFactory<>("jornada"));
        tb_sede_estudiante.setCellValueFactory(new PropertyValueFactory<>("sede"));
        tb_fecha_nacimiento_estudiante.setCellValueFactory(new PropertyValueFactory<>("fecha_nacimiento"));
        tb_edad_estudiante.setCellValueFactory(new PropertyValueFactory<>("edad"));
        tb_direccion_estudiante.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        tb_barrio_estudiante.setCellValueFactory(new PropertyValueFactory<>("barrio"));
        tb_telefono_1_estudiante.setCellValueFactory(new PropertyValueFactory<>("telefono_1"));
        tb_telefono_2_estudiante.setCellValueFactory(new PropertyValueFactory<>("telefono_2"));
        tb_email_estudiante.setCellValueFactory(new PropertyValueFactory<>("email"));
        tb_acudiente_1_estudiante.setCellValueFactory(new PropertyValueFactory<>("acudiente_1"));
        tb_acudiente_2_estudiante.setCellValueFactory(new PropertyValueFactory<>("acudiente_2"));

        tb_estudiantes.setItems(addDatosE);
    }

    //MÉTODO PARA AGREGAR UN DOCENTE
    public void addDocente() {
        String id = txt_id_docente.getText().trim();
        String nombre = txt_nombre_docente.getText();
        String apellido = txt_apellido_docente.getText();
        String cargo = chb_cargo_docente.getValue();
        String area = chb_area_docente.getValue();
        String email = txt_email_docente.getText().trim();
        String telefono = txt_telefono_docente.getText().trim();
        String password = "1234"; //Password por defecto

        //Validar que los campos estén llenos
        if (id.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || cargo == null || area == null || email.isEmpty() || telefono.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacios");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingrese todos los campos.");
            alert.showAndWait();
            return;
        }

        //Validar formato del id para solo números
        if (!id.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formato de ID Incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("El id debe contener solo números.");
            alert.showAndWait();
            return;
        }

        //Validar formato email
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formato de Email Incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingrese un email válido.");
            alert.showAndWait();
            return;
        }

        //Validar formato de teléfono solo números
        if (!telefono.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Teléfono invalido");
            alert.setHeaderText(null);
            alert.setContentText("El teléfono debe contener solo números.");
            alert.showAndWait();
            return;
        }

        //Confirmar antes de agregar
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmar");
        confirmation.setHeaderText(null);
        confirmation.setContentText("¿Está seguro que desea agregar este docente?");
        Optional<ButtonType> resultado = confirmation.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            //Insertar en la base de datos
            addDocenteDB(id, nombre, apellido, cargo, area, email, telefono, password);
        }
    }

    //MÉTODO PARA AGREGAR UN ESTUDIANTE
    public void addEstudiante() {
        String id = txt_id_estudiante.getText().trim();
        String apellidos = txt_apellidos_estudiante.getText();
        String nombres = txt_apellidos_estudiante.getText();
        String curso = chb_curso_estudiante.getValue();
        String jornada = chb_jornada_estudiante.getValue();
        String sede = chb_sede_estudiante.getValue();
        LocalDate fecha_nacimiento = dp_fecha_nacimiento_estudiante.getValue();
        String edad = txt_edad_estudiante.getText();
        String direccion = txt_direccion_estudiante.getText();
        String barrio = txt_barrio_estudiante.getText();
        String telefono_1 = txt_telefono_1_estudiante.getText();
        String telefono_2 = txt_telefono_2_estudiante.getText();
        String email = txt_email_estudiante.getText().trim();
        String acudiente_1 = txt_acudiente_1_estudiante.getText();
        String acudiente_2 = txt_acudiente_2_estudiante.getText();

        //Validar que los campos estén llenos
        if (id.isEmpty() || apellidos.isEmpty() || nombres.isEmpty() || curso == null || jornada == null || sede.isEmpty() || fecha_nacimiento == null || edad.isEmpty() || direccion.isEmpty() || barrio.isEmpty() || telefono_1.isEmpty() || telefono_2.isEmpty() || email.isEmpty() || acudiente_1.isEmpty() || acudiente_2.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacios");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingrese todos los campos.");
            alert.showAndWait();
            return;
        }

        //Validar formato del id para solo números
        if (!id.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formato de ID Incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("El id debe contener solo números.");
            alert.showAndWait();
            return;
        }

        //Validar formato email
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formato de Email Incorrecto");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, ingrese un email válido.");
            alert.showAndWait();
            return;
        }

        //Validar formato de teléfono solo números
        if (!telefono_1.matches("\\d+") && !telefono_2.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Teléfono invalido");
            alert.setHeaderText(null);
            alert.setContentText("El teléfono debe contener solo números.");
            alert.showAndWait();
            return;
        }

        //Confirmar antes de agregar
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirmar");
        confirmation.setHeaderText(null);
        confirmation.setContentText("¿Está seguro que desea agregar este estudiante?");
        Optional<ButtonType> resultado = confirmation.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            // Convertir LocalDate a java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(fecha_nacimiento);
            //Insertar en la base de datos
            addEstudianteDB(id,apellidos,nombres,curso,jornada,sede,sqlDate,edad,direccion,barrio,telefono_1,telefono_2,email,acudiente_1,acudiente_2);
        }
    }

    //MÉTODO PARA INSERTAR EN LA BASE DE DATOS UN DOCENTE
    private void addDocenteDB(String id, String nombre, String apellido, String cargo, String area, String email, String telefono, String password) {
        String consulta = "INSERT INTO docente (id, nombre, apellido, cargo, area, email, telefono, password, imagen) VALUES (?,?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conexionBD = new ConexionDB();
            conn = conexionBD.getConnection();

            //Verificar si el id ya existe en la base de datos
            String consultaVerificar = "SELECT COUNT(*) FROM docente WHERE id = ?";
            ps = conn.prepareStatement(consultaVerificar);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ID ya existente");
                alert.setHeaderText(null);
                alert.setContentText("El id ya existe en la base de datos.");
                alert.showAndWait();
                rs.close();
                ps.close();
                return;
            }
            rs.close();
            ps.close();

            //Agregar en la base de datos el nuevo docente
            ps = conn.prepareStatement(consulta);
            ps.setString(1, id);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, cargo);
            ps.setString(5, area);
            ps.setString(6, email);
            ps.setString(7, telefono);
            ps.setString(8, password);

            // Agregar la imagen (puede ser null)
            if (imagenSeleccionadaDocente != null) {
                ps.setBytes(9, imagenSeleccionadaDocente);
            } else {
                ps.setNull(9, java.sql.Types.BLOB);
            }

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Docente agregado");
                alert.setHeaderText(null);
                alert.setContentText("El docente se ha agregado correctamente.");
                alert.showAndWait();

                //refrescar la tabla
                addDocentesMuestraLista();

                //Limpiar los campos
                limpiarCamposDocente();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al agregar el docente: " + e.getMessage());
            alert.showAndWait();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //MÉTODO PARA INSERTAR EN LA BASE DE DATOS UN ESTUDIANTE
    private void addEstudianteDB(String id, String apellidos, String nombres, String curso, String jornada, String sede, java.sql.Date fecha_nacimiento, String edad, String direccion, String barrio, String telefono_1, String telefono_2, String email, String acudiente_1, String acudiente_2) {
        String consulta = "INSERT INTO estudiante (id, apellidos, nombres, curso, jornada, sede, fecha_nacimiento, edad, direccion, barrio, telefono_1, telefono_2, email, acudiente_1, acudiente_2, imagen) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conexionBD = new ConexionDB();
            conn = conexionBD.getConnection();

            //Verificar si el id ya existe en la base de datos
            String consultaVerificar = "SELECT COUNT(*) FROM estudiante WHERE id = ?";
            ps = conn.prepareStatement(consultaVerificar);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("ID ya existente");
                alert.setHeaderText(null);
                alert.setContentText("El id ya existe en la base de datos.");
                alert.showAndWait();
                rs.close();
                ps.close();
                return;
            }
            rs.close();
            ps.close();

            //Agregar en la base de datos el nuevo estudiante
            ps = conn.prepareStatement(consulta);
            ps.setString(1, id);
            ps.setString(2, apellidos);
            ps.setString(3, nombres);
            ps.setString(4, curso);
            ps.setString(5, jornada);
            ps.setString(6, sede);
            ps.setDate(7, fecha_nacimiento);
            ps.setString(8, edad);
            ps.setString(9, direccion);
            ps.setString(10, barrio);
            ps.setString(11, telefono_1);
            ps.setString(12, telefono_2);
            ps.setString(13, email);
            ps.setString(14, acudiente_1);
            ps.setString(15, acudiente_2);

            // Agregar la imagen (puede ser null)
            if (imagenSeleccionadaEstudiante != null) {
                ps.setBytes(16, imagenSeleccionadaEstudiante);
            } else {
                ps.setNull(16, java.sql.Types.BLOB);
            }

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Estudiante agregado");
                alert.setHeaderText(null);
                alert.setContentText("El estudiante se ha agregado correctamente.");
                alert.showAndWait();

                //refrescar la tabla
                addEstudiantesMuestraLista();

                //Limpiar los campos
                limpiarCamposEstudiante();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error al agregar el estudiante: " + e.getMessage());
            alert.showAndWait();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //MÉTODO PARA LIMPIAR LOS CAMPOS DE DOCENTE
    @FXML
    private void limpiarCamposDocente() {
        txt_id_docente.clear();
        txt_nombre_docente.clear();
        txt_apellido_docente.clear();
        chb_cargo_docente.getSelectionModel().clearSelection();
        chb_area_docente.getSelectionModel().clearSelection();
        txt_email_docente.clear();
        txt_telefono_docente.clear();
        add_imagen_docente.setImage(null);
        imagenSeleccionadaDocente = null;
    }

    //MÉTODO PARA LIMPIAR LOS CAMPOS DE ESTUDIANTE
    @FXML
    private void limpiarCamposEstudiante() {
        txt_id_estudiante.clear();
        txt_apellidos_estudiante.clear();
        txt_nombres_estudiante.clear();
        chb_curso_estudiante.getSelectionModel().clearSelection();
        chb_jornada_estudiante.getSelectionModel().clearSelection();
        chb_sede_estudiante.getSelectionModel().clearSelection();
        dp_fecha_nacimiento_estudiante.setValue(null);
        txt_edad_estudiante.clear();
        txt_direccion_estudiante.clear();
        txt_barrio_estudiante.clear();
        txt_telefono_1_estudiante.clear();
        txt_telefono_2_estudiante.clear();
        txt_email_estudiante.clear();
        txt_acudiente_1_estudiante.clear();
        txt_acudiente_2_estudiante.clear();
        add_imagen_estudiante.setImage(null);
        imagenSeleccionadaEstudiante = null;

    }

    //MÉTODO PARA SELECCIONAR UN  DOCENTE DE LA TABLA
    public void seleccionarDocente() {
        docentesDatos docente = tb_docentes.getSelectionModel().getSelectedItem();

        if (docente != null) {
            //Guarda el id original cuando se selecciona la tabla
            idOriginalDocente = String.valueOf(docente.getId());

            txt_id_docente.setText(String.valueOf(docente.getId()));
            txt_nombre_docente.setText(docente.getNombre());
            txt_apellido_docente.setText(docente.getApellido());
            chb_cargo_docente.getSelectionModel().select(docente.getCargo());
            chb_area_docente.getSelectionModel().select(docente.getArea());
            txt_email_docente.setText(docente.getEmail());
            txt_telefono_docente.setText(docente.getTelefono());

            // Mostrar la imagen si existe
            if (docente.getImagen() != null && docente.getImagen().length > 0) {
                try {
                    Image imagen = new Image(new ByteArrayInputStream(docente.getImagen()));
                    add_imagen_docente.setImage(imagen);
                    imagenSeleccionadaDocente = docente.getImagen();
                } catch (Exception e) {
                    e.printStackTrace();
                    add_imagen_docente.setImage(null);
                    imagenSeleccionadaDocente = null;
                }
            } else {
                add_imagen_docente.setImage(null);
                imagenSeleccionadaDocente = null;
            }
        }
    }

    //MÉTODO PARA SELECCIONAR UN  ESTUDIANTE DE LA TABLA
    public void seleccionarEstudiante() {
        estudiantesDatos estudiante = tb_estudiantes.getSelectionModel().getSelectedItem();

        if (estudiante != null) {
            //Guarda el id original cuando se selecciona la tabla
            idOriginalEstudiante = String.valueOf(estudiante.getId());

            txt_id_estudiante.setText(String.valueOf(estudiante.getId()));
            txt_apellidos_estudiante.setText(estudiante.getApellidos());
            txt_nombres_estudiante.setText(estudiante.getNombres());
            chb_curso_estudiante.getSelectionModel().select(estudiante.getCurso());
            chb_jornada_estudiante.getSelectionModel().select(estudiante.getJornada());
            chb_sede_estudiante.getSelectionModel().select(estudiante.getSede());
            if (dp_fecha_nacimiento_estudiante != null && estudiante.getFecha_nacimiento() != null) {
                // Convertir java.sql.Date o java.util.Date a LocalDate
                java.sql.Date sqlDate = (java.sql.Date) estudiante.getFecha_nacimiento();
                dp_fecha_nacimiento_estudiante.setValue(sqlDate.toLocalDate());
            }
            txt_edad_estudiante.setText(String.valueOf(estudiante.getEdad()));
            txt_direccion_estudiante.setText(estudiante.getDireccion());
            txt_barrio_estudiante.setText(estudiante.getBarrio());
            txt_telefono_1_estudiante.setText(estudiante.getTelefono_1());
            txt_telefono_2_estudiante.setText(estudiante.getTelefono_2());
            txt_email_estudiante.setText(estudiante.getEmail());
            txt_acudiente_1_estudiante.setText(estudiante.getAcudiente_1());
            txt_acudiente_2_estudiante.setText(estudiante.getAcudiente_2());

            // Mostrar la imagen si existe
            if (estudiante.getImagen() != null && estudiante.getImagen().length > 0) {
                try {
                    Image imagen = new Image(new ByteArrayInputStream(estudiante.getImagen()));
                    add_imagen_estudiante.setImage(imagen);
                    imagenSeleccionadaEstudiante = estudiante.getImagen();
                } catch (Exception e) {
                    e.printStackTrace();
                    add_imagen_estudiante.setImage(null);
                    imagenSeleccionadaEstudiante = null;
                }
            } else {
                add_imagen_estudiante.setImage(null);
                imagenSeleccionadaEstudiante = null;
            }
        }
    }

    // MÉTODO PARA ELIMINAR DOCENTE
    public void eliminarDocente() {
        String id = txt_id_docente.getText().trim();

        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Vacío");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleccione un docente para eliminar");
            alert.showAndWait();
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Está seguro de eliminar este docente?\nEsta acción no se puede deshacer.");
        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            String consulta = "DELETE FROM docente WHERE id=?";

            try {
                conexionBD = new ConexionDB();
                Connection conn = conexionBD.getConnection();
                PreparedStatement ps = conn.prepareStatement(consulta);
                ps.setString(1, id);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("Docente eliminado exitosamente");
                    alert.showAndWait();

                    addDocentesMuestraLista();
                    limpiarCamposDocente();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al eliminar: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    // MÉTODO PARA ELIMINAR ESTUDIANTE
    public void eliminarEstudiante() {
        String id = txt_id_estudiante.getText().trim();

        if (id.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Vacío");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleccione un estudiante para eliminar");
            alert.showAndWait();
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Eliminación");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Está seguro de eliminar este estudiante?\nEsta acción no se puede deshacer.");
        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
            String consulta = "DELETE FROM estudiante WHERE id=?";

            try {
                conexionBD = new ConexionDB();
                Connection conn = conexionBD.getConnection();
                PreparedStatement ps = conn.prepareStatement(consulta);
                ps.setString(1, id);

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("Estudiante eliminado exitosamente");
                    alert.showAndWait();

                    addEstudiantesMuestraLista();
                    limpiarCamposEstudiante();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al eliminar: " + e.getMessage());
                alert.showAndWait();
            }
        }
    }

    // MÉTODO PARA ACTUALIZAR DOCENTE PERMITE CAMBIAR EL ID
    public void actualizarDocente() {
        String nuevoId = txt_id_docente.getText().trim();
        String nombre = txt_nombre_docente.getText().trim();
        String apellido = txt_apellido_docente.getText().trim();
        String cargo = chb_cargo_docente.getValue();
        String area = chb_area_docente.getValue();
        String email = txt_email_docente.getText().trim();
        String telefono = txt_telefono_docente.getText().trim();

        // Validar campos vacios
        if (nuevoId.isEmpty() || nombre.isEmpty() || apellido.isEmpty() ||
                cargo == null || area == null || email.isEmpty() || telefono.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor complete todos los campos");
            alert.showAndWait();
            return;
        }

        //validar que se haya seleccionado un docente primero
        if (idOriginalDocente == null || idOriginalDocente.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleccione un docente");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleccione un docente de la tabla para actualizar.");
            alert.showAndWait();
            return;
        }

        //Validar formato del nuevo id
        if (!nuevoId.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Inválido");
            alert.setHeaderText(null);
            alert.setContentText("El ID debe contener solo números.");
            alert.showAndWait();
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Está seguro de actualizar este docente?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {

            Connection conn = null;
            PreparedStatement ps = null;

            try {
                conexionBD = new ConexionDB();
                conn = conexionBD.getConnection();

                // Si el ID cambió, verificar que el nuevo ID no exista
                if (!nuevoId.equals(idOriginalDocente)) {
                    String consultaVerificar = "SELECT COUNT(*) FROM docente WHERE id = ?";
                    ps = conn.prepareStatement(consultaVerificar);
                    ps.setString(1, nuevoId);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next() && rs.getInt(1) > 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ID Duplicado");
                        alert.setHeaderText(null);
                        alert.setContentText("Ya existe un docente con el ID: " + nuevoId + "\nPor favor use otro ID.");
                        alert.showAndWait();
                        rs.close();
                        ps.close();
                        return;
                    }
                    rs.close();
                    ps.close();
                }

                // Actualizar todos los campos incluyendo el ID y la imagen
                String consulta = "UPDATE docente SET id=?, nombre=?, apellido=?, cargo=?, area=?, email=?, telefono=?, imagen=? WHERE id=?";
                ps = conn.prepareStatement(consulta);

                ps.setString(1, nuevoId);           // Nuevo ID
                ps.setString(2, nombre);            // nombre
                ps.setString(3, apellido);          // apellido
                ps.setString(4, cargo);             // cargo
                ps.setString(5, area);              // area
                ps.setString(6, email);             // email
                ps.setString(7, telefono);          // telefono

                // Actualizar imagen
                if (imagenSeleccionadaDocente != null) {
                    ps.setBytes(8, imagenSeleccionadaDocente);
                } else {
                    ps.setNull(8, java.sql.Types.BLOB);
                }

                ps.setString(9, idOriginalDocente); // WHERE id = ID_ORIGINAL

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("Docente actualizado exitosamente");
                    alert.showAndWait();

                    addDocentesMuestraLista();
                    limpiarCamposDocente();
                    idOriginalDocente = ""; // Limpiar el ID original
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No Encontrado");
                    alert.setHeaderText(null);
                    alert.setContentText("No se encontró el docente con el ID especificado");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al actualizar: " + e.getMessage());
                alert.showAndWait();
            } finally {
                try {
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // MÉTODO PARA ACTUALIZAR ESTUDIANTE PERMITE CAMBIAR EL ID
    public void actualizarEstudiante() {
        String nuevoId = txt_id_estudiante.getText().trim();
        String apellidos = txt_apellidos_estudiante.getText().trim();
        String nombres = txt_nombres_estudiante.getText().trim();
        String curso = chb_curso_estudiante.getValue();
        String jornada = chb_jornada_estudiante.getValue();
        String sede = chb_sede_estudiante.getValue();
        LocalDate fecha_nacimiento = dp_fecha_nacimiento_estudiante.getValue();
        String edad = txt_edad_estudiante.getText().trim();
        String direccion = txt_direccion_estudiante.getText().trim();
        String barrio = txt_barrio_estudiante.getText().trim();
        String telefono_1 = txt_telefono_1_estudiante.getText().trim();
        String telefono_2 = txt_telefono_2_estudiante.getText().trim();
        String email = txt_email_estudiante.getText().trim();
        String acudiente_1 = txt_acudiente_1_estudiante.getText().trim();
        String acudiente_2 = txt_acudiente_2_estudiante.getText().trim();

        // Validar campos vacios
        if (nuevoId.isEmpty() || apellidos.isEmpty() || nombres.isEmpty() || curso == null || jornada == null || sede == null || fecha_nacimiento == null || edad.isEmpty() || direccion.isEmpty() || barrio.isEmpty() || telefono_1.isEmpty() || telefono_2.isEmpty() || email.isEmpty() || acudiente_1.isEmpty() || acudiente_2.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor complete todos los campos");
            alert.showAndWait();
            return;
        }

        //validar que se haya seleccionado un estudiante primero
        if (idOriginalEstudiante == null || idOriginalEstudiante.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Seleccione un estudiante");
            alert.setHeaderText(null);
            alert.setContentText("Por favor seleccione un estudiante de la tabla para actualizar.");
            alert.showAndWait();
            return;
        }

        //Validar formato del nuevo id
        if (!nuevoId.matches("\\d+")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ID Inválido");
            alert.setHeaderText(null);
            alert.setContentText("El ID debe contener solo números.");
            alert.showAndWait();
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("¿Está seguro de actualizar este estudiante?");
        Optional<ButtonType> resultado = confirmacion.showAndWait();

        if (resultado.isPresent() && resultado.get() == ButtonType.OK) {

            Connection conn = null;
            PreparedStatement ps = null;

            try {
                conexionBD = new ConexionDB();
                conn = conexionBD.getConnection();

                // Si el ID cambió, verificar que el nuevo ID no exista
                if (!nuevoId.equals(idOriginalEstudiante)) {
                    String consultaVerificar = "SELECT COUNT(*) FROM estudiante WHERE id = ?";
                    ps = conn.prepareStatement(consultaVerificar);
                    ps.setString(1, nuevoId);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next() && rs.getInt(1) > 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ID Duplicado");
                        alert.setHeaderText(null);
                        alert.setContentText("Ya existe un estudiante con el ID: " + nuevoId + "\nPor favor use otro ID.");
                        alert.showAndWait();
                        rs.close();
                        ps.close();
                        return;
                    }
                    rs.close();
                    ps.close();
                }

                //Convertir fecha a java.sql.Date
                java.sql.Date fecha_nacimiento_local = java.sql.Date.valueOf(fecha_nacimiento);

                // Actualizar todos los campos incluyendo el ID y la imagen
                String consulta = "UPDATE estudiante SET id=?, apellidos=?, nombres=?, curso=?, jornada=?, sede=?, fecha_nacimiento=?, edad=?, direccion=?, barrio=?, telefono_1=?, telefono_2=?, email=?, acudiente_1=?, acudiente_2=?, imagen=? WHERE id=?";
                ps = conn.prepareStatement(consulta);

                ps.setString(1, nuevoId);
                ps.setString(2, apellidos);
                ps.setString(3, nombres);
                ps.setString(4, curso);
                ps.setString(5, jornada);
                ps.setString(6, sede);
                ps.setDate(7, fecha_nacimiento_local);
                ps.setString(8, edad);
                ps.setString(9, direccion);
                ps.setString(10, barrio);
                ps.setString(11, telefono_1);
                ps.setString(12, telefono_2);
                ps.setString(13, email);
                ps.setString(14, acudiente_1);
                ps.setString(15, acudiente_2);


                // Actualizar imagen
                if (imagenSeleccionadaEstudiante != null) {
                    ps.setBytes(16, imagenSeleccionadaEstudiante);
                } else {
                    ps.setNull(16, java.sql.Types.BLOB);
                }

                ps.setString(17, idOriginalEstudiante); // WHERE id = ID_ORIGINAL

                int filasAfectadas = ps.executeUpdate();

                if (filasAfectadas > 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Éxito");
                    alert.setHeaderText(null);
                    alert.setContentText("Estudiante actualizado exitosamente");
                    alert.showAndWait();

                    addEstudiantesMuestraLista();
                    limpiarCamposEstudiante();
                    idOriginalEstudiante = ""; // Limpiar el ID original
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("No Encontrado");
                    alert.setHeaderText(null);
                    alert.setContentText("No se encontró el estudiante con el ID especificado");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error al actualizar: " + e.getMessage());
                alert.showAndWait();
            } finally {
                try {
                    if (ps != null) ps.close();
                    if (conn != null) conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }




    //Muestra el nombre de usuario en inicio de seseión
    public void displayUser() {
        lbl_usuario.setText(getData.getNombre);
    }

    //Selecciona del menu izquierdo
    public void switchForm(ActionEvent event) {
        if (event.getSource() == btn_inicio) {
            form_inicio.setVisible(true);
            form_docentes.setVisible(false);
            form_estudiantes.setVisible(false);
            btn_inicio.setStyle("-fx-background-color: rgba(174,174,174,0.5);");
            btn_docentes.setStyle("-fx-background-color: transparent;");
            btn_estudiantes.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == btn_docentes) {
            form_inicio.setVisible(false);
            form_docentes.setVisible(true);
            form_estudiantes.setVisible(false);
            btn_docentes.setStyle("-fx-background-color: rgba(174,174,174,0.5);");
            btn_inicio.setStyle("-fx-background-color: transparent;");
            btn_estudiantes.setStyle("-fx-background-color: transparent;");
        } else if (event.getSource() == btn_estudiantes) {
            form_inicio.setVisible(false);
            form_docentes.setVisible(false);
            form_estudiantes.setVisible(true);
            btn_estudiantes.setStyle("-fx-background-color: rgba(174,174,174,0.5);");
            btn_inicio.setStyle("-fx-background-color: transparent;");
            btn_docentes.setStyle("-fx-background-color: transparent;");
        }
    }

    //Boton para salir de sesión
    public void salir() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Salir");
        alert.setHeaderText(null);
        alert.setContentText("¿Seguro que desea salir?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {
                btn_salir.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/tec/app/login-view.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //DOCENTE
        areDocente();
        cargoDocente();
        addDocentesMuestraLista();

        //Seleccionar un usuario de la tabla
        tb_docentes.setOnMouseClicked(event -> seleccionarDocente());

        //Vincular el botón para seleccionar imagen
        btn_imagen_docente.setOnAction(event -> seleccionarImagenDocente());


        //ESTUDIANTE
        cursoEstudiante();
        jornadaEstudiante();
        sedeEstudiante();
        addEstudiantesMuestraLista();

        //Seleccionar un usuario de la tabla
        tb_estudiantes.setOnMouseClicked(event -> seleccionarEstudiante());

        //Vincular el botón para seleccionar imagen
        btn_imagen_estudiante.setOnAction(event -> seleccionarImagenEstudiante());


        //GENERAL
        btn_inicio.setStyle("-fx-background-color: rgba(174,174,174,0.5);");
        displayUser();


    }
}