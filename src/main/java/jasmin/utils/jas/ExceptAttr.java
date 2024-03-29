/**
 * This attribute is associated with a method, and indicates
 * the set of exceptions (as classCP items) that can be thrown
 * by the method.
 *
 * @author $Author: jonmeyerny $
 * @version $Revision: 1.1 $
 */

package jasmin.utils.jas;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class ExceptAttr
{
  static final CP attr = new AsciiCP("Exceptions");

  Vector cps;

  public ExceptAttr() { cps = new Vector(); }
  /**
   * @param cp Exception class to be added to attribute. This is
   * typically a ClassCP
   */
  public void addException(CP cp)
  { cps.addElement(cp); }

  void resolve(ClassEnv e)
  {
    e.addCPItem(attr);
    for (Enumeration en = cps.elements(); en.hasMoreElements();)
      { e.addCPItem((CP)(en.nextElement())); }
  }

  void write(ClassEnv e, DataOutputStream out)
    throws IOException, jasError
  {
    out.writeShort(e.getCPIndex(attr));
    out.writeInt(cps.size()*2 + 2);
    out.writeShort(cps.size());
    for (Enumeration en = cps.elements(); en.hasMoreElements();)
      { out.writeShort(e.getCPIndex((CP)(en.nextElement()))); }
  }
}
