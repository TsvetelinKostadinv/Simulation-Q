package com.gui;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.gui.events.DeleteNextEvent;
import com.simulationQ.simulation.computation.gates.QGates;
import com.simulationQ.simulation.computation.program.QProgram;
import com.simulationQ.simulation.computation.qubits.register.CRegister;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
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

    private static final int            LINE_SPACING              = 10;

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
        nextRowStarting_Y_pointer -= ( ROW_HEIGHT + LINE_SPACING );

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

        final QProgram program = new QProgram();

        List< List< String > > namesOfGates = programRows.stream()
                                                         .map( x -> x.stream()
                                                                     .map( choice -> choice.getSelectionModel()
                                                                                           .getSelectedItem() )
                                                                     .collect( Collectors.toList() ))
                                                         .collect( Collectors.toList() );
        
        for( int i=0;i<namesOfGates.size();i++ )
        {
            for( String gate : namesOfGates.get( i ) )
            {
                if( gate != null
                        && !gate.isBlank() )
                {                    
                    program.addPart( QGates.getGateByName( gate ) , i );
                }
            }
        }

        try
        {
            final String number = txt_number_of_collapses.getText();
            Integer collapses;
            if ( number == null
                    || number.isEmpty()
                    || number.isBlank() )
            {
                collapses = 1;
            } else
            {
                collapses = Integer.parseInt( number );
            }

            graphData( CollapseDataModel.generateCollapseData( reg ,
                                                               program ,
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
    private final void graphData ( Map< String , Number > collapseData )
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

    private final List< List< ChoiceBox< String > > > programRows   = new LinkedList< List< ChoiceBox< String > > >();

    private int                                       nextRowNumber = 0;

    private final List< Node > constructNewQubitProgramRow ()
    {
        // graphSample();
        final Label startingState = constructStartingStateLabel();

        this.labelsQubits.add( startingState );

        final ChoiceBox< String > operationMenu = constructOperationMenu( nextRowNumber ,
                                                                          0 ,
                                                                          nextRowNumber );

        programRows.add( new LinkedList< ChoiceBox< String > >() );
        programRows.get( nextRowNumber ).add( operationMenu );

        // moving pointers
        nextRowStarting_Y_pointer += ROW_HEIGHT + LINE_SPACING;
        translatePlusAndMinusButtonsDown();
        nextRowNumber++;

        return Arrays.asList( startingState , operationMenu );
    }

    /**
     * @return
     */
    private final Label constructStartingStateLabel ()
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

    private static final int EXPECTED_BOX_WIDTH              = 100;

    private static final int SPACING                         = 5;

    private static final int EXPECTED_BOX_WIDTH_WITH_SPACING = EXPECTED_BOX_WIDTH
            + SPACING;

    /**
     * @param rowNumber
     * @param translateY
     * @param translate
     * @return
     */
    private final ChoiceBox< String > constructOperationMenu ( int rowNumber ,
                                                               int xIndexOffSet ,
                                                               int yIndexOffSet )
    {
        final ChoiceBox< String > operationMenu = new ChoiceBox<>();

        operationMenu.setTranslateX( nextRowStarting_X_pointer + 30
                + xIndexOffSet * EXPECTED_BOX_WIDTH_WITH_SPACING );

        operationMenu.setTranslateY( 10
                + yIndexOffSet * ( ROW_HEIGHT + LINE_SPACING ) );

        Set< String > items = QGates.getAllGates()
                                    .stream()
                                    .map( x -> x.getName() )
                                    .collect( Collectors.toSet() );

        items.add( "" );

        operationMenu.getItems().addAll( items );

        operationMenu.getSelectionModel()
                     .selectedItemProperty()
                     .addListener( new ChangeListener< String >()
                     {

                         private final int indexOfRow = rowNumber;

                         private ChoiceBox< String > next;

                         @Override
                         public void changed ( ObservableValue< ? extends String > observable ,
                                               String oldValue ,
                                               String newValue )
                         {
                             if ( newValue != null
                                     && !newValue.isBlank() )
                             {
                                 // From blank to gate
                                 System.out.println( "Should Add +++++" );

                                 next = constructOperationMenu( indexOfRow ,
                                                                xIndexOffSet
                                                                        + 1 ,
                                                                yIndexOffSet );

                                 // Adding it to the program rows
                                 programRows.get( rowNumber )
                                            .add( next );

                                 // Actually visualizing it

                                 ( ( AnchorPane ) tab_quantum_program.getContent() ).getChildren()
                                                                                    .add( next );

                                 operationMenu.addEventHandler( DeleteNextEvent.inst.getEventType() ,
                                                                event -> {

                                                                    if ( next != null )
                                                                    {
                                                                        next.fireEvent( event );
                                                                    }

                                                                    programRows.get( yIndexOffSet )
                                                                               .remove( next );
                                                                    ( ( AnchorPane ) tab_quantum_program.getContent() ).getChildren()
                                                                                                                       .remove( next );
                                                                } );

                             } else
                             {
                                 if ( newValue.isBlank()
                                         && oldValue != null
                                         && !oldValue.isBlank() )
                                 {
                                     // from gate to blank
                                     System.out.println( "Should remove -----" );
                                     next.fireEvent( DeleteNextEvent.inst );

                                     programRows.get( yIndexOffSet )
                                                .remove( next );
                                     ( ( AnchorPane ) tab_quantum_program.getContent() ).getChildren()
                                                                                        .remove( next );
                                 }
                             }
                         }
                     } );

        return operationMenu;
    }

    /**
     * 
     */
    private final void translatePlusAndMinusButtonsDown ()
    {
        btn_plus.translateYProperty()
                .setValue( nextRowStarting_Y_pointer + ROW_HEIGHT
                        + LINE_SPACING );
        btn_minus.translateYProperty()
                 .setValue( nextRowStarting_Y_pointer + ROW_HEIGHT
                         + LINE_SPACING );
    }

    /**
     * 
     */
    private final void translatePlusAndMinusButtonsUp ()
    {
        btn_plus.translateYProperty()
                .setValue( nextRowStarting_Y_pointer - ROW_HEIGHT
                        + LINE_SPACING );
        btn_minus.translateYProperty()
                 .setValue( nextRowStarting_Y_pointer - ROW_HEIGHT
                         + LINE_SPACING );
    }

    @FXML
    public void initialize ()
    {
        bar_results.getXAxis().setLabel( "Values" );
        bar_results.getYAxis().setLabel( "Probability" );

    }

}
