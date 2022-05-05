package view;

import java.util.ArrayList;

import javax.swing.JButton;

import units.Army;

public class ArmyButton extends JButton {
	
	Army army;
	UnitsFrame UFrame; 
	ArrayList<UnitButton> ArmyUnits = new ArrayList<UnitButton>();
	public ArmyButton(Army a) {
		army = a;
		UFrame = new UnitsFrame();
		this.setFocusable(false);
		this.setToolTipText(this.getText());
	}
}
