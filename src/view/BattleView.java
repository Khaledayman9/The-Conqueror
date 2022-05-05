package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import engine.City;
import units.Army;

public class BattleView extends JFrame{
	
	JPanel Attacking;
	JPanel Defending;
	JPanel Center;
	JTextPane log;
	JPanel buttonsP;
	JButton Attack;
	JButton AutoResolve;
	public BattleView() {	
		this.setSize(720,640);
		setTitle("Battle View");
		this.setLayout(new GridLayout(1,3));
		Attacking = new JPanel();
		Attacking.setLayout(new FlowLayout());
		add(Attacking);
		Center = new JPanel();
		Center.setLayout(new GridLayout(2,1));
		add(Center);
		Defending = new JPanel();
		Defending.setLayout(new FlowLayout());
		add(Defending);
		
		log = new JTextPane();
		log.setLayout(new FlowLayout());
		log.setEditable(false);
		Center.add(log);
		buttonsP = new JPanel();
		buttonsP.setLayout(new FlowLayout());
		Center.add(buttonsP);
		
		Attack = new JButton("Attack");
		buttonsP.add(Attack);
		AutoResolve = new JButton("Auto Resolve");
		buttonsP.add(AutoResolve);
	}

}
