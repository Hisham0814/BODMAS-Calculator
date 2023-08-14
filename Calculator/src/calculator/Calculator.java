package calculator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Button;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;

@SuppressWarnings("serial")
public class Calculator extends JFrame implements ActionListener{

	private JPanel contentPane;
	JTextField display1;
	JTextField displayAns;
	private JTextField textField;
	private JButton b0;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;
	private JButton b6;
	private JButton b7;
	private JButton b8;
	private JButton b9;
	JButton decimal;
	JButton equals;
	JButton add;
	JButton sub;
	JButton mul;
	JButton div;
	JButton percent;
	JButton square;
	JButton power;
	JButton clear;
	private JButton leftBracket;
	private JButton rightBracket;
	private JButton sqrt;
	private JButton factorial;
	private JButton back;
	private JButton btnMr;
	private JButton m1;
	private JButton m2;
	private JButton answer;
	private JButton btnMc;
	JButton prev;
	JButton next;
	
	Calculate calculate;
	
	ArrayList<String> exp = new ArrayList<String>();
	ArrayList<String> res = new ArrayList<String>();
	Iterator<String> iter = exp.iterator();
	String memory = "";
	String ans = "";
	float memory2;
	int i;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calculator() {
		setTitle("Calculator");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 280, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		display1 = new JTextField();
		display1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode() == KeyEvent.VK_ENTER){
		                // something like...
		               //mTextField.getText();
		               // or...
		               equals.doClick();
		            }
			}
		});
		display1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		display1.setBounds(10, 26, 245, 35);
		display1.setColumns(10);
		display1.getCaret();
		contentPane.add(display1);
		
		displayAns = new JTextField();
		displayAns.setHorizontalAlignment(SwingConstants.RIGHT);
		displayAns.setBackground(Color.WHITE);
		displayAns.setFont(new Font("Tahoma", Font.PLAIN, 17));
		displayAns.addActionListener(this);
		displayAns.setEditable(false);
		displayAns.setBounds(10, 61, 245, 35);
		displayAns.setColumns(10);
		contentPane.add(displayAns);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setEditable(false);
		textField.setBounds(10, 11, 245, 15);
		contentPane.add(textField);
		textField.setColumns(10);
		
		clear = new JButton("C");
		clear.setBackground(Color.LIGHT_GRAY);
		clear.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		clear.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		clear.addActionListener(this);
		clear.setBounds(10, 297, 45, 35);
		contentPane.add(clear);
		
		b0 = new JButton("0");
		b0.setBackground(new Color(250, 250, 210));
		b0.addActionListener(this);
		b0.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		b0.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		b0.setBounds(60, 297, 45, 35);
		contentPane.add(b0);
		
		b1 = new JButton("1");
		b1.setBackground(new Color(250, 250, 210));
		b1.addActionListener(this);
		b1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		b1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		b1.setBounds(10, 257, 45, 35);
		contentPane.add(b1);
		
		b2 = new JButton("2");
		b2.setBackground(new Color(250, 250, 210));
		b2.addActionListener(this);
		b2.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		b2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		b2.setBounds(60, 257, 45, 35);
		contentPane.add(b2);
		
		b3 = new JButton("3");
		b3.setBackground(new Color(250, 250, 210));
		b3.addActionListener(this);
		b3.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		b3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		b3.setBounds(110, 257, 45, 35);
		contentPane.add(b3);
		
		b6 = new JButton("6");
		b6.setBackground(new Color(250, 250, 210));
		b6.addActionListener(this);
		b6.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		b6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		b6.setBounds(110, 217, 45, 35);
		contentPane.add(b6);
		
		b5 = new JButton("5");
		b5.setBackground(new Color(250, 250, 210));
		b5.addActionListener(this);
		b5.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		b5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		b5.setBounds(60, 217, 45, 35);
		contentPane.add(b5);
		
		b4 = new JButton("4");
		b4.setBackground(new Color(250, 250, 210));
		b4.addActionListener(this);
		b4.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		b4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		b4.setBounds(10, 217, 45, 35);
		contentPane.add(b4);
		
		b7 = new JButton("7");
		b7.setBackground(new Color(250, 250, 210));
		b7.addActionListener(this);
		b7.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		b7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		b7.setBounds(10, 177, 45, 35);
		contentPane.add(b7);
		
		b8 = new JButton("8");
		b8.setBackground(new Color(250, 250, 210));
		b8.addActionListener(this);
		b8.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		b8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		b8.setBounds(60, 177, 45, 35);
		contentPane.add(b8);
		
		b9 = new JButton("9");
		b9.setBackground(new Color(250, 250, 210));
		b9.addActionListener(this);
		b9.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		b9.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		b9.setBounds(110, 177, 45, 35);
		contentPane.add(b9);
		
		decimal = new JButton(".");
		decimal.addActionListener(this);
		decimal.setBackground(new Color(250, 250, 210));
		decimal.setFont(new Font("Times New Roman", Font.BOLD, 18));
		decimal.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		decimal.setBounds(110, 297, 45, 35);
		contentPane.add(decimal);
		
		equals = new JButton("=");
		equals.setBackground(new Color(211, 211, 211));
		equals.setFont(new Font("Times New Roman", Font.BOLD, 18));
		equals.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		equals.addActionListener(this);
		equals.setBounds(160, 297, 45, 35);
		contentPane.add(equals);
		
		add = new JButton("+");
		add.setBackground(new Color(211, 211, 211));
		add.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add.addActionListener(this);
		add.setFont(new Font("Times New Roman", Font.BOLD, 18));
		add.setBounds(160, 257, 45, 35);
		contentPane.add(add);
		
		sub = new JButton("-");
		sub.setBackground(new Color(211, 211, 211));
		sub.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sub.addActionListener(this);
		sub.setFont(new Font("Tahoma", Font.BOLD, 18));
		sub.setBounds(160, 217, 45, 35);
		contentPane.add(sub);
		
		mul = new JButton("x");
		mul.setBackground(new Color(211, 211, 211));
		mul.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mul.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		mul.addActionListener(this);
		mul.setBounds(160, 177, 45, 35);
		contentPane.add(mul);
		
		div = new JButton("/");
		div.setBackground(new Color(211, 211, 211));
		div.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		div.setFont(new Font("Times New Roman", Font.BOLD, 18));
		div.addActionListener(this);
		div.setBounds(160, 137, 45, 35);
		contentPane.add(div);
		
		percent = new JButton("%");
		percent.setBackground(new Color(211, 211, 211));
		percent.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		percent.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		percent.setBounds(110, 137, 45, 35);
		percent.addActionListener(this);
		contentPane.add(percent);
		
		square = new JButton("^2");
		square.addActionListener(this);
		square.setBackground(Color.LIGHT_GRAY);
		square.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		square.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		square.setBounds(210, 218, 45, 35);
		contentPane.add(square);
		
		power = new JButton("^");
		power.setBackground(Color.LIGHT_GRAY);
		power.addActionListener(this);
		power.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		power.setFont(new Font("Times New Roman", Font.BOLD, 18));
		power.setBounds(210, 257, 45, 35);
		contentPane.add(power);

		leftBracket = new JButton("(");
		leftBracket.setBackground(new Color(211, 211, 211));
		leftBracket.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		leftBracket.addActionListener(this);
		leftBracket.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		leftBracket.setBounds(10, 137, 45, 35);
		contentPane.add(leftBracket);
		
		rightBracket = new JButton(")");
		rightBracket.setBackground(new Color(211, 211, 211));
		rightBracket.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		rightBracket.addActionListener(this);
		rightBracket.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		rightBracket.setBounds(60, 137, 45, 35);
		contentPane.add(rightBracket);
		
		sqrt = new JButton("sqrt");
		sqrt.setBackground(Color.LIGHT_GRAY);
		sqrt.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sqrt.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		sqrt.setBounds(210, 177, 45, 35);
		sqrt.addActionListener(this);
		contentPane.add(sqrt);
		
		factorial = new JButton("!");
		factorial.setBackground(Color.LIGHT_GRAY);
		factorial.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		factorial.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		factorial.setBounds(210, 137, 45, 35);
		factorial.addActionListener(this);
		contentPane.add(factorial);
		
		back = new JButton("del");
		back.setBackground(Color.LIGHT_GRAY);
		back.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		back.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		back.addActionListener(this);
		back.setBounds(210, 297, 45, 35);
		contentPane.add(back);

		btnMr = new JButton("MR");
		btnMr.setBounds(10, 107, 45, 23);
		btnMr.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnMr.addActionListener(this);
		contentPane.add(btnMr);
		
		m1 = new JButton("M+");
		m1.setBounds(110, 107, 45, 23);
		m1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		m1.addActionListener(this);
		contentPane.add(m1);
		
		m2 = new JButton("M-");
		m2.setBounds(160, 107, 45, 23);
		m2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		m2.addActionListener(this);
		contentPane.add(m2);
		
		answer = new JButton("ANS");
		answer.setBounds(210, 107, 45, 23);
		answer.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		answer.addActionListener(this);
		contentPane.add(answer);
		
		btnMc = new JButton("MC");
		btnMc.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		btnMc.setBounds(60, 107, 45, 23);
		btnMc.addActionListener(this);
		contentPane.add(btnMc);

	/*	prev = new JButton("<");
		prev.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		prev.setBounds(10, 107, 45, 23);
		prev.addActionListener(this);
		contentPane.add(prev);
		
		next = new JButton(">");
		next.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		next.setBounds(60, 107, 45, 23);
		next.addActionListener(this);
		contentPane.add(next);	*/
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		calculate = new Calculate();
		int len;
		String last = null;
		String symbols = "+-*/(";
		//String mrc = "";
		ArrayList list = new ArrayList();
		
		
		if(e.getSource()==b0) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "0");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
			//display1.setText(display1.getText()+"0");
		}
		else if(e.getSource()==b1) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "1");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==b2) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "2");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==b3) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "3");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==b4) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "4");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==b5) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "5");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==b6) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "6");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==b7) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "7");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==b8) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "8");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==b9) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "9");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==decimal) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, ".");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		
		else if(e.getSource()==add) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "+");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==sub) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "-");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==mul) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "*");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==div) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "/");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==power) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "^");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==square) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "^2");
			display1.setText(input2.toString());
			cursor = cursor+2;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==sqrt) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "^0.5");
			display1.setText(input2.toString());
			cursor = cursor+4;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==factorial) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "!");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==percent) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "%");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		
		else if(e.getSource()==leftBracket) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, "(");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		else if(e.getSource()==rightBracket) {
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			int cursor = display1.getCaretPosition();
			input2.insert(cursor, ")");
			display1.setText(input2.toString());
			cursor++;
			display1.setCaretPosition(cursor);
		}
		
		else if(e.getSource()==equals) {
			
			String input = display1.getText();
			StringBuilder input2 = new StringBuilder(input);
			len = display1.getText().length();
			if(!display1.getText().equals(""))
				last = Character.toString(display1.getText().charAt(len-1));
			if(display1.getText().equals("")) {
				displayAns.setHorizontalAlignment(SwingConstants.CENTER);
				displayAns.setText("ENTER EXPRESSION");
			}
			else if( (symbols.contains(last) && !last.equals("!")) ) {
				displayAns.setHorizontalAlignment(SwingConstants.CENTER);
				displayAns.setText("ERROR: INVALID EXPRESSION");
			}
			else if(input.equals("."))
				displayAns.setText("0");
			else {
				displayAns.setHorizontalAlignment(SwingConstants.RIGHT);
				String value = calculate.result(display1.getText());
				System.out.println("value: "+value);
				Pattern p = Pattern.compile("^[ A-Za-z]+$");
				Matcher m = p.matcher(value);
				boolean b = m.matches();
				if(b || value.contains(":")) {
					displayAns.setHorizontalAlignment(SwingConstants.CENTER);
				}
				displayAns.setText(value);
				if(!b && !value.contains(":")) {
					ans = value;
					if(exp.size()==4) {
						exp.remove(0);
						res.remove(0);
					}
					exp.add(input);
					res.add(value);
					System.out.println(exp);
					System.out.println(res);
				}
			}
		}
		
		else if(e.getSource()==clear) {
			display1.setText("");
			displayAns.setText("");
			displayAns.setHorizontalAlignment(SwingConstants.RIGHT);
		}
		else if(e.getSource()==back) {
			String str = display1.getText();
			StringBuilder input = new StringBuilder(str);
			int cursor = display1.getCaretPosition();
			if(!str.equals("") && cursor>0) {
				input.deleteCharAt(cursor-1);
				//str = str.substring(0, str.length() - 1);
				display1.setText(input.toString());
				cursor--;
				display1.setCaretPosition(cursor);
			}
		}	
		
		else if(e.getSource()==btnMr) {
			String str = display1.getText();
			String val = memory;
			Float value = memory2;
			Integer value2 = value.intValue();
			if(value2-value==0)
				val = value2.toString();
			if(val.substring(0, 1).equals("-"))
				val = "("+val+")";
			display1.setText(str+val);
			System.out.println("memory: "+memory);
		}
		else if(e.getSource()==btnMc) {
			memory = "";
			memory2 = 0;
			textField.setText("");
		}
		else if(e.getSource()==m1) {
			if(!displayAns.getText().equals("")) {
				String val = displayAns.getText();
				//if(val.substring(0, 1).equals("-"))
					///val = "("+val+")";
				Pattern p = Pattern.compile("^[ A-Za-z]+$");
				Matcher m = p.matcher(val);
				boolean b = m.matches();
				if(!b) {
					memory2 = memory2+Float.parseFloat(val);
					memory = String.valueOf(memory2);
					//textField.setText("M");
				}
				if(!memory.equals("0.0") && !memory.equals(""))
					textField.setText("M: "+memory);
				else if(memory.equals("0.0") || memory.equals(""))
					textField.setText("");
				System.out.println("toMemory: "+memory);
			}
		}
		else if(e.getSource()==m2) {
			String val = displayAns.getText();
			Pattern p = Pattern.compile("^[ A-Za-z]+$");
			Matcher m = p.matcher(val);
			boolean b = m.matches();
			if(!b && !displayAns.getText().equals("")) {
				memory2 = memory2-Float.parseFloat(val);
				memory = String.valueOf(memory2);
				//textField.setText("M");
			}
			if(!memory.equals("0.0") && !memory.equals(""))
				textField.setText("M: "+memory);
			else if(memory.equals("0.0") || memory.equals(""))
				textField.setText("");
			System.out.println(memory);
		}
		else if(e.getSource()==answer) {
			String str = display1.getText();
			display1.setText(str+ans);
		}
		
	/*	else if(e.getSource()==prev) {
			
			Pattern p = Pattern.compile("^[ A-Za-z]+$");
			Matcher m = p.matcher(displayAns.getText());
			boolean b = m.matches();
			if(!b && !displayAns.getText().contains(":")) {
			if(displayAns.getText().equals("")) 
				i=exp.size()-1;
			
			display1.setText((String) exp.get(i));
			displayAns.setText((String) res.get(i));
			if(i>0)
				i--;
			}
		}	
		else if(e.getSource()==next) {
			if(i<exp.size()-1) {
			display1.setText((String) exp.get(i+1));
			displayAns.setText((String) res.get(i+1));
			}
			if(i<exp.size()-1)
				i++;
		}	*/
	}
}
