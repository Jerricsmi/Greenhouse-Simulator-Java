import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GreenhouseControl extends Thread {
    private Greenhouse greenhouse;
    private GreenhouseView view;
    private Furnace furnace;
    private AirConditioner AC;
    private TempSensor tSensor;
    private TempController tController;
    private Sprinkler sprinkler;
    private MoistureSensor mSensor;
    private MoistureController mController;
    private Humidifier humidifier;
    private HumidityController hController;
    private HumiditySensor hSensor;
    private statusThread sThread;
    private currentTempThread cTempThread;
    private currentMoistThread cMoistThread;
    private currentHumThread cHumThread;
    private WeatherTemp wTemp;
    private WeatherMoist wMoist;
    private WeatherHum wHum;
    private boolean running;
    GreenhouseControl (Greenhouse g, GreenhouseView view) {
        this.greenhouse = g;
        this.view = view;
        this.running = false;

        this.view.setButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double startingTemp = Double.parseDouble(view.startingTempTextField.getText());
                double startingMoist = Double.parseDouble(view.startingMoistTextField.getText());
                double startingHum = Double.parseDouble(view.startingHumTextField.getText());
                double furnaceRate = Double.parseDouble(view.furnaceRateTextField.getText());
                double airConditionerRate = Double.parseDouble(view.airConditionerRateTextField.getText());
                double sprinklerRate = Double.parseDouble(view.sprinklerRateTextField.getText());
                double humidifierRate = Double.parseDouble(view.humidifierRateTextField.getText());
                double weatherTempRate = Double.parseDouble(view.weatherTemperatureRateTextField.getText());
                double weatherMoistRate = Double.parseDouble(view.weatherMoistureRateTextField.getText());
                double weatherHumRate = Double.parseDouble(view.weatherHumidityRateTextField.getText());
                long tempUpdateRate = Long.parseLong(view.tempUpdateRateTextField.getText());
                long moistUpdateRate = Long.parseLong(view.moistUpdateRateTextField.getText());
                long humUpdateRate = Long.parseLong(view.humUpdateRateTextField.getText());

                g.setTemperature(startingTemp);
                furnace = new Furnace(g, furnaceRate, weatherTempRate);
                wTemp = new WeatherTemp(g, weatherTempRate);
                AC = new AirConditioner(g, airConditionerRate);
                tController = new TempController(furnace, AC, wTemp);
                tSensor = new TempSensor(g, tController);

                g.setSoilMoisture(startingMoist);
                wMoist = new WeatherMoist(g, weatherMoistRate);
                sprinkler = new Sprinkler(g, sprinklerRate, weatherMoistRate);
                mController = new MoistureController(sprinkler, wMoist);
                mSensor = new MoistureSensor(g, mController);

                g.setHumidity(startingHum);
                wHum = new WeatherHum(g, weatherHumRate);
                humidifier = new Humidifier(g, humidifierRate, weatherHumRate);
                hController = new HumidityController(humidifier, wHum);
                hSensor = new HumiditySensor(g, hController);

                view.temperatureLabel.setText(String.format("%.2f", g.getTemperature()));
                view.moistureLabel.setText(String.format("%.2f", g.getSoilMoisture()) + "%");
                view.humidityLabel.setText(String.format("%.2f", g.getHumidity()) + "%");

                sThread = new statusThread(g, view, sprinkler, furnace, AC, humidifier);
                sThread.stop = false;
                cTempThread = new currentTempThread(g, view, tempUpdateRate);
                cTempThread.stop = false;
                cMoistThread = new currentMoistThread(g, view, moistUpdateRate);
                cMoistThread.stop = false;
                cHumThread = new currentHumThread(g, view, humUpdateRate);
                cHumThread.stop = false;

                tSensor.start();
                mSensor.start();
                hSensor.start();
                cTempThread.start();
                cMoistThread.start();
                cHumThread.start();
                sThread.start();
            }
        });
        this.view.changeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                double desiredTemp = Double.parseDouble(view.temperatureTextField.getText());
                double desiredMoistFrom = Double.parseDouble(view.moistureFromTextField.getText());
                double desiredMoistTo = Double.parseDouble(view.moistureToTextField.getText());
                double desiredHumFrom = Double.parseDouble(view.humidityFromTextField.getText());
                double desiredHumTo = Double.parseDouble(view.humidityToTextField.getText());
                tSensor.setDesiredTemp(desiredTemp);
                mSensor.setDesiredMoistFrom(desiredMoistFrom);
                mSensor.setDesiredMoistTo(desiredMoistTo);
                hSensor.setDesiredHumFrom(desiredHumFrom);
                hSensor.setDesiredHumTo(desiredHumTo);
            }
        });
        this.view.startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cTempThread.stop = false;
                cMoistThread.stop = false;
                cHumThread.stop = false;
                tSensor.stop = false;
                mSensor.stop = false;
                hSensor.stop = false;
                sThread.stop = false;
                running = true;

            }
        });
        this.view.stopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                cTempThread.stop = true;
                cMoistThread.stop = true;
                cHumThread.stop = true;
                tSensor.stop = true;
                mSensor.stop = true;
                hSensor.stop = true;
                sThread.stop = true;
                running = false;
            }
        });
    }
    public class statusThread extends Thread {
        private Greenhouse greenhouse;
        private GreenhouseView view;
        private Sprinkler sprinkler;
        private Furnace furnace;
        private AirConditioner ac;
        private Humidifier humidifier;

        public volatile boolean stop = true;

        public statusThread(Greenhouse g, GreenhouseView v, Sprinkler s, Furnace f, AirConditioner a, Humidifier hum) {
            this.greenhouse = g;
            this.view = v;
            this.sprinkler = s;
            this.furnace = f;
            this.ac = a;
            this.humidifier = hum;
        }
        public void run() {
            while (true) {
                while (!stop) {
                    view.sprinklerStatusLabel.setText(sprinkler.getStringStatus());
                    view.furnaceStatus.setText(furnace.getStringStatus());
                    view.airConditionerStatusLabel.setText(ac.getStringStatus());
                    view.humidifierStatusLabel.setText(humidifier.getStringStatus());
                }
                if (stop) {
                    view.sprinklerStatusLabel.setText("Off");
                    view.furnaceStatus.setText("Off");
                    view.airConditionerStatusLabel.setText("Off");
                    view.humidifierStatusLabel.setText("Off");
                }
            }
        }
    }
    public class currentTempThread extends Thread {
        Greenhouse greenhouse;
        GreenhouseView view;
        public volatile boolean stop = true;
        private long rate;
        public currentTempThread (Greenhouse g, GreenhouseView v, long r) {
            this.greenhouse = g;
            this.view = v;
            this.rate = r;
        }
        public void run() {
            while (true) {
                while (!stop) {
                    try {
                        Thread.sleep(rate);
                    } catch (InterruptedException e) {
                        return;
                    }
                    if (!stop)
                        view.temperatureLabel.setText(String.format("%.2f", greenhouse.getTemperature()) + "°");
                }
                while (stop);
            }
        }
    }
    public class currentMoistThread extends Thread {
        Greenhouse greenhouse;
        GreenhouseView view;
        public volatile boolean stop = true;
        private long rate;
        public currentMoistThread (Greenhouse g, GreenhouseView v, long r) {
            this.greenhouse = g;
            this.view = v;
            this.rate = r;
        }
        public void run() {
            while (true) {
                while (!stop) {
                    try {
                        Thread.sleep(rate);
                    } catch (InterruptedException e) {
                        return;
                    }
                    if (!stop)
                        view.moistureLabel.setText(String.format("%.2f", greenhouse.getSoilMoisture()) + "%");
                }
                while (stop);
            }
        }
    }
    public class currentHumThread extends Thread {
        Greenhouse greenhouse;
        GreenhouseView view;
        public volatile boolean stop = true;
        private long rate;
        public currentHumThread (Greenhouse g, GreenhouseView v, long r) {
            this.greenhouse = g;
            this.view = v;
            this.rate = r;
        }
        public void run() {
            while (true) {
                while (!stop) {
                    try {
                        Thread.sleep(rate);
                    } catch (InterruptedException e) {
                        return;
                    }
                    if (!stop)
                        view.humidityLabel.setText(String.format("%.2f", greenhouse.getHumidity()) + "%");
                }
                while (stop);
            }
        }
    }
}
