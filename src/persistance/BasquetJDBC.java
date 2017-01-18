/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Equipo;
import model.Jugador;

public class BasquetJDBC {
    
    private Connection conexion;

    public List<Equipo> selectAllEquipos() throws SQLException {
        List<Equipo> equipos = new ArrayList<>();
        String query = "select * from team";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Equipo e = new Equipo();
            e.setName(rs.getString("name"));
            e.setCity(rs.getString("city"));
            e.setCreation(rs.getDate("creation").toLocalDate());
            equipos.add(e);
        }
        rs.close();
        st.close();
        return equipos;
    }
    public List<Jugador> selectAllJugadores() throws SQLException {
        List<Jugador> jugadores = new ArrayList<>();
        String query = "select * from  player";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Jugador j = new Jugador();
            Equipo e = new Equipo();
            j.setName(rs.getString("name"));
            j.setBirth(rs.getDate("birth").toLocalDate());
            j.setNbaskets(rs.getInt("nbaskets"));
            j.setNassists(rs.getInt("nassists"));
            j.setNrebounds(rs.getInt("nrebounds"));
            j.setPosition(rs.getString("position"));
            e.setName(rs.getString("team"));
            j.setTeam(e);
            jugadores.add(j);
        }
        rs.close();
        st.close();
        return jugadores;
    }
    
    
    // Función que inserta un cocinero en la bbdd
    public void insertEquipo(Equipo e) throws SQLException {
        String insert = "insert into team values (?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        // Vamos dando valores a los interrogantes
        ps.setString(1, e.getName());
        ps.setString(2, e.getCity());
        ps.setDate(3, java.sql.Date.valueOf(e.getCreation()));
        // ejecutamos la consultas
        ps.executeUpdate();
        ps.close();
    }
    
    public void insertJugador(Jugador j) throws SQLException {
        String insert = "insert into player values (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement ps = conexion.prepareStatement(insert);
        // Vamos dando valores a los interrogantes
        ps.setString(1, j.getName());
        ps.setDate(2, java.sql.Date.valueOf(j.getBirth()));
        ps.setInt(3, j.getNbaskets());
        ps.setInt(4, j.getNassists());
        ps.setInt(5, j.getNrebounds());
        ps.setString(6, j.getPosition());
        ps.setString(7, j.getTeam().getName());
        // ejecutamos la consultas
        ps.executeUpdate();
        ps.close();
    }
    
    // Función que conecta con la bbdd
    public void conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/basket";
        String usr = "root";
        String pass = "";
        conexion = DriverManager.getConnection(url, usr, pass);
    }
    
    // Función que desconecta de la bbdd
    public void desconectar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
    
}
