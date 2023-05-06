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
import java.util.Random;


public class AexamSet extends JFrame implements ActionListener{


    private JPanel seePanel = new JPanel();
    private JPanel controlPanel = new JPanel();

    private JButton finishBtn = new JButton("完成");
    private JButton againBtn = new JButton("取消");

    private DbConnection dbconn = new DbConnection();
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;
    Random r = new Random();

    ArrayList<Integer> add1=new ArrayList<Integer>() ;

    public AexamSet(){
        setBounds(800, 700, 700, 600);
        controlPanel.add(finishBtn);
        controlPanel.add(againBtn);

        finishBtn.setFont(new Font("楷体",Font.BOLD,50));
        againBtn.setFont(new Font("楷体",Font.BOLD,50));

        add(controlPanel, BorderLayout.SOUTH);
        add(seePanel, BorderLayout.CENTER);
        seePanel.setLayout(new GridLayout(50, 2, 20, 10));
        seePanel.setFont(new Font("楷体",Font.BOLD,33));

        finishBtn.addActionListener(this);
        againBtn.addActionListener(this);

        JScrollPane scrollPane = new JScrollPane(seePanel);
        this.add(scrollPane, BorderLayout.CENTER);
        findMany();
        this.setTitle("自动组新卷");
        this.setResizable(true);
        this.setVisible(true);
        seePanel.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH  );




    }
    public void findMany()
    {
        int n1 = r.nextInt(4)+1;

        String sql = "select * from examcontent2 "
                + "left join question on question.questionid=examcontent2.questionid "
                +"where examcontent2.examid= " + n1;
        try
        {
            dbconn = new DbConnection();
            conn = dbconn.getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);
            int a=0;
            if (rs.next())
            {
                while(rs.next()) {
                    a++;
                    add1.add(rs.getInt("questionid"));

                    JTextArea one=new JTextArea(a+"、 "+rs.getString("content"));
                    one.setFont(new Font("楷体",Font.BOLD,50));
                    seePanel.add(one) ;
                    one.setAlignmentX(CENTER_ALIGNMENT);
                    one.setAlignmentY(CENTER_ALIGNMENT);
                    JTextArea two=new JTextArea(rs.getString("typeA"));
                    two.setFont(new Font("楷体",Font.BOLD,50));
                    seePanel.add(two) ;
                    two.setAlignmentX(CENTER_ALIGNMENT);
                    two.setAlignmentY(CENTER_ALIGNMENT);
                    JTextArea three=new JTextArea(rs.getString("typeB"));
                    three.setFont(new Font("楷体",Font.BOLD,50));
                    seePanel.add(three);
                    three.setAlignmentX(CENTER_ALIGNMENT);
                    three.setAlignmentY(CENTER_ALIGNMENT);
                    JTextArea four=new JTextArea(rs.getString("typeC"));
                    four.setFont(new Font("楷体",Font.BOLD,50));
                    seePanel.add(four) ;
                    four.setAlignmentX(CENTER_ALIGNMENT);
                    four.setAlignmentY(CENTER_ALIGNMENT);
                    JTextArea five=new JTextArea(rs.getString("typeD"));
                    seePanel.add(five) ;
                    five.setAlignmentX(CENTER_ALIGNMENT);
                    five.setAlignmentY(CENTER_ALIGNMENT);
                    five.setFont(new Font("楷体",Font.BOLD,50));

                }
            }
            else
            {
                JOptionPane.showMessageDialog(this, "查询失败,您可能没有此信息");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
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


    public void actionPerformed(ActionEvent e) {

        if (e.getSource()==finishBtn){

            int num=add1.size();
            for(int i=0;i<num;i++){
                dbconn = new DbConnection();
//                conn = dbconn.getConnection();

                String sql = "insert into examcontent(examid,questionid)values("+ TestListList.examid+","+add1.get(i)+")";
                try
                {
                    conn = dbconn.getConnection();
                    try {
                        pstmt = conn.prepareStatement(sql);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        pstmt.execute(sql);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                } finally
                {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                    }
                }}

            JOptionPane.showMessageDialog(this, "恭喜！已成功添加一张新试卷");}
        if (e.getSource()==againBtn){this.dispose();}

    }

    public static void main(String[] args) {
        new AexamSet();

    }


}