/**
 * this is an abstraction to contain all the CPE items
 * that can be created.
 *
 * @see AsciiCP
 * @see ClassCP
 * @see NameTypeCP
 * @see FieldCP
 * @see InterfaceCP
 * @see MethodCP
 * @see IntegerCP
 * @see LongCP
 * @see FloatCP
 * @see DoubleCP
 * @see StringCP
 *
 * @author $Author: jonmeyerny $
 * @version $Revision: 1.1 $
 */


package jasmin.utils.jas;
                                // one class to ring them all...

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

public abstract class CP
{
  String uniq;

  String getUniq() { return uniq; }

  abstract void resolve(ClassEnv e);

  abstract void write(ClassEnv e, DataOutputStream out)
   throws IOException, jasError;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CP cp = (CP) o;
    return Objects.equals(uniq, cp.uniq);
  }

  @Override
  public int hashCode() {
    return Objects.hash(uniq);
  }
}
