/**
 * This is used to create a Class constant pool item
 *
 * @author $Author: jonmeyerny $
 * @version $Revision: 1.1 $
 */

package jasmin.utils.jas;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;


public class ClassCP extends CP implements RuntimeConstants
{
  AsciiCP name;

  String strName;

  /**
   * @param name Name of the class
   */
  public ClassCP(String name)
  {
    uniq = ("CLASS: #$%^#$" + name).intern();
    this.name = new AsciiCP(name);
    strName = name;
  }

  public AsciiCP name() {
    return name;
  }

  public String strName() {
    return strName;
  }

  void resolve(ClassEnv e)
  { e.addCPItem(name); }

  void write(ClassEnv e, DataOutputStream out)
    throws IOException, jasError
  {
    out.writeByte(CONSTANT_CLASS);
    out.writeShort(e.getCPIndex(name));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;
    ClassCP classCP = (ClassCP) o;
    return Objects.equals(name, classCP.name) && Objects.equals(strName, classCP.strName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), name, strName);
  }
}
