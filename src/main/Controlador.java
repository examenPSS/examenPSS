/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import newpackage.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *Clase controlador publica del proyecto
 * * @version 1.0.0, 19/05/2020
 * @author Pablo Sánchez Sanvicente
 *
 */
public class Controlador {
/**
 *PSS: Metodo que lista peliculas
 * @return deviuelve una lista de peliculas.
 */
    public List<Film> listar(){
        List<Film> films = null;
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        if(session != null){
            Query query = session.createQuery("from Film");
            films = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        return films;
    }
    /**
 *PSS: Metodo que busca peliculas
 * @return deviuelve las peliculs listadas.
 */
    public List<Film> buscarPeliculas(String pelicula){
        List<Film> films = null;
        pelicula = "'%" + pelicula + "%'";
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        if(session != null){
            Query query = session.createQuery("From Film film where film.title like " + pelicula);
            films = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        return films;
    }
    /**
 *Metodo que busca peliculas por categoria
 * @param categoria cadena para la categoria de la pelicula a buscar
 * @return deviuelve una lista de peliculas segun la categoria dada.
 */
    public List<Film> buscarPorCategoria(String categoria){
        List<Film> films = null;
        categoria = "'%" + categoria + "%'";
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        if(session != null){
            Query query = session.createQuery("select fc.film " +
                "from FilmCategory fc " +
                "where fc.category.name like " + categoria);
            films = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        
        return films;
    }
       /**
 *Metodo que busca peliculas por caracteristicas especiales
 * @param caracteristicas cadena para la caracteristica especial
 * @return deviuelve una lista de peliculas segun caracteristica especial
 */
    public List<Film> buscarPorCaracteristicasEspeciales(String caracteristicas){
        List<Film> films = null;
        caracteristicas = "'%" + caracteristicas + "%'";
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        if(session != null){
            Query query = session.createQuery("from Film f where f.specialFeatures like " + caracteristicas);
            films = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        return films;
    }
          /**
 *Metodo que devuelve las categorias
 * @return deviuelve lista de categorias
 */
    public List<String> categorias(){
        List<String> categorias = null;
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        if(session != null){
            Query query = session.createQuery("select distinct name from Category");
            categorias = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        return categorias;
    }
 /**
 *Metodo devuelve un array categorias
 * @return array categorias
 */
    public ArrayList<String> arrayCategorias(){
        List<String> categorias = categorias();
        ArrayList<String> aux = new ArrayList<>();
        
        for(String str: categorias){
            aux.add(str);
        }
        
        return aux;
    }
     /**
 *Metodo devuelve las caracteristicas
 * @return categorias peliculas
 */
    public List<String> caracteristicas(){
        List<String> caracteristicas = null;
        
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
        if(session != null){
            Query query = session.createQuery("seleccione caractersticas especiales de pelicula");
            caracteristicas = query.list();
            session.getTransaction();
        }
        
        session.close();
        
        return caracteristicas;
    }
       /**
 *Metodo devuelve las array caracteristicas
 * @return array caracteristicas peliculas
 */
    public ArrayList<String> arrayCaracteristicas(){
        ArrayList<String> aux = new ArrayList<>();
        List<String> list = caracteristicas();
        
        for(String str: list){
            aux.add(str);
        }
        
        
        return aux;
    }
          /**
 *Metodo agrega caracteristicas
 * @return arrayList caracteristicas peliculas
 */
    public ArrayList<String> distinctCaracteristicas(){
        ArrayList<String> caracteristicas = new ArrayList<>();
        
        caracteristicas.add("Presentacion");
        caracteristicas.add("Opiniones");
        caracteristicas.add("Tras la escena");
        caracteristicas.add("Escenas eliminadas");
        
        return caracteristicas;
    }
}