/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Abraham
 */
import java.util.*;
import java.sql.*;

public class Acciones_alumno {
    
    /*
    Esta clase se encarga de realizar las acciones por parte del alumno 
    las cuales son conexion con la bd y el crud 
    */
    
    //conexion con la bd
    public static Connection getConnection(){
        //definir los parametros de conexion 
        String url, user, pass;
        
        //establecer la ruta
        url="jdbc:mysql://localhost/Alumno";
        user="root";
        pass="urban";
        
        Connection con = null;
        
        try{
            //driver
            Class.forName("com.mysql.jdbc.Driver");
            //establecer la conexion
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Si conecto");
        }catch(Exception e){
            
            System.out.println("No conecto");
            //mensaje de error
            System.out.println(e.getMessage());
            //hilo de donde se origino el error
            System.out.println(e.getStackTrace());
        }
        return con;
    }
    
    //guardar un alumno 
    public static int Guardar_alumno(Alumno a){
        int estatus = 0;
        
        try{
            //Obtener la conexion
            Connection con = Acciones_alumno.getConnection();
            //querry
            String q = "insert into Alumnos (nom_alu, pass_alu, email_alu, pais_alu)"
                    + "values (?, ?, ?, ?)";
            //preparamos la sentencia
            PreparedStatement ps = con.prepareStatement(q);
            
            //obtener y enviar los datos desde alumno a la querry
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPais());
            //ejecutar la querry
            estatus = ps.executeUpdate();
            con.close();
            
            //comprobar que se hizo la querry
            System.out.println("Registro de alumno");
        }catch(Exception e){
            
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return estatus;
    }
    
    //editar los datos del alumno 
        public static int Actualizar_alumno(Alumno a){
        int estatus = 0;
        
        try{
            //Obtener la conexion
            Connection con = Acciones_alumno.getConnection();
            //querry
            String q = "update Alumnos set nom_alu = ?,"
                    + "pass_alu = ?,"
                    + "email_alu = ?,"
                    + "pais_alu = ?,"
                    + "where id_alu = ?";
            //preparamos la sentencia
            PreparedStatement ps = con.prepareStatement(q);
            
            //obtener y enviar los datos desde alumno a la querry
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getEmail());
            ps.setString(4, a.getPais());
            ps.setInt(5, a.getId());
            //ejecutar la querry
            estatus = ps.executeUpdate();
            con.close();
            
            //comprobar que se hizo la querry
            System.out.println("Actualizacion de alumno");
        }catch(Exception e){
            
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return estatus;
    }
    
    //eliminar a un alumno 
        public static int Eliminar_alumno(int id){
        int estatus = 0;
        
        try{
            //Obtener la conexion
            Connection con = Acciones_alumno.getConnection();
            //querry
            String q = "delete from Alumnos where id_alu = ?";
            //preparamos la sentencia
            PreparedStatement ps = con.prepareStatement(q);
            
            //obtener y enviar los datos desde alumno a la querry
            ps.setInt(1, id);
            
            //ejecutar la querry
            estatus = ps.executeUpdate();
            con.close();
            
            //comprobar que se hizo la querry
            System.out.println("Eliminacion de alumno");
        }catch(Exception e){
            
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return estatus;
    }
    
    
    //buscar un alumno por id
        public static Alumno getAlumnoById(int id){
            Alumno a = new Alumno();
        
        try{
            //Obtener la conexion
            Connection con = Acciones_alumno.getConnection();
            //querry
            String q = "select * from Alumnos where id_alu = ?";
            //preparamos la sentencia
            PreparedStatement ps = con.prepareStatement(q);
            
            //obtener y enviar los datos desde alumno a la querry
            ps.setInt(1, id);
            //ejecutar la consulta
            ResultSet rs = ps.executeQuery();
            
            //buscar al alumno dentro de la tabla a traves de la consulta
            if(rs.next()){
                
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setPais(rs.getString(5)); 
            }
            
            con.close();
            
            //comprobar que se hizo la querry
            System.out.println("Busqueda de alumno");
        }catch(Exception e){
            
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return a;
    }
    
    //lista de todos los alumnos
         public static List<Alumno> getAllAlumnos(){
            //instancia del array list 
            List<Alumno> lista = new ArrayList<Alumno>();
        
        try{
            //Obtener la conexion
            Connection con = Acciones_alumno.getConnection();
            //querry
            String q = "select * from Alumnos";
            //preparamos la sentencia
            PreparedStatement ps = con.prepareStatement(q);
            
            //ejecutar la consulta
            ResultSet rs = ps.executeQuery();
            
            //buscar al alumno dentro de la tabla a traves de la consulta
            while(rs.next()){
                //instancia del alumno
                Alumno a = new Alumno();
                a.setId(rs.getInt(1));
                a.setNombre(rs.getString(2));
                a.setPassword(rs.getString(3));
                a.setEmail(rs.getString(4));
                a.setPais(rs.getString(5));
                lista.add(a);
            }
            
            con.close();
            
            //comprobar que se hizo la querry
            System.out.println("Busqueda de alumno");
        }catch(Exception e){
            
            System.out.println("No encontro la tabla");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return lista;
    }
    
}
