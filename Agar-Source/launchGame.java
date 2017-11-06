/*    */ import javax.swing.JFrame;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class launchGame
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 20 */     Agar applet = new Agar();
/* 21 */     applet.init();
/*    */     
/* 23 */     JFrame frame = new JFrame("Agar Dots");
/* 24 */     frame.setDefaultCloseOperation(3);
/* 25 */     frame.add(applet);
/* 26 */     frame.pack();
/* 27 */     frame.setLocationRelativeTo(null);
/* 28 */     frame.setVisible(true);
/* 29 */     frame.setSize(1000, 621);
/* 30 */     frame.setLocation(0, 0);
/* 31 */     applet.start();
/*    */   }
/*    */ }


/* Location:              C:\Users\User\Desktop\Robotics\Agar - Walker\bin\LaunchGame.jar!\launchGame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */