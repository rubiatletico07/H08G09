public class Tripleta {
    // Estos son los tres datos de una tripleta
    private String s;
    private String p;
    private String o;
    public Tripleta() {
        // Constructor vacio para que Gson no se queje
    }
    public Tripleta(String s, String p, String o) {
        this.s = s;
        this.p = p;
        this.o = o;
    }
    public String getS() {
        return s;
    }
    public String getP() {
        return p;
    }
    public String getO() {
        return o;
    }
    @Override
    public String toString() {
        // Lo  muestro como una tripleta
        return "<\"" + s + "\", \"" + p + "\", \"" + o + "\">";
    }
}