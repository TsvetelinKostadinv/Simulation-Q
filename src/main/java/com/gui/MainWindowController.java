package com.gui;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.simulationQ.simulation.computation.program.QProgram;
import com.simulationQ.simulation.computation.qubits.register.CRegister;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;


public class MainWindowController
{

    @FXML
    private TabPane                     tab_pane_main;

    @FXML
    private Tab                         tab_quantum_program;

    @FXML
    private Tab                         tab_quantum_results;

    @FXML
    private BarChart< String , Number > bar_results;

    @FXML
    private Button                      btn_plus;

    @FXML
    private Button                      btn_minus;

    @FXML
    private TextField                   txt_number_of_collapses;

    private final List< Label >         labelsQubits              = new LinkedList< Label >();

    private static final int            ROW_HEIGHT                = 20;

    private static int                  nextRowStarting_X_pointer = 20;

    private static int                  nextRowStarting_Y_pointer = 10;

    @FXML
    protected final void addNewQubit ()
    {

        List< Node > row = constructNewQubitProgramRow();

        ( ( AnchorPane ) tab_quantum_program.getContent() ).getChildren()
                                                           .addAll( row );
    }

    @FXML
    protected final void deleteLastQubit ()
    {
        System.out.println( "Deleting" );
        this.labelsQubits.remove( this.labelsQubits.size() - 1 );

        List< Node > buttonsAndLabels = ( ( AnchorPane ) tab_quantum_program.getContent() ).getChildren();

        if ( buttonsAndLabels.size() <= 2 ) return; // there are no labels

        while ( ! ( buttonsAndLabels.get( buttonsAndLabels.size()
                - 1 ) instanceof Label ) )
        {
            buttonsAndLabels.remove( buttonsAndLabels.size() - 1 ); // remove
                                                                    // all the
                                                                    // program
                                                                    // operations
                                                                    // on that
                                                                    // row
        }
        buttonsAndLabels.remove( buttonsAndLabels.size() - 1 ); // remove the
                                                                // label

        translatePlusAndMinusButtonsUp();
        nextRowStarting_Y_pointer -= ROW_HEIGHT;

    }

    @FXML
    protected final void calculateAndPopulateGraph ()
    {
        // graphSample();

        final List< Character > zerosAndOnes = labelsQubits.stream()
                                                           .map( x -> x.getText()
                                                                       .toCharArray()[1] )
                                                           .collect( Collectors.toUnmodifiableList() );

        final String startingState = zerosAndOnes.toString()
                                                 .replaceAll( "\\[|\\]|\\s|\\," ,
                                                              "" );

        final CRegister reg = new CRegister( startingState );

        // TODO parse the QProgram and pass it in here
        
        try
        {
            String number = txt_number_of_collapses.getText();
            Integer collapses = Integer.parseInt( txt_number_of_collapses.getText() );

            graphData( CollapseDataModel.generateCollapseData( reg ,
                                                               new QProgram() ,
                                                               collapses ) );
            
        } catch ( NumberFormatException e )
        {
            Alert alert = new Alert( AlertType.ERROR );
            alert.setTitle( "Unexpected number format" );
            alert.setHeaderText( "Check the number of collapses text field" );
            alert.setContentText( txt_number_of_collapses.getText()
                    + " is not a number" );

            alert.showAndWait();
        }
    }

    /**
     * @param collapseData
     */
    private void graphData ( Map< String , Number > collapseData )
    {
        bar_results.getData().clear();

        XYChart.Series< String , Number > chart = new Series< String , Number >();

        chart.getData()
             .addAll(
                      collapseData.entrySet()
                                  .stream()
                                  .map( x -> new XYChart.Data< String , Number >( x.getKey() ,
                                                                                  x.getValue() ) )
                                  .collect( Collectors.toList() ) );

        bar_results.getData().add( chart );

        tab_pane_main.getSelectionModel().select( tab_quantum_results );
    }

    @SuppressWarnings ( "unchecked" )
    private final void graphSample ()
    {

        bar_results.getData().clear();

        XYChart.Series< String , Number > series1 = new Series< String , Number >();

        series1.getData()
               .addAll(
                        new XYChart.Data[] {
                                new XYChart.Data< String , Number >( "|00>" ,
                                                                     0.4 ),
                                new XYChart.Data< String , Number >( "|01>" ,
                                                                     0.2 ),
                                new XYChart.Data< String , Number >( "|10>" ,
                                                                     0.9 ),
                                new XYChart.Data< String , Number >( "|11>" ,
                                                                     -0.2 )
                        } );

        bar_results.getData().add( series1 );

        tab_pane_main.getSelectionModel().select( tab_quantum_results );
    }

    private final List< Node > constructNewQubitProgramRow ()
    {
        // graphSample();
        final Label startingState = constructStartingStateLabel();

        this.labelsQubits.add( startingState );

        // moving pointers
        nextRowStarting_Y_pointer += ROW_HEIGHT;

        translatePlusAndMinusButtonsDown();

        // TODO add the plus for the gate

        return Arrays.asList( startingState );
    }

    /**
     * @return
     */
    private Label constructStartingStateLabel ()
    {
        final Label startingState = new Label( "|0>" );
        startingState.setFont( new Font( ROW_HEIGHT - 2 ) );

        startingState.setTranslateX( nextRowStarting_X_pointer );
        startingState.setTranslateY( nextRowStarting_Y_pointer );

        startingState.setOnMousePressed( ( event ) -> {
            MouseButton mBtn = event.getButton();
            Label lbl = ( Label ) event.getSource();
            if ( mBtn.compareTo( MouseButton.PRIMARY ) == 0 )
            {
                System.out.println( "Changing" );

                switch ( lbl.getText().charAt( 1 ) )
                    {
                        case '0' :
                            lbl.setText( "|1>" );
                            break;
                        case '1' :
                            lbl.setText( "|0>" );
                            break;
                    }

            }
        } );

        return startingState;
    }

    /**
     * 
     */
    private void translatePlusAndMinusButtonsDown ()
    {
        btn_plus.translateYProperty()
                .setValue( nextRowStarting_Y_pointer + ROW_HEIGHT );
        btn_minus.translateYProperty()
                 .setValue( nextRowStarting_Y_pointer + ROW_HEIGHT );
    }

    /**
     * 
     */
    private void translatePlusAndMinusButtonsUp ()
    {
        btn_plus.translateYProperty()
                .setValue( nextRowStarting_Y_pointer - ROW_HEIGHT );
        btn_minus.translateYProperty()
                 .setValue( nextRowStarting_Y_pointer - ROW_HEIGHT );
    }

    @FXML
    public void initialize ()
    {
        bar_results.getXAxis().setLabel( "Values" );
        bar_results.getYAxis().setLabel( "Probability" );

    }

}
