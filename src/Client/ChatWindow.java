package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatWindow extends JFrame {
    private JTextArea chatArea;
    private JTextField messageField;

    public ChatWindow() {
        setTitle("Chat Whatsapp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.GREEN);

        ImageIcon whatsappIcon = new ImageIcon("whatsapp.png");
        Image image = whatsappIcon.getImage();
        Image resizedImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        ImageIcon resizedWhatsappIcon = new ImageIcon(resizedImage);

        JLabel whatsappImageLabel = new JLabel(resizedWhatsappIcon);
        topPanel.add(whatsappImageLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        messageField = new JTextField();
        bottomPanel.add(messageField, BorderLayout.CENTER);

        ImageIcon invio = new ImageIcon("invio.png");
        JButton sendButton = new JButton();
        sendButton.setIcon(invio);
        sendButton.setBackground(Color.white);
        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invia();
            }
        });
        bottomPanel.add(sendButton, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void invia() {
        String message = messageField.getText();
        if (!message.isEmpty()) {
            chatArea.append("Io: " + message + "\n");
            messageField.setText("");

        }
    }
}
