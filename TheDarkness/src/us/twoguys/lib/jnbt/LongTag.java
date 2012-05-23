/*    */ package us.twoguys.lib.jnbt;
/*    */ 
/*    */ public final class LongTag extends Tag
/*    */ {
/*    */   private final long value;
/*    */ 
/*    */   public LongTag(String name, long value)
/*    */   {
/* 54 */     super(name);
/* 55 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public Long getValue()
/*    */   {
/* 60 */     return Long.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 65 */     String name = getName();
/* 66 */     String append = "";
/* 67 */     if ((name != null) && (!name.equals(""))) {
/* 68 */       append = "(\"" + getName() + "\")";
/*    */     }
/* 70 */     return "TAG_Long" + append + ": " + this.value;
/*    */   }
/*    */ }

/* Location:           C:\Users\Nick\Desktop\lib\jnbt-1.1.jar
 * Qualified Name:     org.jnbt.LongTag
 * JD-Core Version:    0.6.0
 */