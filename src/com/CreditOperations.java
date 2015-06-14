package com;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.function.Function;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.List;

import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JScrollBar;

import java.awt.TextArea;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

public class CreditOperations extends JFrame {

	private JPanel contentPane;
	private BankNet banks;
	private final static String[] bankNames = { "DZI", "RF", "DSK", "AL" };
	private final static int countBanks = bankNames.length;
	private final static int safeLevel = 300;
	private final static Function<Integer, String> mapToName = x -> bankNames[x];
	private JTextField txtFromBank;
	private JTextField txtToBank;
	private JLabel lblFrom;
	private JLabel lblTo;
	private JTextField txtCredit;
	private JLabel lblNewLabel;
	private TextArea txtShow;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditOperations frame = new CreditOperations();
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
	public CreditOperations() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 366);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtFromBank = new JTextField();
		txtFromBank.setBounds(62, 13, 116, 22);
		contentPane.add(txtFromBank);
		txtFromBank.setColumns(10);

		txtToBank = new JTextField();
		txtToBank.setBounds(62, 93, 116, 22);
		contentPane.add(txtToBank);
		txtToBank.setColumns(10);

		lblFrom = new JLabel("From:");
		lblFrom.setBounds(12, 16, 56, 16);
		contentPane.add(lblFrom);

		lblTo = new JLabel("To");
		lblTo.setBounds(12, 96, 56, 16);
		contentPane.add(lblTo);

		JButton btnAddCredit = new JButton("Add Credit");
		btnAddCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int a = -1;
					int b = -1;
					for (int i = 0; i < countBanks; i++) {
						if (txtFromBank.getText().equalsIgnoreCase(bankNames[i]))
							a = i;
						if (txtToBank.getText().equalsIgnoreCase(bankNames[i]))
							b = i;
					}
					banks.setCredit(a, b, Double.parseDouble(txtCredit.getText()));
					txtShow.setText("Credit added");
				} catch (Exception e) {
					txtShow.setText("Bad input");
				}
				
				

			}
		});
		btnAddCredit.setBounds(273, 36, 159, 25);
		contentPane.add(btnAddCredit);

		JButton btnRemoveCredit = new JButton("Remove Credit");
		btnRemoveCredit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int a = -1;
					int b = -1;
					for (int i = 0; i < countBanks; i++) {
						if (txtFromBank.getText().equalsIgnoreCase(bankNames[i]))
							a = i;
						if (txtToBank.getText().equalsIgnoreCase(bankNames[i]))
							b = i;
					}
					banks.setCredit(a, b, 0);
					txtShow.setText("Credit removed");
				} catch (Exception e) {
					txtShow.setText("Bad input");
				}
				
			}
		});
		btnRemoveCredit.setBounds(273, 74, 159, 25);
		contentPane.add(btnRemoveCredit);

		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtShow.setText(banks.toString());			}
		});
		btnShow.setBounds(273, 112, 159, 25);
		contentPane.add(btnShow);

		JButton btnShowUnsafedBanks = new JButton("Show unsafed banks");
		btnShowUnsafedBanks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtShow.setText(banks.findUnsafebanks().toString());
			}
		});
		btnShowUnsafedBanks.setBounds(273, 150, 159, 25);
		contentPane.add(btnShowUnsafedBanks);

		txtShow = new TextArea();
		txtShow.setBounds(12, 125, 249, 183);
		contentPane.add(txtShow);

		txtCredit = new JTextField();
		txtCredit.setBounds(62, 48, 116, 22);
		contentPane.add(txtCredit);
		txtCredit.setColumns(10);

		lblNewLabel = new JLabel("Credit");
		lblNewLabel.setBounds(12, 51, 56, 16);
		contentPane.add(lblNewLabel);
		banks = new BankNet(countBanks, safeLevel, mapToName);
	}
}
