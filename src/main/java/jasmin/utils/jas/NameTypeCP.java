/**
 * Create a name/type entry constant pool entry
 * 
 * @author $Author: jonmeyerny $
 * @version $Revision: 1.1 $
 */

package jasmin.utils.jas;

import java.io.DataOutputStream;
import java.io.IOException;


public class NameTypeCP extends CP implements RuntimeConstants
{
  AsciiCP name, sig;

  /**
   * Name/type entries are used to specify the type associated
   * with a symbol name.
   *
   * @param name Name of symbol
   * @param sig Signature of symbol
   */
  public NameTypeCP(String name, String sig)
  {
    uniq = ("NT : @#$%" + name + "SD#$"+ sig).intern();
    this.name = new AsciiCP(name);
    this.sig = new AsciiCP(sig);
  }

  void resolve(ClassEnv e)
  {
    e.addCPItem(name);
    e.addCPItem(sig);
  }

  public ResultCPIndex cpItems(ClassEnv e) {
    this.resolve(e);
    try {
        return ResultCPIndex.createResultCPIndex(e.getCPIndex(this.name), e.getCPIndex(this.sig));
    } catch (jasError ex) {
        throw new RuntimeException(ex);
    }
  }

    public static final class ResultCPIndex {
        public short first;
        public short second;

        private ResultCPIndex() {
        }

        public static ResultCPIndex createResultCPIndex(short first, short second ) {
            ResultCPIndex instance = new ResultCPIndex();
            instance.first = first;
            instance.second = second;
            return instance;
        }

        @Override
        public boolean equals(Object obj) {
            return obj == this || obj != null && obj.getClass() == this.getClass();
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public String toString() {
            return "ResultCPIndex[]";
        }

    }
    
  void write(ClassEnv e, DataOutputStream out)
    throws IOException, jasError
  {
    out.writeByte(CONSTANT_NAMEANDTYPE);
    out.writeShort(e.getCPIndex(name));
    out.writeShort(e.getCPIndex(sig));
  }
}
