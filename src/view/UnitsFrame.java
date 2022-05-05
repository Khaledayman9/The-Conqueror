package view;

import javax.swing.JFrame;
import javax.swing.JTextPane;

public class UnitsFrame extends JFrame {
	JTextPane txt;
	public UnitsFrame() {
		txt = new JTextPane();
		setSize(300,300);
		txt.setEditable(false);
		this.getContentPane().add(txt);
	}
}
