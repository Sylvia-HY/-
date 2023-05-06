import Controller.ClientSS;
import Dao.DbConnection;
import Data.Data;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;

public class QuestionBank extends JFrame implements ActionListener
{

    private JLabel questionId;
    private JLabel content;
    private JLabel typeA;
    private JLabel typeB;
    private JLabel typeC;
    private JLabel typeD;
    private JLabel right;
    private JLabel type;
    private JLabel value;
    private JLabel degree;


    private JTextField JquestionId;
    private JTextField Jcontent;
    private JTextField JtypeA;
    private JTextField JtypeB;
    private JTextField JtypeC;
    private JTextField JtypeD;
    private JTextField Jright;
    private JTextField Jtype;
    private JTextField Jvalue;
    private JTextField Jdegree;

    private JButton btnFirst;
    private JButton btnLast;
    private JButton btnBefore;
    private JButton btnNext;
    private JButton btnAdd;
    private JButton btnModify;
    private JButton btnDel;
    private JButton btnQuery;

    private DbConnection dbconn = null;
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    public QuestionBank()
    {
        this.setLayout(null);
//        this.setBounds(800, 700, 900, 800);

//        ImageIcon i = new ImageIcon("C:\\Users\\魏青欣\\IdeaProjects\\src\\Images\\one.jpg");
//        Image photo = i.getImage();
//        Image bigphoto = photo.getScaledInstance(1700, 800, Image.SCALE_FAST);
//        ImageIcon bigi = new ImageIcon(bigphoto);
//        JLabel imgLabel = new JLabel(bigi);
////底层面板背景最后放
//        this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
//        imgLabel.setBounds(0, 0, bigi.getIconWidth(), bigi.getIconHeight());
//        JPanel p = (JPanel) this.getContentPane();
//        p.setLayout(null);
//        p.setOpaque(false);

        questionId = new JLabel("题目编号");
        questionId.setBounds(900, 150, 400, 60);
        this.add(questionId);
        questionId.setFont(new Font("宋体",Font.BOLD,50));

        JquestionId = new JTextField();
        JquestionId.setFont(new Font("楷体",Font.BOLD,50));
        JquestionId.setBounds(1200, 150, 1000, 80);
        this.add(JquestionId);

        content = new JLabel("题干");
        content.setBounds(900, 260, 400, 60);
        this.add(content);
        content.setFont(new Font("宋体",Font.BOLD,50));

        Jcontent = new JTextField();
        Jcontent.setFont(new Font("楷体",Font.BOLD,50));
        Jcontent.setBounds(1200, 260, 1000, 80);
        this.add(Jcontent);

        typeA = new JLabel("选项A");
        typeA.setBounds(900, 370, 400, 60);
        this.add(typeA);
        typeA.setFont(new Font("宋体",Font.BOLD,50));

        JtypeA = new JTextField();
        JtypeA.setFont(new Font("楷体",Font.BOLD,50));
        JtypeA.setBounds(1200, 370, 1000, 80);
        this.add(JtypeA);

        typeB = new JLabel("选项B");
        typeB.setBounds(900, 480, 400, 60);
        this.add(typeB);
        typeB.setFont(new Font("宋体",Font.BOLD,50));

        JtypeB = new JTextField();
        JtypeB.setFont(new Font("楷体",Font.BOLD,50));
        JtypeB.setBounds(1200, 480, 1000, 80);
        this.add(JtypeB);

        typeC = new JLabel("选项C");
        typeC.setBounds(900, 590, 400, 60);
        this.add(typeC);
        typeC.setFont(new Font("宋体",Font.BOLD,50));

        JtypeC = new JTextField();
        JtypeC.setFont(new Font("楷体",Font.BOLD,50));
        JtypeC.setBounds(1200, 590, 1000, 80);
        this.add(JtypeC);

        typeD = new JLabel("选项D");
        typeD.setBounds(900, 700, 400, 60);
        this.add(typeD);
        typeD.setFont(new Font("宋体",Font.BOLD,50));

        JtypeD = new JTextField();
        JtypeD.setFont(new Font("楷体",Font.BOLD,50));
        JtypeD.setBounds(1200, 700, 1000, 80);
        this.add(JtypeD);

        right = new JLabel("正确答案");
        right.setBounds(900, 810, 400, 60);
        this.add(right);
        right.setFont(new Font("宋体",Font.BOLD,50));

        Jright = new JTextField();
        Jright.setFont(new Font("楷体",Font.BOLD,50));
        Jright.setBounds(1200, 810, 1000, 80);
        this.add(Jright);

        type = new JLabel("题目类型");
        type.setBounds(900, 920, 400, 60);
        this.add(type);
        type.setFont(new Font("宋体",Font.BOLD,50));

        Jtype = new JTextField();
        Jtype.setFont(new Font("楷体",Font.BOLD,50));
        Jtype.setBounds(1200, 920, 1000, 80);
        this.add(Jtype);

        value = new JLabel("题目分值");
        value.setBounds(900, 1030, 400, 60);
        this.add(value);
        value.setFont(new Font("宋体",Font.BOLD,50));

        Jvalue = new JTextField();
        Jvalue.setFont(new Font("楷体",Font.BOLD,50));
        Jvalue.setBounds(1200, 1030, 1000, 80);
        this.add(Jvalue);

        degree = new JLabel("题目难度");
        degree.setBounds(900, 1140, 400, 60);
        this.add(degree);
        degree.setFont(new Font("宋体",Font.BOLD,50));

        Jdegree = new JTextField();
        Jdegree.setFont(new Font("楷体",Font.BOLD,50));
        Jdegree.setBounds(1200, 1140, 1000, 80);
        this.add(Jdegree);

        btnAdd = new JButton("添加");
        btnAdd.setFont(new Font("楷体",Font.BOLD,50));
        btnAdd.addActionListener(this);
        btnAdd.setBounds(800, 1300, 350, 90);
        this.add(btnAdd);

        btnDel = new JButton("删除");
        btnDel.setFont(new Font("楷体",Font.BOLD,50));
        btnDel.addActionListener(this);
        btnDel.setBounds(1200, 1300, 350, 90);
        this.add(btnDel);

        btnModify = new JButton("修改");
        btnModify.setFont(new Font("楷体",Font.BOLD,50));
        btnModify.addActionListener(this);
        btnModify.setBounds(1600, 1300, 350, 90);
        this.add(btnModify);

        btnQuery = new JButton("查询");
        btnQuery.setFont(new Font("楷体",Font.BOLD,50));
        btnQuery.addActionListener(this);
        btnQuery.setBounds(2000, 1300, 350, 90);
        this.add(btnQuery);

        btnFirst = new JButton("第一个");
        btnFirst.setFont(new Font("楷体",Font.BOLD,50));
        btnFirst.addActionListener(this);
        btnFirst.setBounds(800, 1450, 350, 90);
        this.add(btnFirst);

        btnLast = new JButton("最后一个");
        btnLast.setFont(new Font("楷体",Font.BOLD,50));
        btnLast.addActionListener(this);
        btnLast.setBounds(1200, 1450, 350, 90);
        this.add(btnLast);

        btnBefore = new JButton("上一个");
        btnBefore.setFont(new Font("楷体",Font.BOLD,50));
        btnBefore.setBounds(1600, 1450, 350, 90);
        btnBefore.addActionListener(this);
        this.add(btnBefore);

        btnNext = new JButton("下一个");
        btnNext.setFont(new Font("楷体",Font.BOLD,40));
        btnNext.addActionListener(this);
        btnNext.setBounds(2000, 1450, 350, 90);
        this.add(btnNext);


        this.setTitle("我的题库");
        this.setFont(new Font("宋体",Font.BOLD,50));
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH  );
    }
    public static void main(String[] args)
    {
        new QuestionBank();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("添加"))
        {
            Data data = new Data();
            data.setCommand("add");
            data.setId(Integer.parseInt(JquestionId.getText()));
            data.setText(Jcontent.getText());
            data.setA(JtypeA.getText());
            data.setB(JtypeB.getText());
            data.setC(JtypeC.getText());
            data.setD(JtypeD.getText());
            data.setType(Jtype.getText());
            data.setDegree(Integer.parseInt(Jdegree.getText()));
            data.setAnswer(Jright.getText());
            data.setValue(Integer.parseInt(Jvalue.getText()));
            ClientSS client = new ClientSS();
            try {
                client.ClientStart(data);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            if(ClientSS.getData.isResult()){
                JOptionPane.showMessageDialog(null, "添加成功");
            }else
                JOptionPane.showMessageDialog(null, "添加失败");
//            if (JquestionId.getText().equals("")
//                    || Jcontent.getText().equals("")
//                    || Jright.getText().equals("")
//                    || Jtype.getText().equals("")
//                    || Jvalue.getText().equals("")
//                    || Jdegree.getText().equals(""))
//            {
//                JOptionPane.showMessageDialog(this, "请填写相关信息");
//                return;
//            }
//            else
//            {
//                add();
//                JquestionId.setText("");
//                Jcontent.setText("");
//                Jright.setText("");
//                Jtype.setText("");
//                Jvalue.setText("");
//                Jdegree.setText("");
//                JtypeA.setText("");
//                JtypeB.setText("");
//                JtypeC.setText("");
//                JtypeD.setText("");
//            }
        }
        else if (e.getActionCommand().equals("删除"))
        {
            if (JquestionId.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "请选择一条信息");
            }
            else
            {
                int n = JOptionPane.showConfirmDialog(this, "您确定要删除此条记录吗？");
                if (n == 0)
                {
                    del();
                    JquestionId.setText("");
                    Jcontent.setText("");
                    Jright.setText("");
                    Jtype.setText("");
                    Jvalue.setText("");
                    Jdegree.setText("");
                    JtypeA.setText("");
                    JtypeB.setText("");
                    JtypeC.setText("");
                    JtypeD.setText("");
                }
            }
        }
        else if (e.getActionCommand().equals("修改"))
        {
            if (JquestionId.getText().trim().equals("")
                    || Jcontent.getText().trim().equals("")
                    || Jright.getText().trim().equals("")
                    || Jtype.getText().trim().equals("")
                    || Jvalue.getText().trim().equals("")
                    || Jdegree.getText().trim().equals(""))
            {
                JOptionPane.showMessageDialog(this, "请填写相关信息");
            }
            else
            {
                modify();
            }
        }
        else if (e.getActionCommand().equals("查询"))
        {
            if (JquestionId.getText().trim().equals(""))
            {
                JOptionPane.showMessageDialog(this, "请选择要查询题目的ID");
            }
            else
            {
                query();
            }
        }
        else if (e.getActionCommand().equals("第一个"))
        {
            first();
        }
        else if (e.getActionCommand().equals("最后一个"))
        {
            laster();
        }
        else if (e.getActionCommand().equals("上一个"))
        {
            if (JquestionId.getText().trim().equals(""))
            {
                JOptionPane.showMessageDialog(this, "请您先选择一条题目信息");
            }
            else
            {
                before();
            }
        }
        else if (e.getActionCommand().equals("下一个"))
        {
            if (JquestionId.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "请您先选择一条题目信息");
            }
            else
            {
                next();
            }
        }
    }
//
//    public void add()
//    {
//        dbconn = new DbConnection();
//        conn = dbconn.getConnection();
////        String sql1 = "select questionid from question where questionid="
////                + questionId.getText();
//        String sql = "insert into question (`questionid`,`content`,`typeA`,`typeB`,`typeC`,`typeD`,`right`,`type`,`value`,`degree`) values(?,?,?,?,?,?,?,?,?,?)";
//        try
//        {
//
////            if (!rs.next())
////            {
//            try
//            {
//                pstmt = conn.prepareStatement(sql);
//                pstmt.setInt(1, Integer.parseInt(JquestionId.getText()));
//                pstmt.setString(2, Jcontent.getText());
//                pstmt.setString(3, JtypeA.getText());
//                pstmt.setString(4, JtypeB.getText());
//                pstmt.setString(5, JtypeC.getText());
//                pstmt.setString(6, JtypeD.getText());
//                pstmt.setString(7, Jright.getText());
//                pstmt.setInt(8, Integer.parseInt(Jtype.getText()));
//                pstmt.setInt(9, Integer.parseInt(Jvalue.getText()));
//                pstmt.setInt(10, Integer.parseInt(Jdegree.getText()));
//                pstmt.executeUpdate();
//                JOptionPane.showMessageDialog(this, "添加成功");
//            }
//            catch (SQLException e)
//            {
//                // e.printStackTrace();
//                JOptionPane.showMessageDialog(this, "添加失败");
//            }
//            finally
//            {
//                try
//                {
//                    if (pstmt != null)
//                        pstmt.close();
//                    if (conn != null)
//                        conn.close();
//                }
//                catch (SQLException e)
//                {
//                    e.printStackTrace();
//                }
//            }
////            }
////            else
////            {
////                JOptionPane.showMessageDialog(this, "题目编号重复");
////            }
//        }
//        catch (NumberFormatException e)
//        {
//            e.printStackTrace();
//        }
//        catch (HeadlessException e)
//        {
//            e.printStackTrace();
//        }
//    }

    public void del()
    {
        dbconn = new DbConnection();
        conn = dbconn.getConnection();
        String sql = "delete from question where questionid = "
                + JquestionId.getText();
        try
        {
            pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "删除成功");
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

    public void modify()
    {
        dbconn = new DbConnection();
        conn = dbconn.getConnection();
        String sql1 = "select  questionid from question where questionid="
                + JquestionId.getText();
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql1);
        }
        catch (SQLException e1)
        {
            e1.printStackTrace();
        }
        try
        {
            if (rs.next())
            {
                String sql = "update question set content = ?,typeA = ?,typeB=?,typeC=?,typeD=?,`right`=?,`type`=?,`value`=?,`degree`=? where questionid = "
                        + JquestionId.getText();
                try
                {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, Jcontent.getText());
                    pstmt.setString(2, JtypeA.getText());
                    pstmt.setString(3, JtypeB.getText());
                    pstmt.setString(4, JtypeC.getText());
                    pstmt.setString(5, JtypeD.getText());
                    pstmt.setString(6, Jright.getText());
                    pstmt.setInt(7, Integer.parseInt(Jtype.getText()));
                    pstmt.setInt(8, Integer.parseInt(Jvalue.getText()));
                    pstmt.setInt(9, Integer.parseInt(Jdegree.getText()));
                    pstmt.executeUpdate();
                    JOptionPane.showMessageDialog(this, "修改成功");
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
            else
            {
                JOptionPane.showMessageDialog(this, "没有与此ID相对应的记录");
                JquestionId.setText("");
                Jcontent.setText("");
                Jright.setText("");
                Jtype.setText("");
                Jvalue.setText("");
                Jdegree.setText("");
                JtypeA.setText("");
                JtypeB.setText("");
                JtypeC.setText("");
                JtypeD.setText("");
            }
        }
        catch (HeadlessException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void query()
    {
        dbconn = new DbConnection();
        conn = dbconn.getConnection();
        String sql = "select content,typeA,typeB,typeC,typeD,`right`,`type`,`value`,`degree` from question where questionid="
                + JquestionId.getText();
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next())
            {
                String aaa = rs.getString("content");
                String bbb = rs.getString("typeA");
                String ccc = rs.getString("typeB");
                String ddd = rs.getString("typeC");
                String eee = rs.getString("typeD");
                String fff = rs.getString("right");
                int ggg = rs.getInt("type");
                int hhh = rs.getInt("value");
                int iii = rs.getInt("degree");
                Jcontent.setText(aaa);
                JtypeA.setText(bbb);
                JtypeB.setText(ccc);
                JtypeC.setText(ddd);
                JtypeD.setText(eee);
                Jright.setText(fff);
                Jtype.setText(String.valueOf(ggg));
                Jvalue.setText(String.valueOf(hhh));
                Jdegree.setText(String.valueOf(iii));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "查询失败,您可能没有此信息");
                JquestionId.setText("");
                Jcontent.setText("");
                Jright.setText("");
                Jtype.setText("");
                Jvalue.setText("");
                Jdegree.setText("");
                JtypeA.setText("");
                JtypeB.setText("");
                JtypeC.setText("");
                JtypeD.setText("");
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

    public void first()
    {
        dbconn = new DbConnection();
        conn = dbconn.getConnection();
        String sql = "select * from question";
        try
        {
            // 1.TYPE_FORWORD_ONLY,只可向前滚动；
            // 2.TYPE_SCROLL_INSENSITIVE,双向滚动，但不及时更新，就是如果数据库里的数据修改过，并不在ResultSet中反应出来。
            // 3.TYPE_SCROLL_SENSITIVE，双向滚动，并及时跟踪数据库的更新,以便更改ResultSet中的数据。
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);
            if (rs.first())
            {
                // stmt = conn.createStatement()
                String ooo = rs.getString("questionid");
                String aaa = rs.getString("content");
                String bbb = rs.getString("typeA");
                String ccc = rs.getString("typeB");
                String ddd = rs.getString("typeC");
                String eee = rs.getString("typeD");
                String fff = rs.getString("right");
                int ggg = rs.getInt("type");
                int hhh = rs.getInt("value");
                int iii = rs.getInt("degree");
                JquestionId.setText(ooo);
                Jcontent.setText(aaa);
                JtypeA.setText(bbb);
                JtypeB.setText(ccc);
                JtypeC.setText(ddd);
                JtypeD.setText(eee);
                Jright.setText(fff);
                Jtype.setText(String.valueOf(ggg));
                Jvalue.setText(String.valueOf(hhh));
                Jdegree.setText(String.valueOf(iii));
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

    public void laster()
    {
        dbconn = new DbConnection();
        conn = dbconn.getConnection();
        String sql = "select * from question";
        try
        {
            // 1.TYPE_FORWORD_ONLY,只可向前滚动；
            // 2.TYPE_SCROLL_INSENSITIVE,双向滚动，但不及时更新，就是如果数据库里的数据修改过，并不在ResultSet中反应出来。
            // 3.TYPE_SCROLL_SENSITIVE，双向滚动，并及时跟踪数据库的更新,以便更改ResultSet中的数据。
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);
            if (rs.last())
            {
                String ooo = rs.getString("questionId");
                String aaa = rs.getString("content");
                String bbb = rs.getString("typeA");
                String ccc = rs.getString("typeB");
                String ddd = rs.getString("typeC");
                String eee = rs.getString("typeD");
                String fff = rs.getString("right");
                int ggg = rs.getInt("type");
                int hhh = rs.getInt("value");
                int iii = rs.getInt("degree");
                JquestionId.setText(ooo);
                Jcontent.setText(aaa);
                JtypeA.setText(bbb);
                JtypeB.setText(ccc);
                JtypeC.setText(ddd);
                JtypeD.setText(eee);
                Jright.setText(fff);
                Jtype.setText(String.valueOf(ggg));
                Jvalue.setText(String.valueOf(hhh));
                Jdegree.setText(String.valueOf(iii));
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

    public void before()
    {
        ArrayList list = new ArrayList();
        int index = 0;
        dbconn = new DbConnection();
        conn = dbconn.getConnection();
        String sql = "select * from question";
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                Question p = new Question();
                p.setQuestionId(rs.getInt(1));
                p.setContent(rs.getString(2));
                p.setTypeA(rs.getString(3));
                p.setTypeB(rs.getString(4));
                p.setTypeC(rs.getString(5));
                p.setTypeD(rs.getString(6));
                p.setRight(rs.getString(7));
                p.setType(rs.getInt(8));
                p.setValue(rs.getInt(9));
                p.setDegree(rs.getInt(10));

                list.add(p);
            }

            int id = Integer.parseInt(JquestionId.getText());
            for (int i = 0; i < list.size(); i++)
            {
                Question pr = (Question) list.get(i);
                if (pr.getQuestionId() == id)
                {
                    index = i;
                    break;
                }
            }
            if (index >= 1)
            {
                Question pro = (Question) list.get(--index);
                String s = String.valueOf(pro.getQuestionId());
                String g = String.valueOf(pro.getType());
                String h = String.valueOf(pro.getValue());
                String i = String.valueOf(pro.getDegree());
                JquestionId.setText(s);
                Jcontent.setText(pro.getContent());
                JtypeA.setText(pro.getTypeA());
                JtypeB.setText(pro.getTypeB());
                JtypeC.setText(pro.getTypeC());
                JtypeD.setText(pro.getTypeD());
                Jright.setText(pro.getRight());
                Jtype.setText(g);
                Jvalue.setText(h);
                Jdegree.setText(i);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "已经是第一条记录");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void next()
    {
        ArrayList list = new ArrayList();
        int index = 0;
        dbconn = new DbConnection();
        conn = dbconn.getConnection();
        String sql = "select * from question";
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                Question p = new Question();
                p.setQuestionId(rs.getInt(1));
                p.setContent(rs.getString(2));
                p.setTypeA(rs.getString(3));
                p.setTypeB(rs.getString(4));
                p.setTypeC(rs.getString(5));
                p.setTypeD(rs.getString(6));
                p.setRight(rs.getString(7));
                p.setType(rs.getInt(8));
                p.setValue(rs.getInt(9));
                p.setDegree(rs.getInt(10));
                list.add(p);
            }

            int id = Integer.parseInt(JquestionId.getText());
            for (int i = 0; i < list.size(); i++)
            {
                Question pr = (Question) list.get(i);
                if (pr.getQuestionId() == id)
                {
                    index = i;
                    break;
                }
            }
            if (index < list.size() - 1)
            {
                Question pro = (Question) list.get(++index);
                String s = String.valueOf(pro.getQuestionId());
                String g = String.valueOf(pro.getType());
                String h = String.valueOf(pro.getValue());
                String i = String.valueOf(pro.getDegree());
                JquestionId.setText(s);
                Jcontent.setText(pro.getContent());
                JtypeA.setText(pro.getTypeA());
                JtypeB.setText(pro.getTypeB());
                JtypeC.setText(pro.getTypeC());
                JtypeD.setText(pro.getTypeD());
                Jright.setText(pro.getRight());
                Jtype.setText(g);
                Jvalue.setText(h);
                Jdegree.setText(i);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "已经是最后一条记录");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }






}