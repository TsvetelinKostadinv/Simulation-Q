/*
 * 27/11/2019 16:07:58
 * MainWindow.java created by Tsvetelin
 */
package com.simulationQ.GUI;


import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.simulationQ.simulation.computation.gates.QGate;
import com.simulationQ.simulation.computation.gates.impl.Hadamard;
import com.simulationQ.simulation.computation.gates.impl.NOT;
import com.simulationQ.simulation.computation.gates.impl.PauliY;
import com.simulationQ.simulation.computation.gates.impl.PauliZ;
import com.simulationQ.simulation.computation.qubits.QCollapser;
import com.simulationQ.simulation.computation.qubits.Qubit;
import com.simulationQ.simulation.computation.qubits.register.QRegister;


/**
 * @author Tsvetelin
 *
 */
@Deprecated
public class MainWindow extends JFrame implements Runnable
{

    /**
     * 
     */
    private static final long   serialVersionUID = 1L;

    private JPanel              contentPane;

    private JLabel              lblRes;

    private JTextArea           txtCollapseCounting;

    private JComboBox< String > gate1;

    private JComboBox< String > gate2;
    
    private JComboBox< String > gate3;

    private QGate               hadamard         = new Hadamard();

    private QGate               not              = new NOT();

    private QGate               pauliY           = new PauliY();

    private QGate               pauliZ           = new PauliZ();

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

        JLabel lblQubit = new JLabel( "|1>" );
        lblQubit.setBounds( 10 , 40 , 20 , 20 );
        contentPane.add( lblQubit );

        JButton btnCollapse = new JButton( "Collapse" );
        btnCollapse.setBounds( 10 , 700 , 750 , 30 );

        btnCollapse.addMouseListener( new MouseListener()
        {

            public void mouseReleased ( MouseEvent e )
            {}

            public void mousePressed ( MouseEvent e )
            {}

            public void mouseExited ( MouseEvent e )
            {}

            public void mouseEntered ( MouseEvent e )
            {}

            public void mouseClicked ( MouseEvent e )
            {
                updateCollapseInfo();
            }
        } );

        contentPane.add( btnCollapse );

        lblRes = new JLabel( Qubit.QUBIT_ON.toString() );
        lblRes.setBounds( 440 , 40 , 500 , 20 );
        lblRes.setVisible( false );
        contentPane.add( lblRes );

        JButton btnCollapseWithoutFinalResult = new JButton( "Show computation value" );
        btnCollapseWithoutFinalResult.setBounds( 10 , 657 , 750 , 30 );

        btnCollapseWithoutFinalResult.addMouseListener( new MouseListener()
        {

            public void mouseReleased ( MouseEvent e )
            {}

            public void mousePressed ( MouseEvent e )
            {}

            public void mouseExited ( MouseEvent e )
            {}

            public void mouseEntered ( MouseEvent e )
            {}

            public void mouseClicked ( MouseEvent e )
            {
                updateIntospectionInfo();
            }
        } );

        contentPane.add( btnCollapseWithoutFinalResult );

        gate1 = new JComboBox< String >();
        gate1.setBounds( 70 , 40 , 100 , 20 );

        gate1.addItem( "None" );
        gate1.addItem( "Hadamard" );
        gate1.addItem( "Pauli-X/NOT" );
        gate1.addItem( "Pauli-Y" );
        gate1.addItem( "Pauli-Z" );

        contentPane.add( gate1 );

        gate2 = new JComboBox< String >();
        gate2.setBounds( 190 , 40 , 100 , 20 );

        gate2.addItem( "None" );
        gate2.addItem( "Hadamard" );
        gate2.addItem( "Pauli-X/NOT" );
        gate2.addItem( "Pauli-Y" );
        gate2.addItem( "Pauli-Z" );

        contentPane.add( gate2 );
        
        gate3 = new JComboBox< String >();
        gate3.setBounds( 310 , 40 , 100 , 20 );

        gate3.addItem( "None" );
        gate3.addItem( "Hadamard" );
        gate3.addItem( "Pauli-X/NOT" );
        gate3.addItem( "Pauli-Y" );
        gate3.addItem( "Pauli-Z" );

        contentPane.add( gate3 );
    }

//    public void paint ( Graphics gp )
//    {
//        super.paint( gp );
//        Graphics2D graphics = ( Graphics2D ) gp;
//        // Line2D line = new Line2D.Float( 40 , 80 , 300 , 80 );
//        // graphics.draw( line );
//    }

    private void updateCollapseInfo ()
    {
        lblRes.setVisible( true );

        Qubit input = Qubit.QUBIT_ON;
        QRegister inputReg = new QRegister( new Qubit[] { input } );

        switch ( gate1.getSelectedIndex() )
            {
                case 1 :

                    input = hadamard.apply( inputReg ).getQubit( 0 );
                    break;
                case 2 :
                    input = not.apply( inputReg ).getQubit( 0 );
                    break;
                case 3 :
                    input = pauliY.apply( inputReg ).getQubit( 0 );
                    break;
                case 4 :
                    input = pauliZ.apply( inputReg ).getQubit( 0 );
                    break;

                default :
                    break;
            }

        inputReg = new QRegister( new Qubit[] { input } );

        switch ( gate2.getSelectedIndex() )
            {
                case 1 :

                    input = hadamard.apply( inputReg ).getQubit( 0 );
                    break;
                case 2 :
                    input = not.apply( inputReg ).getQubit( 0 );
                    break;
                case 3 :
                    input = pauliY.apply( inputReg ).getQubit( 0 );
                    break;
                case 4 :
                    input = pauliZ.apply( inputReg ).getQubit( 0 );
                    break;

                default :
                    break;
            }
        
        inputReg = new QRegister( new Qubit[] { input } );

        switch ( gate3.getSelectedIndex() )
            {
                case 1 :
                    input = hadamard.apply( inputReg ).getQubit( 0 );
                    break;
                case 2 :
                    input = not.apply( inputReg ).getQubit( 0 );
                    break;
                case 3 :
                    input = pauliY.apply( inputReg ).getQubit( 0 );
                    break;
                case 4 :
                    input = pauliZ.apply( inputReg ).getQubit( 0 );
                    break;

                default :
                    break;
            }
//        
//        inputReg = new QRegister( new Qubit[] { input } );

        Qubit res = QCollapser.collapse( Long.parseLong( txtCollapseCounting.getText() ) ,
                                         input );

        lblRes.setText( res.toString() );
        
        this.paint( this.getGraphics() );

    }

    private void updateIntospectionInfo ()
    {
        lblRes.setVisible( true );

        Qubit input = Qubit.QUBIT_ON;
        
        QRegister inputReg = new QRegister( new Qubit[] { input } );

        switch ( gate1.getSelectedIndex() )
            {
                case 1 :

                    input = hadamard.apply( inputReg ).getQubit( 0 );
                    break;
                case 2 :
                    input = not.apply( inputReg ).getQubit( 0 );
                    break;
                case 3 :
                    input = pauliY.apply( inputReg ).getQubit( 0 );
                    break;
                case 4 :
                    input = pauliZ.apply( inputReg ).getQubit( 0 );
                    break;

                default :
                    break;
            }

        inputReg = new QRegister( new Qubit[] { input } );

        switch ( gate2.getSelectedIndex() )
            {
                case 1 :

                    input = hadamard.apply( inputReg ).getQubit( 0 );
                    break;
                case 2 :
                    input = not.apply( inputReg ).getQubit( 0 );
                    break;
                case 3 :
                    input = pauliY.apply( inputReg ).getQubit( 0 );
                    break;
                case 4 :
                    input = pauliZ.apply( inputReg ).getQubit( 0 );
                    break;

                default :
                    break;
            }
        inputReg = new QRegister( new Qubit[] { input } );

        switch ( gate3.getSelectedIndex() )
            {
                case 1 :
                    input = hadamard.apply( inputReg ).getQubit( 0 );
                    break;
                case 2 :
                    input = not.apply( inputReg ).getQubit( 0 );
                    break;
                case 3 :
                    input = pauliY.apply( inputReg ).getQubit( 0 );
                    break;
                case 4 :
                    input = pauliZ.apply( inputReg ).getQubit( 0 );
                    break;

                default :
                    break;
            }
        
        lblRes.setText( input.toString() );
        

        this.paint( this.getGraphics() );

    }
}
