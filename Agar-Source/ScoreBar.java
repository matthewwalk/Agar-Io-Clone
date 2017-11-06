/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Graphics2D;
/*    */ import java.awt.geom.Rectangle2D;
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
/*    */ public class ScoreBar
/*    */ {
/*    */   int score;
/* 22 */   private final float alpha = 0.75F;
/*    */   private final Color transparentLightGray;
/*    */   private final Color transparentWhite;
/*    */   private final Font f;
/*    */   
/* 27 */   ScoreBar() { this.score = 0;
/* 28 */     this.transparentLightGray = new Color(0.75F, 0.75F, 0.75F, 0.75F);
/* 29 */     this.transparentWhite = new Color(0.95F, 0.95F, 0.95F, 0.9F);
/* 30 */     this.f = new Font("Arial", 1, 55);
/*    */   }
/*    */   
/*    */ 
/*    */   void updateScore(int r)
/*    */   {
/* 36 */     this.score = ((int)(3.141592653589793D * (r * r)));
/* 37 */     this.score /= 150;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   void drawbar(Graphics g)
/*    */   {
/* 44 */     g.setColor(this.transparentLightGray);
/* 45 */     g.fillRoundRect(10, 490, 100, 100, 20, 20);
/*    */     
/*    */ 
/* 48 */     g.setColor(this.transparentWhite);
/* 49 */     g.setFont(this.f);
/* 50 */     Graphics2D g2d = (Graphics2D)g;
/* 51 */     int stringLen = (int)g2d.getFontMetrics().getStringBounds(Integer.toString(this.score), g2d).getWidth();
/* 52 */     int start = 50 - stringLen / 2;
/*    */     
/*    */ 
/* 55 */     g.drawString(Integer.toString(this.score), start + 10, 560);
/*    */   }
/*    */ }


/* Location:              C:\Users\User\Desktop\Robotics\Agar - Walker\bin\LaunchGame.jar!\ScoreBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */