package com.qsp.view.vi.box;

import com.qsp.player.vi.QspUi;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

public class SwingUi implements QspUi {
    private static final Frame FRAME = new Frame() {{
        this.setAlwaysOnTop(true);
    }};

    @Override
    public String showTextInputDialog(String prompt) {
        String input = JOptionPane.showInputDialog(FRAME, prompt);
        if (input == null) {
            return "";
        } else {
            return input;
        }
    }

    @Override
    public void showPicture(String imagePath) {
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(new URL(imagePath));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        JLabel label = new JLabel(icon);
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(label);
        JOptionPane.showMessageDialog(FRAME, panel, "Course", JOptionPane.DEFAULT_OPTION);
    }

    @Override
    public void showErrorialog(String prompt) {
        JOptionPane.showMessageDialog(FRAME, prompt, "错误", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void showAlertDialog(String prompt) {
        JOptionPane.showMessageDialog(FRAME, prompt, "提示", JOptionPane.WARNING_MESSAGE);
    }
}
