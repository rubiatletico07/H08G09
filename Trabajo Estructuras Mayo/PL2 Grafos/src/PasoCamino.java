public class PasoCamino {
    private String desde;
    private String predicado;
    private String hasta;
    public PasoCamino(String desde, String predicado, String hasta) {
        this.desde = desde;
        this.predicado = predicado;
        this.hasta = hasta;
    }
    public String getDesde() {
        return desde;
    }
    public String getPredicado() {
        return predicado;
    }
    public String getHasta() {
        return hasta;
    }
    @Override
    public String toString() {
        // Lo muestro como si fuera una flecha del grafo
        return desde + " --" + predicado + "--> " + hasta;
    }
}