/**
 * This is a class to create Ascii CP entries.
 *
 * @author $Author: jonmeyerny $
 * @version $Revision: 1.1 $
 */

package jasmin.utils.jas;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;


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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    AsciiCP asciiCP = (AsciiCP) o;
    return Objects.equals(val, asciiCP.val);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), val);
  }

  void write(ClassEnv e, DataOutputStream out)
    throws IOException
  {
    out.writeByte(CONSTANT_UTF8);
    out.writeUTF(uniq);
  }
}
