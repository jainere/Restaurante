/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author 2jcue
 */
public class Factura {
    // Constantes de negocio 

    private static final double TASA_IVA = 0.19;
    private static final double TASA_PROPINA = 0.10;
    private static final double TASA_DESCUENTO = 0.05;
    private static final double UMBRAL_PROPINA = 50000;
    private static final int MIN_ITEMS_DESCUENTO = 3;

    private final Pedido pedido;
    private final int numero;

    public Factura(Pedido pedido, int numero) {
        this.pedido = pedido;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Suma precio x cantidad para cada producto pedido.
     */
    public double calcularSubtotal() {
        return pedido.calcularSubtotal();
    }

    /**
     * Aplica descuento del 5% si el pedido tiene más de 3 productos diferentes.
     */
    public double calcularSubtotalConDescuento() {
        double subtotal = calcularSubtotal();
        if (pedido.contarItemsDiferentes() > MIN_ITEMS_DESCUENTO) {
            return subtotal - (subtotal * TASA_DESCUENTO);
        }
        return subtotal;
    }

    /**
     * Calcula el IVA del 19% sobre el subtotal con descuento .
     */
    public double calcularIVA() {
        return calcularSubtotalConDescuento() * TASA_IVA;
    }

    /**
     * Calcula la propina del 10% si el subtotal supera $50.000 y lo aplica al total con iva
     */
    public double calcularPropina() {
        double subtotalConDescuento = calcularSubtotalConDescuento();
        if (subtotalConDescuento > UMBRAL_PROPINA) {
            double totalConIva = subtotalConDescuento + calcularIVA();
            return totalConIva * TASA_PROPINA;
        }
        return 0;
    }

    /**
     * Orquesta subtotal + IVA + propina para obtener el total final.
     */
    public double calcularTotal() {
        double subtotalConDescuento = calcularSubtotalConDescuento();
        double montoIva = calcularIVA();
        double propina = calcularPropina();
        return subtotalConDescuento + montoIva + propina;
    }
}
