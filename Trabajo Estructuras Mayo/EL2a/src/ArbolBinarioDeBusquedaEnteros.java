public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> { // arbol de busqueda preparado para numeros enteros

    public int getSuma() { // metodo que calcula la suma de todos los numeros del arbol
        int suma = 0; // empezamos la suma en 0

        for (Integer numero : getListaOrdenCentral()) { // recorremos todos los numeros del arbol
            suma = suma + numero; // sumamos cada numero al total
        }

        return suma; // devolvemos la suma final
    }
}
