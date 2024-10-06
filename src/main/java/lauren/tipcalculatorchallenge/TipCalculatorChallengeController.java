package lauren.tipcalculatorchallenge;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorChallengeController {
    // formatters for currency and percentages
    private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent = NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal("0.15"); // 15% default

    // GUI controls defined in FXML and used by the controller's code
    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;



    // calculates and displays the tip and total amounts
    private void calculateTipAndTotal() {
        BigDecimal amount;
        try {
            amount = new BigDecimal(amountTextField.getText());
        } catch (NumberFormatException ex) {
            tipTextField.setText("");
            totalTextField.setText("");
            return;
        }
        BigDecimal tipPercentage = BigDecimal.valueOf(tipPercentageSlider.getValue())
            .setScale(2, RoundingMode.HALF_UP)
            .divide(new BigDecimal("100"), RoundingMode.HALF_UP);

        BigDecimal tip = amount.multiply(tipPercentage);
        BigDecimal total = amount.add(tip);

        tipTextField.setText(currency.format(tip));
        totalTextField.setText(currency.format(total));
    }

    // called by FXMLLoader to initialize the controller
    public void initialize() {
        // combines the tipPercentageLabel to the slider value
        tipPercentageLabel.textProperty().bind(tipPercentageSlider.valueProperty().asString("%.0f%%"));

        // 0-4 rounds down, 5-9 rounds up
        currency.setRoundingMode(RoundingMode.HALF_UP);

        //listener for changes to tipPercentageSlider's value
        tipPercentageSlider.valueProperty().addListener((observable, oldValue, newValue) -> calculateTipAndTotal());

        // listener for changes to amountTextField's value
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> calculateTipAndTotal());
            //    tipPercentage = BigDecimal.valueOf(newValue.doubleValue() / 100.0));
        calculateTipAndTotal();
    }
}



