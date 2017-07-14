package sajt.dzfppt.service.pt.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

public class PassWordCreate
{
//  public static String randomKey = "1234567890";
  private static final String ALGORITHM = "MD5";
  private static final String CHARSET = "UTF-8";
  public static PassWordCreate passWordCheck = null;

  public static PassWordCreate instantiation() {
    if (passWordCheck == null) {
      passWordCheck = new PassWordCreate();
    }
    return passWordCheck;
  }

  public static String passWordCreate(String zch,String randomKey)
    throws Exception
  {
    String password = "";
    if ((zch == null) || ("".equals(zch))) {
      return password;
    }
    StringBuffer bf = new StringBuffer();
    bf.append(randomKey);
    bf.append(zch);
    MessageDigest md = MessageDigest.getInstance(ALGORITHM);
    md.update(bf.toString().getBytes(CHARSET));
    password = randomKey + new String(new Base64().encode(md.digest()));
    return password;
  }
}
