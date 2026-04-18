/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author 2jcue
 */
public class ItemPedido {

    private final Producto producto;
    private int cantidad;

    public ItemPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void agregarCantidad(int cantidadAdicional) {
        this.cantidad = this.cantidad + cantidadAdicional;
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
