/**
 * This is a class to create Ascii CP entries.
 *
 * @author $Author: jonmeyerny $
 * @version $Revision: 1.1 $
 */

package jasmin.utils.jas;

import java.io.DataOutputStream;
import java.io.IOException;


public class AsciiCP extends CP implements RuntimeConstants
{
  String val;
  /**
   * @param s Name of the ascii constant pool entry
   */
  public AsciiCP(String s)
  { uniq = s.intern();
  val = s;}

  public String val() {
    return val;
  }

  void resolve(ClassEnv e)
  { return; }

  public String toString() { return "AsciiCP: " + uniq; }
  void write(ClassEnv e, DataOutputStream out)
    throws IOException
  {
    out.writeByte(CONSTANT_UTF8);
    out.writeUTF(uniq);
  }
}
