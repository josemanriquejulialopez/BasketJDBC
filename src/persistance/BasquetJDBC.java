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
    
    public void modificarCaAsRe(Jugador j) throws SQLException{
        String modificar = "update player set nbaskets = ?, nassists = ?, nrebounds = ? where name = ?;";
        PreparedStatement ps = conexion.prepareStatement(modificar);
        ps.setInt(1, 0);
        ps.setInt(2, 0);
        ps.setInt(3, 0);
        ps.setString(4, j.getName());
        ps.executeUpdate();
        ps.close();
    }
    
    public void modificarEquipo(Jugador j, Equipo e) throws SQLException{
        String modificar = "update player set team = ? where name = ?;";
        PreparedStatement ps = conexion.prepareStatement(modificar);
        ps.setString(1, e.getName());
        ps.setString(2, j.getName());
        ps.executeUpdate();
        ps.close();
    }
    
    public void borrarJugador(Jugador j) throws SQLException{
        String borrar = "delete from player where name = ?";
        PreparedStatement ps = conexion.prepareStatement(borrar);
        ps.setString(1, j.getName());
        ps.executeUpdate();
        ps.close();
    }
    
    public Jugador obtenerJugadorporNombre(Jugador jgdr) throws SQLException{
        String obtener = "select * from player where name = '" + jgdr.getName() + "';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        Jugador j = new Jugador();
        while (rs.next()) {
            Equipo e = new Equipo();
            j.setName(rs.getString("name"));
            j.setBirth(rs.getDate("birth").toLocalDate());
            j.setNbaskets(rs.getInt("nbaskets"));
            j.setNassists(rs.getInt("nassists"));
            j.setNrebounds(rs.getInt("nrebounds"));
            j.setPosition(rs.getString("position"));
            e.setName(rs.getString("team"));
            j.setTeam(e);
        }
        rs.close();
        st.close();
        return j;
    }
    
    public List<Jugador> obtenerJugadoresPorNombre(String jgdr) throws SQLException{
        String obtener = "select * from player where name LIKE '%" + jgdr + "%';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List<Jugador> j = new ArrayList<>();
        while (rs.next()) {
            Jugador jr = new Jugador();
            Equipo e = new Equipo();
            jr.setName(rs.getString("name"));
            jr.setBirth(rs.getDate("birth").toLocalDate());
            jr.setNbaskets(rs.getInt("nbaskets"));
            jr.setNassists(rs.getInt("nassists"));
            jr.setNrebounds(rs.getInt("nrebounds"));
            jr.setPosition(rs.getString("position"));
            e.setName(rs.getString("team"));
            jr.setTeam(e);
            j.add(jr);
        }
        rs.close();
        st.close();
        return j;
    }
    
    public List<Jugador> obtenerJugadoresPorNumCanastas(int max) throws SQLException{
        String obtener = "select * from player where nbaskets>=" + max + " ;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List<Jugador> j = new ArrayList<>();
        while (rs.next()) {
            Jugador jr = new Jugador();
            Equipo e = new Equipo();
            jr.setName(rs.getString("name"));
            jr.setBirth(rs.getDate("birth").toLocalDate());
            jr.setNbaskets(rs.getInt("nbaskets"));
            jr.setNassists(rs.getInt("nassists"));
            jr.setNrebounds(rs.getInt("nrebounds"));
            jr.setPosition(rs.getString("position"));
            e.setName(rs.getString("team"));
            jr.setTeam(e);
            j.add(jr);
        }
        rs.close();
        st.close();
        return j;
    }
    
    public List<Jugador> obtenerJugadoresPorNumAssistencias(int min, int max) throws SQLException{
        String obtener = "select * from player where nassists>=" + min + " and nassists<=" + max + " ;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List<Jugador> j = new ArrayList<>();
        while (rs.next()) {
            Jugador jr = new Jugador();
            Equipo e = new Equipo();
            jr.setName(rs.getString("name"));
            jr.setBirth(rs.getDate("birth").toLocalDate());
            jr.setNbaskets(rs.getInt("nbaskets"));
            jr.setNassists(rs.getInt("nassists"));
            jr.setNrebounds(rs.getInt("nrebounds"));
            jr.setPosition(rs.getString("position"));
            e.setName(rs.getString("team"));
            jr.setTeam(e);
            j.add(jr);
        }
        rs.close();
        st.close();
        return j;
    }
    
    public List<Jugador> obtenerJugadoresPorPosicion(String pos) throws SQLException{
        String obtener = "select * from player where position LIKE '" + pos + "';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List<Jugador> j = new ArrayList<>();
        while (rs.next()) {
            Jugador jr = new Jugador();
            Equipo e = new Equipo();
            jr.setName(rs.getString("name"));
            jr.setBirth(rs.getDate("birth").toLocalDate());
            jr.setNbaskets(rs.getInt("nbaskets"));
            jr.setNassists(rs.getInt("nassists"));
            jr.setNrebounds(rs.getInt("nrebounds"));
            jr.setPosition(rs.getString("position"));
            e.setName(rs.getString("team"));
            jr.setTeam(e);
            j.add(jr);
        }
        rs.close();
        st.close();
        return j;
    }
    
    public List<Jugador> obtenerJugadoresPorFecha(int a, int m, int d) throws SQLException{
        String obtener = "select * from player where birth < '" + a + "-" + m + "-" + d + "' ;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List<Jugador> j = new ArrayList<>();
        while (rs.next()) {
            Jugador jr = new Jugador();
            Equipo e = new Equipo();
            jr.setName(rs.getString("name"));
            jr.setBirth(rs.getDate("birth").toLocalDate());
            jr.setNbaskets(rs.getInt("nbaskets"));
            jr.setNassists(rs.getInt("nassists"));
            jr.setNrebounds(rs.getInt("nrebounds"));
            jr.setPosition(rs.getString("position"));
            e.setName(rs.getString("team"));
            jr.setTeam(e);
            j.add(jr);
        }
        rs.close();
        st.close();
        return j;
    }
    
    public List obtenerMediaMaxMinPorPosicion() throws SQLException{
        String obtener = "select position, avg(nbaskets), max(nbaskets), min(nbaskets), avg(nassists), max(nassists), min(nassists), avg(nrebounds), max(nrebounds), min(nrebounds) from player group by position;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List j = new ArrayList<>();
        ArrayList avg = new ArrayList<>();
        while (rs.next()) {
            avg = new ArrayList<>();
            avg.add(rs.getString("position"));
            avg.add(rs.getInt("avg(nbaskets)"));
            avg.add(rs.getInt("max(nbaskets)"));
            avg.add(rs.getInt("min(nbaskets)"));
            avg.add(rs.getInt("avg(nassists)"));
            avg.add(rs.getInt("max(nassists)"));
            avg.add(rs.getInt("min(nassists)"));
            avg.add(rs.getInt("avg(nrebounds)"));
            avg.add(rs.getInt("max(nrebounds)"));
            avg.add(rs.getInt("min(nrebounds)"));
            j.add(avg);
        }
        rs.close();
        st.close();
        return j;
    }
    
    public List obtenerRankingporCanastas() throws SQLException{
        String obtener = "select name, nbaskets from player order by nbaskets desc;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List<Jugador> j = new ArrayList<>();
        int i=0;
        while (rs.next()) {
            Jugador jr = new Jugador();
            jr.setName(rs.getString("name"));
            jr.setNbaskets(rs.getInt("nbaskets"));
            j.add(jr);
        }
        rs.close();
        st.close();
        return j;
    }
    
    public List obtenerJugadorRanking(String nombre) throws SQLException{
        String obtener = "select name, nbaskets from player order by nbaskets desc;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List j = new ArrayList<>();
        ArrayList rank = new ArrayList<>();
        int i=1;
        while (rs.next()) {
            if(rs.getString("name").equalsIgnoreCase(nombre)){
                rank = new ArrayList<>();
                rank.add(i);
                rank.add(rs.getString("name"));
                rank.add(rs.getInt("nbaskets"));
                j.add(rank);
            }
            i++;
        }
        rs.close();
        st.close();
        return j;
    }
    
    public List<Equipo> obtenerEquipoPorCiudad(String localidad) throws SQLException{
        String obtener = "select * from team where city = '" + localidad + "';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List<Equipo> e = new ArrayList<>();
        while (rs.next()) {
            Equipo eq = new Equipo();
            eq.setName(rs.getString("name"));
            eq.setCity(rs.getString("city"));
            eq.setCreation(rs.getDate("creation").toLocalDate());
            e.add(eq);
        }
        rs.close();
        st.close();
        return e;
    }
    
    public List<Jugador> obtenerJugadorporEquipo(Equipo equipo) throws SQLException{
        String obtener = "select * from player where team = '" + equipo.getName() + "';";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List<Jugador> j = new ArrayList<>();
        while (rs.next()) {
            Jugador jr = new Jugador();
            Equipo e = new Equipo();
            jr.setName(rs.getString("name"));
            jr.setBirth(rs.getDate("birth").toLocalDate());
            jr.setNbaskets(rs.getInt("nbaskets"));
            jr.setNassists(rs.getInt("nassists"));
            jr.setNrebounds(rs.getInt("nrebounds"));
            jr.setPosition(rs.getString("position"));
            e.setName(rs.getString("team"));
            jr.setTeam(e);
            j.add(jr);
        }
        rs.close();
        st.close();
        return j;
    }
    
    public List<Jugador> obtenerJugadorporEquipoIPosicion(Equipo equipo, String posicion) throws SQLException{
        String obtener = "select * from player where team = '" + equipo.getName() + "' and position LIKE '" + posicion + "' ;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        List<Jugador> j = new ArrayList<>();
        while (rs.next()) {
            Jugador jr = new Jugador();
            Equipo e = new Equipo();
            jr.setName(rs.getString("name"));
            jr.setBirth(rs.getDate("birth").toLocalDate());
            jr.setNbaskets(rs.getInt("nbaskets"));
            jr.setNassists(rs.getInt("nassists"));
            jr.setNrebounds(rs.getInt("nrebounds"));
            jr.setPosition(rs.getString("position"));
            e.setName(rs.getString("team"));
            jr.setTeam(e);
            j.add(jr);
        }
        rs.close();
        st.close();
        return j;
    }
    
    public Jugador obtenerJugadorMaxCanastasEquipo(Equipo equipo) throws SQLException{
        String obtener = "select * from player where team like '" + equipo.getName() + "' group by nbaskets desc limit 1;";
        Statement st = conexion.createStatement();
        ResultSet rs = st.executeQuery(obtener);
        Jugador j = new Jugador();
        while (rs.next()) {
            Equipo e = new Equipo();
            j.setName(rs.getString("name"));
            j.setBirth(rs.getDate("birth").toLocalDate());
            j.setNbaskets(rs.getInt("nbaskets"));
            j.setNassists(rs.getInt("nassists"));
            j.setNrebounds(rs.getInt("nrebounds"));
            j.setPosition(rs.getString("position"));
            e.setName(rs.getString("team"));
            j.setTeam(e);
        }
        rs.close();
        st.close();
        return j;
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
