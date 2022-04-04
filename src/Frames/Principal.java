package Frames;

import DTO.GenerateNumbers;
import Utils.frameMethods;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

public class Principal {
    private JRadioButton lottoRadioButton;
    private JRadioButton megaSennaRadioButton;
    private JSpinner quantityNumber;
    private JButton generateButton;
    private JPanel panel1;
    private JButton loadFileButton;
    private final ButtonGroup gameTypes;

    public Principal(JFrame fillFile, int frameWidth, int frameHeight) throws ParseException {
        try {
            gameTypes = new ButtonGroup();
            gameTypes.add(megaSennaRadioButton);
            gameTypes.add(lottoRadioButton);
            fillFile.setSize(frameWidth, frameHeight);
            fillFile.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent windowEvent) {
                    if (JOptionPane.showConfirmDialog(fillFile, "Deseja realmente sair ?", "Fechar Janela?",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                }
            });
        }catch(Exception e){
            throw new ParseException(e.getMessage(),e.hashCode());
        }
        generateButton.addActionListener(e -> {
            GenerateNumbers generate = new GenerateNumbers();
            generate.setQuantity(Integer.parseInt(quantityNumber.getValue().toString()));
            String gameType = "";
            if(megaSennaRadioButton.isSelected()){
                gameType = megaSennaRadioButton.getText();
            } else if(lottoRadioButton.isSelected()){
                gameType = lottoRadioButton.getText();
            }
            generate.setTypeGame(gameType);
            System.out.println("Quantity: "+generate.getQuantity());
            System.out.println("Type: "+generate.getTypeGame());
        });
    }

    public static void main(String[] Args) throws ParseException {
        JFrame login = new JFrame("Preenchedor Automático de Horas");
        login.setContentPane(new Principal(login, login.getWidth(), login.getHeight()).panel1);
        login.pack();
        Utils.frameMethods frameMethods = new frameMethods();
        frameMethods.defineFrame(login, 800, 600);
    }
}
