package Design;

import Utils.SelectOption;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;

public class MainDesign {
    private static JFrame modalWindow = new JFrame("Master Mind");
    private JPanel panelBoard, rightPanel, playerCodeButtons, answerTryFields, panelMain;
    private JComboBox numberOfColors, lenghtOfCode;
    private JButton bStart, bSelectedAnswerTry;
    private JLabel testLabel;
    private JPanel secretCodeFields;
    private JButton bVerify;
    private Color[] listOfColors = {
            Color.red,
            Color.blue,
            Color.green,
            Color.yellow,
            Color.pink,
            Color.white,
            Color.orange,
            Color.magenta,
            Color.black,
            Color.cyan };
    private Insets buttonMargins = new Insets(2, 1, 2, 1);
    private Dimension buttonSize = new Dimension(27,27);
    private LineBorder activeBorder = new LineBorder(new Color(0,200,0),2, true),
            noBorder = new LineBorder(Color.black, 1, true);
    private GridBagConstraints gbc = new GridBagConstraints();
    private int[] codeSecret;
    private int[] answerTry;


    public static void main(String[] args) {
        modalWindow.setContentPane(new MainDesign().panelMain);
        modalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modalWindow.pack();
        modalWindow.setLocation(350,50);
        modalWindow.setVisible(true);
    }


    public MainDesign() {
        
        numberOfColors.removeAllItems();
        SelectOption[] nocOptions = {
                new SelectOption("Antos", 3),
                new SelectOption("Agatka", 5),
                new SelectOption("Stefan", 6),
                new SelectOption("Tata", 7),
                new SelectOption("Profesor", 8),
                new SelectOption("Ekspert", 10)
        };

        for(SelectOption option : nocOptions) {
            numberOfColors.addItem(option);
        }
        numberOfColors.setSelectedItem(nocOptions[3]);


        lenghtOfCode.removeAllItems();
        SelectOption[] locOptions = {
                new SelectOption("Antos", 4),
                new SelectOption("Agatka", 4),
                new SelectOption("Stefan", 5),
                new SelectOption("Tata", 5),
                new SelectOption("Profesor", 6),
                new SelectOption("Ekspert", 7)
        };

        for(SelectOption option : locOptions) {
            lenghtOfCode.addItem(option);
        }
        lenghtOfCode.setSelectedItem(locOptions[3]);

        testLabel.setText("<html>Number of <u>colors</u>: "+numberOfColors.getSelectedItem()+"<br />Length of code: "+lenghtOfCode.getSelectedItem()+"<br />Selected index: "+lenghtOfCode.getSelectedIndex()+"</html>");

        DrawBoard();

        bStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testLabel.setText("<html>Button <i>START</i> <u>pressed</u>!<br />Number of colors: "+numberOfColors.getSelectedItem()+"<br />Length of code: "+lenghtOfCode.getSelectedItem()+"</html>");
                DrawBoard();
            }
        });
        numberOfColors.addMouseMotionListener(new MouseMotionAdapter() {
        });
        numberOfColors.addMouseListener(new MouseAdapter() {
        });
    }

    public void DrawBoard() {

        gbc.insets = buttonMargins;

        playerCodeButtons.removeAll();
        SelectOption so = (SelectOption) numberOfColors.getSelectedItem();
        int colorsCount = so.getId();

        for (int i = 0 ; i < colorsCount; i++) {
            JButton playerColor = new JButton();
            playerColor.setPreferredSize(buttonSize);
            playerColor.setMargin(new Insets(2, 10, 2, 10));
            playerColor.setBackground(listOfColors[i]);
            playerColor.putClientProperty("colorId",i);
            playerColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();
                    if (source instanceof JButton) {
                        JButton bSelectedColor = ((JButton) source);
                        bSelectedAnswerTry.setBackground(bSelectedColor.getBackground());
                        // Dodaje wybrana wartosc do odpowiedzi:

//                        answerTry[(int)bSelectedAnswerTry.getClientProperty("id")] = 5;
                    }
                }
            });
            playerColor.setBorder(noBorder);
            playerCodeButtons.add(playerColor, gbc);
        }

        answerTryFields.removeAll();
        so = (SelectOption) lenghtOfCode.getSelectedItem();
        int codeLenght = so.getId();
        for (int i = 0; i < codeLenght; i++) {
            testLabel.setText("Id to: "+i);
            JButton answerTry = new JButton();
            answerTry.setPreferredSize(buttonSize);
            answerTry.setBackground(Color.gray);
            answerTry.putClientProperty("id",i);
            answerTry.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object source = e.getSource();
                    if (source instanceof JButton) {
                        bSelectedAnswerTry.setBorderPainted(false);
                        bSelectedAnswerTry = ((JButton) source);
                        bSelectedAnswerTry.setBorderPainted(true);
                    }
                }
            });
            answerTry.setBorder(activeBorder);
            answerTry.setBorderPainted(false);

            if (i==0) {
                bSelectedAnswerTry = answerTry;
                answerTry.setBorderPainted(true);
            }
            answerTryFields.add(answerTry,gbc);
        }


        secretCodeFields.removeAll();
        for (int i = 0; i < codeLenght; i++) {
            Random rand = new Random();
            int codeNr = rand.nextInt(colorsCount);
//            codeSecret[i] = codeNr;
            
            JPanel codeSecretPanel = new JPanel();
            codeSecretPanel.setPreferredSize(buttonSize);
            codeSecretPanel.setBackground(listOfColors[codeNr]);
            secretCodeFields.add(codeSecretPanel,gbc);
        }
        
        panelBoard.repaint();
        modalWindow.pack();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
