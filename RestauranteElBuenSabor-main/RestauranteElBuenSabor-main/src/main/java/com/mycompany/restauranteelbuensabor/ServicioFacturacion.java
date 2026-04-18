/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author 2jcue
 */

import com.mycompany.restauranteelbuensabor.ItemPedido;

public class ServicioFacturacion {
    private static final String NOMBRE_RESTAURANTE = "El Buen Sabor";
    private static final String DIRECCION = "Calle 15 #8-32, Valledupar";
    private static final String NIT = "900.123.456-7";

    private static final String SEPARADOR_DOBLE = "========================================";
    private static final String SEPARADOR_SIMPLE = "----------------------------------------";
    private static final String FORMATO_ITEM = "%-20s x%-6d $%,.0f%n";
    private static final String FORMATO_TOTAL = "%-27s $%,.0f%n";

    public static void imprimirEncabezado() {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    RESTAURANTE " + NOMBRE_RESTAURANTE);
        System.out.println("    " + DIRECCION);
        System.out.println("    NIT: " + NIT);
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void mostrarCarta() {
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("    RESTAURANTE " + NOMBRE_RESTAURANTE);
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_DOBLE);
        int indice = 0;
        while (indice < Carta.PRODUCTOS.length) {
            Producto producto = Carta.PRODUCTOS[indice];
            System.out.printf("%d. %-22s $%,.0f%n", (indice + 1), producto.getNombre(), producto.getPrecio());
            indice++;
        }
        System.out.println(SEPARADOR_DOBLE);
    }

    public static void mostrarPedido() {
        Pedido pedido = PedidoActual.pedido;
        System.out.println("--- PEDIDO ACTUAL ---");
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf(FORMATO_ITEM,
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }
        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf(FORMATO_TOTAL, "Subtotal:", pedido.calcularSubtotal());
    }

    public static void imprimirFacturaCompleta(Factura factura) {
        double subtotalConDescuento = factura.calcularSubtotalConDescuento();
        double montoIva = factura.calcularIVA();
        double propina = factura.calcularPropina();
        double total = factura.calcularTotal();

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", factura.getNumero());
        System.out.println(SEPARADOR_SIMPLE);

        for (ItemPedido item : factura.getPedido().getItems()) {
            System.out.printf(FORMATO_ITEM,
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }

        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf(FORMATO_TOTAL, "Subtotal:", subtotalConDescuento);
        System.out.printf(FORMATO_TOTAL, "IVA (19%):", montoIva);

        if (propina > 0) {
            System.out.printf(FORMATO_TOTAL, "Propina (10%):", propina);
        }

        System.out.println(SEPARADOR_SIMPLE);
        System.out.printf(FORMATO_TOTAL, "TOTAL:", total);
        System.out.println(SEPARADOR_DOBLE);
        System.out.println("Gracias por su visita!");
        System.out.println(NOMBRE_RESTAURANTE + " - Valledupar");
        System.out.println(SEPARADOR_DOBLE);
    }
}
