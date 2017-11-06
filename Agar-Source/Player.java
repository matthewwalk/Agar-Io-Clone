/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Player
/*     */ {
/*     */   int X_POS;
/*     */   int Y_POS;
/*     */   int radius;
/*     */   int radiusTarget;
/*     */   int rD;
/*     */   int gR;
/*     */   int bL;
/*     */   int randomNum;
/*     */   private double xSpeed;
/*     */   private double ySpeed;
/*     */   private double fontSize;
/*     */   private double maxSpeed;
/*     */   private double deltaGrow;
/*     */   private final double accel;
/*     */   Color plr;
/*     */   Color plrRim;
/*     */   String name;
/*     */   Font f;
/*     */   
/*     */   Player()
/*     */   {
/*  39 */     this.name = "";
/*     */     
/*     */ 
/*  42 */     this.plr = new Color(randInt(0, 255), randInt(0, 255), randInt(0, 255));
/*  43 */     this.plrRim = new Color(randInt(0, 255), randInt(0, 255), randInt(0, 255));
/*     */     
/*     */ 
/*  46 */     this.X_POS = 500;
/*  47 */     this.Y_POS = 300;
/*     */     
/*     */ 
/*  50 */     this.radius = 20;
/*  51 */     this.radiusTarget = 20;
/*     */     
/*     */ 
/*  54 */     this.xSpeed = 0.0D;
/*  55 */     this.ySpeed = 0.0D;
/*     */     
/*     */ 
/*  58 */     this.accel = 0.2D;
/*     */     
/*     */ 
/*  61 */     this.maxSpeed = 5.0D;
/*  62 */     this.fontSize = 10.0D;
/*  63 */     this.f = new Font("Serif", 0, (int)this.fontSize);
/*     */   }
/*     */   
/*     */ 
/*     */   void setName(String r)
/*     */   {
/*  69 */     this.name = r;
/*  70 */     addNSize(0);
/*     */   }
/*     */   
/*     */ 
/*     */   boolean isTouchingEnemy(Enemy f)
/*     */   {
/*  76 */     if (f != null)
/*  77 */       return magnitude(this.X_POS, this.Y_POS, f.X_POS, f.Y_POS) <= this.radius + f.radius * 0.75D;
/*  78 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   boolean isCloseToEnemy(Enemy f)
/*     */   {
/*  84 */     if (f != null)
/*  85 */       return magnitude(this.X_POS, this.Y_POS, f.X_POS, f.Y_POS) <= (this.radius + f.radius) * 2;
/*  86 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   boolean onTopOfEnemy(Enemy f)
/*     */   {
/*  92 */     if (f != null)
/*  93 */       return magnitude(this.X_POS, this.Y_POS, f.X_POS, f.Y_POS) <= (this.radius + f.radius) * 0.25D;
/*  94 */     return false;
/*     */   }
/*     */   
/*     */   double magnitude(double x, double y)
/*     */   {
/*  99 */     return Math.sqrt(x * x + y * y);
/*     */   }
/*     */   
/*     */   double magnitude(double x1, double y1, double x2, double y2)
/*     */   {
/* 104 */     return magnitude(x1 - x2, y1 - y2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void control()
/*     */   {
/* 111 */     if (IsKeyPressed.lPressed) {
/* 112 */       if (this.xSpeed > -this.maxSpeed) {
/* 113 */         this.xSpeed -= this.accel;
/*     */       }
/*     */     }
/* 116 */     else if (IsKeyPressed.rPressed) {
/* 117 */       if (this.xSpeed < this.maxSpeed) {
/* 118 */         this.xSpeed += this.accel;
/*     */       }
/*     */       
/*     */     }
/* 122 */     else if (Math.abs(this.xSpeed) < this.accel) {
/* 123 */       this.xSpeed = 0.0D;
/*     */     } else {
/* 125 */       this.xSpeed -= this.accel * Math.signum(this.xSpeed);
/*     */     }
/*     */     
/*     */ 
/* 129 */     if (IsKeyPressed.upPressed) {
/* 130 */       if (this.ySpeed > -this.maxSpeed) {
/* 131 */         this.ySpeed -= this.accel;
/*     */       }
/*     */     }
/* 134 */     else if (IsKeyPressed.downPressed) {
/* 135 */       if (this.ySpeed < this.maxSpeed) {
/* 136 */         this.ySpeed += this.accel;
/*     */       }
/*     */       
/*     */     }
/* 140 */     else if (Math.abs(this.ySpeed) < this.accel) {
/* 141 */       this.ySpeed = 0.0D;
/*     */     } else {
/* 143 */       this.ySpeed -= this.accel * Math.signum(this.ySpeed);
/*     */     }
/*     */     
/*     */ 
/* 147 */     this.X_POS = ((int)(this.X_POS + this.xSpeed));
/* 148 */     this.Y_POS = ((int)(this.Y_POS + this.ySpeed));
/*     */   }
/*     */   
/*     */ 
/*     */   void addNSize(int t)
/*     */   {
/* 154 */     this.radiusTarget = ((int)Math.sqrt(this.radius * this.radius + t * t));
/* 155 */     if (this.radiusTarget < 1) {
/* 156 */       this.radiusTarget = (this.radius + 1);
/*     */     }
/* 158 */     this.deltaGrow = t;
/*     */     
/* 160 */     if (this.maxSpeed > 2.0D) {
/* 161 */       this.maxSpeed -= 0.1D;
/*     */     }
/*     */   }
/*     */   
/*     */   void sizeUpdate() {
/* 166 */     if (this.radius < this.radiusTarget)
/*     */     {
/* 168 */       AffineTransform affinetransform = new AffineTransform();
/* 169 */       FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
/* 170 */       int textwidth = (int)this.f.getStringBounds(this.name, frc).getWidth();
/*     */       
/* 172 */       if (this.name.length() <= 2) {
/* 173 */         this.fontSize *= 2.0D * this.radius / textwidth / 5.0D;
/*     */       }
/* 175 */       else if (textwidth < this.radius - 5) {
/* 176 */         this.fontSize *= 2.0D * this.radius / textwidth;
/*     */       }
/*     */       
/* 179 */       if (this.radius < 200)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 190 */         this.radius = ((int)(this.radius + this.deltaGrow / 9.0D));
/*     */       }
/* 192 */       this.f = new Font("Serif", 0, (int)this.fontSize);
/*     */     }
/*     */   }
/*     */   
/*     */   final int randInt(int min, int max)
/*     */   {
/* 198 */     return (int)(Math.random() * (max - min) + 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */   void drawPlayer(Graphics g)
/*     */   {
/* 204 */     g.setColor(this.plrRim);
/* 205 */     g.fillOval(this.X_POS - this.radius - 3, this.Y_POS - this.radius - 3, this.radius * 2 + 6, this.radius * 2 + 6);
/*     */     
/* 207 */     g.setColor(this.plr);
/* 208 */     g.fillOval(this.X_POS - this.radius, this.Y_POS - this.radius, this.radius * 2, this.radius * 2);
/*     */     
/* 210 */     FontRenderContext frt = new FontRenderContext(null, false, false);
/* 211 */     Rectangle2D rect = this.f.getStringBounds(this.name, frt);
/* 212 */     int length = (int)rect.getWidth();
/*     */     
/* 214 */     g.setColor(Color.BLACK);
/* 215 */     g.setFont(this.f);
/* 216 */     g.drawString(this.name, this.X_POS - length / 2, (int)(this.Y_POS + this.fontSize / 2.0D) - 4);
/* 217 */     g.drawString(this.name, this.X_POS - length / 2 + 2, (int)(this.Y_POS + this.fontSize / 2.0D) - 4);
/* 218 */     g.drawString(this.name, this.X_POS - length / 2, (int)(this.Y_POS + this.fontSize / 2.0D) - 2);
/* 219 */     g.drawString(this.name, this.X_POS - length / 2 + 2, (int)(this.Y_POS + this.fontSize / 2.0D) - 2);
/*     */     
/* 221 */     g.setColor(Color.WHITE);
/* 222 */     g.setFont(this.f);
/* 223 */     g.drawString(this.name, this.X_POS - length / 2 + 1, (int)(this.Y_POS + this.fontSize / 2.0D) - 3);
/*     */   }
/*     */ }


/* Location:              C:\Users\User\Desktop\Robotics\Agar - Walker\bin\LaunchGame.jar!\Player.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */