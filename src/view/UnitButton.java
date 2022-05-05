package view;

import javax.swing.JButton;

import units.Archer;
import units.Cavalry;
import units.Infantry;
import units.Unit;

public class UnitButton extends JButton {
	
	Unit unit;
	public UnitButton(Unit u){
		unit = u;
		if(u instanceof Infantry) 
			setText("Infantry"+u.getLevel() +u.getCurrentSoldierCount()+u.getMaxSoldierCount());
		if(u instanceof Cavalry) 
			setText("Infantry"+u.getLevel()+u.getCurrentSoldierCount()+u.getMaxSoldierCount());
		if(u instanceof Archer) 
			setText("Infantry" +u.getLevel() +u.getCurrentSoldierCount()+u.getMaxSoldierCount());
	}
}
