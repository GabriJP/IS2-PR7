package kata7.application;

import kata7.application.swing.HistogramPanel;
import kata7.application.swing.Toolbar;
import kata7.control.CalculateCommand;
import kata7.control.Command;
import kata7.view.AttributeDialog;
import kata7.view.HistogramDisplay;
import kata7.view.PopulationDialog;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Application extends JFrame {

    private AttributeDialog attributeDialog;
    private PopulationDialog populationDialog;
    private HistogramDisplay histogramDisplay;
    private HashMap<String, Command> commands;

    public static void main(String[] args) {
        new Application().setVisible(true);
    }

    public Application() {
        this.commands = new HashMap<>();
        this.deployUI();
        this.createCommands();
    }

    private void deployUI() {
        this.setTitle("Histogram viewer");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        this.getContentPane().add(histogramPanel());
        this.getContentPane().add(toolbar(commands), BorderLayout.NORTH);
    }

    private JPanel toolbar(HashMap<String, Command> commands) {
        Toolbar panel = new Toolbar(commands);
        this.attributeDialog = panel;
        this.populationDialog = panel;
        return panel;
    }

    private HistogramPanel histogramPanel() {
        HistogramPanel panel = new HistogramPanel();
        this.histogramDisplay = panel;
        return panel;
    }

    private void createCommands() {
        commands.put("calculate", new CalculateCommand(attributeDialog, populationDialog, histogramDisplay));
    }
}
