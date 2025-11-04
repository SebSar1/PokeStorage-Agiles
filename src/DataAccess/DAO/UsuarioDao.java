package DataAccess.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAccess.IDAO;
import DataAccess.SQLiteDataHelper;
import DataAccess.DTO.UsuarioDTO;

public class UsuarioDao extends SQLiteDataHelper implements IDAO<UsuarioDTO> {

    @Override
    public UsuarioDTO readBy(Integer id) throws Exception {
        UsuarioDTO oS = null;
        String query = "SELECT * FROM Usuario WHERE IdUsuario = " + id.toString();
        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                oS = new UsuarioDTO(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nombre"),
                        rs.getString("Contraseña"),
                        rs.getInt("IdUsuarioSexo"),
                        rs.getInt("Edad"),
                        rs.getString("FechaCrea"),
                        rs.getString("FechaModifica"));
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return oS;
    }

    public UsuarioDTO obtenerUsuarioSexo(Integer idUsuario) throws Exception {
        UsuarioDTO oS = null;
        String query = "SELECT IdUsuarioSexo FROM Usuario WHERE  IdUsuario = " + idUsuario.toString();
        try {
            Connection conn = openConnection(); // Conectar a la base de datos
            Statement stmt = conn.createStatement(); // Crear una declaración SQL
            ResultSet rs = stmt.executeQuery(query); // Ejecutar la consulta SQL
            while (rs.next()) {
                oS = new UsuarioDTO(
                        null, // IdUsuario no se utiliza aquí
                        null,
                        null,
                        rs.getInt("IdUsuarioSexo"),
                        null,
                        null,
                        null);
                return oS;
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return oS;
    }

    @Override
    public ArrayList<UsuarioDTO> readAll() throws Exception {
        ArrayList<UsuarioDTO> lst = new ArrayList<>();
        String query = "SELECT IdUsuario, Nombre, Contraseña, IdUsuarioSexo, Edad, FechaCrea, FechaModifica FROM Usuario ";
        try {
            Connection conn = openConnection(); // conectar a DB
            Statement stmt = conn.createStatement(); // CRUD : select * ...
            ResultSet rs = stmt.executeQuery(query); // ejecutar la consulta
            while (rs.next()) {
                UsuarioDTO s = new UsuarioDTO(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nombre"),
                        rs.getString("Contraseña"),
                        rs.getInt("IdUsuarioSexo"),
                        rs.getInt("Edad"),
                        rs.getString("FechaCrea"),
                        rs.getString("FechaModifica"));
                lst.add(s);
            }
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
        return lst;
    }

    @Override
    public boolean create(UsuarioDTO entity) throws Exception {
        String query = "INSERT INTO Usuario (Nombre, Contraseña, IdUsuarioSexo, Edad, FechaCrea) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getContraseña());
            pstmt.setInt(3, entity.getIdUsuarioSexo());
            pstmt.setInt(4, entity.getEdad());
            pstmt.setString(5, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean update(UsuarioDTO entity) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE Usuario SET Nombre = ?, Contraseña = ?, IdUsuarioSexo = ?, Edad = ?, FechaModifica = ? WHERE IdUsuario = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getContraseña());
            pstmt.setInt(3, entity.getIdUsuarioSexo());
            pstmt.setInt(4, entity.getEdad());
            pstmt.setString(5, dtf.format(now));
            pstmt.setInt(6, entity.getIdUsuario());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE Usuario SET Estado = ? WHERE IdUsuario = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new Exception(e.getMessage());
        }
    }
}

// GM-9 subtarea: GM-10 Guardar nuevo usuario