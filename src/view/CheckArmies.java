package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CheckArmies extends JButton {

	ControlledInfo controlledInfo;
	ArrayList<ArmyButton> march = new ArrayList<ArmyButton>();
	ArrayList<ArmyButton> idle = new ArrayList<ArmyButton>();
	ArrayList<ArmyButton> besiege = new ArrayList<ArmyButton>();
	public CheckArmies() {
		controlledInfo = new ControlledInfo();
	}
}
