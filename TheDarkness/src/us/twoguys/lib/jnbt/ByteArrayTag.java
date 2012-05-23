/*    */ package us.twoguys.lib.jnbt;
/*    */ 
/*    */ public final class ByteArrayTag extends Tag
/*    */ {
/*    */   private final byte[] value;
/*    */ 
/*    */   public ByteArrayTag(String name, byte[] value)
/*    */   {
/* 54 */     super(name);
/* 55 */     this.value = value;
/*    */   }
/*    */ 
/*    */   public byte[] getValue()
/*    */   {
/* 60 */     return this.value;
/*    */   }
/*    */ 
/*    */   public String toString()
/*    */   {
/* 65 */     StringBuilder hex = new StringBuilder();
/* 66 */     for (byte b : this.value) {
/* 67 */       String hexDigits = Integer.toHexString(b).toUpperCase();
/* 68 */       if (hexDigits.length() == 1) {
/* 69 */         hex.append("0");
/*    */       }
/* 71 */       hex.append(hexDigits).append(" ");
/*    */     }
/* 73 */     String name = getName();
/* 74 */     String append = "";
/* 75 */     if ((name != null) && (!name.equals(""))) {
/* 76 */       append = "(\"" + getName() + "\")";
/*    */     }
/* 78 */     return "TAG_Byte_Array" + append + ": " + hex.toString();
/*    */   }
/*    */ }

/* Location:           C:\Users\Nick\Desktop\lib\jnbt-1.1.jar
 * Qualified Name:     org.jnbt.ByteArrayTag
 * JD-Core Version:    0.6.0
 */