package makePaper;

import Dao.DbConnection;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MainFrame extends JFrame implements ActionListener {

	private JButton importQuestionBtn = new JButton("导入题库");
	private JButton okBtn = new JButton("添加进试卷");
	private JButton seeBtn = new JButton("预览试卷");
	private JLabel HHtype = new JLabel("       选择题型（1选择，2填空，3解答题）");
	private JLabel HHdegree = new JLabel("      选择难度（1简单，2中等，3困难）");
	private JTextField Jtype = new JTextField();
	private JTextField Jdegree = new JTextField();
	private  MyJCheckBox[] jcbs;

	private JPanel controlPanel = new JPanel();
	private JPanel choicePanel = new JPanel();
	private JPanel doll = new JPanel();



	private DbConnection dbconn = new DbConnection();
	private Connection conn = null;
	private ResultSet rs = null;
	private PreparedStatement pstmt = null;
	private Statement stmt = null;

	ArrayList<String> content = new ArrayList<String>();
	ArrayList<String> other1 = new ArrayList<String>();
	ArrayList<String> other2 = new ArrayList<String>();
	ArrayList<String> other3 = new ArrayList<String>();
	ArrayList<String> other4 = new ArrayList<String>();
	ArrayList<String> res = new ArrayList<String>();
	ArrayList<Integer> iid=new ArrayList<Integer>();

	public MainFrame() {
		setBounds(800, 700, 700, 600);
		controlPanel.add(importQuestionBtn);
		controlPanel.add(okBtn);
		controlPanel.add(seeBtn);
		choicePanel.add(HHtype);
		choicePanel.add(Jtype);
		choicePanel.add(HHdegree);
		choicePanel.add(Jdegree);


		importQuestionBtn.setFont(new Font("楷体",Font.BOLD,50));
		okBtn.setFont(new Font("楷体",Font.BOLD,50));
		seeBtn.setFont(new Font("楷体",Font.BOLD,50));
		HHtype.setFont(new Font("宋体",Font.BOLD,50));
		Jtype.setFont(new Font("宋体",Font.BOLD,50));
		HHdegree.setFont(new Font("宋体",Font.BOLD,50));
		Jdegree.setFont(new Font("宋体",Font.BOLD,50));



		add(controlPanel, BorderLayout.SOUTH);
		add(choicePanel, BorderLayout.NORTH);
		add(doll, BorderLayout.CENTER);

		doll.setLayout(new BoxLayout(doll,BoxLayout.Y_AXIS ) );
//		doll.setLineWrap(true);
//		doll.setWrapStyleWord(true);
//		doll.setLayout(new GridLayout(20, 0, 20, 10));
		choicePanel.setLayout(new GridLayout(4, 2, 20, 10));

		importQuestionBtn.addActionListener(this);
		okBtn.addActionListener(this);
		seeBtn.addActionListener(this);


		JScrollPane scrollPane = new JScrollPane(doll);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);




		this.add(scrollPane, BorderLayout.CENTER);
		this.setTitle("组卷系统");
		this.setFont(new Font("楷体",Font.BOLD,40));
		this.setResizable(true);
		this.setVisible(true);
		doll.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH  );
//		doll.setBackground(Color.DARK_GRAY);
	}

//	public void showTree()
//	{
//		Jtype.updateAll;
//	}
	public void actionPerformed(ActionEvent e)
	{

		System.out.println(13312);
		if (e.getSource()==importQuestionBtn){
			query();
			int num=content.size();
            jcbs=new MyJCheckBox[num];
			for(int i=0;i<num;i++)
			{
				importQuestionBtn.setLabel("import");
//				JCheckBox btn = new JCheckBox(content.get(i));
//				btn.addActionListener(this);
				MyJCheckBox aa =new MyJCheckBox(iid.get(i),content.get(i));
				aa.setFont(new Font("楷体",Font.BOLD,50));
                jcbs[i] = aa;
				doll.add(aa);

				if(other1.get(i)!=""){
					JLabel a =new JLabel(other1.get(i));
					a.setFont(new Font("楷体",Font.BOLD,50));
					JLabel b =new JLabel(other2.get(i));
					b.setFont(new Font("楷体",Font.BOLD,50));
					JLabel c =new JLabel(other3.get(i));
					c.setFont(new Font("楷体",Font.BOLD,50));
					JLabel d =new JLabel(other4.get(i));
					d.setFont(new Font("楷体",Font.BOLD,50));
					doll.add(a);
					doll.add(b);
					doll.add(c);
					doll.add(d);
				}
				JLabel ans=new JLabel(res.get(i));
				ans.setFont(new Font("楷体",Font.BOLD,50));
				doll.add(ans);

			}
		}
		else if(e.getSource()==okBtn)
		{
			/*query();
			int num=content.size();
			jcbs=new MyJCheckBox[num];
			for(int i=0;i<num;i++)
				jcbs[i]=new MyJCheckBox(iid.get(i),content.get(i));*/
			okBtn.setLabel("product");
			add();
//			Jtype.updateUI();
//			Jdegree.updateUI();
//			doll.updateUI();
		}
		else if (e.getSource()==seeBtn){
			new ExamSet();
			this.dispose();
		}
	}

//        else if(e.getSource()==preBtn){

//            ButtonGroup group=new ButtonGroup();
//            questionLabel.setText("1.题目：啊八八八八八八八八");
//            for(int i=0;i<4;i++){
//                JRadioButton btn= new JRadioButton((char)('A'+i)+"这是选项");
//                choicePanel.add(btn);
//                group.add(btn);
//            }
//            choicePanel.updateUI();
//            setVisible(true);
//        }
//
//        else if(e.getSource()==nextBtn){
//            choicePanel.removeAll();
//            questionLabel.setText("2.题目：嘻嘻嘻嘻八八八八");
//            for(int i=0;i<4;i++) {
//                JCheckBox btn = new JCheckBox((char) ('A' + i) + "这是选项");
//                choicePanel.add(btn);
//            }

//        }

//        else if(e.getSource()==okBtn){}

//    }

	public void query()  {
		dbconn = new DbConnection();
		conn = dbconn.getConnection();

		String sql = "select questionid,content,typeA,typeB,typeC, typeD, `right`, `value` from question where `type` = "
				+ Jtype.getText() + " AND `degree` =" + Jdegree.getText();

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				while (rs.next()) {
					String aaa = rs.getString("content");
					content.add(aaa);
					int agga=rs.getInt("questionid") ;
					iid.add(agga);

					String bbb = rs.getString("typeA");
					String ccc = rs.getString("typeB");
					String ddd = rs.getString("typeC");
					String eee = rs.getString("typeD");
					String fff = rs.getString("right");

					other1.add(bbb);
					other2.add(ccc);
					other3.add(ddd);
					other4.add(eee);
					res.add(fff);
				}

			} else {
				JOptionPane.showMessageDialog(this, "查询失败,您可能没有此信息");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

	public void add()
	{
//		dbconn = new DbConnection();



			for (MyJCheckBox jcb : jcbs) {
				dbconn = new DbConnection();

				if(jcb.isSelected())
				{
					String sql = "insert into examcontent(examid,questionid)values("+ TestListList.examid+","+jcb.id+")";

					try
					{
						conn = dbconn.getConnection();
						pstmt = conn.prepareStatement(sql);
						/*pstmt.setInt(1, 4);
						pstmt.setInt(2, jcb.id);*/
						pstmt.execute(sql);
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
					finally
					{
						try
						{
							if (pstmt != null)
								pstmt.close();
							if (conn != null)
								conn.close();
						}
						catch (SQLException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		JOptionPane.showMessageDialog(this, "插入成功");
		}


	public static void main(String[] args) {
		new MainFrame();
	}

	class MyJCheckBox extends JCheckBox{
		private String Test;
		private int id;
		public MyJCheckBox(int iid,String Testt)
		{
			super(Testt);
			this.id=iid;
		}
	}

}
