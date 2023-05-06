package InquireScore;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class ChartBefore extends JPanel {
    private MyTable table;
    private JScrollPane scrollPane;
    private MyButton[] buttons;
    public static int examid;
    public ChartBefore() {
        init();
    }
    private void init() {
        String headName[] = { "编号", "试卷","难度", "截止时间", "考试时长","操作1","操作2" };
        //查询答题结果表显示数据

        DbConnection dbConnection = new DbConnection();
        Connection conn = dbConnection.getConnection();
        String sql = "select * from exam" ;
        ArrayList<Object[]> data = new ArrayList();
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()){
                Object tmp[] = {
                        rs.getInt("examid"),
                        rs.getString("examname"),
                        rs.getString("examdegree"),
                        rs.getString("deadline"),
                        rs.getString("examinationTime"),
                        new MyButton("柱状图",rs.getInt("examid")),
                        new MyButton("排名",rs.getInt("examid"))
                };
                data.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Object object[][] = new Object[data.size()][6];
        for(int i=0;i<data.size();i++){
            Object o[] = data.get(i);
            object[i] = o;
        }

       /* Object obj[][] = {
                {"LiMing", 23, 12,14,14,15,16,11, buttons[0]},
        };*/
        /*for(int i=0;i<buttons.length;i++){
            buttons[i].setID(object[i][0].toString());
        }*/


        table = new MyTable(new MyTableModel(headName,object));
        table.getTableHeader().setPreferredSize(new Dimension(1, 65));
        table.getTableHeader().setFont(new Font("宋体",Font.BOLD,40));
        table.setRowHeight(65);
        table.setDefaultRenderer(MyButton.class, new ComboBoxCellRenderer2());
        table.setFont(new Font("宋体",Font.BOLD,40));

            table.getTableHeader().setBackground(Color.PINK);



        scrollPane = new JScrollPane(table);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        addHandler();
        JFrame frame = new JFrame();
        frame.add(this);
        frame.setSize(new Dimension(1200, 800));
        frame.setVisible(true);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH  );

    }
    private void addHandler(){
//添加事件
        table.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                System.out.println("row="+row+":"+"column="+column);
                if(column==5){
                    //处理button事件写在这里...
                    int id = ((MyButton)table.getValueAt(row, column)).getID();
                    examid=id;
                    System.out.println(examid);
                    new BarChartDemo();
                }
                if(column==6){
                    //处理button事件写在这里...
                    int id = ((MyButton)table.getValueAt(row, column)).getID();
                    examid=id;
                    System.out.println(examid);
                    new SelectF();
                }
            }
        });
    }
    public static void main(String[] args) {
        new ChartBefore();
    }
    class MyButton extends JButton{
        private int id;
        public MyButton(String text) {
            super(text);
        }
        public MyButton(String text,int id) {
            super(text);
            this.id=id;
        }
        public int getID(){
            return id;
        }
        public void setID(int id){
            this.id = id;
        }
    }
    class MyTable extends JTable{
        public MyTable(Object[][] rowData, Object[] columnNames) {
            super(rowData, columnNames);
        }

        public MyTable(TableModel dm) {
            super(dm);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
//        public void setLength(int aa){
//
//        }
    }
    class MyTableModel extends AbstractTableModel {

        private String headName[];
        private Object obj[][];
        public MyTableModel() {
            super();
        }
        public MyTableModel(String[] headName, Object[][] obj) {
            this();

            this.headName = headName;
            this.obj = obj;

        }

        public int getColumnCount() {
            return headName.length;
        }
        public int getRowCount() {
            return obj.length;
        }
        public Object getValueAt(int r, int c) {
            return obj[r][c];
        }
        public String getColumnName(int c) {
            return headName[c];
        }
        public Class<?> getColumnClass(int columnIndex) {
            return obj[0][columnIndex].getClass();
        }
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            if (columnIndex == 3 || columnIndex == 4) {
                return false;
            }
            return true;
        }

    }
}
class ComboBoxCellRenderer2 implements TableCellRenderer {
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        JButton cmb = (JButton) value;
        if (isSelected) {
            cmb.setForeground(table.getSelectionForeground());
            cmb.setBackground(table.getSelectionBackground());
        } else {
            cmb
                    .setForeground((unselectedForeground != null) ? unselectedForeground
                            : table.getForeground());
            cmb
                    .setBackground((unselectedBackground != null) ? unselectedBackground
                            : table.getBackground());
        }
        cmb.setFont(table.getFont());
        if (hasFocus) {
            cmb
                    .setBorder(UIManager
                            .getBorder("Table.focusCellHighlightBorder"));
            if (!isSelected && table.isCellEditable(row, column)) {
                Color col;
                col = UIManager.getColor("Table.focusCellForeground");
                if (col != null) {
                    cmb.setForeground(col);
                }
                col = UIManager.getColor("Table.focusCellBackground");
                if (col != null) {
                    cmb.setBackground(col);
                }
            }
        } else {
            cmb.setBorder(noFocusBorder);
        }
        return cmb;
    }
    protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
    private Color unselectedForeground;
    private Color unselectedBackground;
}
