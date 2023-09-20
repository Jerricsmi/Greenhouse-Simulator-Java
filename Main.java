public class Main {
    public static void main(String[] args) {
        Greenhouse g = new Greenhouse();
        GreenhouseView v = new GreenhouseView();
        GreenhouseControl bruh = new GreenhouseControl(g, v);
    }
}