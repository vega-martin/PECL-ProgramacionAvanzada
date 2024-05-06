package userInterfaces;

import java.awt.event.*;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;
import static javax.swing.GroupLayout.*;
import static javax.swing.LayoutStyle.ComponentPlacement.*;
import progavanzada.Paso;

public class InterfazServidor extends JFrame implements ActionListener{
    
    // COMPONENTES DE LA INTERFAZ
    
    // Botones:
    private JButton botonPausar = new JButton("Pausar");
    private JButton botonReanudar = new JButton("Reanudar");
    
    // Etiquetas generales:
    private JLabel tituloAeropuertoBarajas = new JLabel("Aeropuerto de Madrid - Barajas");
    private JLabel tituloAeropuertoPrat = new JLabel("Aeropuerto de Barcelona - Prat");
    private JLabel ae_Bar_Prat = new JLabel("Aerovía Barajas - Prat:");
    private JLabel ae_Prat_Bar = new JLabel("Aerovía Prat - Barajas:");
    
    // Etiquetas propias del aeropueto Barajas:
    private JLabel numPas_Bar = new JLabel("Nº pasajeros en Aeropuerto:");
    private JLabel hangar_Bar = new JLabel("Hangar:");
    private JLabel taller_Bar = new JLabel("Taller:");
    private JLabel est_Bar = new JLabel("Área de Estacionamiento:");
    private JLabel pEmb1_Bar = new JLabel("Gate 1:");
    private JLabel pEmb2_Bar = new JLabel("Gate 2:");
    private JLabel pEmb3_Bar = new JLabel("Gate 3:");
    private JLabel pEmb4_Bar = new JLabel("Gate 4:");
    private JLabel pEmb5_Bar = new JLabel("Gate 5:");
    private JLabel pEmb6_Bar = new JLabel("Gate 6:");
    private JLabel rodaje_Bar = new JLabel("Área de Rodaje:");
    private JLabel pista1_Bar = new JLabel("Pista 1:");
    private JLabel pista2_Bar = new JLabel("Pista 2:");
    private JLabel pista3_Bar = new JLabel("Pista 3:");
    private JLabel pista4_Bar = new JLabel("Pista 4:");
    
    // Etiquetas propias del aeropueto El Prat:
    private JLabel numPas_Prat = new JLabel("Nº pasajeros en Aeropuerto:");
    private JLabel hangar_Prat = new JLabel("Hangar:");
    private JLabel taller_Prat = new JLabel("Taller:");
    private JLabel est_Prat = new JLabel("Área de Estacionamiento:");
    private JLabel pEmb1_Prat = new JLabel("Gate 1:");
    private JLabel pEmb2_Prat = new JLabel("Gate 2:");
    private JLabel pEmb3_Prat = new JLabel("Gate 3:");
    private JLabel pEmb4_Prat = new JLabel("Gate 4:");
    private JLabel pEmb5_Prat = new JLabel("Gate 5:");
    private JLabel pEmb6_Prat = new JLabel("Gate 6:");
    private JLabel rodaje_Prat = new JLabel("Área de Rodaje:");
    private JLabel pista1_Prat = new JLabel("Pista 1:");
    private JLabel pista2_Prat = new JLabel("Pista 2:");
    private JLabel pista3_Prat = new JLabel("Pista 3:");
    private JLabel pista4_Prat = new JLabel("Pista 4:");
    
    // Separadores:
    private final JSeparator s_superior = new JSeparator();
    private final JSeparator s_inferior = new JSeparator();
    
    // CUADROS DE TEXTO
    
    // Aeropuerto Barajas:
    private JTextField info_numPas_Bar = new JTextField("0");
    private JTextField info_hangar_Bar = new JTextField();
    private JTextField info_taller_Bar = new JTextField();
    private JTextField info_est_Bar = new JTextField();
    private JTextField info_pEmb1_Bar = new JTextField();
    private JTextField info_pEmb2_Bar = new JTextField();
    private JTextField info_pEmb3_Bar = new JTextField();
    private JTextField info_pEmb4_Bar = new JTextField();
    private JTextField info_pEmb5_Bar = new JTextField();
    private JTextField info_pEmb6_Bar = new JTextField();
    private JTextField info_rodaje_Bar = new JTextField();
    private JTextField info_pista1_Bar = new JTextField();
    private JTextField info_pista2_Bar = new JTextField();
    private JTextField info_pista3_Bar = new JTextField();
    private JTextField info_pista4_Bar = new JTextField();
    
    // Aerovías:
    private JTextField info_ae_Bar_Prat = new JTextField();
    private JTextField info_ae_Prat_Bar = new JTextField();
    
    // Aeropuerto Prat:
    private JTextField info_numPas_Prat = new JTextField("0");
    private JTextField info_hangar_Prat = new JTextField();
    private JTextField info_taller_Prat = new JTextField();
    private JTextField info_est_Prat = new JTextField();
    private JTextField info_pEmb1_Prat = new JTextField();
    private JTextField info_pEmb2_Prat = new JTextField();
    private JTextField info_pEmb3_Prat = new JTextField();
    private JTextField info_pEmb4_Prat = new JTextField();
    private JTextField info_pEmb5_Prat = new JTextField();
    private JTextField info_pEmb6_Prat = new JTextField();
    private JTextField info_rodaje_Prat = new JTextField();
    private JTextField info_pista1_Prat = new JTextField();
    private JTextField info_pista2_Prat = new JTextField();
    private JTextField info_pista3_Prat = new JTextField();
    private JTextField info_pista4_Prat = new JTextField();
    
    // Instancia para pausar el programa:
    private Paso paso;
    
    // Constructor de la clase gráfica:
    public InterfazServidor(Paso p) {
        
        super("Simulador de Aeropuertos : Servidor");
        this.paso = p;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.initComponents();
        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
    }
    
    // Establecer diferentes parámetros a los componentes de Swing:
    private void initComponents() {
        
        botonPausar.addActionListener(this);
        botonReanudar.addActionListener(this);

        tituloAeropuertoBarajas.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        tituloAeropuertoPrat.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        
        botonPausar.setEnabled(true);
        botonReanudar.setEnabled(false);
        botonPausar.setFocusable(false);
        botonReanudar.setFocusable(false);
        
        info_numPas_Bar.setEditable(false);
        info_hangar_Bar.setEditable(false);
        info_taller_Bar.setEditable(false);
        info_est_Bar.setEditable(false);
        info_pEmb1_Bar.setEditable(false);
        info_pEmb4_Bar.setEditable(false);
        info_pEmb2_Bar.setEditable(false);
        info_pEmb5_Bar.setEditable(false);
        info_pEmb3_Bar.setEditable(false);
        info_pEmb6_Bar.setEditable(false);
        info_rodaje_Bar.setEditable(false);
        info_pista1_Bar.setEditable(false);
        info_pista3_Bar.setEditable(false);
        info_pista4_Bar.setEditable(false);
        info_pista2_Bar.setEditable(false);
        info_ae_Bar_Prat.setEditable(false);
        info_ae_Prat_Bar.setEditable(false);
        info_numPas_Prat.setEditable(false);
        info_hangar_Prat.setEditable(false);
        info_pEmb3_Prat.setEditable(false);
        info_taller_Prat.setEditable(false);
        info_pEmb6_Prat.setEditable(false);
        info_est_Prat.setEditable(false);
        info_rodaje_Prat.setEditable(false);
        info_pEmb1_Prat.setEditable(false);
        info_pista1_Prat.setEditable(false);
        info_pista3_Prat.setEditable(false);
        info_pista4_Prat.setEditable(false);
        info_pista2_Prat.setEditable(false);
        info_pEmb4_Prat.setEditable(false);
        info_pEmb2_Prat.setEditable(false);
        info_pEmb5_Prat.setEditable(false);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(LEADING)
            .addComponent(s_superior)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(botonPausar, PREFERRED_SIZE, 120, PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(botonReanudar, PREFERRED_SIZE, 120, PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addGroup(layout.createParallelGroup(TRAILING)
                                .addComponent(ae_Bar_Prat, PREFERRED_SIZE, 170, PREFERRED_SIZE)
                                .addComponent(ae_Prat_Bar, PREFERRED_SIZE, 170, PREFERRED_SIZE))
                            .addGap(10, 10, 10)
                            .addGroup(layout.createParallelGroup(LEADING)
                                .addComponent(info_ae_Bar_Prat, PREFERRED_SIZE, 700, PREFERRED_SIZE)
                                .addComponent(info_ae_Prat_Bar, PREFERRED_SIZE, 700, PREFERRED_SIZE)))
                        .addGroup(TRAILING, layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(layout.createParallelGroup(LEADING, false)
                                .addComponent(info_rodaje_Prat, PREFERRED_SIZE, 425, PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(numPas_Prat, PREFERRED_SIZE, 170, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_numPas_Prat, PREFERRED_SIZE, 150, PREFERRED_SIZE))
                                .addComponent(est_Prat, PREFERRED_SIZE, 170, PREFERRED_SIZE)
                                .addComponent(rodaje_Prat, PREFERRED_SIZE, 100, PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(TRAILING, false)
                                    .addGroup(LEADING, layout.createSequentialGroup()
                                        .addComponent(pista2_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_pista2_Prat, PREFERRED_SIZE, 141, PREFERRED_SIZE)
                                        .addPreferredGap(UNRELATED)
                                        .addComponent(pista4_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_pista4_Prat, PREFERRED_SIZE, 141, PREFERRED_SIZE))
                                    .addGroup(LEADING, layout.createSequentialGroup()
                                        .addComponent(pista1_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_pista1_Prat, PREFERRED_SIZE, 141, PREFERRED_SIZE)
                                        .addPreferredGap(UNRELATED)
                                        .addComponent(pista3_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_pista3_Prat, PREFERRED_SIZE, 141, PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(TRAILING, false)
                                    .addGroup(LEADING, layout.createSequentialGroup()
                                        .addComponent(taller_Prat, PREFERRED_SIZE, 52, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_taller_Prat, PREFERRED_SIZE, 361, PREFERRED_SIZE))
                                    .addGroup(LEADING, layout.createSequentialGroup()
                                        .addComponent(hangar_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_hangar_Prat, PREFERRED_SIZE, 361, PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pEmb3_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_pEmb3_Prat, PREFERRED_SIZE, 141, PREFERRED_SIZE)
                                        .addPreferredGap(UNRELATED)
                                        .addComponent(pEmb6_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_pEmb6_Prat, PREFERRED_SIZE, 141, PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(pEmb2_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_pEmb2_Prat, PREFERRED_SIZE, 141, PREFERRED_SIZE)
                                        .addPreferredGap(UNRELATED)
                                        .addComponent(pEmb5_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_pEmb5_Prat))
                                    .addGroup(LEADING, layout.createSequentialGroup()
                                        .addComponent(pEmb1_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_pEmb1_Prat, PREFERRED_SIZE, 141, PREFERRED_SIZE)
                                        .addPreferredGap(UNRELATED)
                                        .addComponent(pEmb4_Prat, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                        .addPreferredGap(RELATED)
                                        .addComponent(info_pEmb4_Prat, PREFERRED_SIZE, 141, PREFERRED_SIZE))
                                    .addComponent(info_est_Prat, PREFERRED_SIZE, 425, PREFERRED_SIZE)))
                            .addGap(1, 1, 1))))
                .addContainerGap(32, Short.MAX_VALUE))
            .addComponent(s_inferior, TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(LEADING)
                            .addComponent(info_rodaje_Bar, PREFERRED_SIZE, 425, PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(numPas_Bar, PREFERRED_SIZE, 170, PREFERRED_SIZE)
                                .addPreferredGap(RELATED)
                                .addComponent(info_numPas_Bar, PREFERRED_SIZE, 150, PREFERRED_SIZE))
                            .addComponent(est_Bar, PREFERRED_SIZE, 170, PREFERRED_SIZE)
                            .addComponent(rodaje_Bar, PREFERRED_SIZE, 100, PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(TRAILING, false)
                                .addGroup(LEADING, layout.createSequentialGroup()
                                    .addComponent(pista2_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_pista2_Bar, PREFERRED_SIZE, 141, PREFERRED_SIZE)
                                    .addPreferredGap(UNRELATED)
                                    .addComponent(pista4_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_pista4_Bar, PREFERRED_SIZE, 141, PREFERRED_SIZE))
                                .addGroup(LEADING, layout.createSequentialGroup()
                                    .addComponent(pista1_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_pista1_Bar, PREFERRED_SIZE, 141, PREFERRED_SIZE)
                                    .addPreferredGap(UNRELATED)
                                    .addComponent(pista3_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_pista3_Bar, PREFERRED_SIZE, 141, PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(TRAILING, false)
                                .addGroup(LEADING, layout.createSequentialGroup()
                                    .addComponent(taller_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_taller_Bar, PREFERRED_SIZE, 361, PREFERRED_SIZE))
                                .addGroup(LEADING, layout.createSequentialGroup()
                                    .addComponent(hangar_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_hangar_Bar, PREFERRED_SIZE, 361, PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(pEmb3_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_pEmb3_Bar, PREFERRED_SIZE, 141, PREFERRED_SIZE)
                                    .addPreferredGap(UNRELATED)
                                    .addComponent(pEmb6_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_pEmb6_Bar, PREFERRED_SIZE, 141, PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(pEmb2_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_pEmb2_Bar, PREFERRED_SIZE, 141, PREFERRED_SIZE)
                                    .addPreferredGap(UNRELATED)
                                    .addComponent(pEmb5_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_pEmb5_Bar, PREFERRED_SIZE, 141, PREFERRED_SIZE))
                                .addGroup(LEADING, layout.createSequentialGroup()
                                    .addComponent(pEmb1_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_pEmb1_Bar, PREFERRED_SIZE, 141, PREFERRED_SIZE)
                                    .addPreferredGap(UNRELATED)
                                    .addComponent(pEmb4_Bar, PREFERRED_SIZE, 51, PREFERRED_SIZE)
                                    .addPreferredGap(RELATED)
                                    .addComponent(info_pEmb4_Bar, PREFERRED_SIZE, 141, PREFERRED_SIZE))
                                .addComponent(info_est_Bar, PREFERRED_SIZE, 425, PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(tituloAeropuertoBarajas, PREFERRED_SIZE, 314, PREFERRED_SIZE)
                        .addPreferredGap(RELATED, DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tituloAeropuertoPrat)
                        .addGap(100, 100, 100))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(botonReanudar)
                    .addComponent(botonPausar))
                .addPreferredGap(RELATED)
                .addComponent(s_superior, PREFERRED_SIZE, 10, PREFERRED_SIZE)
                .addPreferredGap(RELATED)
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(tituloAeropuertoBarajas, PREFERRED_SIZE, 27, PREFERRED_SIZE)
                    .addComponent(tituloAeropuertoPrat, PREFERRED_SIZE, 27, PREFERRED_SIZE))
                .addPreferredGap(RELATED)
                .addGroup(layout.createParallelGroup(LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numPas_Bar)
                            .addComponent(info_numPas_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(hangar_Bar)
                            .addComponent(info_hangar_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(taller_Bar)
                            .addComponent(info_taller_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addComponent(est_Bar)
                        .addPreferredGap(UNRELATED)
                        .addComponent(info_est_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pEmb1_Bar)
                            .addComponent(info_pEmb1_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(pEmb4_Bar)
                            .addComponent(info_pEmb4_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pEmb2_Bar)
                            .addComponent(info_pEmb2_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(pEmb5_Bar)
                            .addComponent(info_pEmb5_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pEmb3_Bar)
                            .addComponent(info_pEmb3_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(pEmb6_Bar)
                            .addComponent(info_pEmb6_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addComponent(rodaje_Bar)
                        .addPreferredGap(UNRELATED)
                        .addComponent(info_rodaje_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista1_Bar)
                            .addComponent(info_pista1_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(pista3_Bar)
                            .addComponent(info_pista3_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista2_Bar)
                            .addComponent(info_pista2_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(pista4_Bar)
                            .addComponent(info_pista4_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(numPas_Prat)
                            .addComponent(info_numPas_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(hangar_Prat)
                            .addComponent(info_hangar_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(taller_Prat)
                            .addComponent(info_taller_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addComponent(est_Prat)
                        .addPreferredGap(UNRELATED)
                        .addComponent(info_est_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pEmb1_Prat)
                            .addComponent(info_pEmb1_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(pEmb4_Prat)
                            .addComponent(info_pEmb4_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pEmb2_Prat)
                            .addComponent(info_pEmb2_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(pEmb5_Prat)
                            .addComponent(info_pEmb5_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pEmb3_Prat)
                            .addComponent(info_pEmb3_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(pEmb6_Prat)
                            .addComponent(info_pEmb6_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addComponent(rodaje_Prat)
                        .addPreferredGap(UNRELATED)
                        .addComponent(info_rodaje_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista1_Prat)
                            .addComponent(info_pista1_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(pista3_Prat)
                            .addComponent(info_pista3_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                        .addPreferredGap(UNRELATED)
                        .addGroup(layout.createParallelGroup(BASELINE)
                            .addComponent(pista2_Prat)
                            .addComponent(info_pista2_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(pista4_Prat)
                            .addComponent(info_pista4_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))))
                .addPreferredGap(UNRELATED)
                .addComponent(s_inferior, PREFERRED_SIZE, 10, PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(ae_Bar_Prat)
                    .addComponent(info_ae_Bar_Prat, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                .addPreferredGap(RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(BASELINE)
                    .addComponent(ae_Prat_Bar)
                    .addComponent(info_ae_Prat_Bar, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );
        
    }
    
    // Getters para los JTextField:

    public JTextField getInfo_numPas_Bar() {
        return info_numPas_Bar;
    }

    public JTextField getInfo_hangar_Bar() {
        return info_hangar_Bar;
    }

    public JTextField getInfo_taller_Bar() {
        return info_taller_Bar;
    }

    public JTextField getInfo_est_Bar() {
        return info_est_Bar;
    }

    public JTextField getInfo_pEmb1_Bar() {
        return info_pEmb1_Bar;
    }

    public JTextField getInfo_pEmb2_Bar() {
        return info_pEmb2_Bar;
    }

    public JTextField getInfo_pEmb3_Bar() {
        return info_pEmb3_Bar;
    }

    public JTextField getInfo_pEmb4_Bar() {
        return info_pEmb4_Bar;
    }

    public JTextField getInfo_pEmb5_Bar() {
        return info_pEmb5_Bar;
    }

    public JTextField getInfo_pEmb6_Bar() {
        return info_pEmb6_Bar;
    }

    public JTextField getInfo_rodaje_Bar() {
        return info_rodaje_Bar;
    }

    public JTextField getInfo_pista1_Bar() {
        return info_pista1_Bar;
    }

    public JTextField getInfo_pista2_Bar() {
        return info_pista2_Bar;
    }

    public JTextField getInfo_pista3_Bar() {
        return info_pista3_Bar;
    }

    public JTextField getInfo_pista4_Bar() {
        return info_pista4_Bar;
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

    public JTextField getInfo_hangar_Prat() {
        return info_hangar_Prat;
    }

    public JTextField getInfo_taller_Prat() {
        return info_taller_Prat;
    }

    public JTextField getInfo_est_Prat() {
        return info_est_Prat;
    }

    public JTextField getInfo_pEmb1_Prat() {
        return info_pEmb1_Prat;
    }

    public JTextField getInfo_pEmb2_Prat() {
        return info_pEmb2_Prat;
    }

    public JTextField getInfo_pEmb3_Prat() {
        return info_pEmb3_Prat;
    }

    public JTextField getInfo_pEmb4_Prat() {
        return info_pEmb4_Prat;
    }

    public JTextField getInfo_pEmb5_Prat() {
        return info_pEmb5_Prat;
    }

    public JTextField getInfo_pEmb6_Prat() {
        return info_pEmb6_Prat;
    }

    public JTextField getInfo_rodaje_Prat() {
        return info_rodaje_Prat;
    }

    public JTextField getInfo_pista1_Prat() {
        return info_pista1_Prat;
    }

    public JTextField getInfo_pista2_Prat() {
        return info_pista2_Prat;
    }

    public JTextField getInfo_pista3_Prat() {
        return info_pista3_Prat;
    }

    public JTextField getInfo_pista4_Prat() {
        return info_pista4_Prat;
    }
    
    // Método para PAUSAR la ejecución del programa con el correspondiente botón:
    @Override
    public void actionPerformed(ActionEvent evento) {                                         
        Object boton = evento.getSource();
        
        if(boton == botonPausar) {
            botonPausar.setEnabled(false);
            paso.cerrar();
            botonReanudar.setEnabled(true);
        }
        else if (boton == botonReanudar) {
            botonReanudar.setEnabled(false);
            paso.abrir();
            botonPausar.setEnabled(true);
        }
        
    }   
    
} // Fin clase InterfazServidor
