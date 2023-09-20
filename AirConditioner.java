public class AirConditioner extends Thread {
    private Greenhouse greenhouse;
    private double rate;
    public volatile boolean stop = true;

    public AirConditioner(Greenhouse h, double r) {
        this.greenhouse = h;
        if (r > 0)
            this.rate = -r;
        else
            this.rate = r;
    }
    public void run() {
        while (true) {
            while (!stop) {
                greenhouse.changeTemperature(rate / 60);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
            while (stop) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }
//    public void setStatus(boolean b) {
//        this.status = b;
//    }
//    public boolean getStatus() {
//        return this.status;
//    }
    public String getStringStatus() {
        String x = "";
        if (this.stop == true)
            x = "Off";
        if (this.stop == false)
            x = "On";
        return x;
    }
}

