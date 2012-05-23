/*    */ package us.twoguys.lib.jnbt;
/*    */ 
/*    */ public final class DoubleTag extends Tag
/*    */ {
/*    */   private final double value;
/*    */ 
/*    */   public DoubleTag(String name, double value)
/*    */   {
/* 54 */     super(name);
/* 55 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public Double getValue()
/*    */   {
/* 60 */     return Double.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 65 */     String name = getName();
/* 66 */     String append = "";
/* 67 */     if ((name != null) && (!name.equals(""))) {
/* 68 */       append = "(\"" + getName() + "\")";
/*    */     }
/* 70 */     return "TAG_Double" + append + ": " + this.value;
/*    */   }
/*    */ }

/* Location:           C:\Users\Nick\Desktop\lib\jnbt-1.1.jar
 * Qualified Name:     org.jnbt.DoubleTag
 * JD-Core Version:    0.6.0
 */