/*     */ import java.applet.Applet;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Agar
/*     */   extends Applet
/*     */   implements Runnable, KeyListener
/*     */ {
/*  24 */   int typeCounter = 0;
/*     */   
/*     */ 
/*     */   private Thread th;
/*     */   
/*     */   static boolean menu;
/*     */   
/*  31 */   public static boolean dead = true;
/*     */   
/*     */   public static Player agio;
/*     */   
/*     */   static Enemy[] enemyArray;
/*     */   
/*     */   private Image dbImage;
/*     */   
/*     */   private Graphics dbg;
/*     */   
/*     */   static ScoreBar sB;
/*     */   static LeaderBoard lB;
/*     */   Color c3;
/*     */   Color c2;
/*     */   Font f;
/*     */   Font f2;
/*     */   String enteredName;
/*     */   StringBuilder entName;
/*     */   
/*     */   public void init()
/*     */   {
/*  52 */     float alpha = 0.4F;
/*  53 */     int type = 3;
/*  54 */     AlphaComposite composite = AlphaComposite.getInstance(type, alpha);
/*  55 */     this.c3 = new Color(0.05F, 0.05F, 0.05F, alpha);
/*  56 */     this.c2 = new Color(0.95F, 0.95F, 0.95F, 0.9F);
/*  57 */     this.f = new Font("Arial", 1, 35);
/*  58 */     this.f2 = new Font("Arial", 1, 17);
/*     */     
/*  60 */     this.enteredName = "";
/*     */     
/*  62 */     sB = new ScoreBar();
/*  63 */     menu = true;
/*  64 */     enemyArray = new Enemy[25];
/*  65 */     agio = new Player();
/*  66 */     lB = new LeaderBoard();
/*     */     
/*  68 */     setBackground(Color.WHITE);
/*  69 */     resize(1000, 600);
/*  70 */     addKeyListener(this);
/*     */   }
/*     */   
/*     */ 
/*     */   public void start()
/*     */   {
/*  76 */     this.th = new Thread(this);
/*  77 */     this.th.start();
/*     */   }
/*     */   
/*     */ 
/*     */   public void stop()
/*     */   {
/*  83 */     this.th.stop();
/*     */   }
/*     */   
/*     */ 
/*     */   public void destroy()
/*     */   {
/*  89 */     this.th.stop();
/*     */   }
/*     */   
/*     */ 
/*     */   public void run()
/*     */   {
/*  95 */     Thread.currentThread().setPriority(1);
/*  96 */     IsKeyPressed.addActionListener();
/*     */     for (;;) {
/*  98 */       if (menu) {
/*  99 */         if (dead) {
/* 100 */           IsKeyPressed.enterPressed = false;
/* 101 */           this.enteredName = "";
/* 102 */           if (sB.score > 0)
/* 103 */             lB.addScore(sB.score, agio.name);
/* 104 */           sB.score = 0;
/* 105 */           dead = false;
/*     */         }
/*     */         
/* 108 */         if (IsKeyPressed.enterPressed) {
/* 109 */           for (int i = 0; i < enemyArray.length; i++) {
/* 110 */             agio = null;
/* 111 */             agio = new Player();
/*     */             
/*     */ 
/* 114 */             agio.setName(this.enteredName);
/* 115 */             enemyArray[i] = null;
/* 116 */             enemyArray[i] = new Enemy(enemyArray, sB);
/*     */           }
/* 118 */           menu = false;
/*     */         }
/*     */       }
/*     */       
/* 122 */       agio.control();
/* 123 */       agio.sizeUpdate();
/*     */       
/* 125 */       for (int i = 0; i < enemyArray.length; i++) {
/* 126 */         if (enemyArray[i] == null)
/* 127 */           enemyArray[i] = new Enemy(enemyArray, sB);
/* 128 */         if (enemyArray[i] != null)
/*     */         {
/* 130 */           if ((enemyArray[i].radius > agio.radius) && (!menu) && 
/* 131 */             (agio.isCloseToEnemy(enemyArray[i]))) {
/* 132 */             enemyArray[i].suck();
/*     */           }
/* 134 */           if ((enemyArray[i].X_POS >= 1000 + enemyArray[i].radius + 600) || (enemyArray[i].X_POS <= -enemyArray[i].radius - 600) || (enemyArray[i].Y_POS <= -enemyArray[i].radius - 600) || (enemyArray[i].Y_POS >= 600 + enemyArray[i].radius + 600)) {
/* 135 */             enemyArray[i] = null;
/* 136 */             enemyArray[i] = new Enemy(enemyArray, sB);
/*     */           }
/*     */           
/* 139 */           enemyArray[i].move();
/*     */           
/* 141 */           if ((agio.isTouchingEnemy(enemyArray[i])) && (!menu)) {
/* 142 */             enemyArray[i].suck();
/*     */           }
/*     */         }
/*     */       }
/* 146 */       repaint();
/*     */       try
/*     */       {
/* 149 */         Thread.sleep(15L);
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {}
/* 152 */       Thread.currentThread().setPriority(10);
/*     */     }
/*     */   }
/*     */   
/* 156 */   public boolean shiftPressed = false;
/*     */   
/*     */   public void keyPressed(KeyEvent e)
/*     */   {
/* 160 */     char key = e.getKeyChar();
/* 161 */     int keycode = e.getKeyCode();
/* 162 */     if (menu) {
/* 163 */       if ((keycode == 8) && (this.enteredName.length() > 0)) {
/* 164 */         this.enteredName = this.enteredName.substring(0, this.enteredName.length() - 1);
/* 165 */       } else if (keycode == 16) {
/* 166 */         this.shiftPressed = true;
/* 167 */       } else if ((this.enteredName.length() < 17) && (!this.shiftPressed) && (keycode != 38) && (keycode != 40) && (keycode != 37) && (keycode != 39)) {
/* 168 */         this.enteredName += key;
/* 169 */       } else if ((this.enteredName.length() < 17) && (keycode != 38) && (keycode != 40) && (keycode != 37) && (keycode != 39)) {
/* 170 */         this.enteredName += (char)keycode;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public void keyReleased(KeyEvent e) {
/* 176 */     char key = e.getKeyChar();
/* 177 */     int keycode = e.getKeyCode();
/*     */     
/* 179 */     if (keycode == 16) {
/* 180 */       this.shiftPressed = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public void update(Graphics g)
/*     */   {
/* 187 */     if (this.dbImage == null) {
/* 188 */       this.dbImage = createImage(getSize().width, getSize().height);
/* 189 */       this.dbg = this.dbImage.getGraphics();
/*     */     }
/*     */     
/* 192 */     this.dbg.setColor(getBackground());
/* 193 */     this.dbg.fillRect(0, 0, getSize().width, getSize().height);
/*     */     
/* 195 */     this.dbg.setColor(getForeground());
/* 196 */     paint(this.dbg);
/*     */     
/* 198 */     g.drawImage(this.dbImage, 0, 0, this);
/*     */   }
/*     */   
/*     */   static void gameOver()
/*     */   {
/* 203 */     menu = true;
/* 204 */     dead = true;
/*     */   }
/*     */   
/*     */ 
/*     */   int size(int length, int width, int N)
/*     */   {
/* 210 */     if ((length % N == 0) && (width % N == 0)) {
/* 211 */       return N;
/*     */     }
/* 213 */     return size(length, width, N - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g)
/*     */   {
/* 220 */     Color c = new Color(235, 235, 235);
/* 221 */     g.setColor(c);
/*     */     
/* 223 */     int width = 1000;
/* 224 */     int height = 600;
/* 225 */     int j = size(width, height, 25);
/* 226 */     int inc = j;
/* 227 */     for (int i = 0; i < width / j; i++) {
/* 228 */       g.drawLine(0 + inc, 0, 0 + inc, height);
/* 229 */       inc += size(width, height, 25);
/*     */     }
/* 231 */     inc = j;
/* 232 */     for (int k = 0; k < height / j; k++) {
/* 233 */       g.drawLine(0, 0 + inc, width, 0 + inc);
/* 234 */       inc += size(width, height, 25);
/*     */     }
/*     */     Enemy[] arrayOfEnemy;
/* 237 */     int j = (arrayOfEnemy = enemyArray).length; for (int i = 0; i < j; i++) { Enemy enemyArray1 = arrayOfEnemy[i];
/* 238 */       if (enemyArray1 != null) {
/* 239 */         enemyArray1.drawEnemy(g);
/*     */       }
/*     */     }
/* 242 */     if (!menu) {
/* 243 */       agio.drawPlayer(g);
/*     */     }
/* 245 */     sB.drawbar(g);
/* 246 */     lB.drawLeaderBoard(g);
/*     */     
/* 248 */     if (menu) {
/* 249 */       g.setColor(this.c3);
/* 250 */       g.fillRect(0, 0, 1000, 600);
/*     */       
/* 252 */       g.setColor(Color.white);
/* 253 */       g.fillRoundRect(348, 148, 304, 254, 20, 20);
/*     */       
/* 255 */       g.setColor(Color.darkGray);
/* 256 */       g.fillRoundRect(350, 150, 300, 250, 20, 20);
/*     */       
/* 258 */       g.setColor(this.c2);
/* 259 */       g.setFont(this.f);
/* 260 */       g.drawString("AgarDots", 420, 200);
/*     */       
/* 262 */       g.setFont(this.f2);
/* 263 */       g.drawString("Nickname:", 380, 230);
/* 264 */       g.setColor(this.c2);
/* 265 */       g.fillRoundRect(375, 240, 250, 30, 15, 15);
/*     */       
/* 267 */       g.setColor(Color.black);
/* 268 */       g.drawString(this.enteredName, 380, 260);
/*     */       
/* 270 */       g.setColor(this.c2);
/* 271 */       g.drawString(" - Use the arrow keys to move", 380, 300);
/* 272 */       g.drawString(" - eat the smaller dots", 380, 325);
/* 273 */       g.drawString(" - stay away from the big ones", 380, 350);
/* 274 */       g.drawString("Press 'enter' to start", 380, 375);
/*     */     }
/*     */   }
/*     */   
/*     */   public void keyTyped(KeyEvent e) {}
/*     */ }


/* Location:              C:\Users\User\Desktop\Robotics\Agar - Walker\bin\LaunchGame.jar!\Agar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */