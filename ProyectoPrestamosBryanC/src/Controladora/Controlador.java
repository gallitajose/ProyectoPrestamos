package Controladora;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

import Logica.Alerta;
import Logica.Categoria;
import Logica.Item;
import Logica.Prestamo;
import Logica.Tipo;
import Logica.Usuario;

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


    public void crearItem(String descripcion, String tipoDescripcion, String nombre, int codigo) throws Exception {
        if (items.containsKey(codigo)) {
            throw new Exception("Codigo para item ya existe");
        }
        Tipo tipo = tipos.get(tipoDescripcion);
        if (tipo == null) {
            throw new Exception("El tipo seleccionado no existe");
        }
        Item nuevo = new Item(descripcion, tipo, nombre, codigo);
        items.put(codigo, nuevo);
        tipo.agregarItem(nuevo);
    }

    public void editarItem(int codigo, String nuevaDescripcion, String nuevoTipo, String nuevoNombre, int nuevoCodigo) throws Exception {
        Item item = items.get(codigo);
        if (item == null) {
            throw new Exception("No existe un ítem con ese código");
        }
        Tipo tipo = tipos.get(nuevoTipo);
        if (tipo == null) {
            throw new Exception("El tipo seleccionado no existe");
        }
        if (nuevoCodigo != codigo && items.containsKey(nuevoCodigo)) {
            throw new Exception("Codigo para item ya existe");
        }

        item.getTipo().eliminarItem(codigo);

        item.setDescripcion(nuevaDescripcion);
        item.setNombre(nuevoNombre);
        item.setTipo(tipo);

        if (nuevoCodigo != codigo) {
            items.remove(codigo);
            item.setCodigo(nuevoCodigo);
            items.put(nuevoCodigo, item);

            for (Categoria c : item.getCategorias().values()) {
                c.eliminarItem(codigo);
                c.agregarItem(item);
            }
        }

        tipo.agregarItem(item);
    }

    public void borrarItem(int codigo) throws Exception {
        Item item = items.get(codigo);
        if (item == null) {
            throw new Exception("No existe un Item con ese condigo");
        }
        if (itemEnPrestamo(codigo)) {
            throw new Exception("El ítem esta en un préstamo");
        }

        item.getTipo().eliminarItem(codigo);
        for (Categoria c : item.getCategorias().values()) {
            c.eliminarItem(codigo);
        }
        items.remove(codigo);
    }

    public Item buscarItem(int codigo) throws Exception {
        Item item = items.get(codigo);
        if (item == null) {
            throw new Exception("No existe un Item con ese condigo");
        }
        return item;
    }

    private boolean itemEnPrestamo(int codigo) {
        for (Prestamo p : prestamos.values()) {
            if (p.getItems().containsKey(codigo)) {
                return true;
            }
        }
        return false;
    }

    private Prestamo buscarPrestamoDeItem(int codigo) {
        for (Prestamo p : prestamos.values()) {
            if (p.getItems().containsKey(codigo)) {
                return p;
            }
        }
        return null;
    }



    public void agregarUsuario(String nombre, String telefono, String correo) throws Exception {
        if (usuarios.containsKey(telefono)) {
            throw new Exception("Ya existe un usuario con ese telefono");
        }
        usuarios.put(telefono, new Usuario(nombre, telefono, correo));
    }

    public void editarUsuario(String telefono, String nuevoNombre, String nuevoTelefono, String nuevoCorreo) throws Exception {
        Usuario usuario = usuarios.get(telefono);
        if (usuario == null) {
            throw new Exception("No se encontro un usuario con ese telefono");
        }
        if (!telefono.equals(nuevoTelefono) && usuarios.containsKey(nuevoTelefono)) {
            throw new Exception("Ya existe un usuario con ese telefono");
        }

        usuario.setNombre(nuevoNombre);
        usuario.setCorreo(nuevoCorreo);

        if (!telefono.equals(nuevoTelefono)) {
            usuarios.remove(telefono);
            usuario.setTelefono(nuevoTelefono);
            usuarios.put(nuevoTelefono, usuario);
        }
    }

    public void borrarUsuario(String telefono) throws Exception {
        Usuario usuario = usuarios.get(telefono);
        if (usuario == null) {
            throw new Exception("No se encontro un usuario con ese telefono");
        }
        if (!usuario.getPrestamos().isEmpty()) {
            throw new Exception("El usuario tiene prestamos activos");
        }
        usuarios.remove(telefono);
    }

    public Usuario buscarPersona(String telefono) throws Exception {
        Usuario usuario = usuarios.get(telefono);
        if (usuario == null) {
            throw new Exception("No se encontro un usuario con ese telefono");
        }
        return usuario;
    }



    public void crearCategoria(String nombre) throws Exception {
        if (categorias.containsKey(nombre)) {
            throw new Exception("Categoria ya existe");
        }
        categorias.put(nombre, new Categoria(nombre));
    }

    public void editarCategoria(String nombre, String nuevoNombre) throws Exception {
        Categoria categoria = categorias.get(nombre);
        if (categoria == null) {
            throw new Exception("No se encontro la categoria");
        }
        if (!nombre.equals(nuevoNombre) && categorias.containsKey(nuevoNombre)) {
            throw new Exception("Categoria ya existe");
        }

        categoria.setNombre(nuevoNombre);

        if (!nombre.equals(nuevoNombre)) {
            categorias.remove(nombre);
            categorias.put(nuevoNombre, categoria);

            for (Item item : categoria.getItems().values()) {
                item.getCategorias().remove(nombre);
                item.getCategorias().put(nuevoNombre, categoria);
            }
        }
    }

    public void borrarCategoria(String nombre) throws Exception {
        Categoria categoria = categorias.get(nombre);
        if (categoria == null) {
            throw new Exception("No se encontro la categoria");
        }
        for (Item item : categoria.getItems().values()) {
            item.eliminarCategoria(nombre);
        }
        categorias.remove(nombre);
    }

    public Categoria buscarCategoria(String nombre) throws Exception {
        Categoria categoria = categorias.get(nombre);
        if (categoria == null) {
            throw new Exception("No se encontro la categoria");
        }
        return categoria;
    }


    public void crearTipo(String descripcion) throws Exception {
        if (tipos.containsKey(descripcion)) {
            throw new Exception("Descripcion ya existente");
        }
        tipos.put(descripcion, new Tipo(descripcion));
    }

    public void editarTipo(String descripcion, String nuevaDescripcion) throws Exception {
        Tipo tipo = tipos.get(descripcion);
        if (tipo == null) {
            throw new Exception("No se encontro el tipo");
        }
        if (!descripcion.equals(nuevaDescripcion) && tipos.containsKey(nuevaDescripcion)) {
            throw new Exception("Descripcion ya existente");
        }

        tipo.setDescripcion(nuevaDescripcion);

        if (!descripcion.equals(nuevaDescripcion)) {
            tipos.remove(descripcion);
            tipos.put(nuevaDescripcion, tipo);
        }
    }

    public void borrarTipo(String descripcion) throws Exception {
        Tipo tipo = tipos.get(descripcion);
        if (tipo == null) {
            throw new Exception("No se encontro el tipo");
        }
        if (!tipo.getItems().isEmpty()) {
            throw new Exception("Tipo tiene items establecidods");
        }
        tipos.remove(descripcion);
    }

    public Tipo buscarTipo(String descripcion) throws Exception {
        Tipo tipo = tipos.get(descripcion);
        if (tipo == null) {
            throw new Exception("No se encontro el tipo");
        }
        return tipo;
    }


    public Prestamo buscarPrestamo(int idPrestamo) throws Exception {
        Prestamo prestamo = prestamos.get(idPrestamo);
        if (prestamo == null) {
            throw new Exception("Error al buscar préstamo: no se encontró el préstamo");
        }
        return prestamo;
    }

    public void hacerPrestamo(String telefono, int idPrestamo) throws Exception {
        Usuario usuario = usuarios.get(telefono);
        if (usuario == null) {
            throw new Exception("Usuario no encontrado");
        }
        if (prestamos.containsKey(idPrestamo)) {
            throw new Exception("Prestamo con un mismo ID ya existente");
        }
        Prestamo nuevo = new Prestamo(idPrestamo, new Date(), usuario, null);
        prestamos.put(idPrestamo, nuevo);
        usuario.agregarPrestamo(nuevo);
    }

    public void agregarItemAPrestamo(int idPrestamo, int idItem) throws Exception {
        Prestamo prestamo = prestamos.get(idPrestamo);
        if (prestamo == null) {
            throw new Exception("No se encontro el prestamo");
        }
        Item item = items.get(idItem);
        if (item == null) {
            throw new Exception("No se encontro el item");
        }
        if (itemEnPrestamo(idItem)) {
            throw new Exception("El item ya esta en otro prestamo");
        }
        prestamo.agregarItem(item);
    }

    public void eliminarItemDePrestamo(int idPrestamo, int idItem) throws Exception {
        Prestamo prestamo = prestamos.get(idPrestamo);
        if (prestamo == null) {
            throw new Exception("No se encontro el prestamo");
        }
        if (!prestamo.getItems().containsKey(idItem)) {
            throw new Exception("Ese item no pertenece a este prestamo");
        }
        prestamo.eliminarItem(idItem);
    }

    public void retornarItemPrestamo(int idPrestamo, int idItem) throws Exception {
        eliminarItemDePrestamo(idPrestamo, idItem);
    }

    public void terminarPrestamo(int idPrestamo) throws Exception {
        Prestamo prestamo = prestamos.get(idPrestamo);
        if (prestamo == null) {
            throw new Exception("No se encontro el prestamo");
        }
        prestamo.getUsuario().eliminarPrestamo(idPrestamo);
        prestamos.remove(idPrestamo);
    }

    public void agregarAlertaPrestamo(int idPrestamo, String mensaje, int frecuencia) throws Exception {
        Prestamo prestamo = prestamos.get(idPrestamo);
        if (prestamo == null) {
            throw new Exception("No se encontro el prestamo");
        }
        prestamo.setAlerta(new Alerta(mensaje, frecuencia, new Date()));
    }



    public Map<String, Usuario> listarUsuarios() {
        return usuarios;
    }

    public Map<Integer, Item> listarItems() {
        return items;
    }

    public Map<String, Categoria> listarCategorias() {
        return categorias;
    }

    public Map<String, Tipo> listarTipos() {
        return tipos;
    }

    public Map<Integer, Prestamo> listarPrestamos() {
        return prestamos;
    }


    public String reporteUsuario() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Reporte por Usuario ===\n");

        usuarios.values().stream()
            .sorted((a, b) -> a.getNombre().compareToIgnoreCase(b.getNombre()))
            .forEach(usuario -> {
                sb.append("Usuario: ").append(usuario.getNombre());
                sb.append(" | Teléfono: ").append(usuario.getTelefono());
                sb.append(" | Correo: ").append(usuario.getCorreo()).append("\n");

                if (usuario.getPrestamos().isEmpty()) {
                    sb.append("  (sin préstamos)\n");
                } else {
                    for (Prestamo p : usuario.getPrestamos()) {
                        sb.append("  Préstamo #").append(p.getIdPrestamo());
                        sb.append(" | Fecha: ").append(p.getFecha()).append("\n");
                        for (Item item : p.getItems().values()) {
                            sb.append("    - ").append(item.getNombre()).append("\n");
                        }
                    }
                }
            });

        return sb.toString();
    }
    public Map<Integer, Item> listarItemsDisponibles() {
        Map<Integer, Item> disponibles = new HashMap<>();
        for (Item item : items.values()) {
            if (!itemEnPrestamo(item.getCodigo())) {
                disponibles.put(item.getCodigo(), item);
            }
        }
        return disponibles;
    }
    public String reporteItem() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Reporte por Ítem ===\n");

        items.values().stream()
            .sorted((a, b) -> a.getNombre().compareToIgnoreCase(b.getNombre()))
            .forEach(item -> {
                sb.append("Ítem: ").append(item.getNombre());
                sb.append(" | Código: ").append(item.getCodigo());
                sb.append(" | Descripción: ").append(item.getDescripcion());
                sb.append(" | Tipo: ").append(item.getTipo().getDescripcion());

                Prestamo prestamoActual = buscarPrestamoDeItem(item.getCodigo());
                if (prestamoActual != null) {
                    sb.append(" | Prestado a: ").append(prestamoActual.getUsuario().getNombre());
                } else {
                    sb.append(" | Disponible");
                }
                sb.append("\n");
            });

        return sb.toString();
    }

    public String reporteCategoria() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Reporte por Categoría ===\n");

        categorias.values().stream()
            .sorted((a, b) -> a.getNombre().compareToIgnoreCase(b.getNombre()))
            .forEach(categoria -> {
                sb.append("Categoría: ").append(categoria.getNombre()).append("\n");

                if (categoria.getItems().isEmpty()) {
                    sb.append("  (sin ítems)\n");
                } else {
                    for (Item item : categoria.getItems().values()) {
                        sb.append("  - ").append(item.getNombre());
                        sb.append(" (Código: ").append(item.getCodigo()).append(")\n");
                    }
                }
            });

        return sb.toString();
    }
    public int generarIdPrestamo() {
        if (prestamos.isEmpty()) return 1;
        return prestamos.keySet().stream().max(Integer::compareTo).get() + 1;
    }

    public String reporteTipo() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Reporte por Tipo ===\n");

        tipos.values().stream()
            .sorted((a, b) -> a.getDescripcion().compareToIgnoreCase(b.getDescripcion()))
            .forEach(tipo -> {
                sb.append("Tipo: ").append(tipo.getDescripcion()).append("\n");

                if (tipo.getItems().isEmpty()) {
                    sb.append("  (sin ítems)\n");
                } else {
                    for (Item item : tipo.getItems().values()) {
                        sb.append("  - ").append(item.getNombre());
                        sb.append(" (Código: ").append(item.getCodigo()).append(")\n");
                    }
                }
            });

        return sb.toString();
    }
}