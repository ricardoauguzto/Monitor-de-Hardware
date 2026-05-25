package org.example;

import oshi.SystemInfo;
import javax.swing.ImageIcon;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.GraphicsCard;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        // =========================
        // SISTEMA
        // =========================

        SystemInfo systemInfo = new SystemInfo();

        CentralProcessor processor =
                systemInfo.getHardware().getProcessor();

        GlobalMemory memory =
                systemInfo.getHardware().getMemory();

        List<GraphicsCard> gpus =
                systemInfo.getHardware().getGraphicsCards();

        // =========================
        // JANELA
        // =========================

        JFrame frame = new JFrame();
        ImageIcon icon =
                new ImageIcon(
                        Main.class.getResource("/gear.png")
                );

        frame.setIconImage(icon.getImage());

        frame.setTitle("Monitor de Hardware");
        frame.setSize(600, 300);

        frame.setLayout(null);

        frame.getContentPane().setBackground(new Color(20, 20, 20));

        // =========================
        // TITULO
        // =========================

        JLabel titulo = new JLabel("drive-teste-monitor");

        titulo.setBounds(90, 10, 500, 20);

        titulo.setForeground(Color.WHITE);

        titulo.setFont(new Font("montserrat", Font.BOLD, 20));

        frame.add(titulo);

        // =========================
        // CPU
        // =========================

        JLabel textoCPU = new JLabel("CPU");

        textoCPU.setBounds(30, 60, 500, 20);

        textoCPU.setForeground(Color.white);

        frame.add(textoCPU);

        JProgressBar barraCPU = new JProgressBar();

        barraCPU.setBounds(30, 85, 500, 20);

        barraCPU.setStringPainted(true);

        frame.add(barraCPU);

        // =========================
        // RAM
        // =========================

        JLabel textoRAM = new JLabel("RAM");

        textoRAM.setBounds(30, 130, 500, 20);

        textoRAM.setForeground(Color.white);

        frame.add(textoRAM);

        JProgressBar barraRAM = new JProgressBar();

        barraRAM.setBounds(30, 155, 500, 20);

        barraRAM.setStringPainted(true);

        frame.add(barraRAM);

        // =========================
        // GPU
        // =========================

        JLabel textoGPU = new JLabel("GPU: não foi possível verificar...");

        textoGPU.setBounds(30, 210, 380, 25);

        textoGPU.setForeground(Color.WHITE);

        textoGPU.setFont(new Font("montserrat", Font.BOLD, 13));

        frame.add(textoGPU);

        // =========================
        // TIMER
        // =========================

        Timer timer = new Timer(1000, e -> {

            // CPU

            double cpu =
                    processor.getSystemCpuLoad(1000) * 100;

            barraCPU.setValue((int) cpu);

            barraCPU.setString((int) cpu + "%");

            // RAM

            long total = memory.getTotal();

            long disponivel = memory.getAvailable();

            double ram =
                    ((double) (total - disponivel) / total) * 100;

            barraRAM.setValue((int) ram);

            barraRAM.setString((int) ram + "%");

            // GPU


        });

        timer.start();

        // =========================
        // FINALIZAÇÃO
        // =========================

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        frame.setResizable(false);

        frame.setVisible(true);
    }
}
