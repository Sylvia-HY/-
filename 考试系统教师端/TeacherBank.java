import InquireScore.ChartBefore;
import makePaper.TestListList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TeacherBank extends JFrame implements ActionListener{
    private JLabel gaga;
    private JLabel haha;

    private JMenuBar menuBar1;
    private JMenu menu1;
    private JMenuItem menuItem2;
    private JMenuItem menuItem1;
    private JMenu menu2;
    private JMenuItem menuItem3;
    private JMenuItem menuItem4;

    public TeacherBank()
    {

        this.setLayout(null);
        this.setBounds(700, 700, 600, 400);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH  );

        gaga = new JLabel("欢迎您，亲爱的老师");
        gaga.setFont(new Font("楷体",Font.BOLD,80));
        gaga.setBounds(940, 500, 800, 100);
        this.add(gaga);

        haha= new JLabel("点击上方菜单选择你想操作的功能");
        haha.setFont(new Font("楷体",Font.BOLD,80));
        haha.setBounds(860, 900, 2000, 100);
        this.add(haha);

        menuBar1 = new JMenuBar();

        menu1 = new JMenu("学生管理");
        menu1.setFont(new Font("宋体",Font.BOLD,50));
        menu1.addActionListener(this);
        menuItem1 = new JMenuItem("批改学生试卷");
        menuItem1.setFont(new Font("宋体",Font.BOLD,50));
        menuItem1.addActionListener(this);

        menuItem2 = new JMenuItem("查询学生成绩");
        menuItem2.setFont(new Font("宋体",Font.BOLD,50));
        menuItem2.addActionListener(this);

        menuBar1.add(menu1);
        menu1.add(menuItem1);
        menu1.add(menuItem2);

        menu2 = new JMenu("考试管理");
        menu2.setFont(new Font("宋体",Font.BOLD,50));
        menu2.addActionListener(this);

        menuItem3 = new JMenuItem("我的试题库");
        menuItem3.setFont(new Font("宋体",Font.BOLD,50));
        menuItem3.addActionListener(this);

        menuItem4 = new JMenuItem("我的试卷集");
        menuItem4.setFont(new Font("宋体",Font.BOLD,50));
        menuItem4.addActionListener(this);

        menuBar1.add(menu2);
        menu2.add(menuItem3);
        menu2.add(menuItem4);

        setJMenuBar(menuBar1);
        setVisible(true);

        this.setTitle("我的主界面");
        this.setFont(new Font("宋体",Font.BOLD,80));
        this.setResizable(true);
        this.setVisible(true);

    }



    public static void main(String[] args)
    {
        new TeacherBank();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("我的试题库")) {
            new QuestionBank();
        }
        else if (e.getActionCommand().equals("我的试卷集")) {
            new TestListList();
        }
        else if (e.getActionCommand().equals("批改学生试卷")) {
            new TestList();
        }

        else if (e.getActionCommand().equals("查询学生成绩")) {
            new ChartBefore();
        }
    }
}
