package makePaper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.*;


public class TestListList extends JFrame implements ActionListener
{

    private JLabel questionId;
    private JLabel content;
    private JLabel typeA;
    private JLabel typeB;

    private JComboBox hehe;

    private JTextField JquestionId;
    private JTextField Jcontent;
    private JTextField JtypeA;
    private JTextField JtypeB;

    private JButton btnFirst;
    private JButton btnLast;
    private JButton btnBefore;
    private JButton btnNext;
    private JButton btnAdd;
    private JButton btnModify;
    private JButton btnDel;
    private JButton btnQuery;
    private JButton gogo;

    private DbConnection dbconn = null;
    private Connection conn = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    private Statement stmt = null;

    static int examid;
    public TestListList()
    {

        this.setLayout(null);
//        this.setBounds(800, 700, 900, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
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

        questionId = new JLabel("试卷编号");
        questionId.setBounds(700, 480, 400, 100);
        this.add(questionId);
        questionId.setFont(new Font("宋体",Font.BOLD,60));

        JquestionId = new JTextField();
        JquestionId.setFont(new Font("楷体",Font.BOLD,60));
        JquestionId.setBounds(1000, 480, 1400, 100);
        this.add(JquestionId);

        content = new JLabel("试卷名称");
        content.setBounds(700, 620, 400, 100);
        this.add(content);
        content.setFont(new Font("宋体",Font.BOLD,60));

        Jcontent = new JTextField();
        Jcontent.setFont(new Font("楷体",Font.BOLD,60));
        Jcontent.setBounds(1000, 620, 1400, 100);
        this.add(Jcontent);

        typeA = new JLabel("截止日期");
        typeA.setBounds(700, 760, 400, 100);
        this.add(typeA);
        typeA.setFont(new Font("宋体",Font.BOLD,60));

        JtypeA = new JTextField();
        JtypeA.setFont(new Font("楷体",Font.BOLD,60));
        JtypeA.setBounds(1000, 760, 1400, 100);
        this.add(JtypeA);

        typeB = new JLabel("考试时长");
        typeB.setBounds(700, 900, 400, 100);
        this.add(typeB);
        typeB.setFont(new Font("宋体",Font.BOLD,60));

        JtypeB = new JTextField();
        JtypeB.setFont(new Font("楷体",Font.BOLD,60));
        JtypeB.setBounds(1000, 900, 1400, 100);
        this.add(JtypeB);


        gogo = new JButton("自动组卷");
        gogo.setFont(new Font("楷体",Font.BOLD,50));
        gogo.addActionListener(this);
        gogo.setBounds(850, 1400, 800, 80);
        this.add(gogo);

        String[] schoolage = { "简单", "中等", "困难" };
        hehe = new JComboBox(schoolage);
        hehe.addItem("其他");
        hehe.insertItemAt("请选择", 0);
        hehe.setSelectedIndex(0);
        hehe.setFont(new Font("楷体",Font.BOLD,50));
        hehe.addActionListener(this);
        hehe.setBounds(1700, 1400, 300, 80);
        this.add(hehe);



        btnAdd = new JButton("添加");
        btnAdd.setFont(new Font("楷体",Font.BOLD,50));
        btnAdd.addActionListener(this);
        btnAdd.setBounds(800, 1100, 350, 90);
        this.add(btnAdd);

        btnDel = new JButton("删除");
        btnDel.setFont(new Font("楷体",Font.BOLD,50));
        btnDel.addActionListener(this);
        btnDel.setBounds(1200, 1100, 350, 90);
        this.add(btnDel);

        btnModify = new JButton("修改");
        btnModify.setFont(new Font("楷体",Font.BOLD,50));
        btnModify.addActionListener(this);
        btnModify.setBounds(1600, 1100, 350, 90);
        this.add(btnModify);

        btnQuery = new JButton("查询");
        btnQuery.setFont(new Font("楷体",Font.BOLD,50));
        btnQuery.addActionListener(this);
        btnQuery.setBounds(2000, 1100, 350, 90);
        this.add(btnQuery);

        btnFirst = new JButton("第一个");
        btnFirst.setFont(new Font("楷体",Font.BOLD,50));
        btnFirst.addActionListener(this);
        btnFirst.setBounds(800, 1250, 350, 90);
        this.add(btnFirst);

        btnLast = new JButton("最后一个");
        btnLast.setFont(new Font("楷体",Font.BOLD,50));
        btnLast.addActionListener(this);
        btnLast.setBounds(1200, 1250, 350, 90);
        this.add(btnLast);

        btnBefore = new JButton("上一个");
        btnBefore.setFont(new Font("楷体",Font.BOLD,50));
        btnBefore.setBounds(1600, 1250, 350, 90);
        btnBefore.addActionListener(this);
        this.add(btnBefore);

        btnNext = new JButton("下一个");
        btnNext.setFont(new Font("楷体",Font.BOLD,40));
        btnNext.addActionListener(this);
        btnNext.setBounds(2000, 1250, 350, 90);
        this.add(btnNext);

        this.setTitle("我的试卷库");
        this.setFont(new Font("宋体",Font.BOLD,50));
        this.setResizable(true);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH  );

    }
    public static void main(String[] args)
    {
        new TestListList();
    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("添加"))
        {
            examid= Integer.parseInt(JquestionId.getText());
            if (JquestionId.getText().equals("")
                    || Jcontent.getText().equals("")
                    )
            {
                JOptionPane.showMessageDialog(this, "请填写相关信息");
                return;
            }
            else
            {

                add();
                new MainFrame();
                JquestionId.setText("");
                Jcontent.setText("");

                JtypeA.setText("");
                JtypeB.setText("");

            }
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

                    JtypeA.setText("");
                    JtypeB.setText("");

                }
            }
        }
        else if (e.getActionCommand().equals("修改"))
        {
            if (JquestionId.getText().trim().equals("")
                    || Jcontent.getText().trim().equals("")
                )
            {
                JOptionPane.showMessageDialog(this, "请填写相关信息");
            }
            else
            {
                examid= Integer.parseInt(JquestionId.getText());
                modify();
                new MainFrame();
            }
        }
        else if (e.getActionCommand().equals("查询"))
        {
            if (JquestionId.getText().trim().equals(""))
            {
                JOptionPane.showMessageDialog(this, "请选择要查询试卷的ID");
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
                JOptionPane.showMessageDialog(this, "请您先选择一条试卷信息");
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
                JOptionPane.showMessageDialog(this, "请您先选择一条试卷信息");
            }
            else
            {
                next();
            }
        }
        else if (e.getActionCommand().equals("自动组卷")){
            examid= Integer.parseInt(JquestionId.getText());
            add();
        }
    }

    public void add()
    {
        dbconn = new DbConnection();
        conn = dbconn.getConnection();
//        String sql1 = "select questionid from question where questionid="
//                + questionId.getText();
        String sql = "insert into exam (`examid`,`examname`,`deadline`,`examinationTime`) values(?,?,?,?)";
        try
        {

//            if (!rs.next())
//            {
            try
            {
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, Integer.parseInt(JquestionId.getText()));
                pstmt.setString(2, Jcontent.getText());
                pstmt.setString(3, JtypeA.getText());
                pstmt.setString(4, JtypeB.getText());
                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "添加成功");
            }
            catch (SQLException e)
            {
                // e.printStackTrace();
                JOptionPane.showMessageDialog(this, "添加失败");
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
//            }
//            else
//            {
//                JOptionPane.showMessageDialog(this, "题目编号重复");
//            }
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch (HeadlessException e)
        {
            e.printStackTrace();
        }
    }

    public void del()
    {
        dbconn = new DbConnection();
        conn = dbconn.getConnection();
        String sql = "delete from exam where examid = "
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
        String sql1 = "select  examid from exam where examid="
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
                String sql = "update exam set examname = ?,deadline = ?,examinationTime=? where examid = "
                        + JquestionId.getText();
                try
                {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1, Jcontent.getText());
                    pstmt.setString(2, JtypeA.getText());
                    pstmt.setString(3, JtypeB.getText());

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
                JtypeA.setText("");
                JtypeB.setText("");

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
        String sql = "select `examname`,`deadline`,`examinationTime` from exam where examid="
                + JquestionId.getText();
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if (rs.next())
            {
                String aaa = rs.getString("examname");
                String bbb = rs.getString("deadline");
                String ccc = rs.getString("examinationTime");
                Jcontent.setText(aaa);
                JtypeA.setText(bbb);
                JtypeB.setText(ccc);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "查询失败,您可能没有此信息");
                JquestionId.setText("");
                Jcontent.setText("");

                JtypeA.setText("");
                JtypeB.setText("");

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
        String sql = "select * from exam";
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
                int ooo = rs.getInt("examid");
                String aaa = rs.getString("examname");
                String bbb = rs.getString("deadline");
                String ccc = rs.getString("examinationTime");

                JquestionId.setText(String.valueOf(ooo));
                Jcontent.setText(aaa);
                JtypeA.setText(bbb);
                JtypeB.setText(ccc);

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
        String sql = "select * from exam";
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
                int ooo = rs.getInt("examid");
                String aaa = rs.getString("examname");
                String bbb = rs.getString("deadline");
                String ccc = rs.getString("examinationTime");

                JquestionId.setText(String.valueOf(ooo));
                Jcontent.setText(aaa);
                JtypeA.setText(bbb);
                JtypeB.setText(ccc);

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
        String sql = "select * from exam";
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                Exam p = new Exam();
                p.setQuestionId(rs.getInt(1));
                p.setContent(rs.getString(2));
                p.setTypeA(rs.getString(4));
                p.setTypeB(rs.getString(3));

                list.add(p);
            }

            int id = Integer.parseInt(JquestionId.getText());
            for (int i = 0; i < list.size(); i++)
            {
                Exam pr = (Exam) list.get(i);
                if (pr.getQuestionId() == id)
                {
                    index = i;
                    break;
                }
            }
            if (index >= 1)
            {
                Exam pro = (Exam) list.get(--index);
                String s = String.valueOf(pro.getQuestionId());

                JquestionId.setText(s);
                Jcontent.setText(pro.getContent());
                JtypeA.setText(pro.getTypeA());
                JtypeB.setText(pro.getTypeB());

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
        String sql = "select * from exam";
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next())
            {
                Exam p = new Exam();
                p.setQuestionId(rs.getInt(1));
                p.setContent(rs.getString(2));
                p.setTypeA(rs.getString(4));
                p.setTypeB(rs.getString(3));
                list.add(p);
            }

            int id = Integer.parseInt(JquestionId.getText());
            for (int i = 0; i < list.size(); i++)
            {
                Exam pr = (Exam) list.get(i);
                if (pr.getQuestionId() == id)
                {
                    index = i;
                    break;
                }
            }
            if (index < list.size() - 1)
            {
                Exam pro = (Exam) list.get(++index);
                String s = String.valueOf(pro.getQuestionId());

                JquestionId.setText(s);
                Jcontent.setText(pro.getContent());
                JtypeA.setText(pro.getTypeA());
                JtypeB.setText(pro.getTypeB());

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
