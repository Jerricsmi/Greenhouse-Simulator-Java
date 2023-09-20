import javax.swing.*;
import java.awt.*;

public class GreenhouseView {
    public JFrame frame;
    public JPanel mainPanel;
    public JPanel setPanel;
    public JPanel changePanel;
    public JPanel greenhousePanel;

    public JLabel temperatureLabel;
    public JLabel humidityLabel;
    public JLabel moistureLabel;
    public JLabel furnaceStatus;
    public JLabel airConditionerStatusLabel;
    public JLabel humidifierStatusLabel;
    public JLabel sprinklerStatusLabel;
    public JTextField startingTempTextField;
    public JTextField startingHumTextField;
    public JTextField startingMoistTextField;

    public JButton startButton;
    public JButton setButton;
    public JButton changeButton;
    public JButton stopButton;

    public JTextField temperatureTextField;
    public JTextField humidityFromTextField;
    public JTextField humidityToTextField;
    public JTextField moistureFromTextField;
    public JTextField moistureToTextField;
    public JTextField furnaceRateTextField;
    public JTextField airConditionerRateTextField;
    public JTextField humidifierRateTextField;
    public JTextField sprinklerRateTextField;
    public JTextField weatherTemperatureRateTextField;
    public JTextField weatherMoistureRateTextField;
    public JTextField weatherHumidityRateTextField;
    public JTextField tempUpdateRateTextField;
    public JTextField moistUpdateRateTextField;
    public JTextField humUpdateRateTextField;

    public GreenhouseView() {
        frame = new JFrame("Greenhouse Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPanel = new JPanel(new GridLayout(15, 2));
        setPanel.add(new JLabel("Starting Temperature:"));
        startingTempTextField = new JTextField(10);
        setPanel.add(startingTempTextField);
        setPanel.add(new JLabel("Starting Humidity (%):"));
        startingHumTextField = new JTextField(10);
        setPanel.add(startingHumTextField);
        setPanel.add(new JLabel("Starting Soil Moisture (%):"));
        startingMoistTextField = new JTextField(10);
        setPanel.add(startingMoistTextField);
        setPanel.add(new JLabel("Furnace Rate (degrees per minute):"));
        furnaceRateTextField = new JTextField(10);
        setPanel.add(furnaceRateTextField);
        setPanel.add(new JLabel("Humidifier Rate (% per minute):"));
        humidifierRateTextField = new JTextField(10);
        setPanel.add(humidifierRateTextField);
        setPanel.add(new JLabel("AC Rate (degrees per minute):"));
        airConditionerRateTextField = new JTextField(10);
        setPanel.add(airConditionerRateTextField);
        setPanel.add(new JLabel("Sprinkler Rate (% per minute):"));
        sprinklerRateTextField = new JTextField(10);
        setPanel.add(sprinklerRateTextField);
        setPanel.add(new JLabel("Weather Temperature Rate:"));
        weatherTemperatureRateTextField = new JTextField(10);
        setPanel.add(weatherTemperatureRateTextField);
        setPanel.add(new JLabel("Weather Soil Moisture Rate:"));
        weatherMoistureRateTextField = new JTextField(10);
        setPanel.add(weatherMoistureRateTextField);
        setPanel.add(new JLabel("Weather Humidity Rate:"));
        weatherHumidityRateTextField = new JTextField(10);
        setPanel.add(weatherHumidityRateTextField);
        setPanel.add(new JLabel("Temperature Update Rate (millis):"));
        tempUpdateRateTextField = new JTextField(10);
        setPanel.add(tempUpdateRateTextField);
        setPanel.add(new JLabel("Soil Moisture Update Rate (millis):"));
        moistUpdateRateTextField = new JTextField(10);
        setPanel.add(moistUpdateRateTextField);
        setPanel.add(new JLabel("Soil Humidity Update Rate (millis):"));
        humUpdateRateTextField = new JTextField(10);
        setPanel.add(humUpdateRateTextField);

        setButton = new JButton("Set");
        setPanel.add(setButton);

        changePanel = new JPanel(new GridLayout(6, 2));
        changePanel.add(new JLabel("Desired Temperature:"));
        temperatureTextField = new JTextField(10);
        changePanel.add(temperatureTextField);
        changePanel.add(new JLabel("Desired Humidity Range from (%):"));
        humidityFromTextField = new JTextField(10);
        changePanel.add(humidityFromTextField);
        changePanel.add(new JLabel("Desired Humidity Range to (%):"));
        humidityToTextField = new JTextField(10);
        changePanel.add(humidityToTextField);
        changePanel.add(new JLabel("Desired Soil Moisture Range from (%):"));
        moistureFromTextField = new JTextField(10);
        changePanel.add(moistureFromTextField);
        changePanel.add(new JLabel("Desired Soil Moisture Range to (%):"));
        moistureToTextField = new JTextField(10);
        changePanel.add(moistureToTextField);
        changeButton = new JButton("Change");
        changePanel.add(changeButton);


        greenhousePanel = new JPanel(new GridLayout(9, 2));
        greenhousePanel.add(new JLabel("Current Temperature:"));
        temperatureLabel = new JLabel("");
        greenhousePanel.add(temperatureLabel);
        greenhousePanel.add(new JLabel("Current Humidity:"));
        humidityLabel = new JLabel("");
        greenhousePanel.add(humidityLabel);
        greenhousePanel.add(new JLabel("Current Soil Moisture:"));
        moistureLabel = new JLabel("");
        greenhousePanel.add(moistureLabel);
        greenhousePanel.add(new JLabel("Furnace Status:"));
        furnaceStatus = new JLabel("");
        greenhousePanel.add(furnaceStatus);
        greenhousePanel.add(new JLabel("AC Status:"));
        airConditionerStatusLabel = new JLabel("");
        greenhousePanel.add(airConditionerStatusLabel);
        greenhousePanel.add(new JLabel("Humidifier Status:"));
        humidifierStatusLabel = new JLabel("");
        greenhousePanel.add(humidifierStatusLabel);
        greenhousePanel.add(new JLabel("Sprinkler Status:"));
        sprinklerStatusLabel = new JLabel("");
        greenhousePanel.add(sprinklerStatusLabel);
        startButton = new JButton("Start");
        greenhousePanel.add(startButton);
        stopButton = new JButton("Stop");
        greenhousePanel.add(stopButton);

        // This is where the outputs will go when the calculator does the math with the users inputs

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(setPanel, BorderLayout.NORTH);

        mainPanel.add(changePanel, BorderLayout.CENTER);

        mainPanel.add(greenhousePanel, BorderLayout.SOUTH);
        // Adding the outputs to the bottom of the main panel

        frame.getContentPane().add(mainPanel);
        // Adds the main panel to the content pane
        frame.pack();
        // This allows the window to be modified in size
        frame.setVisible(true);
        // Finally, this opens the window for the user to see
    }

}






