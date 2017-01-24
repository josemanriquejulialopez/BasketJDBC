
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
            Equipo a = new Equipo("Adrian", "Gordo", LocalDate.of(1996, 02,03));
            Equipo y = new Equipo("Yolos", "Combateeee", LocalDate.of(1990, 8,26));
            
            Jugador j1 = new Jugador("Pepe", LocalDate.of(1998,02,10), 50, 15, 10, "Alero",p);
            Jugador j2 = new Jugador("Alex", LocalDate.of(1993,10,25), 25, 2, 6, "Base",p);
            Jugador j3 = new Jugador("Alolo", LocalDate.of(1990,02,12), 30, 8, 2, "Base",p);
            
            Jugador j4 = new Jugador("Adrian", LocalDate.of(1998,02,10), 15, 4, 3, "Pivot",a);
            Jugador j5 = new Jugador("Gordo", LocalDate.of(1993,10,25), 8, 10, 1, "Base",a);
            Jugador j6 = new Jugador("Gordako", LocalDate.of(1990,10,25), 20, 5, 1, "Alero",a);
            
            Jugador j7 = new Jugador("Sheila", LocalDate.of(1992,02,10), 6, 11, 3, "Alero",y);
            Jugador j8 = new Jugador("Bily", LocalDate.of(1997,10,25), 12, 7, 5, "Pivot",y);
            Jugador j9 = new Jugador("Kim", LocalDate.of(1999,10,25), 16, 6, 2, "Pivot",y);
            
            /*
            gestor.insertEquipo(p);
            gestor.insertEquipo(a);
            gestor.insertEquipo(y);
            gestor.insertJugador(j1);gestor.insertJugador(j2);gestor.insertJugador(j3);
            gestor.insertJugador(j4);gestor.insertJugador(j5);gestor.insertJugador(j6);
            gestor.insertJugador(j7);gestor.insertJugador(j8);gestor.insertJugador(j9);
                    */
            
            System.out.println("Jugadores y Equipos dados de alta.");
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
            System.out.println("-------------------------------------");
            gestor.modificarCaAsRe(j5);
            System.out.println("Datos j5 ACTUALIZADOS");
            System.out.println("-------------------------------------");
            gestor.modificarEquipo(j6,y);
            System.out.println("Equipo del jugador j6 ACTUALIZADO");
            System.out.println("-------------------------------------");
            gestor.borrarJugador(j9);
            System.out.println("Jugador j9 BORRADO");
            System.out.println("-------------------------------------");
            System.out.println("Jugador por Nombre");
            System.out.println(gestor.obtenerJugadorporNombre(j4));
            System.out.println("-------------------------------------");
            System.out.println("Jugador por Nombre que Contenga Al");
            
            List<Jugador> JugadoresPorNombre = gestor.obtenerJugadoresPorNombre("Al");
            for (Jugador j : JugadoresPorNombre) {
                System.out.println(j);
            }
            System.out.println("-------------------------------------");
            System.out.println("Jugador por Canastas mayor que 28");
            List<Jugador> JugadoresPorCanastas= gestor.obtenerJugadoresPorNumCanastas(28);
            for (Jugador j : JugadoresPorCanastas) {
                System.out.println(j);
            }
            System.out.println("-------------------------------------");
            System.out.println("Jugador por Assistencias entre 16-20");
            List<Jugador> JugadoresPorAssistencias= gestor.obtenerJugadoresPorNumAssistencias(9,11);
            for (Jugador j : JugadoresPorAssistencias) {
                System.out.println(j);
            }
            System.out.println("-------------------------------------");
            System.out.println("Jugador de Posicion alero");
            List<Jugador> JugadoresPorPosicion= gestor.obtenerJugadoresPorPosicion("alero");
            for (Jugador j : JugadoresPorPosicion) {
                System.out.println(j);
            }
            System.out.println("-------------------------------------");
            System.out.println("Jugadores por Fecha Anterior a...");
            List<Jugador> JugadoresPorFecha= gestor.obtenerJugadoresPorFecha(1993, 10, 20);
            for (Jugador j : JugadoresPorFecha) {
                System.out.println(j);
            }
            System.out.println("-------------------------------------");
            System.out.println("Avg, Max, Min por Posicion");
            List<Jugador> AvgMaxMinPosicion= gestor.obtenerMediaMaxMinPorPosicion();
            for(int i=0;i<AvgMaxMinPosicion.size();i++){
                System.out.println(AvgMaxMinPosicion.get(i));
            }
            System.out.println("-------------------------------------");
            System.out.println("Ranking por Canastas");
            List<Jugador> RankingPorCanastas= gestor.obtenerRankingporCanastas();
            int i=1;
            for (Jugador j : RankingPorCanastas) {
                System.out.println(i + ". " + j.getName() + " - " + j.getNbaskets());
                i++;
            }
            System.out.println("-------------------------------------");
            System.out.println("Ranking por Canastas");
            List<Jugador> JugadorPorRanking= gestor.obtenerJugadorRanking("Alolo");
            for (int z=0;z<JugadorPorRanking.size();z++){ 
                System.out.println(JugadorPorRanking.get(z));
            }
            System.out.println("-------------------------------------");
            System.out.println("Jugadores del Equipo Yolos");
            List<Jugador> JugadorDeEquipo = gestor.obtenerJugadorporEquipo(y);
            for (Jugador j : JugadorDeEquipo) {
                System.out.println(j);
            }
            System.out.println("-------------------------------------");
            System.out.println("Jugadores del Equipo Yolos de posicion Pivot");
            List<Jugador> JugadorDeEquipoIPosicion = gestor.obtenerJugadorporEquipoIPosicion(y, "pivot");
            for (Jugador j : JugadorDeEquipoIPosicion) {
                System.out.println(j);
            }
            System.out.println("-------------------------------------");
            System.out.println("Jugador Num 1 del equipo y");
            System.out.println(gestor.obtenerJugadorMaxCanastasEquipo(y));
            System.out.println("-------------------------------------");
            
            
            
            
            gestor.desconectar();
            System.out.println("Cerrada la conexión con la bbdd.");
        } catch (SQLException ex) {
            System.out.println("Error con la BBDD: "+ex.getMessage());
        } 
    }
}
