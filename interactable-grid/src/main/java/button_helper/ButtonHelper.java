package button_helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHelper{

    public static JButton createButton(String buttonText, String buttonName, Color backgroundColor,
                                       Runnable buttonOnClickFunction) {

        JButton newButton = new JButton(buttonText);
        newButton.setName(buttonName);
        newButton.setBackground(backgroundColor);
        newButton.setOpaque(true);

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttonOnClickFunction.run();
            }
        });

        return newButton;
    }
}