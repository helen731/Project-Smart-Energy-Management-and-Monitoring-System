import java.awt.Color;
import java.util.*;
import java.io.*;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.util.Calendar;
import javax.swing.JTextArea;

/**
 * The GUI of the software
 * @author wangce
 * @author yumiao
 *
 */
@SuppressWarnings("serial")
public class Gui extends JFrame
	{
	private static UserAccount temp = new UserAccount();
	Company com = new Company();
	public static MultiValueMap<String, String> stringMultiValueMap = new LinkedMultiValueMap<>();
	JButton JBlight;
	/**
	 * @param args null
	 * @throws Exception Exception of IOread
	 */
	public static void main(String[] args)throws Exception{
		Gui myGUI0 = new Gui();
		myGUI0.readFromFile("UserData.txt");
		myGUI0.init();
		Thread t = new Thread(new WriteTime());
		t.start();
	}
	
	
	JFrame JFmain;
	JPanel JPmain;
	
	/**
	 * The initial of GUI
	 */
	public void init(){
		JFmain = new JFrame();
		JFmain.setResizable(false);
		JPmain = new JPanel();
		JPmain.setLayout(null);
		JPanel JPiden = new JPanel();
		JPiden.setBounds(0, 469, 784, 234);
		JPmain.add(JPiden);
		JPiden.setLayout(null);
		JPiden.setLayout(null);
		
		JButton JBAdm= new JButton("Administrators");
		JBAdm.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBAdm.setBounds(58, 89, 240, 46);
		JPiden.add(JBAdm);
		
		JButton JBuser = new JButton("User");
		JBuser.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBuser.setBounds(469, 89, 232, 46);
		JPiden.add(JBuser);
		
		JBuser.addActionListener(new userListen());
		JBAdm.addActionListener(new admListen());
		JBAdm.setVisible(true);
		
		JFmain.setTitle("Welcome to the ENERGY SYSTEM!");
		JFmain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JFmain.getContentPane().add(JPmain);
		
		JPanel JTips = new JPanel();
		JTips.setBounds(0, 0, 784, 468);
		JPmain.add(JTips);
		JTips.setLayout(null);
		JLabel JLname = new JLabel("ENERGY SYSTEM");
		JLname.setFont(new Font("Algerian", Font.PLAIN, 55));
		JLname.setBounds(129, 257, 523, 67);
		JTips.add(JLname);
		JLname.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel JLtips = new JLabel("Please chose your identity:");
		JLtips.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		JLtips.setBounds(0, 355, 784, 100);
		JTips.add(JLtips);
		JLtips.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel JImage = new JPanel();
		JImage.setBounds(0, 0, 784, 243);
		JPmain.add(JImage);
		JFmain.setSize(800,800);
		JFmain.setVisible(true);
	}
	
	
	
	
	
	JPanel JPuser;
	JTextField JTuserID;
	JButton JBexit;
	/**
	 * The interface of user
	 */
	public void inuser(){
		
		
		
		JFmain.getContentPane().remove(JPmain);
		JFmain.repaint();
		JPuser = new JPanel();
		JLabel JLname = new JLabel("ENERGY SYSTEM");
		JLname.setFont(new Font("Algerian", Font.PLAIN, 55));
		JLname.setHorizontalAlignment(SwingConstants.CENTER);
		JLname.setBounds(129, 257, 523, 67);
		JLabel JLtips = new JLabel("User ID:");
		JLtips.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
		JLtips.setHorizontalAlignment(SwingConstants.LEFT);
		JLtips.setBounds(65, 400, 156, 42);
		JTuserID = new JTextField(20);
		JTuserID.setBounds(251, 400, 450, 42);
		JPanel JPchoice = new JPanel();
		JPchoice.setBounds(0, 469, 800, 231);
		
		JPuser.setLayout(null);
		
		JPuser.add(JLname);
		JPuser.add(JLtips);
		JPuser.add(JTuserID);
		JPuser.add(JPchoice);
		
		JButton JBlogin = new JButton("Login");
		JBlogin.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBlogin.setBounds(58, 89, 232, 46);
		JBlogin.addActionListener(new userlogListen());
		JBexit = new JButton("Exit");//zhi jie tui chu an niu quan ju
		JBexit.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBexit.setBounds(469, 89, 232, 46);
		JBexit.addActionListener(new userexitListen());
		JPchoice.setLayout(null);
		JPchoice.add(JBlogin);
		JPchoice.add(JBexit);
		
		JFmain.getContentPane().add(JPuser);
		JFmain.setVisible(true);
	}
	
	
	JPanel JPuserIDfail;
	/**
	 * The interface show that user's ID error
	 */
	public void userIDfail(){
		JFmain.getContentPane().remove(JPuser);
		JFmain.repaint();
		JPuserIDfail = new JPanel(new GridLayout(2,1));
		JLabel JLuserIDfail = new JLabel("Wrong!");
		JPanel JPuserIDfailback = new JPanel();
		JPuserIDfail.add(JLuserIDfail);
		JPuserIDfail.add(JPuserIDfailback);
		
		JButton JBuserIDfailback = new JButton("Back");
		JBuserIDfailback.addActionListener(new userIDfailbackListen());
		JPuserIDfailback.add(JBuserIDfailback);
		
		JFmain.getContentPane().add(JPuserIDfail);
		JFmain.setVisible(true);
	}
	
	
	
	JPanel JPmonitor;
	
	JButton JBeleprice;
	JButton JBelequantity;
	JButton JBelemoney;
	int elemon=1;
	JButton JBgasprice;
	JButton JBgasquantity;
	JButton JBgasmoney;
	int gasmon=2;
	JButton JBtolmoney;
	int sum;
	/**
	 * The interface of user monitor
	 * @throws Exception Exception of IOread
	 */
	public void userMonitor() throws Exception{
		
		JFmain.getContentPane().remove(JPuser);
		JFmain.repaint();
		JPmonitor = new JPanel(new GridLayout(4,1));
		JPanel JPele = new JPanel();
		JPanel JPgas = new JPanel();
		JPanel JPtotal = new JPanel();
		JPanel JPcho = new JPanel();
		JPmonitor.add(JPele);
		JPmonitor.add(JPgas);
		JPmonitor.add(JPtotal);
		JPmonitor.add(JPcho);
		
		
		
		JLabel JLele = new JLabel("Electricity:");
		JLele.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		JLele.setHorizontalAlignment(SwingConstants.CENTER);
		JLele.setBounds(1, 0, 133, 200);
		JBeleprice = new JButton(""+temp.getEleUnitPrice());
		JBeleprice.setBounds(144, 77, 133, 46);
		JLabel JLmul1 = new JLabel("x");
		JLmul1.setHorizontalAlignment(SwingConstants.CENTER);
		JLmul1.setBounds(277, 0, 67, 200);
		JBelequantity = new JButton(String.format("%4d",temp.getEleMeter()).replace(" ","0"));
		JBelequantity.setBounds(344, 78, 133, 46);
		JLabel JLequ1 = new JLabel("=");
		JLequ1.setHorizontalAlignment(SwingConstants.CENTER);
		JLequ1.setBounds(477, 1, 67, 200);
		JBelemoney = new JButton(String.format("%.2f",temp.getEleMeter()*temp.getEleUnitPrice()));
		JBelemoney.setBounds(544, 78, 133, 46);
		JPele.setLayout(null);
		//tian jia yi ge cheng fa ji suan
		JPele.add(JLele);
		JPele.add(JBeleprice);
		JPele.add(JLmul1);
		JPele.add(JBelequantity);
		JPele.add(JLequ1);
		JPele.add(JBelemoney);
		
		JLabel JLgas = new JLabel("Gas:");
		JLgas.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		JLgas.setHorizontalAlignment(SwingConstants.CENTER);
		JLgas.setBounds(1, 0, 133, 200);
		JBgasprice = new JButton(""+temp.getGasUnitPrice());
		JBgasprice.setBounds(144, 77, 133, 46);
		JLabel JLmul2 = new JLabel("x");
		JLmul2.setHorizontalAlignment(SwingConstants.CENTER);
		JLmul2.setBounds(277, 0, 67, 200);
		JBgasquantity = new JButton(String.format("%4d",temp.getGasMeter()).replace(" ","0"));
		JBgasquantity.setBounds(344, 78, 133, 46);
		JLabel JLequ2 = new JLabel("=");
		JLequ2.setHorizontalAlignment(SwingConstants.CENTER);
		JLequ2.setBounds(477, 1, 67, 200);
		JBgasmoney = new JButton(String.format("%.2f",temp.getGasMeter()*temp.getGasUnitPrice()));
		JBgasmoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		JBgasmoney.setBounds(544, 78, 133, 46);
		JPgas.setLayout(null);
		//tian jia yi ge cheng fa ji suan
		JPgas.add(JLgas);
		JPgas.add(JBgasprice);
		JPgas.add(JLmul2);
		JPgas.add(JBgasquantity);
		JPgas.add(JLequ2);
		JPgas.add(JBgasmoney);
		
		JLabel JLtotal = new JLabel("Sum");
		JLtotal.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		JLtotal.setBounds(0, 0, 400, 200);
		JLtotal.setHorizontalAlignment(SwingConstants.CENTER);
		JBtolmoney = new JButton(String.format("%.2f",temp.getGasMeter()*temp.getGasUnitPrice()
				+temp.getEleMeter()*temp.getEleUnitPrice()));
		JBtolmoney.setBounds(414, 78, 275, 46);
		sum = elemon + gasmon;
		JPtotal.setLayout(null);
		JPtotal.add(JLtotal);
		JPtotal.add(JBtolmoney);
		
		JButton JBrecord = new JButton("Record");
		JBrecord.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBrecord.setBounds(56, 77, 200, 46);
		JBrecord.addActionListener(new inhistorychoiceListen());
		JButton JBbudget = new JButton("Budget");
		JBbudget.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBbudget.setBounds(309, 77, 200, 46);
		JBbudget.addActionListener(new inbudgetListen());
		JBexit = new JButton("Exit");//zhi jie tui chu an niu
		JBexit.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBexit.setBounds(553, 77, 200, 46);
		JBexit.addActionListener(new monitorexitListen());
		JPcho.setLayout(null);
		JPcho.add(JBrecord);
		JPcho.add(JBbudget);
		JPcho.add(JBexit);
		
		JFmain.getContentPane().add(JPmonitor);
		JFmain.setVisible(true);
	}
	
	JPanel JPhistorychoice;
	JComboBox<String> month;
	JComboBox<String> year;
	/**
	 * The interface of history choose data
	 */
	public void historychoice(){
		JFmain.getContentPane().remove(JPmonitor);
		JFmain.repaint();
		JPhistorychoice = new JPanel();
		JPhistorychoice.setLayout(null);
		
		JLabel JLtitle = new JLabel("The history record");
		JLtitle.setBounds(0, 13, 800, 123);
		JLtitle.setFont(new Font("Algerian", Font.PLAIN, 45));
		JLtitle.setHorizontalAlignment(SwingConstants.CENTER);
		JPhistorychoice.add(JLtitle);
		
		JLabel JLTips = new JLabel("Please choose the date:");
		JLTips.setBounds(250, 175, 300, 60);
		JLTips.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		JLTips.setHorizontalAlignment(SwingConstants.CENTER);
		JPhistorychoice.add(JLTips);
		
		JLabel JLyear = new JLabel("Year:");
		JLyear.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JLyear.setBounds(185, 289, 100, 40);
		JLyear.setHorizontalAlignment(SwingConstants.CENTER);
		JPhistorychoice.add(JLyear);
		
		JLabel JLmonth = new JLabel("Month:");
		JLmonth.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JLmonth.setBounds(450, 289, 100, 40);
		JLmonth.setHorizontalAlignment(SwingConstants.CENTER);
		JPhistorychoice.add(JLmonth);
		
		JFmain.getContentPane().add(JPhistorychoice);
		
		month = new JComboBox<String>();
		month.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		month.setBounds(550, 286, 100, 40);
		month.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		JPhistorychoice.add(month);
		
		year = new JComboBox<String>();
		year.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		year.setBounds(285, 286, 100, 40);
		year.setModel(new DefaultComboBoxModel<String>(new String[] {"2018"}));
		JPhistorychoice.add(year);
		JPanel JPhistorycho = new JPanel();
		JPhistorycho.setBounds(0, 528, 800, 204);
		JPhistorychoice.add(JPhistorycho);
		
		//date choice part
		JButton JBok = new JButton("OK");
		JBok.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBok.setBounds(58, 89, 240, 46);
		JBok.addActionListener(new inhistoryListen());
		JButton JBback = new JButton("Back");
		JBback.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBback.setBounds(469, 89, 240, 46);
		JBback.addActionListener(new historychoicebackListen());
		JPhistorycho.setLayout(null);
		JPhistorycho.add(JBok);
		JPhistorycho.add(JBback);
		JFmain.setVisible(true);
	}
	
	JPanel JPhistory;
	JLabel JLhistorytitle;
	String userDate;
	JTextArea JTuserRecord;
	/**
	 * The interface of history
	 */
	public void history(){
		String[] his = com.getHistory(userDate);
		int a = his.length;
		JFmain.getContentPane().remove(JPhistorychoice);
		JFmain.repaint();
		JPhistory = new JPanel(new GridLayout(4,1));
		JLabel JLhistorytitle = new JLabel("The history record");
		JLhistorytitle.setFont(new Font("Algerian", Font.PLAIN, 25));
		JLhistorytitle.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel JPback = new JPanel();
		JTuserRecord = new JTextArea();
		JPhistory.add(JLhistorytitle);
		JPhistory.add(JTuserRecord);
		JPhistory.add(JPback);
		if(a == 1){
			JTuserRecord.append("There is not historical record of this month" );}
		else{
			// call the method of userAccount to get the history record
			String userHis = temp.getUserHistory(userDate,JTuserID.getText());
			JTuserRecord.append("UserIdGasMeterElectricityMeterGasBillElectricityBill");
			JTuserRecord.append("\n");
			JTuserRecord.append(userHis);
		}
		JButton JBhistoryback = new JButton("Back");
		JBhistoryback.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JBhistoryback.setBounds(278, 75, 240, 46);
		JBhistoryback.addActionListener(new historybackListen());
		JPback.setLayout(null);
		JPback.add(JBhistoryback);
		
		JFmain.getContentPane().add(JPhistory);
		JFmain.setVisible(true);
	}
	
	JPanel JPbudget;
	JButton JBinital;
	JTextField JTnew;
	/**
	 * The interface shows budget
	 */
	public void budget(){
		JFmain.getContentPane().remove(JPmonitor);
		JFmain.repaint();
		JPbudget = new JPanel();
		JLabel JLbudgettip = new JLabel("Please set your budget");
		JLbudgettip.setHorizontalAlignment(SwingConstants.CENTER);
		JLbudgettip.setFont(new Font("Algerian", Font.PLAIN, 25));
		JLbudgettip.setBounds(0, 1, 800, 200);
		JPanel JPinital = new JPanel();
		JPinital.setBounds(0, 201, 800, 160);
		JPanel JPcheck = new JPanel();
		JPcheck.setBounds(0, 301, 800, 150);
		JPanel JPnew = new JPanel();
		JPnew.setBounds(0, 401, 800, 200);
		JPanel JPbudgetcho = new JPanel();
		JPbudgetcho.setBounds(0, 601, 800, 200);
		JPbudget.setLayout(null);
		JPbudget.add(JLbudgettip);
		JPbudget.add(JPinital);
		JPbudget.add(JPcheck);
		JPbudget.add(JPnew);
		JPbudget.add(JPbudgetcho);
		
		JLabel JLinital = new JLabel("Inital Budget: ");
		JLinital.setHorizontalAlignment(SwingConstants.CENTER);
		JLinital.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JLinital.setBounds(0, 0, 400, 200);
		JBinital = new JButton(""+temp.getBudget());//inital budget from...
		JBinital.setBounds(460, 70, 240, 46);
		JPinital.setLayout(null);
		JPinital.add(JLinital);
		JPinital.add(JBinital);
		
		JButton JBcheck = new JButton("Budget Alert");
		JBcheck.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JBcheck.setBounds(460, 70, 240, 46);
		JBlight = new JButton();
		JBlight.setBounds(190,90, 10, 20);
		//Graphic
		JBcheck.addActionListener(new budgetCheck());
		JPcheck.setLayout(null);
		JPcheck.add(JBcheck);
		JPcheck.add(JBlight);

		JLabel JLnew = new JLabel("New Budget: ");
		JLnew.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JLnew.setHorizontalAlignment(SwingConstants.CENTER);
		JLnew.setBounds(0, 0, 400, 200);
		JTnew = new JTextField(10);
		JTnew.setBounds(460, 70, 240, 46);
		JPnew.setLayout(null);
		JPnew.add(JLnew);
		JPnew.add(JTnew);
		
		JButton JBbudgetback = new JButton("Back");
		JBbudgetback.setBounds(63, 77, 240, 46);
		JBbudgetback.addActionListener(new budgetbackListen());
		JButton JBbudgetsave = new JButton("Save");
		JBbudgetsave.setBounds(478, 77, 240, 46);
		JBbudgetsave.addActionListener(new inbudgetfinListen());
		JPbudgetcho.setLayout(null);
		JPbudgetcho.add(JBbudgetback);
		JPbudgetcho.add(JBbudgetsave);
		
		JFmain.getContentPane().add(JPbudget);
		JFmain.setVisible(true);
	}
	
	JPanel JPbudgetfail;
	/**
	 * The interface show that user's entered budget is wrong
	 */
	public void budgetfail(){
		JFmain.getContentPane().remove(JPbudget);
		JFmain.repaint();
		JPbudgetfail = new JPanel(new GridLayout(2,1));
		JLabel JLbudgetfail = new JLabel("Wrong!");
		JPanel JPbudgetfailback = new JPanel();
		JPbudgetfail.add(JLbudgetfail);
		JPbudgetfail.add(JPbudgetfailback);
		
		JButton JBbudgetfailback = new JButton("Back");
		JBbudgetfailback.addActionListener(new budgetfailbackListen());
		JPbudgetfailback.add(JBbudgetfailback);
		
		JFmain.getContentPane().add(JPbudgetfail);
		JFmain.setVisible(true);
	}
	
	JPanel JPsuccess;
	/**
	 * The interface informs user that change budget complete
	 */
	public void budgetfin(){
		JFmain.getContentPane().remove(JPbudget);
		JFmain.repaint();
		JPsuccess = new JPanel();
		JLabel JLsuccess = new JLabel("Congratulations! New Budget has been set~");
		JLsuccess.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		JLsuccess.setHorizontalAlignment(SwingConstants.CENTER);
		JLsuccess.setBounds(0, 0, 800, 401);
		JPanel JPsuccessback = new JPanel();
		JPsuccessback.setBounds(0, 401, 800, 401);
		JPsuccess.setLayout(null);
		JPsuccess.add(JLsuccess);
		JPsuccess.add(JPsuccessback);
		
		JButton JBsuccessback = new JButton("Back");
		JBsuccessback.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JBsuccessback.setBounds(272, 170, 240, 46);
		JBsuccessback.addActionListener(new budgetfinbackListen());
		JPsuccessback.setLayout(null);
		JPsuccessback.add(JBsuccessback);
		
		JFmain.getContentPane().add(JPsuccess);
		JFmain.setVisible(true);
	}
	
	
	
	JPanel JPadm;
	JTextField JTadmID;
	/**
	 * The interface of administrator
	 */
	public void inadm(){
		JFmain.getContentPane().remove(JPmain);
		JFmain.repaint();
		JPadm = new JPanel();
		JPanel JPchoice = new JPanel();
		JPchoice.setBounds(0, 469, 800, 234);
		JPadm.setLayout(null);
		
		JPanel JTIps = new JPanel();
		JTIps.setBounds(0, 0, 10, 10);
		JPadm.add(JTIps);
		JTIps.setLayout(null);
		JPadm.add(JPchoice);
		
		JButton JBlogin = new JButton("Login");
		JBlogin.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBlogin.setBounds(58, 89, 240, 46);
		JBlogin.addActionListener(new admlogListen());
		JBexit = new JButton("Exit");
		JBexit.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBexit.setBounds(469, 89, 240, 46);
		JBexit.addActionListener(new admexitListen());
		JPchoice.setLayout(null);
		JPchoice.add(JBlogin);
		JPchoice.add(JBexit);
		
		JFmain.getContentPane().add(JPadm);
		JLabel JLtips = new JLabel("Administrator ID: ");
		JLtips.setBounds(65, 400, 227, 42);
		JPadm.add(JLtips);
		JLtips.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
		JLtips.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel JLadmtitle = new JLabel("energy system");
		JLadmtitle.setBounds(129, 257, 523, 67);
		JPadm.add(JLadmtitle);
		JLadmtitle.setFont(new Font("Algerian", Font.PLAIN, 55));
		JLadmtitle.setHorizontalAlignment(SwingConstants.CENTER);
		JTadmID = new JTextField(20);
		JTadmID.setBounds(317, 400, 384, 42);
		JPadm.add(JTadmID);
		JFmain.setVisible(true);
		
	}
	
	JPanel JPadmfail;
	/**
	 * The interface shows that admin's ID entered wrong
	 */
	public void admfail(){
		JFmain.getContentPane().remove(JPadm);
		JFmain.repaint();
		JPadmfail = new JPanel(new GridLayout(2,1));
		JLabel JLadmfail = new JLabel("Wrong!");
		JPanel JPadmfailback = new JPanel();
		JPadmfail.add(JLadmfail);
		JPadmfail.add(JPadmfailback);
		
		JButton JBadmfailback = new JButton("Back");
		JBadmfailback.addActionListener(new admfailbackListen());
		JPadmfailback.add(JBadmfailback);
		
		JFmain.getContentPane().add(JPadmfail);
		JFmain.setVisible(true);
	}
	
	
	JPanel JPfunctioncho;
	/**
	 * The interface of admin's function
	 */
	public void admFunction(){
		JFmain.getContentPane().remove(JPadm);
		JFmain.repaint();
		JPfunctioncho = new JPanel();
		JLabel JLfunctiontitle = new JLabel("energy system");
		JLfunctiontitle.setFont(new Font("Algerian", Font.PLAIN, 40));
		JLfunctiontitle.setHorizontalAlignment(SwingConstants.CENTER);
		JLfunctiontitle.setBounds(200, 45, 400, 65);
		JPanel JPmanage = new JPanel();
		JPmanage.setBounds(0, 160, 800, 160);
		JPanel JPsetprice = new JPanel();
		JPsetprice.setBounds(0, 320, 800, 160);
		JPanel JPbill = new JPanel();
		JPbill.setBounds(0, 480, 800, 160);
		JPanel JPexit = new JPanel();
		JPexit.setBounds(0, 640, 800, 160);
		JPfunctioncho.setLayout(null);
		JPfunctioncho.add(JLfunctiontitle);
		JPfunctioncho.add(JPmanage);
		JPfunctioncho.add(JPsetprice);
		JPfunctioncho.add(JPbill);
		JPfunctioncho.add(JPexit);
		
		JButton JBmanage = new JButton("Manage Users");
		JBmanage.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JBmanage.setBounds(280, 40, 240, 46);
		JBmanage.addActionListener(new inmanageuserListen());
		JPmanage.setLayout(null);
		JPmanage.add(JBmanage);
		JButton JBsetprice = new JButton("Set unit price");
		JBsetprice.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JBsetprice.setBounds(280, 40, 240, 46);
		JBsetprice.addActionListener(new inunitpriceListen());
		JPsetprice.setLayout(null);
		JPsetprice.add(JBsetprice);
		JButton JBbill = new JButton("Send bill to users");
		JBbill.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JBbill.setBounds(280, 40, 240, 46);
		JBbill.addActionListener(new insendbillListen());
		JPbill.setLayout(null);
		JPbill.add(JBbill);
		JBexit = new JButton("Exit");
		JBexit.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBexit.setBounds(330, 40, 140, 46);
		JBexit.addActionListener(new admfunctionexitListen());
		JPexit.setLayout(null);
		JPexit.add(JBexit);
		
		JFmain.getContentPane().add(JPfunctioncho);
		JFmain.setVisible(true);
	}
	
	
	JPanel JPmanageuser;
	/**
	 * The interface of manage user
	 */
	public void manageUser(){
		JFmain.getContentPane().remove(JPfunctioncho);
		JFmain.repaint();
		JPmanageuser = new JPanel();
		JLabel JLmanagetitle = new JLabel("Manage Users");
		JLmanagetitle.setHorizontalAlignment(SwingConstants.CENTER);
		JLmanagetitle.setFont(new Font("Algerian", Font.PLAIN, 40));
		JLmanagetitle.setBounds(200, 45, 400, 65);
		JPanel JPadd = new JPanel();
		JPadd.setBounds(0, 160, 800, 160);
		JPanel JPdelete = new JPanel();
		JPdelete.setBounds(0, 320, 800, 160);
		JPanel JPlookover = new JPanel();
		JPlookover.setBounds(0, 480, 800, 160);
		JPanel JPmanageback = new JPanel();
		JPmanageback.setBounds(0, 640, 800, 160);
		JPmanageuser.setLayout(null);
		JPmanageuser.add(JLmanagetitle);
		JPmanageuser.add(JPadd);
		JPmanageuser.add(JPdelete);
		JPmanageuser.add(JPlookover);
		JPmanageuser.add(JPmanageback);
		
		JButton JBadd = new JButton("Add Users");
		JBadd.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JBadd.setBounds(280, 40, 240, 46);
		JBadd.addActionListener(new inadduserListen());
		JPadd.setLayout(null);
		JPadd.add(JBadd);
		JButton JBdelete = new JButton("Delete Users");
		JBdelete.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JBdelete.setBounds(280, 40, 240, 46);
		JBdelete.addActionListener(new indeleteuserListen());
		JPdelete.setLayout(null);
		JPdelete.add(JBdelete);
		JButton JBlookover = new JButton("Look over");
		JBlookover.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JBlookover.setBounds(280, 40, 240, 46);
		JBlookover.addActionListener(new inlookoverListen());
		JPlookover.setLayout(null);
		JPlookover.add(JBlookover);
		JButton JBmanageback = new JButton("Back");
		JBmanageback.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBmanageback.setBounds(330, 40, 140, 46);
		JBmanageback.addActionListener(new manageuserbackListen());
		JPmanageback.setLayout(null);
		JPmanageback.add(JBmanageback);
		
		JFmain.getContentPane().add(JPmanageuser);
		JFmain.setVisible(true);
	}
		
	//addfail
	JPanel JPadduserfail;
	/**
	 * The interface show that administrator add user error
	 */
	public void addUserfail(){
		JFmain.getContentPane().remove(JPmanageuser);
		JFmain.repaint();
		JPadduserfail = new JPanel(new GridLayout(2,1));
		JLabel JLadduserfail = new JLabel("Wrong!");
		JPanel JPadduserfailback = new JPanel();
		JPadduserfail.add(JLadduserfail);
		JPadduserfail.add(JPadduserfailback);
		
		JButton JBadduserfailback = new JButton("Back");
		JBadduserfailback.addActionListener(new adduserfailbackListen());
		JPadduserfailback.add(JBadduserfailback);
		
		JFmain.getContentPane().add(JPadduserfail);
		JFmain.setVisible(true);
	}
	
	//delete
	JPanel JPdeleteuser;
	JTextField JTdeleteID;
	/**
	 * The interface of deleting user
	 */
	public void deleteUser(){
		JFmain.getContentPane().remove(JPmanageuser);
		JFmain.repaint();
		JPdeleteuser = new JPanel();
		JPanel JPdeletecho = new JPanel();
		JPdeletecho.setBounds(0, 600, 800, 200);
		JPdeleteuser.setLayout(null);
		
		JPanel JPdeletHead = new JPanel();
		JPdeletHead.setBounds(0, 0, 800, 200);
		JPdeleteuser.add(JPdeletHead);
		JPdeletHead.setLayout(null);
		
		JLabel JLdeletsUser = new JLabel("Delete User");
		JLdeletsUser.setFont(new Font("Algerian", Font.PLAIN, 40));
		JLdeletsUser.setHorizontalAlignment(SwingConstants.CENTER);
		JLdeletsUser.setBounds(200, 59, 400, 80);
		JPdeletHead.add(JLdeletsUser);
		
		JPanel JPdeleteTips = new JPanel();
		JPdeleteTips.setBounds(0, 0, 800, 600);
		JPdeleteuser.add(JPdeleteTips);
		JPdeleteTips.setLayout(null);
		JLabel JLdeletetips = new JLabel("Write the ID of the user you want to delete");
		JLdeletetips.setHorizontalAlignment(SwingConstants.CENTER);
		JLdeletetips.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JLdeletetips.setBounds(190, 250, 420, 46);
		JPdeleteTips.add(JLdeletetips);
		JTdeleteID = new JTextField(20);
		JTdeleteID.setBounds(200, 350, 400, 40);
		JPdeleteTips.add(JTdeleteID);
		JPdeleteuser.add(JPdeletecho);
		
		JButton JBdelete = new JButton("Delete");
		JBdelete.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBdelete.setBounds(80, 77, 240, 46);
		JBdelete.addActionListener(new deleteuserback1Listen());
		JButton JBdeleteback = new JButton("Back");
		JBdeleteback.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBdeleteback.setBounds(480, 77, 240, 46);
		JBdeleteback.addActionListener(new deleteuserback2Listen());
		JPdeletecho.setLayout(null);
		JPdeletecho.add(JBdelete);
		JPdeletecho.add(JBdeleteback);
		
		JFmain.getContentPane().add(JPdeleteuser);
		JFmain.setVisible(true);
	}
	//deletefail
	JPanel JPdeleteuserfail;
	/**
	 * The interface show that deleting user error
	 */
	public void deleteUserfail(){
		JFmain.getContentPane().remove(JPdeleteuser);
		JFmain.repaint();
		JPdeleteuserfail = new JPanel(new GridLayout(2,1));
		JLabel JLdeleteuserfail = new JLabel("Wrong!");
		JPanel JPdeleteuserfailback = new JPanel();
		JPdeleteuserfail.add(JLdeleteuserfail);
		JPdeleteuserfail.add(JPdeleteuserfailback);
		
		JButton JBdeleteuserfailback = new JButton("Back");
		JBdeleteuserfailback.addActionListener(new deleteuserfailbackListen());
		JPdeleteuserfailback.add(JBdeleteuserfailback);
		
		JFmain.getContentPane().add(JPdeleteuserfail);
		JFmain.setVisible(true);
	}
	
	//look over
	JPanel JPlookoveruser;
	String lookoverID;
	JComboBox<String> JCyearChoose = new JComboBox<String>();
	JComboBox<String> JCmonthChoose = new JComboBox<String>();
	/**
	 * The interface of looking over users
	 */
	public void lookover(){
		JFmain.getContentPane().remove(JPmanageuser);
		JFmain.repaint();
		JPlookoveruser = new JPanel();
		JPanel JPlookovercho = new JPanel();
		JPlookovercho.setBounds(0, 600, 800, 200);
		JPlookoveruser.setLayout(null);
		
		JPanel JPlookoverHead = new JPanel();
		JPlookoverHead.setBounds(0, 0, 800, 200);
		JPlookoveruser.add(JPlookoverHead);
		JPlookoverHead.setLayout(null);
		
		JLabel JLlookoverHead = new JLabel("Look Over");
		JLlookoverHead.setBounds(200, 59, 400, 80);
		JLlookoverHead.setFont(new Font("Algerian", Font.PLAIN, 40));
		JLlookoverHead.setHorizontalAlignment(SwingConstants.CENTER);
		JPlookoverHead.add(JLlookoverHead);
		
		JPanel JPlookoverTips = new JPanel();
		JPlookoverTips.setBounds(0, 0, 800, 600);
		JPlookoveruser.add(JPlookoverTips);
		JPlookoverTips.setLayout(null);
		JLabel JLlookovertips = new JLabel("Please choose which month that you want to look over: ");
		JLlookovertips.setHorizontalAlignment(SwingConstants.CENTER);
		JLlookovertips.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JLlookovertips.setBounds(150, 250, 500, 46);
		JPlookoverTips.add(JLlookovertips);
		
		JCyearChoose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JCyearChoose.setModel(new DefaultComboBoxModel<String>(new String[] {"Year", "2018"}));
		JCyearChoose.setBounds(195, 340, 150, 40);
		JPlookoverTips.add(JCyearChoose);
		
		JCmonthChoose.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JCmonthChoose.setModel(new DefaultComboBoxModel<String>(new String[] {"Month", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		JCmonthChoose.setBounds(450, 340, 150, 40);
		JPlookoverTips.add(JCmonthChoose);
		JPlookoveruser.add(JPlookovercho);
		
		JButton JBlookover = new JButton("OK");
		JBlookover.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBlookover.setBounds(80, 77, 240, 46);
		JBlookover.addActionListener(new initshistoryListen());
		JButton JBlookoverback = new JButton("Back");
		JBlookoverback.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBlookoverback.setBounds(480, 77, 240, 46);
		JBlookoverback.addActionListener(new lookoverbackListen());
		JPlookovercho.setLayout(null);
		JPlookovercho.add(JBlookover);
		JPlookovercho.add(JBlookoverback);
		
		JFmain.getContentPane().add(JPlookoveruser);
		JFmain.setVisible(true);
	}
	//history record
	JPanel JPitshistory;
	JTextArea JTrecord;
	/**
	 * The interface show user's history
	 */
	public void itshistory(){
		String[] his = com.getHistory(lookoverID);
		int n = his.length;
		
		JFmain.getContentPane().remove(JPlookoveruser);
		JFmain.repaint();
		JPitshistory = new JPanel();
		JPanel JPrecordback = new JPanel();
		JPrecordback.setBounds(0, 600, 800, 200);
		JPitshistory.setLayout(null);
		
		JPanel JPhistoryHead = new JPanel();
		JPhistoryHead.setBounds(0, 0, 800, 200);
		JPitshistory.add(JPhistoryHead);
		JPhistoryHead.setLayout(null);
		
		JLabel JLhistoryHead = new JLabel("History Record");
		JLhistoryHead.setHorizontalAlignment(SwingConstants.CENTER);
		JLhistoryHead.setFont(new Font("Algerian", Font.PLAIN, 40));
		JLhistoryHead.setBounds(200, 59, 400, 80);
		JPhistoryHead.add(JLhistoryHead);
		
		JPanel JPhistoryTips = new JPanel();
		JPhistoryTips.setBounds(0, 0, 800, 600);
		JPitshistory.add(JPhistoryTips);
		JPhistoryTips.setLayout(null);
		JLabel JLrecordtitle = new JLabel("The history record");
		JLrecordtitle.setHorizontalAlignment(SwingConstants.CENTER);
		JLrecordtitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JLrecordtitle.setBounds(150, 225, 500, 46);
		JPhistoryTips.add(JLrecordtitle);
		JTrecord = new JTextArea();
		JTrecord.setBounds(0, 300, 800, 300);
		JPhistoryTips.add(JTrecord);
		//zai ci tian jia cong wen jian zhong du qu de ji lu
		if(n == 1){
			JTrecord.append("There is not historical record of this month" );}
		else{
			for(int i=0;i<n;i++){
				JTrecord.append(his[i]);
				JTrecord.append("\n");
			}
		}
		JPitshistory.add(JPrecordback);
		
		JButton JBrecordback = new JButton("Back");
		JBrecordback.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBrecordback.setBounds(280, 77, 240, 46);
		JBrecordback.addActionListener(new itshistorybackListen());
		JPrecordback.setLayout(null);
		JPrecordback.add(JBrecordback);
		
		JFmain.getContentPane().add(JPitshistory);
		JFmain.setVisible(true);
	}
	
	//change unit price
	JPanel JPunitprice;
	JLabel JLeleinital;
	JTextField JTelenew;
	JLabel JLgasinital;
	JTextField JTgasnew;
	/**
	 * The interface of changing unit price
	 */
	public void unitprice(){
		JFmain.getContentPane().remove(JPfunctioncho);
		JFmain.repaint();
		JPunitprice = new JPanel();
		JPanel JPprice = new JPanel();
		JPprice.setBounds(0, 267, 800, 267);
		JPanel JPunitpricecho = new JPanel();
		JPunitpricecho.setBounds(0, 600, 800, 200);
		JPunitprice.setLayout(null);
		
		JPanel JPsetunitpriceHead = new JPanel();
		JPsetunitpriceHead.setBounds(0, 0, 800, 200);
		JPunitprice.add(JPsetunitpriceHead);
		JPsetunitpriceHead.setLayout(null);
		JLabel JLunitprice = new JLabel("Set unit price");
		JLunitprice.setFont(new Font("Algerian", Font.PLAIN, 40));
		JLunitprice.setHorizontalAlignment(SwingConstants.CENTER);
		JLunitprice.setBounds(200, 59, 400, 80);
		JPsetunitpriceHead.add(JLunitprice);
		JPunitprice.add(JPprice);
		JPunitprice.add(JPunitpricecho);
		
		
		JPanel JPelectricity = new JPanel();
		JPelectricity.setBounds(0, 0, 400, 267);
		JPanel JPgas = new JPanel();
		JPgas.setBounds(400, 0, 400, 267);
		JPprice.setLayout(null);
		JPprice.add(JPelectricity);
		JPprice.add(JPgas);
		
		try{com.readFromFile("UserData.txt");} catch (Exception e1) {
			e1.printStackTrace();
		}
		String electricityTariff = com.getElectricityTariff();
		JLabel JLeletip = new JLabel("Electricity:");
		JLeletip.setHorizontalAlignment(SwingConstants.CENTER);
		JLeletip.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JLeletip.setBounds(110, 0, 120, 89);
		JLeleinital = new JLabel(electricityTariff);
		JLeleinital.setHorizontalAlignment(SwingConstants.CENTER);
		JLeleinital.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JLeleinital.setBounds(110, 89, 120, 89);
		JTelenew = new JTextField();
		JTelenew.setBounds(147, 178, 200, 40);
		JPelectricity.setLayout(null);
		JPelectricity.add(JLeletip);
		JPelectricity.add(JLeleinital);
		
		JLabel lblNewLabel = new JLabel("New: ");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 177, 120, 40);
		JPelectricity.add(lblNewLabel);
		JPelectricity.add(JTelenew);
		
		String gasTariff = com.getGasTariff();
		JLabel JLgastip = new JLabel("Gas:");
		JLgastip.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		JLgastip.setHorizontalAlignment(SwingConstants.CENTER);
		JLgastip.setBounds(110, 0, 120, 89);
		JLgasinital = new JLabel(gasTariff);
		JLgasinital.setFont(new Font("Comic Sans MS", Font.PLAIN, 19));
		JLgasinital.setHorizontalAlignment(SwingConstants.CENTER);
		JLgasinital.setBounds(110, 89, 120, 89);
		JTgasnew = new JTextField();
		JTgasnew.setBounds(150, 178, 200, 40);
		JPgas.setLayout(null);
		JPgas.add(JLgastip);
		JPgas.add(JLgasinital);
		
		JLabel lblNewLabel_1 = new JLabel("New: ");
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 177, 120, 40);
		JPgas.add(lblNewLabel_1);
		JPgas.add(JTgasnew);
		
		JButton JBpricesave = new JButton("Save and Back");
		JBpricesave.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBpricesave.setBounds(80, 77, 240, 46);
		JBpricesave.addActionListener(new unitpriceback1Listen());
		JButton JBpriceback = new JButton("Back");
		JBpriceback.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBpriceback.setBounds(480, 77, 240, 46);
		JBpriceback.addActionListener(new unitpriceback2Listen());
		JPunitpricecho.setLayout(null);
		JPunitpricecho.add(JBpricesave);
		JPunitpricecho.add(JBpriceback);
		
		JFmain.getContentPane().add(JPunitprice);
		JFmain.setVisible(true);
	}
	//change fail
	JPanel JPunitpricefail;
	/**
	 * The interface show that administrator changing unit price fails
	 */
	public void unitpricefail(){
		JFmain.getContentPane().remove(JPunitprice);
		JFmain.repaint();
		JPunitpricefail = new JPanel(new GridLayout(2,1));
		JLabel JLunitpricefail = new JLabel("Wrong!");
		JPanel JPunitpricefailback = new JPanel();
		JPunitpricefail.add(JLunitpricefail);
		JPunitpricefail.add(JPunitpricefailback);
		
		JButton JBunitpricefailback = new JButton("Back");
		JBunitpricefailback.addActionListener(new unitpricefailbackListen());
		JPunitpricefailback.add(JBunitpricefailback);
		
		JFmain.getContentPane().add(JPunitpricefail);
		JFmain.setVisible(true);
	}
	
	//send bill to user
	JPanel JPsendbill;
	/**
	 * The interface of sending bills
	 */
	public void sendBill(){
		JFmain.getContentPane().remove(JPfunctioncho);
		JFmain.repaint();
		JPsendbill = new JPanel();
		JLabel JLsendbill = new JLabel("The bill has been set to users!");
		JLsendbill.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
		JLsendbill.setHorizontalAlignment(SwingConstants.CENTER);
		JLsendbill.setBounds(0, 124, 800, 276);
		JPanel JPsendback = new JPanel();
		JPsendback.setBounds(0, 400, 800, 200);
		JPsendbill.setLayout(null);
		JPsendbill.add(JLsendbill);
		JPsendbill.add(JPsendback);
		
		JButton JBsendback = new JButton("Back");
		JBsendback.setFont(new Font("Monotype Corsiva", Font.PLAIN, 20));
		JBsendback.setBounds(280, 77, 240, 46);
		JBsendback.addActionListener(new sendbillbackListen());
		JPsendback.setLayout(null);
		JPsendback.add(JBsendback);
		
		JFmain.getContentPane().add(JPsendbill);
		JFmain.setVisible(true);
	}
	
	//add actionlistener of user button
	/**
	 * The user button actionlistener class
	 * @author yumiao
	 *
	 */
	class userListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			inuser();
		}
	}
	/**
	 * The actionlistener of user login button class
	 * @author yumiao
	 *
	 */
	class userlogListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(JTuserID.getText().equals(""));
			else{
				if (stringMultiValueMap.containsKey(JTuserID.getText())){
					String userID = JTuserID.getText();
					map2Object(userID);
					try {
						userMonitor();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				else{
					userIDfail();
				}
			}
		}
	}
	
	/**
	 * The user exit button actionlistener class
	 * @author yumiao
	 *
	 */
	class userexitListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPuser);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmain);
			JFmain.setVisible(true);
		}
	}
	//uesrIDfail one button
	/**
	 * The back button in the userID error interface actionlistener class
	 * @author yumiao 
	 *
	 */
	class userIDfailbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPuserIDfail);
			JFmain.repaint();
			JFmain.getContentPane().add(JPuser);
			JFmain.setVisible(true);
		}
	}
	//monitor three button
	/**
	 * The history button actionlistener class
	 * @author wangce
	 *
	 */
	class inhistorychoiceListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			historychoice();
		}
	}
	/**
	 * The budget button actionlistener class
	 * @author wangce
	 *
	 */
	class inbudgetListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			budget();
		}
	}
	/**
	 * The exit button actionlistener class
	 * @author wangce
	 *
	 */
	class monitorexitListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPmonitor);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmain);
			JFmain.setVisible(true);
		}
	}
	//historychoice two button
	/**
	 * The history button actionlistener class
	 * @author wangce
	 *
	 */
	class inhistoryListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String userYear = (String)year.getSelectedItem();
			String userMonth = (String)month.getSelectedItem();
			userDate = userYear + "." + userMonth;
			history();
		}
	}
	/**
	 * The back button in history choose interface actionlistener class
	 * @author wangce
	 *
	 */
	class historychoicebackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				reinit();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			JFmain.getContentPane().remove(JPhistorychoice);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmonitor);
			JFmain.setVisible(true);
		}
	}
	//history one back button
	/**
	 * The back button in history interface actionlistener class
	 * @author wangce 
	 *
	 */
	class historybackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				reinit();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			JFmain.getContentPane().remove(JPhistory);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmonitor);
			JFmain.setVisible(true);
		}
	}
	//budget two button
	/**
	 * The finish button in budget interface actionlistener class
	 * @author yumiao
	 *
	 */
	class inbudgetfinListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(JTnew.getText().equals(""))
			{
				
				return; }
			else{
				if(JTnew.getText().matches("[0-9]{1,}")){
					
					temp.setBudget(Integer.parseInt(JTnew.getText()));
					object2Map(temp);
					try{saveToFile("UserData.txt");}
					catch (Exception er){
						er.printStackTrace();
					}
					budgetfin();
				}
				else
				{
					
					budgetfail();}
				
			}
		}
	}
	/**
	 * The back button in budget interface actionlistener class
	 * @author wangce
	 *
	 */
	class budgetbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				reinit();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			JFmain.getContentPane().remove(JPbudget);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmonitor);
			JFmain.setVisible(true);
		}
	}
	//budgetfail one button
	/**
	 * @author wangce
	 * The back button in budget error interface actionlistener class
	 *
	 */
	class budgetfailbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				reinit();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			JFmain.getContentPane().remove(JPbudgetfail);
			JFmain.repaint();
			JFmain.getContentPane().add(JPbudget);
			JFmain.setVisible(true);
		}
	}
	//budgetfin one button
	/**
	 * The back button in budget complete interface actionlistener class
	 * @author yumiao
	 *
	 */
	class budgetfinbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				reinit();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			JFmain.getContentPane().remove(JPsuccess);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmonitor);
			JFmain.setVisible(true);
		}
	}
	
	//add actionlistener of adm button
	/**
	 * The administer button actionlistener class
	 * @author wangce
	 *
	 */
	class admListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			inadm();
		}
	}
	/**
	 * The administrator login button actionlistener class
	 * @author yumiao 
	 *
	 */
	class admlogListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String admId = JTadmID.getText();
			if(admId.equals("admin")){
				admFunction();
			}
			else
			{admfail();}
		}
	}
	/**
	 * The administrator exit button actionlistener class
	 * @author yumiao
	 *
	 */
	class admexitListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPadm);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmain);
			JFmain.setVisible(true);
		}
	}
	//admfail one button
	/**
	 * The back button in administrator login error interface actionlistener class
	 * @author yumiao
	 *
	 */
	class admfailbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPadmfail);
			JFmain.repaint();
			JFmain.getContentPane().add(JPadm);
			JFmain.setVisible(true);
		}
	}
	//admFunction four button
	/**
	 * The manage button actionlistener class
	 * @author yumiao
	 *
	 */
	class inmanageuserListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			manageUser();
		}
	}
	/**
	 * The change unit price button actionlistener class
	 * @author yumiao
	 *
	 */
	class inunitpriceListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			unitprice();
		}
	}
	/**
	 * The send bills button actionlistener class
	 * @author yumiao
	 *
	 */
	class insendbillListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				com.readFromFile("UserData.txt");
				Calendar c = Calendar.getInstance();//Change time 
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH)+1;
				String pastFile = year+"."+month + ".txt";
				com.saveToFile(pastFile);
				com.zero();
				com.saveToNewFile("UserData.txt");
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			sendBill();
		}
	}
	/**
	 * The exit button in administrator interface actionlistener class
	 * @author wangce
	 *
	 */
	class admfunctionexitListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPfunctioncho);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmain);
			JFmain.setVisible(true);
		}
	}
	//manageuser four button
	/**
	 * The add users button in administrator interface actionlistener class
	 * @author wangce
	 *
	 */
	class inadduserListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				com.readFromFile("UserData.txt");
			} catch (Exception e2) {
				
				e2.printStackTrace();
			}
			int addid = com.addUserToMap();
			if(addid!=0){
				try {
					com.saveToFile("UserData.txt");
					JOptionPane.showMessageDialog(null, "The user is" +addid+ "successfully added!",
							"Information", JOptionPane.PLAIN_MESSAGE);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
			}
			else
			{addUserfail();}
			//addUser();
		}
	}
	/**
	 * The delete user button in administrator interface actionlistener class
	 * @author wangce
	 *
	 */
	class indeleteuserListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			deleteUser();
		}
	}
	/**
	 * The look over button in administrator interface actionlistener class
	 * @author yumiao
	 *
	 */
	class inlookoverListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			lookover();
		}
	}
	/**
	 * The back button in manage users interface actionlistener class
	 * @author yumiao
	 *
	 */
	class manageuserbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPmanageuser);
			JFmain.repaint();
			JFmain.getContentPane().add(JPfunctioncho);
			JFmain.setVisible(true);
		}
	}
	//adduser fail one button
	/**
	 * The back button in add user interface actionlistener class
	 * @author zhang han
	 *
	 */
	class adduserfailbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPadduserfail);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmanageuser);
			JFmain.setVisible(true);
		}
	}
	//deleteUser two back button
	/**
	 * The back button delete user interface actionlistener class
	 * @author wangce 
	 *
	 */
	class deleteuserback1Listen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String delid = JTdeleteID.getText();
			int id = Integer.parseInt(delid);
			try {
				com.readFromFile("UserData.txt");
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			if(com.removeUserfromMap(id)==1){
				try {
					com.saveToFile("Userdata.txt");
				} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				JFmain.getContentPane().remove(JPdeleteuser);
				JFmain.repaint();
				JFmain.getContentPane().add(JPmanageuser);
				JFmain.setVisible(true);
			}
			else
			{deleteUserfail();}
		}
		
	}
	/**
	 * Another back button delete user interface actionlistener class
	 * @author wangce
	 *
	 */
	class deleteuserback2Listen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPdeleteuser);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmanageuser);
			JFmain.setVisible(true);
		}
	}
	//deletefail one button
	/**
	 * The back button in delete user error interface actionlistener class
	 * @author wangce
	 *
	 */
	class deleteuserfailbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPdeleteuserfail);
			JFmain.repaint();
			JFmain.getContentPane().add(JPdeleteuser);
			JFmain.setVisible(true);
		}
	}
	
	//lookover two back button
	/**
	 * The history button in administrator interface actionlistener class
	 * @author yumiao
	 *
	 */
	class initshistoryListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String lookoveryear = (String)JCyearChoose.getSelectedItem();
			String lookovermonth = (String)JCmonthChoose.getSelectedItem();
			lookoverID = lookoveryear + "." + lookovermonth;
			itshistory();
		}
	}
	/**
	 * The back button in look over interface actionlistener class
	 * @author yumiao
	 *
	 */
	class lookoverbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPlookoveruser);
			JFmain.repaint();
			JFmain.getContentPane().add(JPmanageuser);
			JFmain.setVisible(true);
		}
	}
	//itshistory one back button.
	/**
	 * The back button in history interface actionlistener class
	 * @author yumiao
	 *
	 */
	class itshistorybackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPitshistory);
			JFmain.repaint();
			JFmain.getContentPane().add(JPlookoveruser);
			JFmain.setVisible(true);
		}
	}
	//unitprice two back button
	/**
	 * The back button in unit price interface actionlistener class
	 * @author wangce
	 *
	 */
	class unitpriceback1Listen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String newele = JTelenew.getText();
			String newgas = JTgasnew.getText();
			Double neweleB = 0.0;
			Double newgasB = 0.0;
			if(newele == null || newele.length()<= 0 ){
				if(newgas == null || newgas.length() <= 0){
					unitpricefail();
				}
				else{
					try{
						newgasB = Double.parseDouble(newgas);
					}catch(NumberFormatException e0){unitpricefail();return;}
					com.setGasTariff(newgasB);
					try{com.saveToFile("UserData.txt");} catch (Exception e1) {
						
						e1.printStackTrace();
					}
					
					JFmain.getContentPane().remove(JPunitprice);
					JFmain.repaint();
					JFmain.getContentPane().add(JPfunctioncho);
					JFmain.setVisible(true);
				}
			}
			else if(newgas == null || newgas.length() <= 0){
				if(newele == null || newele.length() <= 0){
					unitpricefail();
				}
				else{
					try{
						neweleB = Double.parseDouble(newele);
					}catch(NumberFormatException e0){unitpricefail();return;}
					com.setEleTariff(neweleB);
					try{com.saveToFile("UserData.txt");} catch (Exception e1) {
						
						e1.printStackTrace();
					}
					
					JFmain.getContentPane().remove(JPunitprice);
					JFmain.repaint();
					JFmain.getContentPane().add(JPfunctioncho);
					JFmain.setVisible(true);
				}
			}
			else{
				try{
					neweleB = Double.parseDouble(newele);
					newgasB = Double.parseDouble(newgas);
				}catch(NumberFormatException e0){unitpricefail();return;}
				com.setEleTariff(neweleB);
				com.setGasTariff(newgasB);
				try{com.saveToFile("UserData.txt");} catch (Exception e1) {
					
					e1.printStackTrace();
				}
				
				JFmain.getContentPane().remove(JPunitprice);
				JFmain.repaint();
				JFmain.getContentPane().add(JPfunctioncho);
				JFmain.setVisible(true);
			}
			
		}
	}
	/**
	 * Another back button in unit price interface actionlistener class
	 * @author wangce
	 *
	 */
	class unitpriceback2Listen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPunitprice);
			JFmain.repaint();
			JFmain.getContentPane().add(JPfunctioncho);
			JFmain.setVisible(true);
		}
	}
	//unitprice fail one button
	/**
	 * The back button in error in unit price interface actionlistener class
	 * @author wance
	 *
	 */
	class unitpricefailbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPunitpricefail);
			JFmain.repaint();
			JFmain.getContentPane().add(JPunitprice);
			JFmain.setVisible(true);
		}
	}
	//sendBill one back button
	/**
	 * The back button in send bills interface actionlistener class
	 * @author yumiao 
	 *
	 */
	class sendbillbackListen implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JFmain.getContentPane().remove(JPsendbill);
			JFmain.repaint();
			JFmain.getContentPane().add(JPfunctioncho);
			JFmain.setVisible(true);
		}
	}
	
	/**
	 * The function to read from file
	 * @param fileName The file name
	 * @throws Exception Exception of IOread
	 */
	public void readFromFile(String fileName) throws Exception{
		stringMultiValueMap = new LinkedMultiValueMap<>();
		File file = new File(fileName) ;
		InputStream input = new FileInputStream(file) ;
		byte[] b = new byte[(int)file.length()] ;
		input.read(b) ;
		String str = new String(b) ;
		String[] split = str.split(" ") ;
		for (int i = 0; i<(split.length/7);i++){
			int tempNo = Integer.parseInt(split[i*7]);
			for(int j=0;j<6;j++){
				stringMultiValueMap.add(String.format("%4d", tempNo).replace(" ", "0"), split[i*7+j+1]);
			}
		}
		input.close();
	}
	/**
	 * The method to save the data to file
	 * @param filename Flie name
	 * @throws Exception Exception of IOread
	 */
	public void saveToFile(String filename) throws Exception{
		File wfile = new File(filename);
		InputStream input = new FileInputStream(wfile) ;
		byte[] b = new byte[(int)wfile.length()] ;
		input.read(b) ;
		String str = new String(b) ;
		String[] split = str.split(" ") ;
		for (int i = 0; i<(split.length/7);i++){
			int tempNo = Integer.parseInt(split[i*7]);
			stringMultiValueMap.setValue(String.format("%4d", tempNo).replace(" ", "0"), 0, split[i*7+1]);
			stringMultiValueMap.setValue(String.format("%4d", tempNo).replace(" ", "0"), 1, split[i*7+2]);
			
		}
		FileOutputStream output = new FileOutputStream(wfile,false);
		String space = " ";
		byte[] s = space.getBytes();
		Set<String> keySet = stringMultiValueMap.keySet();
		for (String key : keySet) {
			byte[] k = key.getBytes();
			output.write(k);
			output.write(s);
			List<String> values = stringMultiValueMap.getValues(key);
			for (String value : values) {
				byte[] v = value.getBytes();
				output.write(v);
				output.write(s);
			}
		}
		input.close();
		output.close();
	}
	/**
	 * The method to turn the map to object
	 * @param key The key need to be turned to object
	 */
	public void map2Object(String key){
		temp.setAccNo(Integer.parseInt(key));
		temp.setGasMeter(Integer.parseInt(stringMultiValueMap.getValue(key, 0)));
		temp.setEleMeter(Integer.parseInt(stringMultiValueMap.getValue(key, 1)));
		temp.setGasUnitPrice(Double.parseDouble(stringMultiValueMap.getValue(key, 2)));
		temp.setEleUnitPrice(Double.parseDouble(stringMultiValueMap.getValue(key, 3)));
		temp.setBudget(Integer.parseInt(stringMultiValueMap.getValue(key, 4)));
		temp.setAlert(Integer.parseInt(stringMultiValueMap.getValue(key, 5)));
	}
	
	
	/**
	 * The method to save the object data to map
	 * @param uuuu The object need to turn to map
	 */
	public void object2Map(UserAccount uuuu){
		stringMultiValueMap.setValue(String.format("%4d", uuuu.getAccNo()).replace(" ", "0"), 0, "" + uuuu.getGasMeter());
		stringMultiValueMap.setValue(String.format("%4d", uuuu.getAccNo()).replace(" ", "0"), 1, "" + uuuu.getEleMeter());
		stringMultiValueMap.setValue(String.format("%4d", uuuu.getAccNo()).replace(" ", "0"), 2, "" + uuuu.getGasUnitPrice());
		stringMultiValueMap.setValue(String.format("%4d", uuuu.getAccNo()).replace(" ", "0"), 3, "" + uuuu.getEleUnitPrice());
		stringMultiValueMap.setValue(String.format("%4d", uuuu.getAccNo()).replace(" ", "0"), 4, "" + uuuu.getBudget());
		stringMultiValueMap.setValue(String.format("%4d", uuuu.getAccNo()).replace(" ", "0"), 5, "" + uuuu.getAlert());
		
	}
	
	/**
	 * The method to re-read number of ele and gas to map and object
	 * @throws Exception Exception of IOread
	 */
	public void reinit() throws Exception{
		readFromFile("UserData.txt");
		temp.setEleMeter(Integer.parseInt(stringMultiValueMap.getValue(
				String.format("%4d", temp.getAccNo()).replace(" ", "0"),1)));
		temp.setGasMeter(Integer.parseInt(stringMultiValueMap.getValue(
				String.format("%4d", temp.getAccNo()).replace(" ", "0"),0)));
		JBelequantity.setText(String.format("%4d",temp.getEleMeter()).replace(" ","0"));
		JBelemoney.setText(String.format("%.2f",temp.getEleMeter()
				*temp.getEleUnitPrice()));
		JBgasquantity.setText(String.format("%4d",temp.getGasMeter()).replace(" ","0"));
		JBgasmoney.setText(String.format("%.2f",temp.getGasMeter()
				*temp.getGasUnitPrice()));
	}
	
	
	/**
	 * The check budget actionlistener class
	 * @author chenyilin
	 *
	 */
	class budgetCheck implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(temp.checkAlert()==1){
				JBlight.setBackground(Color.RED);
			}else if(temp.checkAlert()==0){
				JBlight.setBackground(Color.GREEN);
				
			}
		}
	}
	
	
	
	}
	/**
	 * The method to update meter readings in real time
	 * @author wangxudong
	 *
	 */
	class WriteTime implements Runnable{
		
		public void run(){
			
			try{
				File ff = new File("UserData.txt") ;
				int i = 0;
				while(true){
					MultiValueMap<String, String> stringMultiValueMap = new LinkedMultiValueMap<>();
					InputStream input = new FileInputStream(ff) ;
					byte[] b = new byte[(int)ff.length()] ;
					input.read(b) ;
					String str = new String(b) ;
					String[] split = str.split(" ") ;
					for (i = 0; i<(split.length/7);i++){
						int tempNo = Integer.parseInt(split[i*7]);
						for(int j=0;j<6;j++){
							stringMultiValueMap.add(String.format("%4d", tempNo).replace(" ", "0"), split[i*7+j+1]);
						}
					}
					input.close();
					Set<String> keySet = stringMultiValueMap.keySet();
					for (String key : keySet) {
						int num0 = Integer.parseInt(stringMultiValueMap.getValue(key, 0));
						int num1 = Integer.parseInt(stringMultiValueMap.getValue(key, 1));
						int rand0 = 0;
						int rand1 = 0;
						Random random = new Random();
						rand0 = random.nextInt(100)%(100-1+1) + 1;
						rand1 = random.nextInt(100)%(100-1+1) + 1;
						if(rand0<20){
							num0++;
						}
						if(rand1<20){
							num1++;
						}
						
						stringMultiValueMap.setValue(key,0,String.format("%4d", num0).replace(" ", "0"));
						stringMultiValueMap.setValue(key,1,String.format("%4d", num1).replace(" ", "0"));						
					}
					FileOutputStream output = new FileOutputStream(ff,false);
					String space = " ";
					byte[] s = space.getBytes();
					keySet = stringMultiValueMap.keySet();
					for (String key : keySet) {
						byte[] k = key.getBytes();
						output.write(k);
						output.write(s);
						List<String> values = stringMultiValueMap.getValues(key);
						for (String value : values) {
							byte[] v = value.getBytes();
							output.write(v);
							output.write(s);
						}
					}
					output.close();
					Thread.sleep(10000);
				}
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}