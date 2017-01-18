
package basket;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Equipo;
import model.Jugador;
import persistance.BasquetJDBC;


public class Basket {
    
    
   
    
    public static void main(String[] args) {
        BasquetJDBC gestor = new BasquetJDBC();
        try {
            System.out.println("Estableciendo conexión con la bbdd...");
            gestor.conectar();
            System.out.println("Conectado a la bbdd basket");
            Equipo p = new Equipo("Pepes", "Pocholo", LocalDate.of(1996, 02,03));
            Jugador a = new Jugador("Pepe", LocalDate.of(1998,02,10), 2, 34, 2, "Postres",p);
            gestor.insertEquipo(p);
            gestor.insertJugador(a);
            System.out.println("Cocinero dado de alta.");
            List<Equipo> todosEquipos = gestor.selectAllEquipos();
            List<Jugador> todosJugadores = gestor.selectAllJugadores();
            System.out.println("Listado de equios");
            for (Equipo e : todosEquipos) {
                System.out.println(e);
            }
            System.out.println("Listado de jugadores");
            for (Jugador j : todosJugadores) {
                System.out.println(j);
            }
            gestor.desconectar();
            System.out.println("Cerrada la conexión con la bbdd.");
        } catch (SQLException ex) {
            System.out.println("Error con la BBDD: "+ex.getMessage());
        } 
    }
}
