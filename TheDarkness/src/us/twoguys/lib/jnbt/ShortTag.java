/*    */ package us.twoguys.lib.jnbt;
/*    */ 
/*    */ public final class ShortTag extends Tag
/*    */ {
/*    */   private final short value;
/*    */ 
/*    */   public ShortTag(String name, short value)
/*    */   {
/* 54 */     super(name);
/* 55 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public Short getValue()
/*    */   {
/* 60 */     return Short.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 65 */     String name = getName();
/* 66 */     String append = "";
/* 67 */     if ((name != null) && (!name.equals(""))) {
/* 68 */       append = "(\"" + getName() + "\")";
/*    */     }
/* 70 */     return "TAG_Short" + append + ": " + this.value;
/*    */   }
/*    */ }

/* Location:           C:\Users\Nick\Desktop\lib\jnbt-1.1.jar
 * Qualified Name:     org.jnbt.ShortTag
 * JD-Core Version:    0.6.0
 */