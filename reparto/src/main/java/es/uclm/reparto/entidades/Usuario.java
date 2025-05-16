package es.uclm.reparto.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nickname; // Nombre de usuario para login

    @Column
    private String password; // Contraseña

    @Column (nullable=true)
    private String rol; // CLIENTE, RESTAURANTE o REPARTIDOR

    // Constructor vacío
    public Usuario() {
    }

    // Constructor con parámetros
    public Usuario(String nombre, String apellido, String usuario, String password, String rol) {
        this.nickname = usuario;
        this.password = password;
        this.rol = rol;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nick) {
        this.nickname = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
    return String.format("Usuario [id=%s, nick=%s, rol=%s]",
    id, nickname, rol);
    }
}