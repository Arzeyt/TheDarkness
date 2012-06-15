/*    */ package us.twoguys.lib.jnbt;
/*    */ 
/*    */ public final class ByteTag extends Tag
/*    */ {
/*    */   private final byte value;
/*    */ 
/*    */   public ByteTag(String name, byte value)
/*    */   {
/* 54 */     super(name);
/* 55 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public Byte getValue()
/*    */   {
/* 60 */     return Byte.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 65 */     String name = getName();
/* 66 */     String append = "";
/* 67 */     if ((name != null) && (!name.equals(""))) {
/* 68 */       append = "(\"" + getName() + "\")";
/*    */     }
/* 70 */     return "TAG_Byte" + append + ": " + this.value;
/*    */   }
/*    */ }

/* Location:           C:\Users\Nick\Desktop\lib\jnbt-1.1.jar
 * Qualified Name:     org.jnbt.ByteTag
 * JD-Core Version:    0.6.0
 */