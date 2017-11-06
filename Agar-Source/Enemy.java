/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Enemy
/*     */ {
/*     */   private final int RADIUS_MIN;
/*     */   private final int RADIUS_MAX;
/*  21 */   int[] NORTH = new int[4];
/*  22 */   int[] SOUTH = new int[4];
/*  23 */   int[] EAST = new int[4];
/*  24 */   int[] WEST = new int[4];
/*     */   
/*     */   int radius;
/*     */   int choice;
/*  28 */   boolean attack = false;
/*  29 */   boolean X = false;
/*  30 */   boolean Y = false;
/*     */   double direction;
/*     */   double X_POS;
/*     */   double Y_POS;
/*     */   double speedX;
/*     */   double speedY;
/*     */   double speed;
/*     */   private final Color center;
/*     */   private final Color rim;
/*     */   ScoreBar bar;
/*     */   
/*     */   Enemy(Enemy[] f, ScoreBar b) {
/*  42 */     this.RADIUS_MIN = ((int)(Agar.agio.radius / 3.0D));
/*  43 */     this.RADIUS_MAX = ((int)(Agar.agio.radius * 2.5D));
/*  44 */     this.radius = randInt(this.RADIUS_MIN, this.RADIUS_MAX);
/*     */     
/*     */ 
/*     */ 
/*  48 */     this.NORTH[0] = 0;
/*  49 */     this.NORTH[1] = 1000;
/*  50 */     this.NORTH[2] = (-this.radius);
/*  51 */     this.NORTH[3] = (-this.radius - 600);
/*     */     
/*     */ 
/*  54 */     this.SOUTH[0] = 0;
/*  55 */     this.SOUTH[1] = 1000;
/*  56 */     this.SOUTH[2] = (600 + this.radius);
/*  57 */     this.SOUTH[3] = (600 + this.radius + 600);
/*     */     
/*     */ 
/*  60 */     this.WEST[0] = (-this.radius);
/*  61 */     this.WEST[1] = (-this.radius - 600);
/*  62 */     this.WEST[2] = 0;
/*  63 */     this.WEST[3] = 600;
/*     */     
/*     */ 
/*  66 */     this.EAST[0] = (1000 + this.radius);
/*  67 */     this.EAST[1] = (1000 + this.radius + 600);
/*  68 */     this.EAST[2] = 0;
/*  69 */     this.EAST[3] = 1000;
/*     */     
/*     */ 
/*  72 */     this.choice = randInt(1, 4);
/*  73 */     if (this.choice == 1) {
/*  74 */       this.X_POS = randInt(this.NORTH[0], this.NORTH[1]);
/*  75 */       this.Y_POS = randInt(this.NORTH[3], this.NORTH[2]);
/*     */     }
/*  77 */     else if (this.choice == 2) {
/*  78 */       this.X_POS = randInt(this.SOUTH[0], this.SOUTH[1]);
/*  79 */       this.Y_POS = randInt(this.SOUTH[2], this.SOUTH[3]);
/*     */     }
/*  81 */     else if (this.choice == 3) {
/*  82 */       this.X_POS = randInt(this.WEST[1], this.WEST[0]);
/*  83 */       this.Y_POS = randInt(this.WEST[2], this.WEST[3]);
/*     */     }
/*  85 */     else if (this.choice == 4) {
/*  86 */       this.X_POS = randInt(this.EAST[0], this.EAST[1]);
/*  87 */       this.Y_POS = randInt(this.EAST[2], this.EAST[3]);
/*     */     }
/*     */     
/*  90 */     this.bar = b;
/*     */     
/*     */ 
/*  93 */     double size = 1.0D;
/*  94 */     if (this.radius > Agar.agio.radius)
/*  95 */       size = 1.5D;
/*  96 */     this.speed = (randDouble(1.0D, 2.0D) / size);
/*     */     
/*     */ 
/*  99 */     this.direction = Math.atan2(Agar.agio.Y_POS - this.Y_POS, Agar.agio.X_POS - this.X_POS);
/*     */     
/*     */ 
/* 102 */     this.speedX = (this.speed * Math.cos(this.direction));
/* 103 */     this.speedY = (this.speed * Math.sin(this.direction));
/*     */     
/*     */ 
/* 106 */     this.center = new Color(randInt(0, 255), randInt(0, 255), randInt(0, 255));
/* 107 */     this.rim = new Color(randInt(0, 255), randInt(0, 255), randInt(0, 255));
/*     */   }
/*     */   
/*     */   void drawEnemy(Graphics g)
/*     */   {
/* 112 */     g.setColor(this.rim);
/* 113 */     g.fillOval((int)this.X_POS - this.radius - 3, (int)this.Y_POS - this.radius - 3, this.radius * 2 + 6, this.radius * 2 + 6);
/*     */     
/* 115 */     g.setColor(this.center);
/* 116 */     g.fillOval((int)this.X_POS - this.radius, (int)this.Y_POS - this.radius, this.radius * 2, this.radius * 2);
/*     */   }
/*     */   
/*     */   void suck()
/*     */   {
/* 121 */     this.attack = true;
/*     */   }
/*     */   
/*     */   void move()
/*     */   {
/* 126 */     if (this.attack)
/*     */     {
/*     */ 
/* 129 */       if (Agar.agio.radius > this.radius)
/*     */       {
/*     */ 
/* 132 */         if (Math.abs(this.X_POS - Agar.agio.X_POS) > 100.0D) {
/* 133 */           this.attack = false;
/*     */         }
/*     */         
/* 136 */         if (Math.abs(this.Y_POS - Agar.agio.Y_POS) > 100.0D) {
/* 137 */           this.attack = false;
/*     */         }
/*     */         
/* 140 */         if (Agar.agio.onTopOfEnemy(this)) {
/* 141 */           this.attack = false;
/* 142 */           this.bar.updateScore(Agar.agio.radius);
/* 143 */           Agar.agio.addNSize((int)(this.radius / 1.5D));
/* 144 */           for (int i = 0; i < Agar.enemyArray.length; i++) {
/* 145 */             if (Agar.enemyArray[i] == this) {
/* 146 */               Agar.enemyArray[i] = null;
/* 147 */               break;
/*     */             }
/*     */           }
/*     */         }
/*     */         else {
/* 152 */           if (this.X_POS < Agar.agio.X_POS) {
/* 153 */             this.X_POS += 2.0D;
/* 154 */           } else if (this.X_POS > Agar.agio.X_POS) {
/* 155 */             this.X_POS -= 2.0D;
/*     */           }
/* 157 */           if (this.Y_POS < Agar.agio.Y_POS) {
/* 158 */             this.Y_POS += 2.0D;
/* 159 */           } else if (this.Y_POS > Agar.agio.Y_POS) {
/* 160 */             this.Y_POS -= 2.0D;
/*     */           }
/*     */           
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 167 */         if (Math.abs(this.X_POS - Agar.agio.X_POS) > 200.0D) {
/* 168 */           this.attack = false;
/*     */         }
/*     */         
/* 171 */         if (Math.abs(this.Y_POS - Agar.agio.Y_POS) > 200.0D) {
/* 172 */           this.attack = false;
/*     */         }
/*     */         
/*     */ 
/* 176 */         this.direction = Math.atan2(Agar.agio.Y_POS - this.Y_POS, Agar.agio.X_POS - this.X_POS);
/* 177 */         this.speedX = (this.speed * Math.cos(this.direction));
/* 178 */         this.speedY = (this.speed * Math.sin(this.direction));
/*     */         
/*     */ 
/* 181 */         if (Agar.agio.onTopOfEnemy(this)) {
/* 182 */           this.attack = false;
/* 183 */           Agar.gameOver();
/*     */         }
/*     */         else
/*     */         {
/* 187 */           if (this.X_POS < Agar.agio.X_POS) {
/* 188 */             this.X_POS += this.speed;
/* 189 */           } else if (this.X_POS > Agar.agio.X_POS) {
/* 190 */             this.X_POS -= this.speed;
/*     */           }
/* 192 */           if (this.Y_POS < Agar.agio.Y_POS) {
/* 193 */             this.Y_POS += this.speed;
/* 194 */           } else if (this.Y_POS > Agar.agio.Y_POS) {
/* 195 */             this.Y_POS -= this.speed;
/*     */           }
/*     */         }
/*     */       }
/*     */     } else {
/* 200 */       this.X_POS += this.speedX;
/* 201 */       this.Y_POS += this.speedY;
/*     */     }
/*     */   }
/*     */   
/*     */   final int randInt(int min, int max)
/*     */   {
/* 207 */     return (int)(Math.random() * (max + 1 - min) + min);
/*     */   }
/*     */   
/*     */   final double randDouble(double min, double max) {
/* 211 */     return Math.random() * (max + 1.0D - min) + min;
/*     */   }
/*     */ }


/* Location:              C:\Users\User\Desktop\Robotics\Agar - Walker\bin\LaunchGame.jar!\Enemy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */