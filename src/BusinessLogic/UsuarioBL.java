package BusinessLogic;

import java.util.ArrayList;

import DataAccess.DAO.UsuarioDao;
import DataAccess.DTO.UsuarioDTO;

public class UsuarioBL {
    private static UsuarioDTO Usuario;
    private static UsuarioDTO Usuario2;
    private static UsuarioDao pDAO = new UsuarioDao();

    public UsuarioBL() {
    }

    public static ArrayList<UsuarioDTO> getAll() throws Exception {
        return pDAO.readAll();
    }

    public static UsuarioDTO getById(int IdUsuario) throws Exception {
        Usuario = pDAO.readBy(IdUsuario);
        return Usuario;
    }

    public static UsuarioDTO getUsuarioSexo(Integer idUsuario) throws Exception {
        return pDAO.obtenerUsuarioSexo(idUsuario);
    }

    public boolean add(String nombre, String contraseña, Integer idUsuarioSexo, Integer edad) throws Exception {
        Usuario = new UsuarioDTO();
        Usuario.setNombre(nombre);
        Usuario.setContraseña(contraseña);
        Usuario.setIdUsuarioSexo(idUsuarioSexo);
        Usuario.setEdad(edad);
        // El campo FechaCrea se asignará automáticamente en el DAO
        return pDAO.create(Usuario);
    }

    public boolean update(Integer idUsuario, String nombre, String contraseña, Integer idUsuarioSexo, Integer edad)
            throws Exception {

        Usuario2 = new UsuarioDTO();
        Usuario2.setIdUsuario(idUsuario);
        Usuario2.setNombre(nombre);
        Usuario2.setContraseña(contraseña);
        Usuario2.setIdUsuarioSexo(idUsuarioSexo);
        Usuario2.setEdad(edad);
        // El campo FechaModifica se asignará automáticamente en el DAO
        return pDAO.update(Usuario2);
    }

    public boolean delete(int idUsuario) throws Exception {
        return pDAO.delete(idUsuario);
    }
}
// GM-12 subtarea: GM-13 Leer Usuario y verificar contraseña

// GM-9 subtarea: GM-10 Guardar nuevo usuario

// GM-9 subtarea: GM-11 Manejo de error si un usuario ya existe

