/**
 * Some instructions are perniticky enough that its simpler
 * to write them separately instead of smushing them with
 * all the rest. the invokeinterface instruction is one of them.
 * @author $Author: jonmeyerny $
 * @version $Revision: 1.1 $
 */

package jasmin.utils.jas;


public class InvokeinterfaceInsn extends Insn implements RuntimeConstants
{
  public InvokeinterfaceInsn(CP cpe, int nargs)
  {
    opc = opc_invokeinterface;
    operand = new InvokeinterfaceOperand(cpe, nargs);
  }
}
