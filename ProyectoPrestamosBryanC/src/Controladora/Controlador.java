package Controladora;

import java.util.ArrayList;
import Logica.Alerta;
import Logica.Categoria;
import Logica.Item;
import Logica.Prestamo;
import Logica.Tipo;
import Logica.Usuario;
import java.util.Map;
import java.util.HashMap;

public class Controlador {

    private Map<String, Usuario> usuarios = new HashMap<>();
    private Map<Integer, Item> items = new HashMap<>();
    private Map<String, Categoria> categorias = new HashMap<>();
    private Map<String, Tipo> tipos = new HashMap<>();
    private Map<Integer, Prestamo> prestamos = new HashMap<>();

    private static Controlador instancia;

    private Controlador() {
        this.usuarios = new HashMap<>();
        this.items = new HashMap<>();
        this.categorias = new HashMap<>();
        this.tipos = new HashMap<>();
        this.prestamos = new HashMap<>();
    }

    public static Controlador getInstancia() {
        if (instancia == null) {
            instancia = new Controlador();
        }
        return instancia;
    }
    
    
    
    public void crearItem(Item item) {
        items.put(item.getCodigo(), item);
    }

    public void editarItem(int codigo, Item item) {
        items.put(codigo, item);
    }

    public void borrarItem(int codigo) {
        items.remove(codigo);
    }

    public Item buscarItem(int codigo) {
        return items.get(codigo);
    }
    
    
    
    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getTelefono(), usuario);
    }

    public void borrarUsuario(String telefono) {
        usuarios.remove(telefono);
    }

    public void editarUsuario(String telefono, Usuario usuario) {
        usuarios.put(telefono, usuario);
    }

    public Usuario buscarPersona(String telefono) {
        return usuarios.get(telefono);
    }
    
    

    public void crearCategoria(Categoria categoria) {
        if (!categorias.containsKey(categoria.getNombre())) {
            categorias.put(categoria.getNombre(), categoria);
        }
    }

    public void editarCategoria(String nombre, Categoria categoria) {
        categorias.put(nombre, categoria);
    }

    public void borrarCategoria(String nombre) {
        categorias.remove(nombre);
    }

    public Categoria buscarCategoria(String nombre) {
        return categorias.get(nombre);
    }
    
    //falta tipo, prestamos y alertas
}