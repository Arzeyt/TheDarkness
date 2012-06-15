/*    */ package us.twoguys.lib.jnbt;
/*    */ 
/*    */ public abstract class Tag
/*    */ {
/*    */   private final String name;
/*    */ 
/*    */   public Tag(String name)
/*    */   {
/* 53 */     this.name = name;
/*    */   }
/*    */ 
/*    */   public final String getName()
/*    */   {
/* 61 */     return this.name;
/*    */   }
/*    */ 
/*    */   public abstract Object getValue();
/*    */ }

/* Location:           C:\Users\Nick\Desktop\lib\jnbt-1.1.jar
 * Qualified Name:     org.jnbt.Tag
 * JD-Core Version:    0.6.0
 */