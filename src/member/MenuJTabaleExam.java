package member;

import java.awt.BorderLayout;
//MenuJTabaleExam.java
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;



public class MenuJTabaleExam extends JFrame implements ActionListener {
	
	static JLabel txt;
  JMenu m = new JMenu("회원");
  JMenuItem insert = new JMenuItem("가입");
  JMenuItem update = new JMenuItem("수정");
  JMenuItem delete = new JMenuItem("삭제");
  JMenuItem quit = new JMenuItem("종료");

  
  JMenu sta = new JMenu("통계");
  JMenuItem age = new JMenuItem("나이");
  JMenuItem gender = new JMenuItem("성별");

  JMenuBar mb = new JMenuBar();
  
  String[] name = { "아이디", "이름", "나이", "성별","주소" };

  DefaultTableModel dt = new DefaultTableModel(name, 0);
  JTable jt = new JTable(dt);

  JScrollPane jsp = new JScrollPane(jt);
  

  

  /*
   * South 영역에 추가할 Componet들
   */
  JPanel p = new JPanel();
  String[] comboName = { "  전체  ", "  아이디  "};
  Font font = new Font("맑은고딕", Font.PLAIN, 30);
  JComboBox combo = new JComboBox(comboName);
  JTextField jtf = new JTextField(18);
  JButton serach = new JButton("검색");

  UserDefaultJTableDAO dao = new UserDefaultJTableDAO();

  /**
   * 화면구성 및 이벤트등록
   */
  public MenuJTabaleExam() {
     
      super("회원관리프로그램");

      //메뉴아이템을 메뉴에 추가
      m.add(insert);
      m.add(update);
      m.add(delete);
      m.add(quit);
      //메뉴를 메뉴바에 추가
      mb.add(m);
     
      //윈도우에 메뉴바 세팅
      setJMenuBar(mb);

      //메뉴아이템을 메뉴에 추가
      sta.add(age);
      sta.add(gender);

      //메뉴를 메뉴바에 추가
      mb.add(sta);
     
      //윈도우에 메뉴바 세팅
      setJMenuBar(mb);
      
      // South영역
      p.setBackground(Color.white);
      p.add(combo);
      p.add(jtf);
      p.add(serach);

      add(jsp, "Center");
      add(p, "South");

      setSize(900, 600);
      
      setVisible(true);

      super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      txt = new JLabel("");
      // 이벤트등록
      insert.addActionListener(this);
      update.addActionListener(this);
      delete.addActionListener(this);
      quit.addActionListener(this);
      age.addActionListener(this);
      gender.addActionListener(this);
      serach.addActionListener(this);

      // 모든레코드를 검색하여 DefaultTableModle에 올리기
      dao.userSelectAll(dt);
     
      //첫번행 선택.
      if (dt.getRowCount() > 0)
          jt.setRowSelectionInterval(0, 0);

  }// 생성자끝

  /**
   * main메소드 작성
   */
  public static void main(String[] args) {
      new MenuJTabaleExam();
  }


  private void showStatistics(String category) {
	    if (category.equals("age")) {
	        GraphClass statisticsDialog = new GraphClass(this, "나이 통계");
	        statisticsDialog.setSize(800,600);
	        statisticsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        statisticsDialog.setLocationRelativeTo(this);
	        statisticsDialog.setVisible(true);
	    }else if((category.equals("gender"))){
	    	GenderGraph statisticsDialog = new GenderGraph(this, "성별 통계");
	        statisticsDialog.setSize(500,600);
	        statisticsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	        statisticsDialog.setLocationRelativeTo(this);
	        statisticsDialog.setVisible(true);
	    }
	}

  /**
   * 가입/수정/삭제/검색기능을 담당하는 메소드
   * */

  public void actionPerformed(ActionEvent e) {
      if (e.getSource() == insert) {// 가입 메뉴아이템 클릭
          new UserJDailogGUI(this, "가입");

      } else if (e.getSource() == update) {// 수정 메뉴아이템 클릭
          new UserJDailogGUI(this, "수정");

      } 
      else if (e.getSource() == age) {
          // 나이 통계 메뉴 아이템 클릭
    	  showStatistics("age");
      
      } else if (e.getSource() == gender) {
          // 나이 통계 메뉴 아이템 클릭
    	  showStatistics("gender");
      
      } 
      else if (e.getSource() == delete) {// 삭제 메뉴아이템 클릭
          // 현재 Jtable의 선택된 행과 열의 값을 얻어온다.
          int row = jt.getSelectedRow();
          System.out.println("선택행 : " + row);

          Object obj = jt.getValueAt(row, 0);// 행 열에 해당하는 value
          System.out.println("값 : " + obj);

          if (dao.userDelete(obj.toString()) > 0) {
              UserJDailogGUI.messageBox(this, "레코드 삭제되었습니다.");
             
              //리스트 갱신
              dao.userSelectAll(dt);
              if (dt.getRowCount() > 0)
                  jt.setRowSelectionInterval(0, 0);

          } else {
              UserJDailogGUI.messageBox(this, "레코드가 삭제되지 않았습니다.");
          }

      } else if (e.getSource() == quit) {// 종료 메뉴아이템 클릭
          System.exit(0);

      } else if (e.getSource() == serach) {// 검색 버튼 클릭
          // JComboBox에 선택된 value 가져오기
          String fieldName = combo.getSelectedItem().toString();
          System.out.println("필드명 " + fieldName);

          if (fieldName.trim().equals("ALL")) {// 전체검색
              dao.userSelectAll(dt);
              if (dt.getRowCount() > 0)
                  jt.setRowSelectionInterval(0, 0);
          } else {
              if (jtf.getText().trim().equals("")) {
                  UserJDailogGUI.messageBox(this, "검색단어를 입력해주세요!");
                  jtf.requestFocus();
              } else {// 검색어를 입력했을경우
                  dao.getUserSearch(dt, fieldName, jtf.getText());
                  if (dt.getRowCount() > 0)
                      jt.setRowSelectionInterval(0, 0);
              }
          }
      }

  }//actionPerformed()----------

}
