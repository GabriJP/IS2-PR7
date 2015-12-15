package kata7.application.swing;

import kata7.control.Command;
import kata7.model.Attribute;
import kata7.view.AttributeDialog;
import kata7.view.PopulationDialog;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Toolbar extends JPanel implements PopulationDialog, AttributeDialog {

    private final Map<String, Command> commands;
    private final List<Attribute> attributes;
    private JComboBox combo;

    public Toolbar(Map<String, Command> commands) {
        super(new FlowLayout());
        this.commands = commands;
        this.attributes = new ArrayList<>();
        this.add(mailDomainAttribute());
        this.add(firstMailAttribute());
        this.add(combobox());
        this.add(calculateButton());
    }

    private JButton calculateButton() {
        JButton button = new JButton("Calculate");
        button.addActionListener(e -> commands.get("calculate").execute());
        return button;
    }

    private Attribute firstMailAttribute() {
        return new Attribute<Person, Character>() {
            @Override
            public Character get(Person item) {
                return item.getMail().charAt(0);
            }
        };
    }

    private Attribute mailDomainAttribute() {
        return new Attribute<Person, String>() {
            @Override
            public String get(Person item) {
                return item.getMail().split("@")[1];
            }
        };
    }

    private JComboBox combobox() {
        combo = new JComboBox(options("Mail Domains", "First Char"));
        return combo;
    }

    private String[] options(String... options) {
        return options;
    }

    private void add(Attribute attribute) {
        attributes.add(attribute);
    }

    @Override
    public Attribute<Person, String> attribute() {
        return attributes.get(combo.getSelectedIndex());
    }

    @Override
    public List population() {
        try {
            return MailReader.read("emails.txt");
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
