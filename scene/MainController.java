package scene;

import core.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

public class MainController implements Initializable
{
    private Stage mainStage;
    private int inputFieldCaretPos;

    @FXML private HBox baseBox;

    // Calculate Pane
    @FXML private VBox calculateBox;
    @FXML private CheckMenuItem checkMenuItemVirtualKeyboard;
    @FXML private CheckMenuItem checkMenuItemHistory;
    @FXML private RadioMenuItem radioMenuItemDegree;
    @FXML private RadioMenuItem radioMenuItemRadian;
    @FXML private MenuItem menuItemAbout;
    @FXML private TextField inputField;
    @FXML private TextField resultField;

    // keyboard
    @FXML private HBox keyboardBox;
    @FXML private Button buttonClear;
    @FXML private Button buttonDel;
    @FXML private Button buttonAdd;
    @FXML private Button buttonSub;
    @FXML private Button buttonMul;
    @FXML private Button buttonDiv;
    @FXML private Button buttonLeftParen;
    @FXML private Button buttonRightParen;
    @FXML private Button button0;
    @FXML private Button button1;
    @FXML private Button button2;
    @FXML private Button button3;
    @FXML private Button button4;
    @FXML private Button button5;
    @FXML private Button button6;
    @FXML private Button button7;
    @FXML private Button button8;
    @FXML private Button button9;

    @FXML private Button buttonDot;
    @FXML private Button buttonAns;
    @FXML private Button buttonEqual;

    @FXML private Button buttonRightArrow;
    @FXML private Button buttonLeftArrow;
    @FXML private Button buttonPow;
    @FXML private Button buttonFactorial;
    @FXML private Button buttonAbs;
    @FXML private Button buttonSin;
    @FXML private Button buttonCos;
    @FXML private Button buttonTan;
    @FXML private Button buttonAsin;
    @FXML private Button buttonAcos;
    @FXML private Button buttonAtan;
    @FXML private Button buttonLog;
    @FXML private Button buttonLn;
    @FXML private Button buttonE;
    @FXML private Button buttonPi;
    @FXML private Button buttonSqrt;
    @FXML private Button buttonCbrt;

    // history
    @FXML private VBox historyBox;
    @FXML private Label historyLabel;
    @FXML private ListView<String> listHistory;
    @FXML private Button buttonAddToInput;
    @FXML private Button buttonClearSelected;
    @FXML private Button buttonClearAll;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        setUpStyle();

        // show/hide scene
        keyboardBox.managedProperty().bind(keyboardBox.visibleProperty());
        keyboardBox.visibleProperty().bind(checkMenuItemVirtualKeyboard.selectedProperty());

        historyBox.managedProperty().bind(historyBox.visibleProperty());
        historyBox.visibleProperty().bind(checkMenuItemHistory.selectedProperty());

        checkMenuItemVirtualKeyboard.setSelected(true);

        inputField.focusedProperty().addListener((ob, oldVal, newVal) ->
        {
            if (newVal.booleanValue() == false)
            {
                inputFieldCaretPos = inputField.getCaretPosition();
            }
        });

    }

    private void setUpStyle()
    {
        //
        //button style
        //

        // number-button
        button0.setId("number-button");
        button1.setId("number-button");
        button2.setId("number-button");
        button3.setId("number-button");
        button4.setId("number-button");
        button5.setId("number-button");
        button6.setId("number-button");
        button7.setId("number-button");
        button8.setId("number-button");
        button9.setId("number-button");

        // function-button
        buttonAdd.setId("function-button");
        buttonSub.setId("function-button");
        buttonMul.setId("function-button");
        buttonDiv.setId("function-button");
        buttonLeftParen.setId("function-button");
        buttonRightParen.setId("function-button");
        buttonDot.setId("function-button");
        buttonAns.setId("function-button");
        buttonPow.setId("function-button");
        buttonFactorial.setId("function-button");
        buttonAbs.setId("function-button");
        buttonSin.setId("function-button");
        buttonCos.setId("function-button");
        buttonTan.setId("function-button");
        buttonAsin.setId("function-button");
        buttonAcos.setId("function-button");
        buttonAtan.setId("function-button");
        buttonLog.setId("function-button");
        buttonLn.setId("function-button");
        buttonE.setId("function-button");
        buttonPi.setId("function-button");
        buttonSqrt.setId("function-button");
        buttonCbrt.setId("function-button");

        // special button
        buttonRightArrow.setId("control-button");
        buttonLeftArrow.setId("control-button");
        buttonEqual.setId("control-button");

        // clear button
        buttonClear.setId("clear-button");
        buttonDel.setId("clear-button");

        // normal button
        buttonAddToInput.setId("normal-button");
        buttonClearSelected.setId("normal-button");
        buttonClearAll.setId("normal-button");

        // textfield
        inputField.setId("input-field");
        resultField.setId("result-field");

        // other
        historyLabel.setId("history-label");
    }

    //
    // Menu item event
    //
    @FXML
    private void checkMenuItemVirtualKeyboardOnAction()
    {
        mainStage.sizeToScene();
    }

    @FXML
    private void checkMenuItemHistoryOnAction()
    {
        mainStage.sizeToScene();
    }

    @FXML
    private void radioMenuItemDegreeOnAction()
    {
        if (radioMenuItemDegree.isSelected())
        {
            Main.calculator.setAngularMeasure(0);
        }
    }

    @FXML
    private void radioMenuItemRadianOnAction()
    {
        if (radioMenuItemRadian.isSelected())
        {
            Main.calculator.setAngularMeasure(1);
        }
    }

    @FXML
    private void menuItemAboutOnAction()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("About Xabui Calculator");
        alert.setHeaderText("Xabui Calculator v1.0");
        alert.setContentText("Author: Lễ Bùi\nContact: lebui185@gmail.com\nFacebook: https://www.facebook.com/le.bui.332");
        alert.showAndWait();
    }

    //
    //  InputField event
    //
    @FXML
    private void inputFieldOnAction()
    {
        String result = calculate();
        resultField.setText(result);
    }

    @FXML
    private void inputFieldOnKeyReleased(KeyEvent e)
    {
        if (e.getCode() == KeyCode.P)
        {
            inputField.deletePreviousChar();
            inputField.insertText(inputField.getCaretPosition(), buttonPi.getText());
        }
        else if (e.getCode() == KeyCode.Q)
        {
            try
            {
                if (Character.toLowerCase(inputField.getText().charAt(inputField.getCaretPosition() - 2)) == 's')
                {
                    inputField.deletePreviousChar();
                    inputField.deletePreviousChar();
                    inputField.insertText(inputField.getCaretPosition(), buttonSqrt.getText() + "(");
                }
            }
            catch (ArrayIndexOutOfBoundsException exception)
            {
                // do thing
            }
        }
        else if (e.getCode() == KeyCode.B)
        {
            try
            {
                if (Character.toLowerCase(inputField.getText().charAt(inputField.getCaretPosition() - 2)) == 'c')
                {
                    inputField.deletePreviousChar();
                    inputField.deletePreviousChar();
                    inputField.insertText(inputField.getCaretPosition(), buttonCbrt.getText() + "(");
                }
            }
            catch (ArrayIndexOutOfBoundsException exception)
            {
                // do thing
            }
        }
    }

    //
    // keyboard button event
    //
    @FXML
    private void buttonInputOnAction(ActionEvent e)
    {
        Button button = (Button) e.getSource();
        inputField.requestFocus();
        inputField.insertText(inputFieldCaretPos, button.getText());
    }

    @FXML
    private void buttonFunctionOnAction(ActionEvent e)
    {
        Button button = (Button) e.getSource();
        inputField.requestFocus();
        inputField.insertText(inputFieldCaretPos, button.getText() + "(");
    }

    @FXML
    private void buttonClearOnAction(ActionEvent e) {
        inputField.clear();
        resultField.clear();
        inputField.requestFocus();
    }

    @FXML
    private void buttonDelOnAction(ActionEvent e)
    {
        inputField.requestFocus();
        inputField.positionCaret(inputFieldCaretPos);
        inputField.deletePreviousChar();
    }

    @FXML
    private void buttonEqualOnAction(ActionEvent e)
    {
        String result = calculate();
        resultField.setText(result);
    }

    @FXML
    private void buttonRightArrowOnAction(ActionEvent e)
    {
        inputField.requestFocus();
        inputField.positionCaret(inputFieldCaretPos);
        inputField.forward();
    }

    @FXML
    private void buttonLeftArrowOnAction(ActionEvent e)
    {
        inputField.requestFocus();
        inputField.positionCaret(inputFieldCaretPos);
        inputField.backward();
    }

    //
    // history box button event
    //
    @FXML
    private void buttonAddToInputOnAction(ActionEvent e)
    {
        String item = listHistory.getSelectionModel().getSelectedItem();
        if (item != null)
        {
            inputField.appendText(item);
        }
    }

    @FXML
    private void buttonClearSelectedOnAction(ActionEvent e)
    {
        String item = listHistory.getSelectionModel().getSelectedItem();
        listHistory.getItems().remove(item);
    }

    @FXML
    private void buttonClearAllOnAction(ActionEvent e)
    {
        listHistory.getItems().clear();
    }

    //
    // Other
    //
    public String calculate()
    {
        String result = null;
        String mathExpr = inputField.getText();
        if ((mathExpr.equals("")) == false)
        {
            try
            {
                result = String.valueOf(Main.calculator.calculate(mathExpr));
                if (result.matches("\\d+.0"))
                {
                    result = result.replaceAll("\\.0", "");
                }
                listHistory.getItems().add(mathExpr);
                listHistory.getItems().add(result);
            }
            catch(InputMismatchException | ArithmeticException e)
            {
                return e.getMessage();
            }
        }
        return result;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }
}
