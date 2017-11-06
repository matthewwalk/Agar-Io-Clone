/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.io.File;
/*     */ import java.io.PrintWriter;
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
/*     */ 
/*     */ 
/*     */ public class LeaderBoard
/*     */ {
/*     */   String[][] nameArray;
/*     */   private final Color c;
/*     */   private final Color c2;
/*     */   private final Font f;
/*     */   
/*     */   LeaderBoard()
/*     */   {
/*  31 */     this.nameArray = new String[6][2];
/*  32 */     fileReading();
/*  33 */     fileWriting();
/*  34 */     float alpha = 0.75F;
/*  35 */     this.c = new Color(0.75F, 0.75F, 0.75F, alpha);
/*  36 */     this.c2 = new Color(0.95F, 0.95F, 0.95F, 0.9F);
/*  37 */     this.f = new Font("Arial", 1, 20);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   void addScore(int score, String name)
/*     */   {
/*  44 */     if (onBoard(score)) {
/*  45 */       this.nameArray[5][0] = name;
/*  46 */       this.nameArray[5][1] = Integer.toString(score);
/*  47 */       sortBoard();
/*  48 */       fileWriting();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   boolean onBoard(int playerScore)
/*     */   {
/*  55 */     for (int i = 0; i < this.nameArray.length - 1; i++)
/*  56 */       if (playerScore >= Integer.parseInt(this.nameArray[i][1]))
/*  57 */         return true;
/*  58 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   void sortBoard()
/*     */   {
/*  66 */     for (int i = 0; i < this.nameArray.length; i++)
/*  67 */       for (int j = 1; j < this.nameArray.length - i; j++)
/*  68 */         if (Integer.parseInt(this.nameArray[(j - 1)][1]) < Integer.parseInt(this.nameArray[j][1])) {
/*  69 */           String temp = this.nameArray[(j - 1)][1];
/*  70 */           String temp1 = this.nameArray[(j - 1)][0];
/*  71 */           this.nameArray[(j - 1)][1] = this.nameArray[j][1];
/*  72 */           this.nameArray[(j - 1)][0] = this.nameArray[j][0];
/*  73 */           this.nameArray[j][1] = temp;
/*  74 */           this.nameArray[j][0] = temp1;
/*     */         }
/*  76 */     this.nameArray[5][0] = "Default";
/*  77 */     this.nameArray[5][1] = "0";
/*     */   }
/*     */   
/*     */ 
/*     */   void drawLeaderBoard(Graphics g)
/*     */   {
/*  83 */     g.setColor(this.c);
/*  84 */     g.fillRoundRect(790, 10, 200, 300, 20, 20);
/*  85 */     g.setFont(this.f);
/*  86 */     g.setColor(this.c2);
/*  87 */     int y = 40;
/*  88 */     for (int i = 0; i < this.nameArray.length - 1; i++) {
/*  89 */       int no = i + 1;
/*  90 */       g.drawString(no + ": " + this.nameArray[i][0], 800, y);
/*  91 */       g.drawString("Score: " + this.nameArray[i][1], 800, y + 20);
/*  92 */       y += 60;
/*     */     }
/*     */   }
/*     */   
/*     */   final void fileWriting()
/*     */   {
/*     */     try
/*     */     {
/* 100 */       File file = new File("highScores.txt");
/*     */       
/* 102 */       PrintWriter printWriter = new PrintWriter("highScores.txt");
/*     */       
/* 104 */       for (int i = 0; i < this.nameArray.length - 1; i++) {
/* 105 */         for (int j = 0; j < this.nameArray[0].length; j++)
/* 106 */           printWriter.println(this.nameArray[i][j].trim());
/*     */       }
/* 108 */       printWriter.close();
/*     */     }
/*     */     catch (Exception localException) {}
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   final void fileReading()
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aconst_null
/*     */     //   1: astore_1
/*     */     //   2: new 130	java/io/File
/*     */     //   5: dup
/*     */     //   6: ldc -124
/*     */     //   8: invokespecial 134	java/io/File:<init>	(Ljava/lang/String;)V
/*     */     //   11: astore_2
/*     */     //   12: new 153	java/io/BufferedReader
/*     */     //   15: dup
/*     */     //   16: new 155	java/io/FileReader
/*     */     //   19: dup
/*     */     //   20: aload_2
/*     */     //   21: invokespecial 157	java/io/FileReader:<init>	(Ljava/io/File;)V
/*     */     //   24: invokespecial 160	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
/*     */     //   27: astore_1
/*     */     //   28: iconst_0
/*     */     //   29: istore_3
/*     */     //   30: goto +85 -> 115
/*     */     //   33: iconst_0
/*     */     //   34: istore 4
/*     */     //   36: goto +64 -> 100
/*     */     //   39: aload_1
/*     */     //   40: invokevirtual 163	java/io/BufferedReader:readLine	()Ljava/lang/String;
/*     */     //   43: astore 5
/*     */     //   45: aload 5
/*     */     //   47: invokevirtual 166	java/lang/String:isEmpty	()Z
/*     */     //   50: ifne +17 -> 67
/*     */     //   53: aload_0
/*     */     //   54: getfield 18	LeaderBoard:nameArray	[[Ljava/lang/String;
/*     */     //   57: iload_3
/*     */     //   58: aaload
/*     */     //   59: iload 4
/*     */     //   61: aload 5
/*     */     //   63: aastore
/*     */     //   64: goto +33 -> 97
/*     */     //   67: iload 4
/*     */     //   69: ifne +17 -> 86
/*     */     //   72: aload_0
/*     */     //   73: getfield 18	LeaderBoard:nameArray	[[Ljava/lang/String;
/*     */     //   76: iload_3
/*     */     //   77: aaload
/*     */     //   78: iload 4
/*     */     //   80: ldc 79
/*     */     //   82: aastore
/*     */     //   83: goto +14 -> 97
/*     */     //   86: aload_0
/*     */     //   87: getfield 18	LeaderBoard:nameArray	[[Ljava/lang/String;
/*     */     //   90: iload_3
/*     */     //   91: aaload
/*     */     //   92: iload 4
/*     */     //   94: ldc 81
/*     */     //   96: aastore
/*     */     //   97: iinc 4 1
/*     */     //   100: iload 4
/*     */     //   102: aload_0
/*     */     //   103: getfield 18	LeaderBoard:nameArray	[[Ljava/lang/String;
/*     */     //   106: iconst_0
/*     */     //   107: aaload
/*     */     //   108: arraylength
/*     */     //   109: if_icmplt -70 -> 39
/*     */     //   112: iinc 3 1
/*     */     //   115: iload_3
/*     */     //   116: aload_0
/*     */     //   117: getfield 18	LeaderBoard:nameArray	[[Ljava/lang/String;
/*     */     //   120: arraylength
/*     */     //   121: if_icmplt -88 -> 33
/*     */     //   124: goto +30 -> 154
/*     */     //   127: astore_2
/*     */     //   128: aload_1
/*     */     //   129: invokevirtual 170	java/io/BufferedReader:close	()V
/*     */     //   132: goto +35 -> 167
/*     */     //   135: astore 7
/*     */     //   137: goto +30 -> 167
/*     */     //   140: astore 6
/*     */     //   142: aload_1
/*     */     //   143: invokevirtual 170	java/io/BufferedReader:close	()V
/*     */     //   146: goto +5 -> 151
/*     */     //   149: astore 7
/*     */     //   151: aload 6
/*     */     //   153: athrow
/*     */     //   154: aload_1
/*     */     //   155: invokevirtual 170	java/io/BufferedReader:close	()V
/*     */     //   158: goto +9 -> 167
/*     */     //   161: astore 7
/*     */     //   163: goto +4 -> 167
/*     */     //   166: astore_1
/*     */     //   167: return
/*     */     // Line number table:
/*     */     //   Java source line #118	-> byte code offset #0
/*     */     //   Java source line #121	-> byte code offset #2
/*     */     //   Java source line #122	-> byte code offset #12
/*     */     //   Java source line #123	-> byte code offset #28
/*     */     //   Java source line #124	-> byte code offset #33
/*     */     //   Java source line #125	-> byte code offset #39
/*     */     //   Java source line #126	-> byte code offset #45
/*     */     //   Java source line #127	-> byte code offset #53
/*     */     //   Java source line #129	-> byte code offset #67
/*     */     //   Java source line #130	-> byte code offset #72
/*     */     //   Java source line #132	-> byte code offset #86
/*     */     //   Java source line #124	-> byte code offset #97
/*     */     //   Java source line #123	-> byte code offset #112
/*     */     //   Java source line #135	-> byte code offset #124
/*     */     //   Java source line #138	-> byte code offset #128
/*     */     //   Java source line #139	-> byte code offset #132
/*     */     //   Java source line #136	-> byte code offset #140
/*     */     //   Java source line #138	-> byte code offset #142
/*     */     //   Java source line #139	-> byte code offset #146
/*     */     //   Java source line #140	-> byte code offset #151
/*     */     //   Java source line #138	-> byte code offset #154
/*     */     //   Java source line #139	-> byte code offset #158
/*     */     //   Java source line #141	-> byte code offset #163
/*     */     //   Java source line #142	-> byte code offset #167
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	168	0	this	LeaderBoard
/*     */     //   1	154	1	reader	java.io.BufferedReader
/*     */     //   166	1	1	localException4	Exception
/*     */     //   11	10	2	file	File
/*     */     //   127	1	2	localException	Exception
/*     */     //   29	87	3	x	int
/*     */     //   34	67	4	r	int
/*     */     //   43	19	5	Line	String
/*     */     //   140	12	6	localObject	Object
/*     */     //   135	1	7	localException1	Exception
/*     */     //   149	1	7	localException2	Exception
/*     */     //   161	1	7	localException3	Exception
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   2	124	127	java/lang/Exception
/*     */     //   128	132	135	java/lang/Exception
/*     */     //   2	128	140	finally
/*     */     //   142	146	149	java/lang/Exception
/*     */     //   154	158	161	java/lang/Exception
/*     */     //   0	163	166	java/lang/Exception
/*     */   }
/*     */ }


/* Location:              C:\Users\User\Desktop\Robotics\Agar - Walker\bin\LaunchGame.jar!\LeaderBoard.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */