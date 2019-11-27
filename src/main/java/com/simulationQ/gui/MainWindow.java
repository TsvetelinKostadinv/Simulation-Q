/*
 * 27/11/2019 16:07:58
 * MainWindow.java created by Tsvetelin
 */
package com.simulationQ.gui;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.simulationQ.computation.qubits.QCollapser;
import com.simulationQ.computation.qubits.Qubit;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;

import javax.swing.JButton;


/**
 * @author Tsvetelin
 *
 */
public class MainWindow extends JFrame implements Runnable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private JPanel            contentPane;

    private JLabel            lblRes;

    private JTextArea         txtCollapseCounting;

    public void run ()
    {
        try
        {
            MainWindow frame = new MainWindow();
            frame.setVisible( true );
        } catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    /**
     * Create the frame.
     */
    public MainWindow ()
    {
        setTitle( "Simulation Q" );
        setIconImage( Toolkit.getDefaultToolkit()
                             .getImage( MainWindow.class.getResource( "/com/simulationQ/gui/icon/logo.png" ) ) );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setBounds( 100 , 100 , 800 , 800 );

        this.setLocationRelativeTo( null );

        contentPane = new JPanel();
        contentPane.setBorder( new EmptyBorder( 5 , 5 , 5 , 5 ) );
        setContentPane( contentPane );
        contentPane.setLayout( null );

        JLabel lblClarificationCollapseCounting = new JLabel( "Number of Collapses" );
        lblClarificationCollapseCounting.setBounds( 10 , 10 , 200 , 20 );
        contentPane.add( lblClarificationCollapseCounting );

        txtCollapseCounting = new JTextArea( "100" );
        txtCollapseCounting.setBounds( 150 , 10 , 200 , 20 );
        contentPane.add( txtCollapseCounting );

        JLabel lblQubit = new JLabel( "|0>" );
        lblQubit.setBounds( 10 , 40 , 20 , 20 );
        contentPane.add( lblQubit );

        JButton btnCollapse = new JButton( "Collapse" );
        btnCollapse.setBounds( 10 , 700 , 750 , 30 );

        btnCollapse.addMouseListener( new MouseListener()
        {

            @Override
            public void mouseReleased ( MouseEvent e )
            {}

            @Override
            public void mousePressed ( MouseEvent e )
            {}

            @Override
            public void mouseExited ( MouseEvent e )
            {}

            @Override
            public void mouseEntered ( MouseEvent e )
            {}

            @Override
            public void mouseClicked ( MouseEvent e )
            {
                updateCollapseInfo();
            }
        } );

        contentPane.add( btnCollapse );

        lblRes = new JLabel( Qubit.QUBIT_ON.toString() );
        lblRes.setBounds( 300 , 40 , 500 , 20 );
        lblRes.setVisible( false );
        contentPane.add( lblRes );
    }

    public void paint ( Graphics gp )
    {
        super.paint( gp );
        Graphics2D graphics = ( Graphics2D ) gp;
        Line2D line = new Line2D.Float( 40 , 80 , 300 , 80 );
        graphics.draw( line );
    }

    private void updateCollapseInfo ()
    {
        lblRes.setVisible( true );
        
        Qubit input = Qubit.QUBIT_HALF_HALF;
        
        Qubit res = QCollapser.collapse( Long.parseLong( txtCollapseCounting.getText() ) , input );
        
        lblRes.setText( res.toString() );
        
        this.paint( this.getGraphics() );
        
    }
}
