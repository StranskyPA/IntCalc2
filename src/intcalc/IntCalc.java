package intcalc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.hendrix.grambler.ParseException;

@SuppressWarnings("serial")
public class IntCalc extends JFrame {
	private JTextArea input, output;
	private JButton run;
	private Evaluator eval = new Evaluator();
	
	public IntCalc() {
		setSize(600, 400);
		setTitle("Integer Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout());
		run = new JButton("Run");
		run.addActionListener(new Eval());
		top.add(run);
		
		JPanel middle = new JPanel();
		middle.setLayout(new GridLayout(1, 2));
		input = new JTextArea(40, 40);
		output = new JTextArea(40, 40);
		input.setBorder(BorderFactory.createTitledBorder("Input"));
		output.setBorder(BorderFactory.createTitledBorder("Output"));
		middle.add(new JScrollPane(input));
		middle.add(new JScrollPane(output));
		
		setLayout(new BorderLayout());
		add(top, BorderLayout.NORTH);
		add(middle, BorderLayout.CENTER);
	}
	
	private class Eval implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				output.setText(eval.eval(input.getText()));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		new IntCalc().setVisible(true);
	}
}
