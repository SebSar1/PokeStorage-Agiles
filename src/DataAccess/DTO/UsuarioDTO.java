package DataAccess.DTO;

public class UsuarioDTO {
    private Integer IdUsuario;
    private String Nombre;
    private String Contraseña;
    private Integer IdUsuarioSexo;
    private Integer Edad;
    private String FechaCrea;
    private String FechaModifica;

    public UsuarioDTO(Integer idUsuario, String nombre, String contraseña, Integer idUsuarioSexo, Integer edad,
            String fechaCrea, String fechaModifica) {
        IdUsuario = idUsuario;
        Nombre = nombre;
        Contraseña = contraseña;
        IdUsuarioSexo = idUsuarioSexo;
        Edad = edad;
        FechaCrea = fechaCrea;
        FechaModifica = fechaModifica;
    }

    public UsuarioDTO() {
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public Integer getIdUsuarioSexo() {
        return IdUsuarioSexo;
    }

    public void setIdUsuarioSexo(Integer idUsuarioSexo) {
        IdUsuarioSexo = idUsuarioSexo;
    }

    public Integer getEdad() {
        return Edad;
    }

    public void setEdad(Integer edad) {
        Edad = edad;
    }

    public String getFechaCrea() {
        return FechaCrea;
    }

    public void setFechaCrea(String fechaCrea) {
        FechaCrea = fechaCrea;
    }

    public String getFechaModifica() {
        return FechaModifica;
    }

    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "IdUsuario=" + IdUsuario +
                ", Nombre='" + Nombre + '\'' +
                ", Contraseña='" + Contraseña + '\'' +
                ", IdUsuarioSexo=" + IdUsuarioSexo +
                ", Edad=" + Edad +
                ", FechaCrea='" + FechaCrea + '\'' +
                ", FechaModifica='" + FechaModifica + '\'' +
                '}';
    }
}

// GM-9 subtarea: GM-10 Guardar nuevo usuario

