import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
/**
 * 
 * @author KashmiraPuranik (216814360)
 *
 */

public class CoffeeShoppingViewerFrame extends JFrame
{

	JCheckBox check[];

	JLabel cb;

	JRadioButton radio[], radio1[];

	static String[] adons = "Caramel, Mocha, Double Chocolate, Strawberry, Whipped Cream, Chocolate Syrup, Berries".split(",");

	static String[] cupSizes = "Tall: $3.60, Grande: $4.06, Venti: $5.15".split(",");

	static String[] Coffeetypes = "Iced, Hot, Frappe".split(",");

	static final String CLEAR = "clear";

	static final String TOTAL = "total";

	final ButtonGroup groupSizes = new ButtonGroup();

	final ButtonGroup groupSizes1 = new ButtonGroup();

	JPanel sizes, toppings, types;

	JTextArea textArea;

	static final private int[] PRICE_SIZES =

		{ 650, 850, 1000 }; // pennies

	private static final String EXIT = null;

	/** This frame contains a checkbox, buttons that used to place the order.*/

	public CoffeeShoppingViewerFrame()

	{

		super("Coffee Shop");

		radio = new JRadioButton[cupSizes.length];

		radio1 = new JRadioButton[Coffeetypes.length];

		check = new JCheckBox[adons.length];

		JPanel options = makeOptionsPanel();

		add(options);

		setSize(800, 450);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLocationRelativeTo(null);

		setVisible(true);

	}

	public static void main(String[] args)

	{

		new CoffeeShoppingViewerFrame();

	}
	/** Creates the panel and radio boxes on the frame.*/

	private JPanel makeOptionsPanel()

	{

		sizes = addRadioBoxes(cupSizes, "Cup Size", radio, groupSizes);

		toppings = addCkBoxes(adons, check);

		types = addRadioBoxes(Coffeetypes, "Coffee Type", radio1, groupSizes1);

		JPanel size_type = new JPanel();

		size_type.setLayout(new GridLayout(1, 2, 15, 10));

		size_type.add(sizes);

		size_type.add(types);
		/** Create clear button, exit and total for the frame.*/

		JPanel buttonControls = new JPanel();

		buttonControls.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 10));

		JButton clear = new JButton("Clear");

		clear.setActionCommand(CLEAR);

		clear.addActionListener(new MyButtonListener());

		JButton exit = new JButton("Exit");

		exit.setActionCommand(EXIT);

		exit.addActionListener(new MyButtonListener());

		JButton total = new JButton("Confirm Order");

		total.setActionCommand(TOTAL);

		total.addActionListener(new MyButtonListener());

		buttonControls.add(clear);

		buttonControls.add(total);

		JPanel si_ty_cont = new JPanel();

		si_ty_cont.setLayout(new GridLayout(2, 1, 15, 15));

		si_ty_cont.add(size_type);

		si_ty_cont.add(buttonControls);

		JPanel p = new JPanel();

		p.setLayout(new GridLayout(1, 2, 15, 15));

		p.add(toppings);

		p.add(si_ty_cont);

		p.setAlignmentX(Component.LEFT_ALIGNMENT);

		JPanel completePanel = new JPanel();

		cb.setAlignmentX(Component.CENTER_ALIGNMENT);

		completePanel.setLayout(new BoxLayout(completePanel, BoxLayout.PAGE_AXIS));

		JLabel jlb = new JLabel("Your order: ", SwingConstants.LEFT);

		textArea = new JTextArea();

		textArea.setRows(5);

		JScrollPane sp = new JScrollPane(textArea);

		sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		cb = new JLabel("Welcome to Coffee Bucks !");

		cb.setFont(new Font("Courier New", Font.BOLD, 20));

		cb.setForeground(Color.BLUE);

		completePanel.add(cb);

		completePanel.add(Box.createVerticalGlue());

		completePanel.add(p);

		completePanel.add(Box.createVerticalGlue());

		completePanel.add(jlb);

		completePanel.add(sp);

		return completePanel;

	}
	/** Calculate the total price of the placed order.*/

	private void calcPrice()
	{

		int total = 0, subTotal = 0;
		int priceIndex = 0, priceIndex1 = 0;

		for (int i = 0; i < radio.length; i++)
		{

			if (radio[i].isSelected())
			{
				priceIndex = i;

				break;
			}

		}

		for (int i = 0; i < radio1.length; i++)
		{

			if (radio1[i].isSelected())
			{

				priceIndex1 = i;

				break;

			}

		}

		textArea.setText("");

		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("Coffee type: %s%n", Coffeetypes[priceIndex1]));
		sb.append(String.format("Coffee size: %s%n", cupSizes[priceIndex]));
		sb.append("Ad-ons : ");

		total += PRICE_SIZES[priceIndex];

		String accumTops = "";

		for (int i = 0; i < check.length; i++)
		{

			if (check[i].isSelected())
			{
				accumTops += "" + i + ",";
				subTotal += 150;

			}

		}

		if (!accumTops.isEmpty())
		{

			String[] orderedTops = accumTops.split(",");

			for (int i = 0; i < orderedTops.length; i++)
			{
				sb.append(String.format("%s, ",
						adons[Integer.parseInt(orderedTops[i])]));
			}
		}
		else
		{
			sb.append(String.format("%s%n", "<no extras"));
		}

		total += subTotal;

		sb.append(String.format("\nAmount Due: $ %d.%2d%n", total / 100, total % 100));

		textArea.setText(sb.toString());

	}

	private JPanel addRadioBoxes(String[] opts, String title, JRadioButton jrb[],ButtonGroup bg)
	{

		int rows = opts.length;
		JPanel p = new JPanel();
		addBorder(p);

		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		cb = new JLabel(title);
		cb.setFont(new Font("Courier New", Font.ROMAN_BASELINE, 18));
		cb.setForeground(Color.BLUE);
		p.add(cb);
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(rows, 1));

		for (int i = 0; i < rows; i++)
		{

			jrb[i] = new JRadioButton(opts[i]);
			p1.add(jrb[i]);
			bg.add(jrb[i]);
		}
		p.add(p1);
		return p;
	}

	private void addBorder(JPanel p)
	{
		p.setBorder(BorderFactory.createLineBorder(Color.BLUE));
	}

	private JPanel addCkBoxes(String[] opts, JCheckBox checkbox[])
	{

		int rows = opts.length;

		JPanel p = new JPanel();
		addBorder(p);
		p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
		cb = new JLabel("Each ad-on at $1.50", SwingConstants.LEFT);
		cb.setFont(new Font("Courier New", Font.ROMAN_BASELINE, 18));
		cb.setForeground(Color.BLUE);
		p.add(cb);
		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(rows, 1));

		for (int i = 0; i < rows; i++)
		{
			checkbox[i] = new JCheckBox(opts[i]);
			p1.add(checkbox[i]);
		}
		p.add(p1);
		return p;
	}

	private void clearButtons()
	{
		groupSizes.clearSelection();
		groupSizes1.clearSelection();

		for (int i = 0; i < check.length; i++)
		{
			check[i].setSelected(false);
		}
		textArea.setText("");
	}

	class MyButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if (e.getActionCommand().equals(CLEAR))
			{
				clearButtons();
			}
			else if (e.getActionCommand().equals(TOTAL))
			{
				calcPrice();
			}

		}

	}

}

