/*    */ import java.awt.KeyEventDispatcher;
/*    */ import java.awt.event.KeyEvent;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ class IsKeyPressed$1
/*    */   implements KeyEventDispatcher
/*    */ {
/*    */   public boolean dispatchKeyEvent(KeyEvent ke)
/*    */   {
/* 25 */     synchronized (IsKeyPressed.class)
/*    */     {
/* 27 */       switch (ke.getID()) {
/*    */       case 401: 
/* 29 */         if (ke.getKeyCode() == 37)
/* 30 */           IsKeyPressed.lPressed = true;
/* 31 */         if (ke.getKeyCode() == 39)
/* 32 */           IsKeyPressed.rPressed = true;
/* 33 */         if (ke.getKeyCode() == 32)
/* 34 */           IsKeyPressed.spacePressed = true;
/* 35 */         if (ke.getKeyCode() == 38)
/* 36 */           IsKeyPressed.upPressed = true;
/* 37 */         if (ke.getKeyCode() == 40)
/* 38 */           IsKeyPressed.downPressed = true;
/* 39 */         if (ke.getKeyCode() == 82)
/* 40 */           IsKeyPressed.lRPressed = true;
/* 41 */         if (ke.getKeyCode() == 10)
/* 42 */           IsKeyPressed.enterPressed = true;
/* 43 */         break;
/*    */       
/*    */       case 402: 
/* 46 */         if (ke.getKeyCode() == 37)
/* 47 */           IsKeyPressed.lPressed = false;
/* 48 */         if (ke.getKeyCode() == 39)
/* 49 */           IsKeyPressed.rPressed = false;
/* 50 */         if (ke.getKeyCode() == 32)
/* 51 */           IsKeyPressed.spacePressed = false;
/* 52 */         if (ke.getKeyCode() == 38)
/* 53 */           IsKeyPressed.upPressed = false;
/* 54 */         if (ke.getKeyCode() == 40)
/* 55 */           IsKeyPressed.downPressed = false;
/* 56 */         if (ke.getKeyCode() == 82)
/* 57 */           IsKeyPressed.lRPressed = false;
/* 58 */         if (ke.getKeyCode() == 10) {
/* 59 */           IsKeyPressed.enterPressed = true;
/*    */         }
/*    */         break;
/*    */       }
/* 63 */       return false;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\User\Desktop\Robotics\Agar - Walker\bin\LaunchGame.jar!\IsKeyPressed$1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */