import java.util.List;

public class PruebaOrdenada { // programa de prueba insertando los numeros de 0 a 128 en orden

    public static void main(String[] args) { // metodo principal del programa
        ArbolBinarioDeBusquedaEnteros arbol = new ArbolBinarioDeBusquedaEnteros(); // creamos un arbol de enteros vacio

        for (int i = 0; i <= 128; i++) { // recorremos todos los numeros desde 0 hasta 128
            arbol.add(i); // insertamos cada numero en el arbol
        }

        mostrarResultados(arbol); // mostramos todas las respuestas que pide la practica
    }

    private static void mostrarResultados(ArbolBinarioDeBusquedaEnteros arbol) { // metodo para mostrar los resultados de la prueba
        System.out.println("prueba insertando de 0 a 128 en orden"); // titulo de la prueba
        System.out.println("-------------------------------------"); // separador visual

        int sumaGetSuma = arbol.getSuma(); // calculamos la suma usando getSuma
        int sumaPreOrden = sumarLista(arbol.getListaPreOrden()); // calculamos la suma recorriendo en preorden
        int sumaOrdenCentral = sumarLista(arbol.getListaOrdenCentral()); // calculamos la suma recorriendo en orden central
        int sumaPostOrden = sumarLista(arbol.getListaPostOrden()); // calculamos la suma recorriendo en postorden

        System.out.println("suma con getSuma: " + sumaGetSuma); // mostramos la suma normal
        System.out.println("suma con preorden: " + sumaPreOrden); // mostramos la suma con preorden
        System.out.println("suma con orden central: " + sumaOrdenCentral); // mostramos la suma con orden central
        System.out.println("suma con postorden: " + sumaPostOrden); // mostramos la suma con postorden

        ArbolBinarioDeBusqueda<Integer> subIzquierda = arbol.getSubArbolIzquierda(); // obtenemos el subarbol izquierdo
        ArbolBinarioDeBusqueda<Integer> subDerecha = arbol.getSubArbolDerecha(); // obtenemos el subarbol derecho

        int sumaIzquierda = sumarLista(subIzquierda.getListaOrdenCentral()); // sumamos el subarbol izquierdo
        int sumaDerecha = sumarLista(subDerecha.getListaOrdenCentral()); // sumamos el subarbol derecho
        int raiz = arbol.getDatoRaiz(); // guardamos el valor de la raiz

        System.out.println("suma subarbol izquierdo: " + sumaIzquierda); // mostramos la suma izquierda
        System.out.println("raiz: " + raiz); // mostramos la raiz
        System.out.println("suma subarbol derecho: " + sumaDerecha); // mostramos la suma derecha
        System.out.println("izquierda + raiz + derecha: " + (sumaIzquierda + raiz + sumaDerecha)); // comprobamos la suma total
        System.out.println("explicacion: la suma total sale de sumar el subarbol izquierdo, la raiz y el subarbol derecho"); // explicacion sencilla

        System.out.println("grado del arbol: " + arbol.getGrado()); // mostramos el grado del arbol
        System.out.println("altura del arbol: " + arbol.getAltura()); // mostramos la altura del arbol
        System.out.println("datos del nivel 0: " + arbol.getListaDatosNivel(0)); // mostramos los datos del nivel 0
        System.out.println("datos del nivel 1: " + arbol.getListaDatosNivel(1)); // mostramos los datos del nivel 1
        System.out.println("es homogeneo: " + arbol.isArbolHomogeneo()); // comprobamos si es homogeneo
        System.out.println("es completo: " + arbol.isArbolCompleto()); // comprobamos si es completo
        System.out.println("es casi completo: " + arbol.isArbolCasiCompleto()); // comprobamos si es casi completo

        List<Integer> camino110 = arbol.getCamino(110); // calculamos el camino hasta el 110
        System.out.println("camino hasta 110: " + camino110); // mostramos el camino
        System.out.println("longitud del camino hasta 110: " + (camino110.size() - 1)); // mostramos la longitud del camino

    }

    private static int sumarLista(List<Integer> lista) { // metodo auxiliar para sumar una lista de enteros
        int suma = 0; // empezamos la suma en 0

        for (Integer numero : lista) { // recorremos cada numero de la lista
            suma = suma + numero; // añadimos el numero a la suma
        }

        return suma; // devolvemos la suma final
    }
}

/*
Verifica que la suma es la misma cuando se suman los elementos de los subárboles izquierdo y derecho.
¿Por qué?

La suma con getSuma(), con preorden, con orden central y con postorden
da siempre lo mismo, porque los recorridos cambian el orden en el que vemos
los numeros, pero no cambian los numeros que hay dentro del arbol.

¿Cuál es la altura del árbol?

Como hay numeros desde 0 hasta 128, el camino mas largo o que es lo mismo la altura, tiene longitud 128.

¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?

El camino hasta 110 es muy largo, porque para llegar al 110 hay que pasar por: 0, 1, 2, 3, ..., 110.
La longitud del camino hasta 110 es 110.
*/
