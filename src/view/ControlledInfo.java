package view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ControlledInfo extends JFrame{

	JPanel Idle;
	JPanel Marching;
	JPanel Besieging;

	public ControlledInfo(){
		setSize(1000,700);
		setResizable(false);
		setLayout(new GridLayout(0,3,5,5));
		setTitle("Control Armies Information");
		Idle = new JPanel();
		Idle.setFont(new Font("Tahoma", Font.BOLD, 12));
		Idle.setBackground(new Color(0, 255, 0));
		Idle.setLayout(new FlowLayout());
		Marching = new JPanel();
		Marching.setFont(new Font("Tahoma", Font.BOLD, 12));
		Marching.setBackground(new Color(230, 100, 0));
		Marching.setLayout(new FlowLayout());
		Besieging = new JPanel();
		Besieging.setFont(new Font("Tahoma", Font.BOLD, 12));
		Besieging.setBackground(new Color(255, 0, 0));
		Besieging.setLayout(new FlowLayout());

		JLabel IdleN = new JLabel("Idle Armies");
		IdleN.setBounds(0,0,150,34);
		IdleN.setForeground(new Color(240,240,240));
		IdleN.setFont(new Font("Tahoma", Font.BOLD, 13));
		Idle.add(IdleN);
		JLabel MarchingN = new JLabel("Marching Armies");
		MarchingN.setBounds(0,0,150,34);
		MarchingN.setForeground(new Color(240,240,240));
		MarchingN.setFont(new Font("Tahoma", Font.BOLD, 13));
		Marching.add(MarchingN);
		JLabel BesiegingN = new JLabel("Besieging Armies");
		BesiegingN.setBounds(0,0,150,34);
		BesiegingN.setForeground(new Color(240,240,240));
		BesiegingN.setFont(new Font("Tahoma", Font.BOLD, 13));
		Besieging.add(BesiegingN);


		this.getContentPane().add(Idle);
		this.getContentPane().add(Marching);
		this.getContentPane().add(Besieging);
	}

}
