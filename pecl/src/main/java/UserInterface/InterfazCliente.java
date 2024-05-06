package UserInterface;

import interfaces.IAeropuerto;
import interfaces.IPaso;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.*;
import javax.swing.*;
import static javax.swing.LayoutStyle.ComponentPlacement.*;


public class InterfazCliente extends JFrame implements ActionListener {
    
    // COMPONENTES DE LA INTERFAZ
    
    // Aeropuerto Barajas:
    private JLabel tituloAeropuertoBarajas = new JLabel("Aeropuerto de Madrid - Barajas");
    
    private JLabel numPas_Bar = new JLabel("Nº pasajeros en Aeropuerto:");
    private JLabel numHangar_Bar = new JLabel("Nº aviones en Hangar:");
    private JLabel numTaller_Bar = new JLabel("Nº aviones en Taller:");
    private JLabel numArEst_Bar = new JLabel("Nº aviones en Área de Estacionamiento:");
    private JLabel numArRod_Bar = new JLabel("Nº aviones en Área de Rodaje:");
    private JLabel numPuerEmb_Bar = new JLabel("Nº Puertas de Embarque ocupadas:");
    private JLabel numPistas_Bar = new JLabel("Nº Pistas ocupadas:");
    private JLabel pista1_Bar = new JLabel("Pista 1:");
    private JLabel pista2_Bar = new JLabel("Pista 2:");
    private JLabel pista3_Bar = new JLabel("Pista 3:");
    private JLabel pista4_Bar = new JLabel("Pista 4:");
    
    private JTextField info_numPas_Bar = new JTextField();
    private JTextField info_numHangar_Bar = new JTextField();
    private JTextField info_numTaller_Bar = new JTextField();
    private JTextField info_numArEst_Bar = new JTextField();
    private JTextField info_numArRod_Bar = new JTextField();
    private JTextField info_numPuerEmb_Bar = new JTextField();
    private JTextField info_numPistas_Bar = new JTextField();
    
    private JButton botonP1Abrir_Bar = new JButton("Abrir");
    private JButton botonP1Cerrar_Bar = new JButton("Cerrar");
    private JButton botonP2Abrir_Bar = new JButton("Abrir");
    private JButton botonP2Cerrar_Bar = new JButton("Cerrar");
    private JButton botonP3Abrir_Bar = new JButton("Abrir");
    private JButton botonP3Cerrar_Bar = new JButton("Cerrar");
    private JButton botonP4Abrir_Bar = new JButton("Abrir");
    private JButton botonP4Cerrar_Bar = new JButton("Cerrar");
    
    // Aerovías:
    private JLabel ae_Bar_Prat = new JLabel("Aerovía Barajas - Prat: ");
    private JLabel ae_Prat_Bar = new JLabel("Aerovía Prat - Barajas: ");
    private JTextField info_ae_Bar_Prat = new JTextField();
    private JTextField info_ae_Prat_Bar = new JTextField();
    
    // Aeropuerto Prat:
    private JLabel tituloAeropuertoPrat = new JLabel("Aeropuerto de Barcelona - Prat");
    
    private JLabel numPas_Prat = new JLabel("Nº pasajeros en Aeropuerto:");
    private JLabel numHangar_Prat = new JLabel("Nº aviones en Hangar:");
    private JLabel numTaller_Prat = new JLabel("Nº aviones en Taller:");
    private JLabel numArEst_Prat = new JLabel("Nº aviones en Área de Estacionamiento:");
    private JLabel numArRod_Prat = new JLabel("Nº aviones en Área de Rodaje:");
    private JLabel numPuerEmb_Prat = new JLabel("Nº Puertas de Embarque ocupadas:");
    private JLabel numPistas_Prat = new JLabel("Nº Pistas ocupadas:");
    private JLabel pista1_Prat = new JLabel("Pista 1:");
    private JLabel pista2_Prat = new JLabel("Pista 2:");
    private JLabel pista3_Prat = new JLabel("Pista 3:");
    private JLabel pista4_Prat = new JLabel("Pista 4:");
    
    private JTextField info_numPas_Prat = new JTextField();
    private JTextField info_numHangar_Prat = new JTextField();
    private JTextField info_numTaller_Prat = new JTextField();
    private JTextField info_numArEst_Prat = new JTextField();
    private JTextField info_numArRod_Prat = new JTextField();
    private JTextField info_numPuerEmb_Prat = new JTextField();
    private JTextField info_numPistas_Prat = new JTextField();
    
    private JButton botonP1Abrir_Prat = new JButton("Abrir");
    private JButton botonP1Cerrar_Prat = new JButton("Cerrar");
    private JButton botonP2Abrir_Prat = new JButton("Abrir");
    private JButton botonP2Cerrar_Prat = new JButton("Cerrar");
    private JButton botonP3Abrir_Prat = new JButton("Abrir");
    private JButton botonP3Cerrar_Prat = new JButton("Cerrar");
    private JButton botonP4Abrir_Prat = new JButton("Abrir");
    private JButton botonP4Cerrar_Prat = new JButton("Cerrar");
    
    private IPaso paso;
    private IAeropuerto barajas, prat;
    
    // Constructor de la clase gráfica:
    public InterfazCliente(IPaso p, IAeropuerto barajas, IAeropuerto prat) {
        
        super("Simulador de Aeropuertos : Cliente");
        this.paso = p;
        this.barajas = barajas;
        this.prat = prat;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initComponents();
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
    }
    
    // Establecer diferentes parámetros a los componentes de Swing:
    private void initComponents() {
        
        tituloAeropuertoBarajas.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        tituloAeropuertoPrat.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        
        info_numPas_Bar.setEditable(false);
        info_numTaller_Bar.setEditable(false);
        info_numHangar_Bar.setEditable(false);
        info_numArRod_Bar.setEditable(false);
        info_numArEst_Bar.setEditable(false);
        info_numPuerEmb_Bar.setEditable(false);
        info_ae_Bar_Prat.setEditable(false);
        info_numPistas_Bar.setEditable(false);
        info_ae_Prat_Bar.setEditable(false);
        info_numTaller_Prat.setEditable(false);
        info_numHangar_Prat.setEditable(false);
        info_numArRod_Prat.setEditable(false);
        info_numArEst_Prat.setEditable(false);
        info_numPuerEmb_Prat.setEditable(false);
        botonP1Abrir_Prat.setEnabled(false);
        botonP2Abrir_Prat.setEnabled(false);
        botonP3Abrir_Prat.setEnabled(false);
        botonP4Abrir_Prat.setEnabled(false);
        info_numPistas_Prat.setEditable(false);
        info_numPas_Prat.setEditable(false);
        
        // Botones:
        botonP1Abrir_Bar.setFocusable(false);
        botonP2Abrir_Bar.setFocusable(false);
        botonP3Abrir_Bar.setFocusable(false);
        botonP4Abrir_Bar.setFocusable(false);
        botonP1Abrir_Prat.setFocusable(false);
        botonP2Abrir_Prat.setFocusable(false);
        botonP3Abrir_Prat.setFocusable(false);
        botonP4Abrir_Prat.setFocusable(false);
        botonP1Cerrar_Bar.setFocusable(false);
        botonP2Cerrar_Bar.setFocusable(false);
        botonP3Cerrar_Bar.setFocusable(false);
        botonP4Cerrar_Bar.setFocusable(false);
        botonP1Cerrar_Prat.setFocusable(false);
        botonP2Cerrar_Prat.setFocusable(false);
        botonP3Cerrar_Prat.setFocusable(false);
        botonP4Cerrar_Prat.setFocusable(false);
        
        botonP1Abrir_Bar.setEnabled(false);
        botonP2Abrir_Bar.setEnabled(false);
        botonP3Abrir_Bar.setEnabled(false);
        botonP4Abrir_Bar.setEnabled(false);
        botonP1Abrir_Prat.setEnabled(false);
        botonP2Abrir_Prat.setEnabled(false);
        botonP3Abrir_Prat.setEnabled(false);
        botonP4Abrir_Prat.setEnabled(false);
        
        botonP1Abrir_Bar.addActionListener(this);
        botonP2Abrir_Bar.addActionListener(this);
        botonP3Abrir_Bar.addActionListener(this);
        botonP4Abrir_Bar.addActionListener(this);
        botonP1Abrir_Prat.addActionListener(this);
        botonP2Abrir_Prat.addActionListener(this);
        botonP3Abrir_Prat.addActionListener(this);
        botonP4Abrir_Prat.addActionListener(this);
        botonP1Cerrar_Bar.addActionListener(this);
        botonP2Cerrar_Bar.addActionListener(this);
        botonP3Cerrar_Bar.addActionListener(this);
        botonP4Cerrar_Bar.addActionListener(this);
        botonP1Cerrar_Prat.addActionListener(this);
        botonP2Cerrar_Prat.addActionListener(this);
        botonP3Cerrar_Prat.addActionListener(this);
        botonP4Cerrar_Prat.addActionListener(this);
        
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(tituloAeropuertoBarajas)
                        .addPreferredGap(RELATED, DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tituloAeropuertoPrat)
                        .addGap(46, 46, 46))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(LEADING)
                            .addComponent(ae_Prat_Bar)
                            .addComponent(ae_Bar_Prat))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(LEADING)
                            .addComponent(info_ae_Prat_Bar)
                            .addComponent(info_ae_Bar_Prat)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numHangar_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(info_numHangar_Bar, PREFERRED_SIZE, 239, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numArRod_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(info_numArRod_Bar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pista1_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(botonP1Cerrar_Bar, PREFERRED_SIZE, 106, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonP1Abrir_Bar, PREFERRED_SIZE, 106, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pista2_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(botonP2Cerrar_Bar, PREFERRED_SIZE, 106, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonP2Abrir_Bar, PREFERRED_SIZE, 106, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pista3_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(botonP3Cerrar_Bar, PREFERRED_SIZE, 106, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonP3Abrir_Bar, PREFERRED_SIZE, 106, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pista4_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(botonP4Cerrar_Bar, PREFERRED_SIZE, 106, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonP4Abrir_Bar, PREFERRED_SIZE, 106, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numPuerEmb_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(info_numPuerEmb_Bar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numPistas_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(info_numPistas_Bar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numPas_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(info_numPas_Bar, PREFERRED_SIZE, 207, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numTaller_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(info_numTaller_Bar, PREFERRED_SIZE, 249, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numArEst_Bar)
                                .addGap(18, 18, 18)
                                .addComponent(info_numArEst_Bar, PREFERRED_SIZE, 146, PREFERRED_SIZE)))
                        .addPreferredGap(RELATED, 64, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numHangar_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(info_numHangar_Prat, PREFERRED_SIZE, 239, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numArRod_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(info_numArRod_Prat))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pista1_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(botonP1Cerrar_Prat, PREFERRED_SIZE, 106, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonP1Abrir_Prat, PREFERRED_SIZE, 106, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pista2_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(botonP2Cerrar_Prat, PREFERRED_SIZE, 106, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonP2Abrir_Prat, PREFERRED_SIZE, 106, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pista3_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(botonP3Cerrar_Prat, PREFERRED_SIZE, 106, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonP3Abrir_Prat, PREFERRED_SIZE, 106, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pista4_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(botonP4Cerrar_Prat, PREFERRED_SIZE, 106, PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botonP4Abrir_Prat, PREFERRED_SIZE, 106, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numPuerEmb_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(info_numPuerEmb_Prat))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numPistas_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(info_numPistas_Prat))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numPas_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(info_numPas_Prat, PREFERRED_SIZE, 207, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numTaller_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(info_numTaller_Prat, PREFERRED_SIZE, 249, PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numArEst_Prat)
                                .addGap(18, 18, 18)
                                .addComponent(info_numArEst_Prat, PREFERRED_SIZE, 146, PREFERRED_SIZE)))))
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(tituloAeropuertoBarajas)
                    .addComponent(tituloAeropuertoPrat))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numPas_Bar)
                            .addComponent(info_numPas_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numHangar_Bar)
                            .addComponent(info_numHangar_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numTaller_Bar)
                            .addComponent(info_numTaller_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numArEst_Bar)
                            .addComponent(info_numArEst_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numArRod_Bar)
                            .addComponent(info_numArRod_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numPuerEmb_Bar)
                            .addComponent(info_numPuerEmb_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista1_Bar)
                            .addComponent(botonP1Cerrar_Bar)
                            .addComponent(botonP1Abrir_Bar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista2_Bar)
                            .addComponent(botonP2Cerrar_Bar)
                            .addComponent(botonP2Abrir_Bar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista3_Bar)
                            .addComponent(botonP3Cerrar_Bar)
                            .addComponent(botonP3Abrir_Bar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista4_Bar)
                            .addComponent(botonP4Cerrar_Bar)
                            .addComponent(botonP4Abrir_Bar))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numPistas_Bar)
                            .addComponent(info_numPistas_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numPas_Prat)
                            .addComponent(info_numPas_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numHangar_Prat)
                            .addComponent(info_numHangar_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numTaller_Prat)
                            .addComponent(info_numTaller_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numArEst_Prat)
                            .addComponent(info_numArEst_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numArRod_Prat)
                            .addComponent(info_numArRod_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numPuerEmb_Prat)
                            .addComponent(info_numPuerEmb_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista1_Prat)
                            .addComponent(botonP1Cerrar_Prat)
                            .addComponent(botonP1Abrir_Prat))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista2_Prat)
                            .addComponent(botonP2Cerrar_Prat)
                            .addComponent(botonP2Abrir_Prat))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista3_Prat)
                            .addComponent(botonP3Cerrar_Prat)
                            .addComponent(botonP3Abrir_Prat))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista4_Prat)
                            .addComponent(botonP4Cerrar_Prat)
                            .addComponent(botonP4Abrir_Prat))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numPistas_Prat)
                            .addComponent(info_numPistas_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(ae_Bar_Prat)
                    .addComponent(info_ae_Bar_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                .addPreferredGap(UNRELATED)
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(info_ae_Prat_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                    .addComponent(ae_Prat_Bar))
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }

    // Getters para los JTextField:
    
    public JTextField getInfo_numPas_Bar() {
        return info_numPas_Bar;
    }

    public JTextField getInfo_numHangar_Bar() {
        return info_numHangar_Bar;
    }

    public JTextField getInfo_numTaller_Bar() {
        return info_numTaller_Bar;
    }

    public JTextField getInfo_numArEst_Bar() {
        return info_numArEst_Bar;
    }

    public JTextField getInfo_numArRod_Bar() {
        return info_numArRod_Bar;
    }

    public JTextField getInfo_numPuerEmb_Bar() {
        return info_numPuerEmb_Bar;
    }

    public JTextField getInfo_numPistas_Bar() {
        return info_numPistas_Bar;
    }

    public JTextField getInfo_ae_Bar_Prat() {
        return info_ae_Bar_Prat;
    }

    public JTextField getInfo_ae_Prat_Bar() {
        return info_ae_Prat_Bar;
    }

    public JTextField getInfo_numPas_Prat() {
        return info_numPas_Prat;
    }

    public JTextField getInfo_numHangar_Prat() {
        return info_numHangar_Prat;
    }

    public JTextField getInfo_numTaller_Prat() {
        return info_numTaller_Prat;
    }

    public JTextField getInfo_numArEst_Prat() {
        return info_numArEst_Prat;
    }

    public JTextField getInfo_numArRod_Prat() {
        return info_numArRod_Prat;
    }

    public JTextField getInfo_numPuerEmb_Prat() {
        return info_numPuerEmb_Prat;
    }

    public JTextField getInfo_numPistas_Prat() {
        return info_numPistas_Prat;
    }
    
    // Método que habilita el cierre y apertura de pistas:
    
    @Override
    public void actionPerformed(ActionEvent evento) {                                         
        try {
            Object boton = evento.getSource();
            paso.mirar();
            // Botones Aeropuerto Barajas
            if(boton == this.botonP1Abrir_Bar) {
                botonP1Abrir_Bar.setEnabled(false);
                barajas.setEstadoPista(0, true);
                botonP1Cerrar_Bar.setEnabled(true);
            }
            else if (boton == botonP2Abrir_Bar) {
                botonP2Abrir_Bar.setEnabled(false);
                barajas.setEstadoPista(1, true);
                botonP2Cerrar_Bar.setEnabled(true);
            }
            else if (boton == botonP3Abrir_Bar) {
                botonP3Abrir_Bar.setEnabled(false);
                barajas.setEstadoPista(2, true);
                botonP3Cerrar_Bar.setEnabled(true);
            }
            else if (boton == botonP4Abrir_Bar) {
                botonP4Abrir_Bar.setEnabled(false);
                barajas.setEstadoPista(3, true);
                botonP4Cerrar_Bar.setEnabled(true);
            }
            else if (boton == botonP1Cerrar_Bar) {
                botonP1Cerrar_Bar.setEnabled(false);
                barajas.setEstadoPista(0, false);
                botonP1Abrir_Bar.setEnabled(true);
            }
            else if (boton == botonP2Cerrar_Bar) {
                botonP2Cerrar_Bar.setEnabled(false);
                barajas.setEstadoPista(1, false);
                botonP2Abrir_Bar.setEnabled(true);
            }
            else if (boton == botonP3Cerrar_Bar) {
                botonP3Cerrar_Bar.setEnabled(false);
                barajas.setEstadoPista(2, false);
                botonP3Abrir_Bar.setEnabled(true);
            }
            else if (boton == botonP4Cerrar_Bar) {
                botonP4Cerrar_Bar.setEnabled(false);
                barajas.setEstadoPista(3, false);
                botonP4Abrir_Bar.setEnabled(true);
            }
            // Botones Aeropuerto Prat:
            else if(boton == this.botonP1Abrir_Prat) {
                botonP1Abrir_Prat.setEnabled(false);
                prat.setEstadoPista(0, true);
                botonP1Cerrar_Prat.setEnabled(true);
            }
            else if (boton == botonP2Abrir_Prat) {
                botonP2Abrir_Prat.setEnabled(false);
                prat.setEstadoPista(1, true);
                botonP2Cerrar_Prat.setEnabled(true);
            }
            else if (boton == botonP3Abrir_Prat) {
                botonP3Abrir_Prat.setEnabled(false);
                prat.setEstadoPista(2, true);
                botonP3Cerrar_Prat.setEnabled(true);
            }
            else if (boton == botonP4Abrir_Prat) {
                botonP4Abrir_Prat.setEnabled(false);
                prat.setEstadoPista(3, true);
                botonP4Cerrar_Prat.setEnabled(true);
            }
            else if (boton == botonP1Cerrar_Prat) {
                botonP1Cerrar_Prat.setEnabled(false);
                prat.setEstadoPista(0, false);
                botonP1Abrir_Prat.setEnabled(true);
            }
            else if (boton == botonP2Cerrar_Prat) {
                botonP2Cerrar_Prat.setEnabled(false);
                prat.setEstadoPista(1, false);
                botonP2Abrir_Prat.setEnabled(true);
            }
            else if (boton == botonP3Cerrar_Prat) {
                botonP3Cerrar_Prat.setEnabled(false);
                prat.setEstadoPista(2, false);
                botonP3Abrir_Prat.setEnabled(true);
            }
            else if (boton == botonP4Cerrar_Prat) {
                botonP4Cerrar_Prat.setEnabled(false);
                prat.setEstadoPista(3, false);
                botonP4Abrir_Prat.setEnabled(true);
            }
        } catch (RemoteException ex) {
            Logger.getLogger(InterfazCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
