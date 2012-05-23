/*    */ package us.twoguys.lib.jnbt;
/*    */ 
/*    */ public final class FloatTag extends Tag
/*    */ {
/*    */   private final float value;
/*    */ 
/*    */   public FloatTag(String name, float value)
/*    */   {
/* 54 */     super(name);
/* 55 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public Float getValue()
/*    */   {
/* 60 */     return Float.valueOf(this.value);
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 65 */     String name = getName();
/* 66 */     String append = "";
/* 67 */     if ((name != null) && (!name.equals(""))) {
/* 68 */       append = "(\"" + getName() + "\")";
/*    */     }
/* 70 */     return "TAG_Float" + append + ": " + this.value;
/*    */   }
/*    */ }

/* Location:           C:\Users\Nick\Desktop\lib\jnbt-1.1.jar
 * Qualified Name:     org.jnbt.FloatTag
 * JD-Core Version:    0.6.0
 */