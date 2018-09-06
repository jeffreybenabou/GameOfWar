package GameCore;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public class HintTextField extends JTextField {

    Font gainFont = new Font("Tahoma", Font.PLAIN, 11);
    Font lostFont = new Font("Tahoma", Font.ITALIC, 11);
    private boolean hintActive=true;

    public HintTextField(final String hint) {

        setText(hint);
        setFont(lostFont);
        setForeground(Color.GRAY);

        this.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(hint)) {
                    hintActive=true;
                    setText("");
                    setFont(gainFont);
                } else {
                    hintActive=false;
                    setText(getText());

                    setFont(gainFont);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().equals(hint)|| getText().length()==0) {
                    setText(hint);
                    hintActive=true;
                    setFont(lostFont);
                    setForeground(Color.GRAY);
                } else {
                    hintActive=false;
                    setText(getText());
                    setFont(gainFont);
                    setForeground(Color.BLACK);
                }
            }
        });

    }

    public boolean isHintActive() {
        return hintActive;
    }

    public void setHintActive(boolean hintActive) {
        this.hintActive = hintActive;
    }
}